package com.maven.project.exceptions.user;

public class UserRegistrationDuplicateUserNameException extends UserRegistrationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	// Constructors--------------------------------------------------
	public UserRegistrationDuplicateUserNameException(String message) {
		super(message);
	}

}
