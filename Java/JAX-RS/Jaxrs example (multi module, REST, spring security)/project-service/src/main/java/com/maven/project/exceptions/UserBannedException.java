package com.maven.project.exceptions;

public class UserBannedException extends UserException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -8364531828740554841L;

	// Constructors--------------------------------------------------
	public UserBannedException() {
	}

	public UserBannedException(String msg) {
		super(msg);
	}

}
