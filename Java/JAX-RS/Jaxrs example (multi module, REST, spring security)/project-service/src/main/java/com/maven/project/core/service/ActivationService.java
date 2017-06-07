package com.maven.project.core.service;

import com.maven.project.exceptions.user.UserActivationException;

public interface ActivationService {

	// Methods-------------------------------------------------------------
	void activateUser(Long userId, String activationCode) throws UserActivationException;

}
