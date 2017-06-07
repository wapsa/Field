package com.maven.project.auth.custom;

// Imports--------------------------------------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maven.project.auth.custom.dto.FacebookAuthUser;
import com.maven.project.auth.custom.exception.FacebookAccessException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

public class FacebookAuthUtil {

	// Variables--------------------------------------------------
	private static Logger logger = LoggerFactory.getLogger(FacebookAuthUtil.class);

	// Methods--------------------------------------------------
	public static FacebookAuthUser getFacebookAuthUser(String fbAccessToken, String fbAppSecret)
			throws FacebookAccessException {

		try {
			FacebookClient facebookClient = new DefaultFacebookClient(fbAccessToken, fbAppSecret, Version.VERSION_2_4);
			User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id,name,email"));
			return new FacebookAuthUser(user.getId(), user.getName(), user.getEmail());
		} catch (FacebookException fe) {
			logger.error("FacebookException occured:", fe);
			throw new FacebookAccessException(fe.getMessage());
		}

	}
}
