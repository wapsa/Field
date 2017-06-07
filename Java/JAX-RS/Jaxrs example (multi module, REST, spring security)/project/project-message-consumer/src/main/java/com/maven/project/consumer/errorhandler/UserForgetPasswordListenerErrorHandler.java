package com.maven.project.consumer.errorhandler;

// Imports--------------------------------------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class UserForgetPasswordListenerErrorHandler implements ErrorHandler {

	// Constructors--------------------------------------------------
	public UserForgetPasswordListenerErrorHandler() {
	}

	// Variables--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(UserForgetPasswordListenerErrorHandler.class);

	// Methods--------------------------------------------------
	public void handleError(Throwable t) {

		logger.error("error in UserForgetPasswordListenerErrorHandler:", t);

	}

}
