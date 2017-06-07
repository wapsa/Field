package com.maven.project.auth.custom.dto;

public class FacebookAuthUser {

	// Properties--------------------------------------------------
	private final String id;
	private final String name;
	private final String email;

	// Constructors--------------------------------------------------
	public FacebookAuthUser(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	// Getters & Setters--------------------------------------------------
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
