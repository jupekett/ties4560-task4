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
	 * Returns an owner based on it's ID.
	 * @param id
	 * @return
	 */
	public Owner getOwner(int id) {
		for (Owner owner : OWNERS) {
			if (owner.getId() == id) {
				return owner;
			}
		}
		// TODO 404 response
		return null;
	}

}
