package com.maven.project.exception.mapper;

// Imports--------------------------------------------------
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.maven.project.exceptions.service.user.UserBannedException;
import com.maven.project.exceptions.service.user.UserException;
import com.maven.project.exceptions.service.user.UserInactiveException;
import com.maven.project.exceptions.service.user.UserNotFoundException;
import com.maven.project.jaxrs.command.resource.response.UserExceptionStatus;

@Provider
public class UserExceptionMapper implements ExceptionMapper<UserException> {

	// Constructors--------------------------------------------------
	public UserExceptionMapper() {
	}

	// Methods--------------------------------------------------
	public Response toResponse(UserException ue) {

		if (ue instanceof UserBannedException) {
			return Response.status(Status.FORBIDDEN).entity(UserExceptionStatus.USER_BANNED)
					.type(MediaType.APPLICATION_JSON).build();
		}
		if (ue instanceof UserInactiveException) {
			return Response.status(Status.FORBIDDEN).entity(UserExceptionStatus.USER_INACTIVE)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (ue instanceof UserNotFoundException) {
			return Response.status(Status.BAD_REQUEST).entity(UserExceptionStatus.USER_NOT_FOUND)
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(ue.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}
