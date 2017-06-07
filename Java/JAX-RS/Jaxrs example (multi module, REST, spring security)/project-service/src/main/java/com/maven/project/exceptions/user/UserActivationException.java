package com.maven.project.exceptions.user;

public class UserActivationException extends RuntimeException {

	// Constants--------------------------------------------------------
	private static final long serialVersionUID = -4761639194399858588L;

	private static final String MESSAGE_FORMAT = "UserActivationException: '%s'";

	// Constructors--------------------------------------------------
	public UserActivationException() {
	}

	public UserActivationException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));

	}

}
