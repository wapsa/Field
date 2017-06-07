package com.maven.project.auth.custom.dto;

public class LoginFailureResponse extends LoginResponse {

	// Constructors--------------------------------------------------
	public LoginFailureResponse() {
	}

	// Properties--------------------------------------------------
	private DomainSpecificStatus domainSpecificStatus;
	private String errorMessage;

	// Getters & Setters--------------------------------------------------
	public DomainSpecificStatus getDomainSpecificStatus() {
		return domainSpecificStatus;
	}

	public void setDomainSpecificStatus(DomainSpecificStatus domainSpecificStatus) {
		this.domainSpecificStatus = domainSpecificStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
