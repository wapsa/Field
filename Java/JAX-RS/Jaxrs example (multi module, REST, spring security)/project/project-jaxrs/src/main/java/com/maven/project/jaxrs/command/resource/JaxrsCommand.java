package com.maven.project.jaxrs.command.resource;

// Imports--------------------------------------------------
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.maven.project.exceptions.jaxrs.user.UserRegistrationException;
import com.maven.project.jaxrs.command.resource.request.UserGeneralRegistrationRequest;

@Path("restcommand/")
public interface JaxrsCommand {

	// Methods--------------------------------------------------
	
	@Path("user/general/register")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	Response registerUser(@Valid UserGeneralRegistrationRequest userGeneralRegistrationRequest,
			@Context HttpServletRequest request) throws UserRegistrationException;
	
}
