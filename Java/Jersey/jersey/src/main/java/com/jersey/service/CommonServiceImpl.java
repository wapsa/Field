package com.jersey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jersey.dao.general.UserDao;
import com.jersey.dto.request.UserProfileIn;
import com.jersey.dto.response.GenericResponse;
import com.jersey.model.User;

@Service
public class CommonServiceImpl implements CommonService {

	Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	private UserDao userDao;

	@Autowired
	public CommonServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public GenericResponse saveUserProfile(UserProfileIn req) {
		logger.info("Inside #saveUserProfile(UserProfileIn) for emailAddress: {}, fullName: {}", req.getEmailAddress(),
				req.getFullName());
		User user = userDao.findByEmailAddress(req.getEmailAddress());
		if (user == null) {
			logger.info("User not found, creating a new user.");
			user = new User();
			user.setEmailAddress(req.getEmailAddress());
			user.setFullName(req.getFullName());
			userDao.save(user);
			return new GenericResponse.Builder().success().message("Successfully created a new user profile.")
					.description("The user was not found in the system, creating a new profile.").build();
		} else {
			logger.info("User found, only updating profile.");
			user.setFullName(req.getFullName());
			userDao.update(user);
			return new GenericResponse.Builder().success().message("Successfully updated user profile.")
					.description("The user was already found in the system. Only updating the profile details.")
					.build();
		}
	}
}
