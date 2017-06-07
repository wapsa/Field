package com.maven.project.base.security.domain;

// Imports--------------------------------------------------
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * This class represents User in terms of security. <blockquote> <b>enabled</b>
 * of super class i.e. User is mapped to <b>activated</b> and
 * <b>accountNonLocked</b> of super class is mapped to
 * <b>accountNotBanned</b> </blockquote> If account is not enabled and locked
 * then authenticationProvider(here AbstractUserDetailsAuthenticationProvider)
 * will throw DisabledException and LockedException respectively. These need to
 * be caught in AuthenticationFailureHandler to write custom domain status.
 */
public class SecurityUserDetails extends User {

	// Constants--------------------------------------------------
	private static final long serialVersionUID = 1L;

	// Properties--------------------------------------------------
	private Long userId;
	private String authProvider;
	private String role;
	private String userName;
	private String mobile;
	private String displayName;

	// Constructors--------------------------------------------------
	public SecurityUserDetails(String username, String password, boolean activated, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNotBanned,
			Collection<? extends GrantedAuthority> authorities) {

		super(username, password, activated, accountNonExpired, credentialsNonExpired, accountNotBanned, authorities);
	}

	/**
	 * @Builder is used to build the SecurityUserDetails i.e. @this class
	 */
	// Getters & Setters--------------------------------------------------
	public static Builder getBuilder() {
		return new Builder();
	}

	public Long getUserId() {
		return userId;
	}

	public String getAuthProvider() {
		return authProvider;
	}

	public String getRole() {
		return role;
	}

	public String getUserName() {
		return userName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static class Builder {

		// Properties--------------------------------------------------
		private Long userId;
		private String authProvider;
		private String role;
		private String userName;
		private String password;
		private String mobile;
		private Set<GrantedAuthority> authorities;
		private boolean accountNotBanned;
		private boolean activated;
		private String displayName;

		// Constructors--------------------------------------------------
		private Builder() {
			this.authorities = new HashSet<GrantedAuthority>();
		}

		// Methods--------------------------------------------------
		public SecurityUserDetails build() {

			SecurityUserDetails securityUserDetails = new SecurityUserDetails(userName, password, activated, true, true,
					accountNotBanned, authorities);

			securityUserDetails.authProvider = authProvider;
			securityUserDetails.mobile = mobile;
			securityUserDetails.role = role;
			securityUserDetails.userId = userId;
			securityUserDetails.userName = userName;
			securityUserDetails.displayName = displayName;

			return securityUserDetails;
		}

		// Getters & Setters--------------------------------------------------
		public Builder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public Builder authProvider(String authProvider) {
			this.authProvider = authProvider;
			return this;
		}

		public Builder role(String role) {
			this.role = role;
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			this.authorities.add(authority);
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder mobile(String mobile) {
			this.mobile = mobile;
			return this;
		}

		public Builder accountNotBanned(boolean accountNotBanned) {
			this.accountNotBanned = accountNotBanned;
			return this;
		}

		public Builder activated(boolean activated) {
			this.activated = activated;
			return this;
		}

		public Builder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("SecurityUserDetails [userId=").append(userId).append(", authProvider=").append(authProvider)
				.append(", role=").append(role).append(", userName=")
				.append(userName).append(", displayName=").append(displayName).append("]");
		return builder2.toString();
	}
}
