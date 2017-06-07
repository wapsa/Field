package com.maven.project.exceptions;

public class UserInactiveException extends UserException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -3542035055416419915L;

	// Constructors--------------------------------------------------
	public UserInactiveException() {
	}

	public UserInactiveException(String msg) {
		super(msg);
	}

}
