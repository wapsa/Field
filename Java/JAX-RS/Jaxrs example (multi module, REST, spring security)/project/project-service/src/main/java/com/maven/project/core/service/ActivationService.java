package com.maven.project.core.service;

// Imports------------------------------------------------------------------
import com.maven.project.exceptions.jaxrs.user.UserActivationException;

public interface ActivationService {

	// Methods-------------------------------------------------------------
	void activateUser(Long userId, String activationCode) throws UserActivationException;

}
