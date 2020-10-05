package fi.jupekett.task3.credentials;


/**
 * Handles saving, retrieving and checking credentials.
 */
public class CredentialService {
	
	private CredentialStorage credentialStorage = CredentialStorage.getInstance();
	
	
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
}
