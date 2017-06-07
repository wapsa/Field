package com.maven.project.base.constants;

public interface AppConstants {

	// Constants--------------------------------------------------

	/** Activation link related **/
	public static final String ACTIVATION_CODE_EXPIRATION_IN_MINS = "activationCodeExpirationInMins";
	public static final String ACTIVATION_CODE_EXPIRATION_IN_MINS_DEFAULT = "10";

	/** Facebook related **/
	public static final String FACEBOOK_APP_SECRET = "facebook.app.secret";
	public static final String FACEBOOK_USER_ID_EMPTY_MSG = "Facebook User ID is empty or null ! Please check the granted permissions on accessToken.";
	public static final String FACEBOOK_EMAIL_ADDRESS_EMPTY_MSG = "Facebook emailAddress is empty or null ! Please check the granted permissions on accessToken.";
	public static final String FACEBOOK_NAME_EMPTY_MSG = "Facebook username or user's real name is empty or null ! Please check the granted permissions on accessToken.";

	public static final String BAD_CREDENTIALS = "Bad credentials !";

	public static final String ACCESS_TOKEN_EMPTY_MSG = "accessToken parameter must not be empty or null.";

	public static final String SINGLE_SPACE = " ";

	public static final String SCHEME_SEPRATOR = "://";

	public static final String WEB_RESOURCE_SEPRATOR = "/";

	public static final String QUESTION_MARK = "?";

	public static final String AMPERSAND = "&";

	public static final String SINGLE_EQUALS = "=";

	public static final String COLON = ":";

	/** String variable to store "." literal */
	public static final String DOT = ".";

	public static final String UNDERSCORE = "_";

	public static final String ENCODING_UTF8 = "UTF-8";

	public static final String UNKNOWN_FILE_NAME = "_unknown";

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/*** Mail Related ***/
	public static final String MAIL_FROM = "mail.from";
	public static final String MAIL_ACTIVATION_SUBJECT = "mail.activation.subject";
	public static final String MAIL_FORGET_PASSWORD_SUBJECT = "mail.forget.password.subject";
	public static final String MAIL_USER_INT_NOTIFICATION_SUBJECT = "mail.user.int.notification.subject";
	public static final String MAIL_LOCATION_DISALLOWED_SUBJECT = "mail.location.disallowed.subject";
	public static final String MAIL_BRAND_DISALLOWED_SUBJECT = "mail.brand.disallowed.subject";
	public static final String MAIL_LOCATION_APPROVED_SUBJECT = "mail.location.approved.subject";
	public static final String MAIL_BRAND_APPROVED_SUBJECT = "mail.brand.approved.subject";
	public static final String MAIL_NEW_BRAND_VOTE_NOTIFICATION_SUBJECT = "mail.new.brand.vote.notification.subject";
	public static final String MAIL_NEW_LOCATION_VOTE_NOTIFICATION_SUBJECT = "mail.new.location.vote.notification.subject";
	public static final String MAIL_NEW_PRODUCT_NOTIFICATION_SUBJECT = "mail.new.product.notification.subject";

	/** Activation Related **/
	public static final String ACTIVATION_ID = "AID";
	public static final String ACTIVATION_CODE = "ACD";
	public static final String ACTIVATION_LINK = "ALNK";
	public static final String ACTIVATION_EMAIL_ADDRESS = "AMAIL";

	/** Messaging Related for JMS **/
	public static final String KEY_USER_NAME = "UserName";
	public static final String KEY_USER_ID = "UserId";

	/** Registration related for JMS **/
	public static final String KEY_ACTIVATION_LINK = "ActivationLink";
	public static final String KEY_ACTIVATION_CODE = "ActivationCode";
	public static final String KEY_REG_MSG_TYPE = "REG_MSG_TYPE";
	public static final String VALUE_REG_MSG_ACTIVATION = "REG_MSG_ACTIVATION";
	public static final String VALUE_REG_MSG_FORGET_PASSWORD = "REG_MSG_FORGET_PASSWORD";
	public static final String KEY_EMAIL_ADDRESS = "EmailAddress";

}