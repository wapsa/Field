package com.maven.project.exceptions.user;

public class UserRegistrationAccountBannedException extends UserRegistrationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	// Constructors--------------------------------------------------
	public UserRegistrationAccountBannedException(String message) {
		super(message);
	}

}