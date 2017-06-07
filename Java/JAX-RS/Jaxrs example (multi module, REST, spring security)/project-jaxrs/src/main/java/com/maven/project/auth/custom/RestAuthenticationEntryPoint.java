package com.maven.project.auth.custom;

// Imports--------------------------------------------------
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Represents custom entry point to override the default behavior of redirecting
 * to the login page if any secured URL is accessed without authentication. Will
 * now not be redirected to login page as what ideally happens in web rather
 * will be given with error 401.
 *
 * This does not handle the authentication failure that happens during login
 * activity itself(i.e using /login url).
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// Constructors--------------------------------------------------
	public RestAuthenticationEntryPoint() {
	}

	// Methods--------------------------------------------------
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

	}

}
