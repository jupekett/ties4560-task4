package fi.jupekett.task3;

import java.util.List;

/**
 * A class to mediate owner information between the API and "database"
 */
public class OwnerService {
	private DatabaseMock database = DatabaseMock.getInstance();
	private List<Owner> OWNERS = database.getAllOwners();
	
	public OwnerService() {
		// default constructor
	}
	

	/**
	 * Returns a list of all owners
	 * @return
	 */
	public List<Owner> getAllOwners() {
		return OWNERS;
	}
	
	
	/**
	 * Returns an owner based on its ID.
	 * @param id
	 * @return
	 */
	public Owner getOwner(int id) {
		for (Owner owner : OWNERS) {
			if (owner.getId() == id) {
				return owner;
			}
		}
		throw new DataNotFoundException("Owner with ID "+id+" not found.");
	}
	
	

	/**
	 * Returns an owner based on its email
	 * @param email
	 * @return
	 */
	public Owner getOwnerByEmail(String email) {
		for (Owner owner : OWNERS) {
			if (owner.getEmail().equalsIgnoreCase(email)) {
				return owner;
			}
		}
		throw new DataNotFoundException("Owner with email "+email+" not found.");
	}
	
	
	/**
	 * Adds an owner.
	 * @param owner
	 * @return Owner that was added to the database.
	 */
	public Owner addOwner(Owner owner) {
		Owner addedOdwner = database.addOwner(owner);
		return addedOdwner;
	}
	
	
	/**
	 * Adds an owner based on a name.
	 * @param name
	 * @param email
	 * @return Owner that was added to the database.
	 */
	public Owner addOwner(String name, String email) {
		Owner newOwner = new Owner(name, email);
		Owner addedOwner = database.addOwner(newOwner);
		return addedOwner;
	}



}
