package com.maven.project.auth.custom.exception;

// Imports-------------------------------------------------------
import org.springframework.security.core.AuthenticationException;

public class AuthenticationAuthProviderException extends AuthenticationException {

	// Constants
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "AuthenticationAuthProviderException: '%s'";

	// Constructors--------------------------------------------------
	public AuthenticationAuthProviderException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));

	}

}
