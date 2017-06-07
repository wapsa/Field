package com.maven.project.jaxrs.command.resource.response;

public enum UserRegistrationResponse {
	/**
	 * These are the registration status that application need to throw to
	 * client.
	 */
	USER_NAME_ALREADY_TAKEN, USER_NAME_ALREADY_USED_ACCOUNT_ACTIVE, ACCOUNT_BANNED, USER_NAME_ALREADY_USED_ACCOUNT_INACTIVE, REGISTRATION_SUCCESSFULL;

}
