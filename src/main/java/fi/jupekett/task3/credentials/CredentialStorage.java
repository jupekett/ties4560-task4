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
	 * Adds given user information to the storage.
	 * @param username
	 * @param password
	 * @param roleStr
	 * @return true if no existing user with username was found.
	 */
	public boolean addCredentials(String username, String password, String roleStr) {
		if (Utilities.someStringsAreNullOrBlank(
				new String[] {username, password, roleStr})) {
			// TODO throw error?
		}
		Role role = Role.getRoleFromString(roleStr);
		Credentials newCredentials= new Credentials(username, password, role);
		
		// put returns null if credentials are not found -> adding was successful.
		Credentials existingCredentials = CREDENTIALS.put(username, newCredentials);
		return existingCredentials == null;
	}
	
	
	/**
	 * Checks if the given credentials match existing Credentials.
	 * @param username 
	 * @param password
	 * @param role
	 * @return true if credentials match. Otherwise false.
	 */
	public boolean doCredentialsMatch(String username, String password, String roleStr) {
		if (!CREDENTIALS.containsKey(username)) {
			return false;
		}
		Role role = Role.getRoleFromString(roleStr);
		Credentials inputCredentials= new Credentials(username, password, role);
		Credentials savedCredentials = CREDENTIALS.get(username);
		
		return inputCredentials.equals(savedCredentials);
	}
	

}
