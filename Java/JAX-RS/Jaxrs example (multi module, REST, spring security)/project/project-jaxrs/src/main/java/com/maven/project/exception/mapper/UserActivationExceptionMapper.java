package com.maven.project.exception.mapper;

// Imports------------------------------------------------------
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.maven.project.exceptions.jaxrs.user.UserActivationAccountAlreadyActiveException;
import com.maven.project.exceptions.jaxrs.user.UserActivationAccountBannedException;
import com.maven.project.exceptions.jaxrs.user.UserActivationActivationCodeExpiredException;
import com.maven.project.exceptions.jaxrs.user.UserActivationException;
import com.maven.project.exceptions.jaxrs.user.UserActivationInvalidLinkException;
import com.maven.project.jaxrs.query.resource.response.UserAccountActivationResponse;

@Provider
public class UserActivationExceptionMapper implements ExceptionMapper<UserActivationException> {

	// Constructors-------------------------------------------------------------
	public UserActivationExceptionMapper() {

	}

	// Methods-----------------------------------------------------------------------
	public Response toResponse(UserActivationException uae) {

		if (uae instanceof UserActivationAccountAlreadyActiveException) {
			return Response.status(Status.CONFLICT).entity(UserAccountActivationResponse.ACCOUNT_ALREADY_ACTIVE)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (uae instanceof UserActivationAccountBannedException) {
			return Response.status(Status.CONFLICT).entity(UserAccountActivationResponse.ACCOUNT_BANNED)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (uae instanceof UserActivationActivationCodeExpiredException) {
			return Response.status(Status.CONFLICT).entity(UserAccountActivationResponse.ACTIVATION_LINK_EXPIRED)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (uae instanceof UserActivationInvalidLinkException) {
			return Response.status(Status.CONFLICT).entity(UserAccountActivationResponse.INVALID_LINK)
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(uae.getMessage()).type(MediaType.APPLICATION_JSON)
					.build();
		}
	}

}
