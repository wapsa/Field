package com.maven.project.exceptions.user;

public class UserRegistrationDuplicateUserNameActiveAccountException extends UserRegistrationException {
	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	// Constructors--------------------------------------------------
	public UserRegistrationDuplicateUserNameActiveAccountException(String message) {
		super(message);
	}

}
