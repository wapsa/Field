package com.maven.project.auth.custom.dto;

// Imports--------------------------------------------------
import com.maven.project.base.security.domain.AuthProvider;

/**
 * If @username and @password comes from the client, @accessToken will be null. 
 * @authProvider is mandatory to come from client.
 */
public class LoginRequest {
	
	// Properties--------------------------------------------------
	private String username;
	private String password;
	private String accessToken;
	private AuthProvider authProvider;
	
	// Getters & Setters--------------------------------------------------
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public AuthProvider getAuthProvider() {
		return authProvider;
	}
	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}
}
