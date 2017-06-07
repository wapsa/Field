package com.maven.project.base.security.facade;

// Imports--------------------------------------------------
import org.springframework.security.core.Authentication;

import com.maven.project.base.security.domain.SecurityUserDetails;

public interface AuthenticationFacade {

	// Methods--------------------------------------------------
	Authentication getAuthentication();

	SecurityUserDetails getSecurityUserDetails();

	void clearCurrentAuthenticationCotext();
}
