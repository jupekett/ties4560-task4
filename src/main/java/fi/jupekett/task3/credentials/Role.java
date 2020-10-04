package fi.jupekett.task3.credentials;


/**
 * Enumerates the possible user roles within the system.
 * VISITOR practically means "no authority".
 */
public enum Role {
	CUSTOMER,
	OWNER,
	VISITOR;
	
	
	/**
	 * Gets a Role enumeration from a matching string.
	 * @param roleStr
	 * @return a matching Role, or VISITOR if no match.
	 */
	public static Role getRoleFromString(String roleStr) {
		if (roleStr.equalsIgnoreCase("customer")) {
			return CUSTOMER;
		} else if (roleStr.equalsIgnoreCase("owner")) {
			return OWNER;
		} else {
			return VISITOR;
		}
	}
}