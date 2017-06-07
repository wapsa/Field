package com.maven.project.hib.model;

// Imports--------------------------------------------------
import java.util.Date;

public class ActivationLink implements java.io.Serializable {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -7421919938172147646L;
	
	// Properties--------------------------------------------------
	private long userId;
	private User user;
	private String email;
	private String activationCode;
	private Date activationCodeSentTime;
	private Date activationCodeExpirationTime;

	// Constructors--------------------------------------------------
	public ActivationLink() {
	}

	public ActivationLink(User user, String email, String activationCode, Date activationCodeSentTime,
			Date activationCodeExpirationTime) {
		this.user = user;
		this.email = email;
		this.activationCode = activationCode;
		this.activationCodeSentTime = activationCodeSentTime;
		this.activationCodeExpirationTime = activationCodeExpirationTime;
	}

	// Getters & Setters--------------------------------------------------
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Date getActivationCodeSentTime() {
		return this.activationCodeSentTime;
	}

	public void setActivationCodeSentTime(Date activationCodeSentTime) {
		this.activationCodeSentTime = activationCodeSentTime;
	}

	public Date getActivationCodeExpirationTime() {
		return this.activationCodeExpirationTime;
	}

	public void setActivationCodeExpirationTime(Date activationCodeExpirationTime) {
		this.activationCodeExpirationTime = activationCodeExpirationTime;
	}

}
