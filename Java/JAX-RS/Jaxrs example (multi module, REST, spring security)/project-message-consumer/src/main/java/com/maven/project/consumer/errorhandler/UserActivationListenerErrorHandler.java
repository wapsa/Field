package com.maven.project.consumer.errorhandler;

// Imports--------------------------------------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class UserActivationListenerErrorHandler implements ErrorHandler {

	// Constructors--------------------------------------------------
	public UserActivationListenerErrorHandler() {
	}

	// Variables--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(UserActivationListenerErrorHandler.class);

	// Methods--------------------------------------------------
	public void handleError(Throwable t) {

		logger.error("error in UserActivationListenerErrorHandler:", t);

	}

}
