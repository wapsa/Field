package com.maven.project.jaxrs.command.resource.impl;

// Imports--------------------------------------------------
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;

import com.maven.project.base.constants.AppConstants;
import com.maven.project.base.security.domain.AuthProvider;
import com.maven.project.base.security.domain.Role;
import com.maven.project.core.service.RegistrationService;
import com.maven.project.exceptions.jaxrs.user.UserRegistrationException;
import com.maven.project.jaxrs.command.resource.JaxrsCommand;
import com.maven.project.jaxrs.command.resource.request.UserGeneralRegistrationRequest;
import com.maven.project.jaxrs.command.resource.response.UserRegistrationResponse;
import com.maven.project.message.producer.UserActivationProducer;

public class JaxrsCommandImpl implements JaxrsCommand {

	// Variables, Objects,
	// References--------------------------------------------------
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private UserActivationProducer userActivationProducer;

	Logger logger = LoggerFactory.getLogger(JaxrsCommandImpl.class);

	// Constructors--------------------------------------------------
	public JaxrsCommandImpl() {
	}

	// Methods--------------------------------------------------
	private String activationMailLinkBuilder(HttpServletRequest request, String activationCode, Long userId) {

		// Preparing link for activation message to send to message broker
		StringBuilder linkBuilder = new StringBuilder();
		linkBuilder.append(request.getScheme());
		linkBuilder.append(AppConstants.SCHEME_SEPRATOR);
		// linkBuilder.append(request.getServerName());
		linkBuilder.append("192.168.1.105");
		linkBuilder.append(AppConstants.COLON);
		linkBuilder.append(new Integer(request.getServerPort()).toString());
		linkBuilder.append(request.getContextPath());
		linkBuilder.append("/services/restquery/public/user/activate/" + userId + "/");
		linkBuilder.append(activationCode);

		return linkBuilder.toString();
	}

	public Response registerUser(UserGeneralRegistrationRequest userGeneralRegistrationRequest,
			HttpServletRequest request) throws UserRegistrationException {

		logger.debug("inside registerUser for general user: incoming request is:{}", userGeneralRegistrationRequest);

		String activationCode = Integer.toString(100000 + new SecureRandom().nextInt(900000));

		Long userId = registrationService.registerUser(userGeneralRegistrationRequest.getUserName(),
				userGeneralRegistrationRequest.getDisplayName(), userGeneralRegistrationRequest.getPassWord(),
				userGeneralRegistrationRequest.getEmail_Address(), userGeneralRegistrationRequest.getMobile_Number(),
				AuthProvider.AUP_APPLICATION.name(), Role.ROLE_NORMAL_USER.name(), activationCode);

		String activationLink = activationMailLinkBuilder(request, activationCode, userId);
		try {
			userActivationProducer.processForActivationMail(userId, userGeneralRegistrationRequest.getEmail_Address(),
					activationLink);
		} catch (JmsException e) {
			logger.error("userActivationProducer fails to send message:", e);
			e.printStackTrace();
		}
		return Response.status(Status.CREATED).entity(UserRegistrationResponse.REGISTRATION_SUCCESSFULL).build();
	}
}
