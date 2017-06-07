package com.maven.project.exceptions.user;

public class UserActivationInvalidLinkException extends UserActivationException {

	// Constants----------------------------------------------------
	private static final long serialVersionUID = -4601623043184697174L;

	// Constructors---------------------------------------------------
	public UserActivationInvalidLinkException(String message) {
		super(message);
	}

}
