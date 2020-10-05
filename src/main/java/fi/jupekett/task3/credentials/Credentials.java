package fi.jupekett.task3.credentials;

import java.security.Principal;

/**
 * Represents the credentials of a user
 */
public class Credentials implements Principal{
	
	private String email;
	private String password;
	private Role role;
	
	
	public Credentials(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	
	
	public String getPassword() {
		return this.password;
	}
	
	
	public Role getRole() {
		return this.role;
	}
	
	
	public String getRoleAsString() {
		return this.role.toString();
	}
	
	
	@Override
	public boolean equals(Object o) {
		
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof Credentials)) {
			return false;
		}
		
		Credentials creds = (Credentials) o;
		
		return this.email.equals(creds.email) 
				&& this.password.equals(creds.password)
				&& this.role.equals(creds.role); 
	}


	/**
	 * FIXME fakes the implementation, not name but email
	 * @return
	 */
	@Override
	public String getName() {
		return email;
	}

}
