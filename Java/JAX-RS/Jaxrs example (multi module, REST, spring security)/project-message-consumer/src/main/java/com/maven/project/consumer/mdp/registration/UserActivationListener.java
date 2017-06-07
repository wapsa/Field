package com.maven.project.consumer.mdp.registration;

// Imports--------------------------------------------------
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maven.project.base.constants.AppConstants;
import com.maven.project.consumer.service.mail.MailService;
import com.maven.project.core.dao.UserDao;
import com.maven.project.hib.model.User;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserActivationListener implements MessageListener {

	// Constructors--------------------------------------------------
	public UserActivationListener() {
	}

	// Variables, Objects, References--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(UserActivationListener.class);

	@Autowired
	private MailService mailService;
	@Autowired
	private UserDao userDao;

	// Methods--------------------------------------------------
	public void onMessage(Message msg) {

		try {

			if (msg instanceof MapMessage) {

				MapMessage activationMsg = (MapMessage) msg;

				long userId = activationMsg.getLong(AppConstants.KEY_USER_ID);
				String emailAddress = activationMsg.getStringProperty(AppConstants.KEY_EMAIL_ADDRESS);
				String[] recipients = { emailAddress };
				String activationLink = activationMsg.getStringProperty(AppConstants.KEY_ACTIVATION_LINK);

				System.out.println("MESSAGE ENTITIES: " + userId + "|" + emailAddress + "|" + activationLink);
				// getting a persistent context for User to be tracked by
				// hibernate
				User user = userDao.findOne(userId);
				if (user != null) {
					if (!user.isActivatedFlag()) {
						try {
							mailService.sendFromGMail(recipients, activationLink);
						} catch (MailException me) {
							logger.error("MailException:", me);
							throw me;
						}
					} else {
						logger.info("User is already activated and userId:{}", userId);
					}
				} else {
					logger.error("User is not found in db against userId:{}", userId);
				}

			}
		} catch (JMSException ex) {
			logger.error("JMSException:", ex);
			throw new RuntimeException(ex);
		}
	}

}
