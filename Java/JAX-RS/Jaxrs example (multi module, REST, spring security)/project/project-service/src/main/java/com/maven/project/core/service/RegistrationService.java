package com.maven.project.core.service;

// Imports--------------------------------------------------
import com.maven.project.exceptions.jaxrs.user.UserRegistrationException;

public interface RegistrationService {

	// Methods--------------------------------------------------
	Long registerUser(String userName, String displayName, String passWord, String email_Address, String mobile_Number,
			String authProvider, String role, String activationCode) throws UserRegistrationException;
}
