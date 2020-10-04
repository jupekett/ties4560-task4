package fi.jupekett.task3.credentials;


/**
 * Represents the credentials of a user
 */
public class Credentials {
	
	private String username;
	private String password;
	private Role role;
	
	
	public Credentials(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	
	public String getUsername() {
		return this.username;
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
		
		return this.username.equals(creds.username) 
				&& this.password.equals(creds.password)
				&& this.role.equals(creds.role); 
	}

}
