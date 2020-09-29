package fi.jupekett.task3;

import java.util.List;

/**
 * A class to mediate owner information between the API and "database"
 * @author Juho Kettunen (jupekett)
 *
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
	 * @return Owner that was added to the database.
	 */
	public Owner addOwner(String name) {
		Owner newOwner = new Owner(name);
		Owner addedOwner = database.addOwner(newOwner);
		return addedOwner;
	}

}
