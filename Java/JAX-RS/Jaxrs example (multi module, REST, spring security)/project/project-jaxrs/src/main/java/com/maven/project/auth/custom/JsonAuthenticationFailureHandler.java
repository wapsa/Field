package com.maven.project.auth.custom;

// Imports--------------------------------------------------
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.maven.project.auth.custom.dto.DomainSpecificStatus;
import com.maven.project.auth.custom.dto.LoginFailureResponse;

public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

	// Constructors--------------------------------------------------
	public JsonAuthenticationFailureHandler() {
		// org.springframework.security.authentication.dao.DaoAuthenticationProvider
		// v;
	}

	// Methods--------------------------------------------------
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setContentType("application/json");
		// logic to get the domain specific status from exception if exist
		LoginFailureResponse loginFailureResponse = new LoginFailureResponse();
		loginFailureResponse.setErrorMessage(exception.getMessage());

		if (exception instanceof LockedException) {
			loginFailureResponse.setDomainSpecificStatus(DomainSpecificStatus.ACCOUNT_BANNED);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else if (exception instanceof DisabledException) {
			loginFailureResponse.setDomainSpecificStatus(DomainSpecificStatus.ACCOUNT_INACTIVE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else if (exception instanceof UsernameNotFoundException) {
			loginFailureResponse.setDomainSpecificStatus(DomainSpecificStatus.USERNAME_NOT_FOUND);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else if (exception instanceof BadCredentialsException) {
			loginFailureResponse.setDomainSpecificStatus(DomainSpecificStatus.PASSWORD_NOT_MATCH);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		loginFailureResponse.writeLoginResponse(response, loginFailureResponse);
		// not used because sendError displays server made html page.
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
		// "Authentication Failed: " + exception.getMessage());
	}

}
