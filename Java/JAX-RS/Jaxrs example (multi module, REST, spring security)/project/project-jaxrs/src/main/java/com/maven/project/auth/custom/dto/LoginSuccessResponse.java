package com.maven.project.auth.custom.dto;

public class LoginSuccessResponse extends LoginResponse {

	// Constructors---------------------------------------------
	public LoginSuccessResponse() {
	}

	// Properties--------------------------------------------------
	private String displayName;
	private Long appUserId;

	// Getters & Setters--------------------------------------------------
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

}
