package fi.jupekett.task3.credentials;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles saving, retrieving and checking credentials.
 */
public class CredentialService {
	
	private CredentialStorage credentialStorage = CredentialStorage.getInstance();
	private Map<String, Credentials> CREDENTIALS;
	
	
	/**
	 * Zero parameter constructor.
	 */
	public CredentialService() {
		this.CREDENTIALS = credentialStorage.getAllCredentials();
	}
	
	
	/**
	 * Adds given user information to the storage.
	 * @param email
	 * @param password
	 * @param roleStr
	 * @return true if adding was successful.
	 */
	public boolean addCredentials(String email, String password, String roleStr) {
		return credentialStorage.addCredentials(email, password, roleStr);
	}
	
	
	/**
	 * Checks if the given credentials match existing Credentials.
	 * @param email 
	 * @param password
	 * @param role
	 * @return true if credentials match.
	 */
	public boolean doCredentialsMatch(String email, String password, String roleStr) {
		return credentialStorage.doCredentialsMatch(email, password, roleStr);
	}
	
	
	/**
	 * Checks if the given credentials match existing Credentials.
	 * @param email 
	 * @param password
	 * @param role
	 * @return true if credentials match.
	 */
	public boolean doCredentialsMatch(String email, String password, Role role) {
		return credentialStorage.doCredentialsMatch(email, password, role);
	}


	/**
	 * Gets user role that corresponds to an email
	 * @param email
	 * @return
	 */
	public Role getRoleWithEmail(String email) {
		return credentialStorage.getRoleWithEmail(email);
	}


}
