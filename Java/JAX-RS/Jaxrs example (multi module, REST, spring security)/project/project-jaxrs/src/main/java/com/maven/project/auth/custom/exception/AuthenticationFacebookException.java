package com.maven.project.auth.custom.exception;

// Imports--------------------------------------------------
import org.springframework.security.core.AuthenticationException;

public class AuthenticationFacebookException extends AuthenticationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 5289808352015408823L;

	private static final String MESSAGE_FORMAT = "AuthenticationFacebookException: '%s'";

	// Constructors--------------------------------------------------
	public AuthenticationFacebookException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));

	}
}
