package com.maven.project.jaxrs.query.resource.impl;

// Imports--------------------------------------------------------
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.maven.project.core.service.ActivationService;
import com.maven.project.exceptions.user.UserActivationException;
import com.maven.project.jaxrs.query.resource.JaxrsQuery;
import com.maven.project.jaxrs.query.resource.response.UserAccountActivationResponse;

public class JaxrsQueryImpl implements JaxrsQuery {

	// Variables, Objects,
	// References-------------------------------------------------------------------
	@Autowired
	private ActivationService activationService;

	Logger logger = LoggerFactory.getLogger(JaxrsQueryImpl.class);

	// Constructors---------------------------------------------------------------
	public JaxrsQueryImpl() {
	}

	// Methods-------------------------------------------------------------------------------
	@Override
	public Response activateUser(Long userId, String activationCode) throws UserActivationException {

		activationService.activateUser(userId, activationCode);

		return Response.status(Status.OK).entity(UserAccountActivationResponse.ACTIVATION_SUCCESSFUL)
				.type(MediaType.APPLICATION_JSON).build();
	}

}
