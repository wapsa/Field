package com.maven.project.jaxrs.query.resource;


// Imports----------------------------------------------------
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.maven.project.exceptions.user.UserActivationException;

@Path("restquery/public/")
public interface JaxrsQuery {

	// Methods-------------------------------------------------

	@Path("user/activate/{userId}/{activationCode}")
	@GET
	@Produces(value = MediaType.TEXT_HTML)
	Response activateUser(@PathParam("userId") Long userId, @PathParam("activationCode") String activationCode)
			throws UserActivationException;

}
