package com.jersey.dao.general;

import com.jersey.dao.common.CommonOperations;
import com.jersey.model.User;

public interface UserDao extends CommonOperations<User> {
	
	User findByEmailAddress(String emailAddress);

}
