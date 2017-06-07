package com.jersey.jaxrs;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jersey.dto.request.UserProfileIn;
import com.jersey.service.CommonService;

@Component
@Path("/hl")
public class JerseyJaxrsImpl implements JerseyJaxrs {

	Logger logger = LoggerFactory.getLogger(JerseyJaxrsImpl.class);

	@Autowired
	private CommonService commonService;

	@Override
	public Response saveUserProfile(UserProfileIn req) {
		return Response.status(Status.OK).entity(commonService.saveUserProfile(req)).type(MediaType.APPLICATION_JSON)
				.build();
	}

}
