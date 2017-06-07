package com.maven.project.auth.custom;

// Imports--------------------------------------------------
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.maven.project.base.security.domain.SecurityUserDetails;

public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

	// Variables---------------------------------------------
	Logger logger = LoggerFactory.getLogger(RestLogoutSuccessHandler.class);

	public RestLogoutSuccessHandler() {
	}

	// Methods--------------------------------------------------
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		SecurityUserDetails userDetails = null;
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof SecurityUserDetails) {
				userDetails = (SecurityUserDetails) principal;
			}

		}
		String refererUrl = request.getHeader("Referer");
		logger.warn(":::LOUGOUT HAPPENED FOR:::{}:::TIMESTAMP:::{}:::Referer:::{}", userDetails,
				new Timestamp(System.currentTimeMillis()), refererUrl);

		response.setStatus(HttpServletResponse.SC_OK);
	}

}
