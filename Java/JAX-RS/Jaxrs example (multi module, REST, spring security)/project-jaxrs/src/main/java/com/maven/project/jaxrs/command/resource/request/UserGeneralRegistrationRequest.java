package com.maven.project.jaxrs.command.resource.request;

// Imports--------------------------------------------------
import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserGeneralRegistrationRequest implements Serializable {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 5745931775045872636L;

	// Constructors--------------------------------------------------
	public UserGeneralRegistrationRequest() {
	}

	// Properties--------------------------------------------------

	@NotEmpty
	@Size(max = 50)
	private String displayName;

	@NotEmpty
	@Email
	@Size(max = 50)
	private String email_Address;

	@NotEmpty
	@Size(max = 50)
	private String userName;

	@NotEmpty
	@Size(max = 50)
	private String passWord;

	@NotEmpty
	@Size(min = 10)
	private String mobile_Number;

	// Getters & Setters--------------------------------------------------
	public String getEmail_Address() {
		return email_Address;
	}

	public void setEmail_Address(String email_Address) {
		this.email_Address = email_Address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getMobile_Number() {
		return mobile_Number;
	}

	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	// Methods--------------------------------------------------
	@Override
	public String toString() {
		return "UserGeneralRegistrationRequest [Display_Name=" + displayName + ", email_Address=" + email_Address
				+ ", userName=" + userName + ", passWord=" + passWord + ", mobile_Number=" + mobile_Number + "]";
	}
}
