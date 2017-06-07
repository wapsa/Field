package com.maven.project.core.service.impl;

// Imports--------------------------------------------------
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maven.project.core.dao.UserDao;
import com.maven.project.core.service.RegistrationService;
import com.maven.project.exceptions.jaxrs.user.UserRegistrationAccountBannedException;
import com.maven.project.exceptions.jaxrs.user.UserRegistrationDuplicateUserNameActiveAccountException;
import com.maven.project.exceptions.jaxrs.user.UserRegistrationDuplicateUserNameInactiveAccountException;
import com.maven.project.exceptions.jaxrs.user.UserRegistrationException;
import com.maven.project.hib.model.User;
import com.maven.project.base.constants.AppConstants;
import com.maven.project.base.security.domain.*;
import com.maven.project.hib.model.ActivationLink;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	// Variables, Objects, References--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	private UserDao userDao;
	private MessageSource messageSource;

	// Constructors--------------------------------------------------
	@Autowired
	public RegistrationServiceImpl(UserDao userDao, MessageSource messageSource) {
		this.userDao = userDao;
		this.messageSource = messageSource;
	}

	// Methods--------------------------------------------------
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long registerUser(String userName, String displayName, String passWord, String email_Address,
			String mobile_Number, String authProvider, String role, String activationCode)
					throws UserRegistrationException {

		List<Object[]> existingUsers = userDao.fetchUserNameIfExists(userName);

		if (existingUsers.isEmpty()) {
			// means there is no duplicate userName or emailAddress exists in
			// the db.

			User userToInsert = new User();

			userToInsert.setUsername(userName);
			userToInsert.setDisplayName(displayName);
			userToInsert.setPassword(passWord);
			userToInsert.setEmailAddress(email_Address);
			userToInsert.setMobileNumber(mobile_Number);
			userToInsert.setAuthProvider(authProvider);
			userToInsert.setRole(role);

			Date creationDate = new Date();

			if (authProvider.equals(AuthProvider.AUP_APPLICATION.name())) {

				userToInsert.setActivatedFlag(false);

				ActivationLink activationLink = new ActivationLink();
				activationLink.setEmail(email_Address);
				activationLink.setActivationCode(activationCode);
				activationLink.setActivationCodeSentTime(creationDate);

				long currentDateInMillis = creationDate.getTime();
				long expirationDateInMillis = Long
						.parseLong(messageSource.getMessage(AppConstants.ACTIVATION_CODE_EXPIRATION_IN_MINS, null,
								AppConstants.ACTIVATION_CODE_EXPIRATION_IN_MINS_DEFAULT, null))
						* (60000L);

				activationLink.setActivationCodeExpirationTime(new Date(currentDateInMillis + expirationDateInMillis));

				// adding bidirectional reference between user and activation
				// link.
				userToInsert.addBidirectionalRefForActivationLink(activationLink);

			} else if (authProvider.equals(AuthProvider.AUP_FACEBOOK.name())) {
				userToInsert.setActivatedFlag(true);
				userToInsert.setActivationTime(creationDate);
			}
			System.out.println(":::::::::::::::::::GOING TO SAVE NOW:::::::::::::::::::");
			return userDao.save(userToInsert);

		} else {
			// means there is duplicate Username existing in the database.
			Object[] existingUser = existingUsers.get(0);
			String existingUserName = (String) existingUser[0];
			boolean existingUserActivatedFlag = (boolean) existingUser[1];
			boolean existingUserBannedFlag = (boolean) existingUser[2];

			if (existingUserName != null && existingUserName.equals(userName)) {
				if (existingUserActivatedFlag == true && existingUserBannedFlag == false) {
					throw new UserRegistrationDuplicateUserNameActiveAccountException(
							"Account already active for that Username");
				} else if (existingUserActivatedFlag == false && existingUserBannedFlag == false) {
					throw new UserRegistrationDuplicateUserNameInactiveAccountException(
							"Username already used but account not activated.");
				} else if (existingUserBannedFlag == true) {
					throw new UserRegistrationAccountBannedException("Account banned for corresponding Username");
				}
			}

		}
		return null;
	}
}
