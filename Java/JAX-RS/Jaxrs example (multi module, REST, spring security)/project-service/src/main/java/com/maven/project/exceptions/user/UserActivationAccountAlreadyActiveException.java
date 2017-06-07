package com.maven.project.exceptions.user;

public class UserActivationAccountAlreadyActiveException extends UserActivationException {

	// Constants----------------------------------------------------------
	private static final long serialVersionUID = -2837810741158901495L;

	// Constructors--------------------------------------------------
	public UserActivationAccountAlreadyActiveException(String message) {
		super(message);
	}
}
