package com.maven.project.exceptions.user;

public class UserRegistrationAccountAlreadyActiveException extends UserRegistrationException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1836552303676147735L;

	// Constructors--------------------------------------------------
	public UserRegistrationAccountAlreadyActiveException(String message) {
		super(message);
	}

}
