package com.maven.project.consumer.errorhandler;

// Imports--------------------------------------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class UserInteractionMajorListenerErrorHandler implements ErrorHandler {

	// Variables--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(UserInteractionMajorListenerErrorHandler.class);

	// Constructors--------------------------------------------------
	public UserInteractionMajorListenerErrorHandler() {
	}

	// Methods--------------------------------------------------
	public void handleError(Throwable t) {

		logger.error("error in UserInteractionMajorListenerErrorHandler:", t);

	}

}
