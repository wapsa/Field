package com.maven.project.core.service;

import com.maven.project.exceptions.user.UserRegistrationException;

public interface RegistrationService {

	// Methods--------------------------------------------------
	Long registerUser(String userName, String displayName, String passWord, String email_Address, String mobile_Number,
			String authProvider, String role, String activationCode) throws UserRegistrationException;
}
