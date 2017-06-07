package com.maven.project.auth.custom;

// Imports--------------------------------------------------
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.maven.project.auth.custom.dto.LoginSuccessResponse;
import com.maven.project.base.security.domain.SecurityUserDetails;

public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	// Variables--------------------------------------------------
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(JsonAuthenticationSuccessHandler.class);

	// Constructors--------------------------------------------------
	public JsonAuthenticationSuccessHandler() {
	}

	// Methods--------------------------------------------------
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException, AuthenticationServiceException {

		LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse();

		Object principal = authentication.getPrincipal();
		// get the details from authentication and put into loginResponse.

		if (principal instanceof SecurityUserDetails) {
			SecurityUserDetails securityUserDetails = (SecurityUserDetails) principal;
			loginSuccessResponse.setDisplayName(securityUserDetails.getDisplayName());
			loginSuccessResponse.setAppUserId(securityUserDetails.getUserId());

			try {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);
				loginSuccessResponse.writeLoginResponse(response, loginSuccessResponse);
			} catch (AuthenticationServiceException e) {
				request.getSession().invalidate();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}
}
