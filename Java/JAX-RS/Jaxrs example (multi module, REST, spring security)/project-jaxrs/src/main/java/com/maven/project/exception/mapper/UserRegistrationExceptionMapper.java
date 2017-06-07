package com.maven.project.exception.mapper;

// Imports--------------------------------------------------
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.maven.project.exceptions.user.UserRegistrationAccountBannedException;
import com.maven.project.exceptions.user.UserRegistrationDuplicateUserNameActiveAccountException;
import com.maven.project.exceptions.user.UserRegistrationDuplicateUserNameException;
import com.maven.project.exceptions.user.UserRegistrationDuplicateUserNameInactiveAccountException;
import com.maven.project.exceptions.user.UserRegistrationException;
import com.maven.project.jaxrs.command.resource.response.UserRegistrationResponse;

@Provider
public class UserRegistrationExceptionMapper implements ExceptionMapper<UserRegistrationException> {

	// Constructors--------------------------------------------------
	public UserRegistrationExceptionMapper() {
	}

	// Methods--------------------------------------------------
	public Response toResponse(UserRegistrationException ure) {

		// USER_NAME_ALREADY_TAKEN,EMAIL_ADDRESS_ALREADY_TAKEN may come in
		// production environment
		if (ure instanceof UserRegistrationDuplicateUserNameException) {
			return Response.status(Status.CONFLICT).entity(UserRegistrationResponse.USER_NAME_ALREADY_TAKEN)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (ure instanceof UserRegistrationDuplicateUserNameActiveAccountException) {
			return Response.status(Status.CONFLICT)
					.entity(UserRegistrationResponse.USER_NAME_ALREADY_USED_ACCOUNT_ACTIVE)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (ure instanceof UserRegistrationDuplicateUserNameInactiveAccountException) {
			return Response.status(Status.CONFLICT)
					.entity(UserRegistrationResponse.USER_NAME_ALREADY_USED_ACCOUNT_INACTIVE)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (ure instanceof UserRegistrationAccountBannedException) {
			return Response.status(Status.CONFLICT).entity(UserRegistrationResponse.ACCOUNT_BANNED)
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(ure.getMessage()).type(MediaType.APPLICATION_JSON)
					.build();
		}

	}

}
