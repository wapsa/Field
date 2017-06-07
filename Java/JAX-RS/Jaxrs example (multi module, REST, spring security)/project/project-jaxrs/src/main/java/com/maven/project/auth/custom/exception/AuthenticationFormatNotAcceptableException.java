package com.maven.project.auth.custom.exception;

// Imports--------------------------------------------------
import org.springframework.security.core.AuthenticationException;

/**
 * Represents format not acceptable Type appears for JSON or other data format
 * parsing during authentication process.
 */
public class AuthenticationFormatNotAcceptableException extends AuthenticationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "AuthenticationFormatNotAcceptableException: '%s'";

	// Constructors--------------------------------------------------
	public AuthenticationFormatNotAcceptableException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));
	}

}
