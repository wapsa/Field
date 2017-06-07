package com.maven.project.auth.custom;

// Imports--------------------------------------------------
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maven.project.auth.custom.dto.FacebookAuthUser;
import com.maven.project.auth.custom.dto.LoginRequest;
import com.maven.project.auth.custom.exception.AppAuthenticationException;
import com.maven.project.auth.custom.exception.AuthenticationAuthProviderException;
import com.maven.project.auth.custom.exception.AuthenticationFacebookException;
import com.maven.project.auth.custom.exception.AuthenticationFormatNotAcceptableException;
import com.maven.project.auth.custom.exception.FacebookAccessException;
import com.maven.project.base.constants.AppConstants;
import com.maven.project.base.security.domain.AuthProvider;
import com.maven.project.base.utility.StringUtils;

/**
 * This class will help picking the custom authentication data from the
 * HttpServletRequest. Here we have two choices to implement. If we want to go
 * with post parameters, then we need to simply override
 * {@code protected String obtainUsername(HttpServletRequest request);} and
 * {@code protected String obtainPassword(HttpServletRequest request);} methods.
 * Here we can simply extract some another authentication related parameters
 * from HttpServletRequest.
 * 
 * If we want to use Json Payload to be parsed we need to override
 * {@code  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response);}
 */
public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// Constructors--------------------------------------------------
	public JsonUsernamePasswordAuthenticationFilter() {
	}

	// Variables, Objects, References --------------------------------------------------
	@Autowired
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(JsonUsernamePasswordAuthenticationFilter.class);

	// Methods--------------------------------------------------
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		LoginRequest loginRequest = this.getLoginRequest(request);

		// getting authProvider from the login request
		AuthProvider authProvider = loginRequest.getAuthProvider();

		if (authProvider == null) {
			throw new AuthenticationAuthProviderException(AppConstants.BAD_CREDENTIALS);
		}

		UsernamePasswordAuthenticationToken authRequest = null;

		if (authProvider == AuthProvider.AUP_FACEBOOK) {

			String accessToken = loginRequest.getAccessToken();
			if (StringUtils.isBlank(accessToken)) {
				throw new AuthenticationFacebookException(
						AppConstants.BAD_CREDENTIALS + AppConstants.SINGLE_SPACE + AppConstants.ACCESS_TOKEN_EMPTY_MSG);
			}

			FacebookAuthUser facebookAuthUser = null;
			try {
				facebookAuthUser = FacebookAuthUtil.getFacebookAuthUser(accessToken,
						messageSource.getMessage(AppConstants.FACEBOOK_APP_SECRET, null, null));
			} catch (FacebookAccessException fe) {
				logger.error("FacebookAccessException occurred:", fe);
				throw new AuthenticationFacebookException(
						AppConstants.BAD_CREDENTIALS + AppConstants.SINGLE_SPACE + fe.getMessage());
			}

			String facebookUserId = facebookAuthUser.getId();
			if (StringUtils.isBlank(facebookUserId)) {
				throw new AuthenticationFacebookException(AppConstants.BAD_CREDENTIALS + AppConstants.SINGLE_SPACE
						+ AppConstants.FACEBOOK_USER_ID_EMPTY_MSG);
			}

			// placing facebookUserId for both userName and password.
			authRequest = new UsernamePasswordAuthenticationToken(facebookUserId, facebookUserId);

		} else if (authProvider == AuthProvider.AUP_APPLICATION) {
			// username and Password equality check is required to avoid login
			// using facebook userId as registered user(placing both userId and
			// password as facebookId).
			if (loginRequest.getUsername().equals(loginRequest.getPassword())) {
				throw new AppAuthenticationException("Username and Password cannot be same!");
			}

			authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
					loginRequest.getPassword());

		}
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private LoginRequest getLoginRequest(HttpServletRequest request) {
		BufferedReader reader = null;
		LoginRequest loginRequest = null;

		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			reader = request.getReader();
			loginRequest = jsonMapper.readValue(reader, LoginRequest.class);
		} catch (JsonParseException e) {
			throw new AuthenticationFormatNotAcceptableException("JsonParserException " + e.getMessage());
		} catch (JsonMappingException e) {
			throw new AuthenticationFormatNotAcceptableException("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			throw new AuthenticationFormatNotAcceptableException("HttpRequest Reader " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				logger.error("Exception Ocuured while closing the reader", ex);
			}
		}

		if (loginRequest == null) {
			loginRequest = new LoginRequest();
		}

		return loginRequest;
	}
}
