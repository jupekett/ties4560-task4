package fi.jupekett.task3;

import java.util.List;

/**
 * A class to mediate accommodation data between API and "database"
 * @author Juho Kettunen (jupekett)
 *
 */
public class AccommodationService {
	private AccommodationDB database = AccommodationDB.getInstance();
	private List<Accommodation> ACCOMMODATIONS = database.getAllAccommodations();
	
	public AccommodationService() {
		// default constructor
	}
	
	
	/**
	 * Returns all accommodations in the system.
	 * @return
	 */
	public List<Accommodation> getAllAccommodations() {
		return ACCOMMODATIONS;
	}
	

	/**
	 * Returns a list of owner's accommodations based on owner ID.
	 * @return
	 */
	public List<Accommodation> getOwnersAccommodations(int ownerId) {
		OwnerService ownerService = new OwnerService();
		List<Owner> owners = ownerService.getAllOwners();
		for (Owner owner : owners) {
			if (owner.getId() == ownerId) {
				return owner.getAccommodations();
			}
		}
		// TODO 404 response
		return null;
	} 

	
	
	/**
	 * Returns an accommodation based on owner ID and accommodation ID.
	 */
	public Accommodation getAccommodation(int ownerId, int accommodationId) {
		OwnerService ownerService = new OwnerService();
		List<Owner> owners = ownerService.getAllOwners();
		for (Owner owner : owners) {
			if (owner.getId() == ownerId) {
				var accommodations = owner.getAccommodations();
				for (var accommodation : accommodations) {
					if (accommodation.getId() == accommodationId) {
						return accommodation;
					}
				}
				break;
			}
		}
		// TODO 404 response
		return null;
	} 

}
