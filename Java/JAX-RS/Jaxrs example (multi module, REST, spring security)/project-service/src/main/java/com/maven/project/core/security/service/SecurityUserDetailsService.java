package com.maven.project.core.security.service;

// Imports--------------------------------------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maven.project.base.security.domain.SecurityUserDetails;
import com.maven.project.core.dao.UserDao;
import com.maven.project.hib.model.User;

public class SecurityUserDetailsService implements UserDetailsService {

	// Variables, Objects, References--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

	@Autowired
	private UserDao userDao;

	// Constructors--------------------------------------------------
	public SecurityUserDetailsService() {
	}

	// Methods--------------------------------------------------
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.debug("userDao:{}", userDao);
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		SecurityUserDetails principal = SecurityUserDetails.getBuilder().accountNotBanned(!user.isBannedFlag())
				.activated(user.isActivatedFlag()).authProvider(user.getAuthProvider()).mobile(user.getMobileNumber())
				.password(user.getPassword()).role(user.getRole()).userId(user.getUserId()).userName(user.getUsername())
				.displayName(user.getDisplayName()).build();

		return principal;
	}

}
