package com.maven.project.base.exceptions;

// Imports--------------------------------------------------
import org.springframework.security.core.AuthenticationException;

public class AuthenticationFacadeException extends AuthenticationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -2984806812560730741L;

	// Variables--------------------------------------------------
	private static final String MESSAGE_FORMAT = "AuthenticationFacadeException: '%s'";

	// Constructors--------------------------------------------------
	public AuthenticationFacadeException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));
	}

}
