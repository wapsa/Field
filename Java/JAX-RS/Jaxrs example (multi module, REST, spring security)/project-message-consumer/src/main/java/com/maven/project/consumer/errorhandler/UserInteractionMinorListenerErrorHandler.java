package com.maven.project.consumer.errorhandler;

// Imports--------------------------------------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class UserInteractionMinorListenerErrorHandler implements ErrorHandler {

	// Variables--------------------------------------------------
	private Logger logger = LoggerFactory.getLogger(UserInteractionMinorListenerErrorHandler.class);

	// Constructors--------------------------------------------------
	public UserInteractionMinorListenerErrorHandler() {
	}

	// Methods--------------------------------------------------
	public void handleError(Throwable t) {
		logger.error("error in UserInteractionMinorListenerErrorHandler:", t);
	}

}
