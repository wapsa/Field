package com.maven.project.auth.custom.exception;

// Imports--------------------------------------------------
import org.springframework.security.core.AuthenticationException;

public class FacebookAccessException extends AuthenticationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 2899938670308102773L;

	private static final String MESSAGE_FORMAT = "FacebookAccessException: '%s'";

	// Constructors--------------------------------------------------
	public FacebookAccessException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));

	}

}
