package com.maven.project.exceptions;

public abstract class UserException extends RuntimeException {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 3174614870077918049L;
	
	private static final String MESSAGE_FORMAT = "UserException: '%s'";

	// Constructors--------------------------------------------------
	public UserException() {}

	public UserException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));
		
	}


}
