package com.maven.project.core.service.impl;

//Imports---------------------------------------------------------
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maven.project.core.dao.UserDao;
import com.maven.project.core.service.ActivationService;
import com.maven.project.exceptions.jaxrs.user.UserActivationAccountAlreadyActiveException;
import com.maven.project.exceptions.jaxrs.user.UserActivationAccountBannedException;
import com.maven.project.exceptions.jaxrs.user.UserActivationActivationCodeExpiredException;
import com.maven.project.exceptions.jaxrs.user.UserActivationException;
import com.maven.project.exceptions.jaxrs.user.UserActivationInvalidLinkException;
import com.maven.project.hib.model.User;

@Service
public class ActivationServiceImpl implements ActivationService {

	// Variables, Objects,
	// References--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	private UserDao userDao;

	// Constructors--------------------------------------------------
	@Autowired
	public ActivationServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	// Methods-----------------------------------------------------------------
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void activateUser(Long userId, String activationCode) throws UserActivationException {

		User user = userDao.findOne(userId);

		if (user.isBannedFlag()) {
			throw new UserActivationAccountBannedException("Account Banned");
		}

		if (user.isActivatedFlag()) {
			throw new UserActivationAccountAlreadyActiveException("Account Already Active");
		}

		List<Object[]> activationEntity = userDao.fetchActivationExpiryTime(userId, activationCode);

		if (!activationEntity.isEmpty()) {

			Object[] activationData = activationEntity.get(0);
			Date activationCodeExpireTime = (Date) activationData[1];
			Date currentTime = new Date();

			Long activationCodeExpireTimeMillis = activationCodeExpireTime.getTime();
			Long currentTimeMillis = currentTime.getTime();

			Long difference = currentTimeMillis - activationCodeExpireTimeMillis;

			if (difference < 10) {
				user.setActivatedFlag(true);
				Date activationTime = new Date();
				user.setActivationTime(activationTime);
				userDao.deleteActivationEntity(userId);
			} else {
				throw new UserActivationActivationCodeExpiredException("Activation Link Expired");
			}

		} else {
			throw new UserActivationInvalidLinkException("Activation Entity Non-existant");
		}

	}

}
