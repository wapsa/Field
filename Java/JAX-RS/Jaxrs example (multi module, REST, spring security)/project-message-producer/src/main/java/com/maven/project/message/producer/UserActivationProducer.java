package com.maven.project.message.producer;

// Imports--------------------------------------------------
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.maven.project.base.constants.AppConstants;

@Component
public class UserActivationProducer {

	// Constructors--------------------------------------------------
	public UserActivationProducer() {
	}

	// Variables, Objects, References--------------------------------------------------
	@Autowired
	private JmsTemplate queueUserRegistrationTemplate;

	// Methods--------------------------------------------------
	public void processForActivationMail(final Long userId, final String emailAddress, final String activationLink)
			throws JmsException {

		queueUserRegistrationTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();

				mapMessage.setStringProperty(AppConstants.KEY_EMAIL_ADDRESS, emailAddress);
				mapMessage.setStringProperty(AppConstants.KEY_REG_MSG_TYPE, AppConstants.VALUE_REG_MSG_ACTIVATION);
				mapMessage.setLong(AppConstants.KEY_USER_ID, userId);
				mapMessage.setStringProperty(AppConstants.KEY_ACTIVATION_LINK, activationLink);

				System.out.println("PRODUCING MESSAGE: " + userId + "|" + emailAddress + "|" + activationLink);
				return mapMessage;
			}
		});

	}

}
