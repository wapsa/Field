package com.maven.project.exceptions.user;

public class UserActivationAccountBannedException extends UserActivationException {

	// Constants---------------------------------------------------
	private static final long serialVersionUID = 3443909554123246479L;

	// Constructors--------------------------------------------------
	public UserActivationAccountBannedException(String message) {
			super(message);
		}

}
