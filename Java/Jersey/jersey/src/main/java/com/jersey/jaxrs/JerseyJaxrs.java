package com.jersey.jaxrs;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jersey.dto.request.UserProfileIn;

public interface JerseyJaxrs {

	@Path("1.0/save-user-profile")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response saveUserProfile(@Valid UserProfileIn req);

}
