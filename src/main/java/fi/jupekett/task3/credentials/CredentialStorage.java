package fi.jupekett.task3.credentials;

import java.util.HashMap;
import java.util.Map;

import fi.jupekett.task3.Utilities;


/**
 * A class to store and manipulate user credentials.
 */
public class CredentialStorage {
	
	private static CredentialStorage INSTANCE;
	private final static Map<String, Credentials> CREDENTIALS =  new HashMap<>();
	
	
	
	private CredentialStorage() {
		// TODO
	}
	
	
	/**
	 * Returns the singleton database instance.
	 * @return
	 */
	public static synchronized CredentialStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CredentialStorage();
		}
		return INSTANCE;
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
	 * @return true if no existing user with email was found.
	 */
	public boolean addCredentials(String email, String password, String roleStr) {
		if (Utilities.someStringsAreNullOrBlank(
				new String[] {email, password, roleStr})) {
			// TODO throw error?
		}
		Role role = Role.getRoleFromString(roleStr);
		Credentials newCredentials= new Credentials(email, password, role);
		
		// put returns null if credentials are not found -> adding was successful.
		Credentials existingCredentials = CREDENTIALS.put(email, newCredentials);
		return existingCredentials == null;
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
