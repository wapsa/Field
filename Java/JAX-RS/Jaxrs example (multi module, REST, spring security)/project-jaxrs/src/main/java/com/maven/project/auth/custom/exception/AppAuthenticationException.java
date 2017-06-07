package com.maven.project.auth.custom.exception;

// Imports--------------------------------------------------
import org.springframework.security.core.AuthenticationException;

public class AppAuthenticationException extends AuthenticationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "AppAuthenticationException: '%s'";

	// Constructors--------------------------------------------------
	public AppAuthenticationException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));
	}

}
