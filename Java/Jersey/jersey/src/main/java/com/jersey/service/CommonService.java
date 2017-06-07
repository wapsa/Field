package com.jersey.service;

import com.jersey.dto.request.UserProfileIn;
import com.jersey.dto.response.GenericResponse;

public interface CommonService {

	GenericResponse saveUserProfile(UserProfileIn req);

}
