package com.maven.project.hib.model;

// Imports-------------------------------------------------------
import java.util.Date;

public class User implements java.io.Serializable {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 7786567031329981547L;

	// Properties--------------------------------------------------
	private Long userId;
	private String authProvider;
	private String role;
	private User banningAdmin;
	private String displayName;
	private String username;
	private String emailAddress;
	private String mobileNumber;
	private String password;
	private boolean activatedFlag;
	private Date activationTime;
	private boolean bannedFlag;
	private ActivationLink activationLink;

	// Constructors------------------------------------------------------------
	public User() {
	}

	public User(Long userId) {
		this.userId = userId;
	}

	public User(String username) {
		this.username = username;
	}

	public User(Long userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public User(String mobileNumber, String displayName, String emailAddress, String password, String displayImage,
			String dateOfBirth) {
		this.mobileNumber = mobileNumber;
		this.displayName = displayName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public User(String username, String emailAddress, String password, String mobileNumber) {
		this.username = username;
		this.emailAddress = emailAddress;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}

	public User(String username, String authProvider, boolean activatedFlag, boolean bannedFlag, String password,
			String role, Long userId, String emailAddress, String mobileNumber, String displayName) {
		this.username = username;
		this.authProvider = authProvider;
		this.activatedFlag = activatedFlag;
		this.bannedFlag = bannedFlag;
		this.password = password;
		this.role = role;
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.displayName = displayName;
	}

	public User(String authProvider, String role, String displayName, String username, String emailAddress,
			String mobileNumber, String password, boolean activatedFlag, boolean bannedFlag) {
		this.authProvider = authProvider;
		this.role = role;
		this.displayName = displayName;
		this.username = username;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.activatedFlag = activatedFlag;
		this.bannedFlag = bannedFlag;
	}

	public User(String authProvider, String role, User user, String displayName, String username, String emailAddress,
			String mobileNumber, String password, boolean activatedFlag, Date activationTime, boolean bannedFlag,
			ActivationLink activationLink) {
		this.authProvider = authProvider;
		this.role = role;
		this.displayName = displayName;
		this.username = username;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.activatedFlag = activatedFlag;
		this.activationTime = activationTime;
		this.bannedFlag = bannedFlag;
		this.activationLink = activationLink;
	}

	// Methods-------------------------------------------------------

	// Bi-directional mapping
	public void addBidirectionalRefForActivationLink(ActivationLink activationLink) {
		this.setActivationLink(activationLink);
		activationLink.setUser(this);
	}

	// Getters & Setters--------------------------------------------------
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivatedFlag() {
		return this.activatedFlag;
	}

	public void setActivatedFlag(boolean activatedFlag) {
		this.activatedFlag = activatedFlag;
	}

	public Date getActivationTime() {
		return this.activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	public boolean isBannedFlag() {
		return this.bannedFlag;
	}

	public void setBannedFlag(boolean bannedFlag) {
		this.bannedFlag = bannedFlag;
	}

	public ActivationLink getActivationLink() {
		return this.activationLink;
	}

	public void setActivationLink(ActivationLink activationLink) {
		this.activationLink = activationLink;
	}

	public String getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(String authProvider) {
		this.authProvider = authProvider;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getBanningAdmin() {
		return banningAdmin;
	}

	public void setBanningAdmin(User banningAdmin) {
		this.banningAdmin = banningAdmin;
	}

}
