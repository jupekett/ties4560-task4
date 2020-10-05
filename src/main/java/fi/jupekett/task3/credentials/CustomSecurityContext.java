package fi.jupekett.task3.credentials;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class CustomSecurityContext implements SecurityContext {
	
	private Credentials credentials;
	private String scheme;
	
	public CustomSecurityContext(Credentials credentials, String scheme) {
		this.credentials = credentials;
		this.scheme = scheme;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.credentials;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (credentials.getRole() != null) {
			return credentials.getRole().toString().equals(role);
		}
		return false;
	}

	@Override
	public boolean isSecure() {
		return "https".equals(this.scheme);
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

}
