package fi.jupekett.task3.credentials;


/**
 * Handles saving, retrieving and checking credentials.
 */
public class CredentialService {
	
	private CredentialStorage credentialStorage = CredentialStorage.getInstance();
	
	
	/**
	 * Adds given user information to the storage.
	 * @param username
	 * @param password
	 * @param roleStr
	 * @return true if adding was successful.
	 */
	public boolean addCredentials(String username, String password, String roleStr) {
		return credentialStorage.addCredentials(username, password, roleStr);
	}
	
	
	/**
	 * Checks if the given credentials match existing Credentials.
	 * @param username 
	 * @param password
	 * @param role
	 * @return true if credentials match.
	 */
	public boolean doCredentialsMatch(String username, String password, String roleStr) {
		return credentialStorage.doCredentialsMatch(username, password, roleStr);
	}
}
