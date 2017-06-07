package com.maven.project.base.security.facade;

// Imports--------------------------------------------------
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.maven.project.base.exceptions.AuthenticationFacadeException;
import com.maven.project.base.security.domain.SecurityUserDetails;

/**
 * This is a Facade for getting authentication or principal from the spring
 * SecurityContextHolder.
 * 
 * If the methods of this facade throws AuthenticationFacadeException(which
 * extends Authentication Exception), so in this situation request processing
 * goes to springs's ExceptionTranslationFilter which in turn calls
 * AuthenticationEntry point(our RestAuthenticationEntryPoint).
 * 
 * It means the session is automatically expired by spring security.
 */

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	// Constructors--------------------------------------------------
	public AuthenticationFacadeImpl() {
	}

	// Getters & Setters--------------------------------------------------
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	// Methods--------------------------------------------------
	public SecurityUserDetails getSecurityUserDetails() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			throw new AuthenticationFacadeException("Authentication object returned by SecurityContextHolder is null");
		} else {
			Object principal = authentication.getPrincipal();
			if (principal instanceof SecurityUserDetails) {
				return (SecurityUserDetails) principal;
			} else {
				throw new AuthenticationFacadeException(
						"principal in SecurityContextHolder is not SecurityUserDetails.");
			}
		}

	}

	public void clearCurrentAuthenticationCotext() {

		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(null);

		SecurityContextHolder.clearContext();

	}
}
