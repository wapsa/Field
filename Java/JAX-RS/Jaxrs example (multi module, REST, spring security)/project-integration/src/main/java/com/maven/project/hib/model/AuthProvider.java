package com.maven.project.hib.model;

public class AuthProvider implements java.io.Serializable {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = -2693862094488506687L;

	// Properties--------------------------------------------------
	private String authProviderId;
	private String description;

	// Constructors--------------------------------------------------
	public AuthProvider() {
	}

	// Getters & Setters--------------------------------------------------
	public AuthProvider(String authProviderId) {
		this.authProviderId = authProviderId;
	}

	public AuthProvider(String authProviderId, String description) {
		this.authProviderId = authProviderId;
		this.description = description;
	}

	public String getAuthProviderId() {
		return this.authProviderId;
	}

	public void setAuthProviderId(String authProviderId) {
		this.authProviderId = authProviderId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
