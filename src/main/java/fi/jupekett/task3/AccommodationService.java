package fi.jupekett.task3;

import java.util.List;

/**
 * A class to mediate accommodation data between API and "database"
 */
public class AccommodationService {
	private DatabaseMock database = DatabaseMock.getInstance();
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
	 * @param ownerId
	 * @return
	 */
	public List<Accommodation> getOwnersAccommodations(int ownerId) {
		OwnerService ownerService = new OwnerService();
		List<Owner> owners = ownerService.getAllOwners();
		for (Owner owner : owners) {
			if (owner.getId() == ownerId) {
				List<Accommodation> accommodations = owner.getAccommodations();
				if (accommodations == null || accommodations.size() == 0) {
					throw new DataNotFoundException(
							"Owner with ID "+ownerId+" doesn't have any accommodations.");
				}
				return accommodations;
			}
		}
		throw new DataNotFoundException("Owner with ID "+ownerId+" not found.");
	} 

	
	
	/**
	 * Returns an accommodation based on owner ID and accommodation ID.
	 * @param ownerId
	 * @param accommodationId
	 * @return 
	 */
	public Accommodation getAccommodation(int ownerId, int accommodationId) {
		OwnerService ownerService = new OwnerService();
		Owner owner = ownerService.getOwner(ownerId);
		List<Accommodation> accommodations = owner.getAccommodations();
		for (Accommodation accommodation : accommodations) {
			if (accommodation.getId() == accommodationId) {
				return accommodation;
			}
		}
		throw new DataNotFoundException(
				"Accommodation with owner ID "+ownerId+
				" and accommodation ID "+accommodationId+
				" not found.");
	} 
	
	
	/**
	 * Adds a new accommodation to a certain owner.
	 * @param ownerId
	 * @param accommodation
	 * @return The added accommodation.
	 */
	public Accommodation addAccommodation(int ownerId, Accommodation accommodation) {
		Accommodation addedAccommodation = database.addAccommodation(ownerId, accommodation);
		return addedAccommodation;
	}

}
