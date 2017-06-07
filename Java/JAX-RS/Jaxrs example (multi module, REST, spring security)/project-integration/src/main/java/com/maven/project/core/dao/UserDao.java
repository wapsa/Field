package com.maven.project.core.dao;

// Imports--------------------------------------------------
import java.util.List;

import com.maven.project.core.dao.common.CommonOperations;
import com.maven.project.hib.model.User;

public interface UserDao extends CommonOperations<User> {

	// Methods--------------------------------------------------
	User findByUserName(String userName);

	List<Object[]> fetchUserNameIfExists(String userName);
	
	List<Object[]> fetchActivationExpiryTime(Long userId, String activationCode);
	
	void deleteActivationEntity(Long userId);

}
