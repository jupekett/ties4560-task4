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
	 * @return all credentials in the system.
	 */
	public Map<String, Credentials> getAllCredentials() {
		return CREDENTIALS;
	}
	
	
	/**
	 * Adds given user information to the storage.
	 * @param email
	 * @param password
	 * @param roleStr
	 * @return true if adding was successful.
	 */
	public boolean addCredentials(String email, String password, String roleStr) {
		boolean addingSuccessful = credentialStorage.addCredentials(email, password, roleStr);
		if (addingSuccessful) {
			this.CREDENTIALS = credentialStorage.getAllCredentials();
		}
		return addingSuccessful;
	}
	
	
	/**
	 * Checks if the given credentials match existing Credentials.
	 * @param email 
	 * @param password
	 * @param role
	 * @return true if credentials match. Otherwise false.
	 */
	public boolean doCredentialsMatch(String email, String password, String roleStr) {
		Role role = Role.getRoleFromString(roleStr);
		return doCredentialsMatch(email, password, role);
	}
	
	
	/**
	 * Checks if the given credentials match existing Credentials.
	 * @param email 
	 * @param password
	 * @param role
	 * @return true if credentials match. Otherwise false.
	 */
	public boolean doCredentialsMatch(String email, String password, Role role) {
		if (!CREDENTIALS.containsKey(email)) {
			return false;
		}
		Credentials inputCredentials= new Credentials(email, password, role);
		Credentials savedCredentials = CREDENTIALS.get(email);
		
		return inputCredentials.equals(savedCredentials);
	}


	/**
	 * Returns the role corresponding to an email, or VISITOR if not found.
	 * @param email
	 * @return
	 */
	public Role getRoleWithEmail(String email) {
		Credentials credentials = CREDENTIALS.get(email); 
		if (credentials == null) {
			return Role.VISITOR;
		}
		return credentials.getRole();
	}


}
