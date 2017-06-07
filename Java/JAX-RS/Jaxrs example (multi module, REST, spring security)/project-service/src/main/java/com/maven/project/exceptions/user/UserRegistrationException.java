package com.maven.project.exceptions.user;

import com.maven.project.exceptions.UserException;

public class UserRegistrationException extends UserException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "UserRegistrationException: '%s'";

	// Constructors--------------------------------------------------
	public UserRegistrationException(String message) {
		super(String.format(MESSAGE_FORMAT, message));
	}

}
