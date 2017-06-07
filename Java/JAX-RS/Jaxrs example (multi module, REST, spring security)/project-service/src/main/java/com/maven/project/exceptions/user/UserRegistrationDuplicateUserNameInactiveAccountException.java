package com.maven.project.exceptions.user;

public class UserRegistrationDuplicateUserNameInactiveAccountException extends UserRegistrationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	// Constructors--------------------------------------------------
	public UserRegistrationDuplicateUserNameInactiveAccountException(String message) {
		super(message);
	}

}
