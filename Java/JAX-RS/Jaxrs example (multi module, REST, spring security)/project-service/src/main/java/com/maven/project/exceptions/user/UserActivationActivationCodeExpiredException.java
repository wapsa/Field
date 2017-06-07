package com.maven.project.exceptions.user;

public class UserActivationActivationCodeExpiredException extends UserActivationException {

	// Constants--------------------------------------------------------------
	private static final long serialVersionUID = -9059941884776155150L;

	// Constructors--------------------------------------------------
	public UserActivationActivationCodeExpiredException(String message) {
		super(message);
	}
}
