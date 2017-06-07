package com.maven.project.exceptions;

public class UserNotFoundException extends UserException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -726981697115983105L;

	// Constructors--------------------------------------------------
	public UserNotFoundException(String message) {
		super(message);
	}

}
