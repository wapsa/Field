package com.jersey.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.jersey.util.common.GsonProvider;

public class UserProfileIn implements Serializable {

	private static final long serialVersionUID = -8328987436869392717L;

	@Email
	@NotBlank
	private String emailAddress;

	@NotBlank
	private String fullName;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return GsonProvider.gson.toJson(this);
	}

}
