package com.maven.project.hib.model;

public class Role implements java.io.Serializable {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 3080092207392512016L;

	// Properties--------------------------------------------------
	private String roleId;
	private String description;

	// Constructors--------------------------------------------------
	public Role() {
	}

	public Role(String roleId) {
		this.roleId = roleId;
	}

	public Role(String roleId, String description) {
		this.roleId = roleId;
		this.description = description;
	}

	// Getters & Setters--------------------------------------------------
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
