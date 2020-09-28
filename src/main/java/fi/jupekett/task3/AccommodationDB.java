package fi.jupekett.task3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * A mock of a database.
 * @author Juho Kettunen (jupekett)
 *
 */
public class AccommodationDB {
	private static AccommodationDB instance;
	private List<Owner> owners;
	private int nextOwnerId;
	
	private List<Accommodation> accommodations;
	private int nextAccommodationId;
	
	
	/**
	 * Private constructor. 
	 * To be called from the getInstance-function. 
	 */
	private AccommodationDB() {	
		this.nextAccommodationId = 0;
		this.accommodations = getInitialAccommodations(this);
		this.nextOwnerId = 0;
		this.owners = getInitialOwners(this);
	}
	
	
	/**
	 * Returns the singleton database instance.
	 * Not robust! Only for demo purposes.
	 * @return
	 */
	public static synchronized AccommodationDB getInstance() {
		if (instance == null) {
			instance = new AccommodationDB();
		}
		return instance;
	}
	
	
	/**
	 * Initializes the list of accommodations
	 * @param database
	 * @return
	 */
	private List<Accommodation> getInitialAccommodations(AccommodationDB database) {
		List<Accommodation> accommodations = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int id = database.nextAccommodationId++;
			String name = APIHelpers.getRandomResortName();
			Accommodation accommodation = new Accommodation(id, name);
			accommodations.add(accommodation);
		}
		return accommodations;
	}
	
	
	/**
	 * Initializes the list of owners.
	 * @return
	 */
	private List<Owner> getInitialOwners(AccommodationDB database) {
		List<Owner> owners = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int id = database.nextOwnerId++;
			String name = APIHelpers.getRandomPersonName();
			List<Accommodation> accommodations = getRandomAccommodations(this.accommodations);
			Owner owner = new Owner(id, name, accommodations);
			owners.add(owner);
		}
		return owners;
	}
	
	
	/**
	 * Returns a random number of random accommodations from available accommodations.
	 * @param accommodations
	 * @return
	 */
	private List<Accommodation> getRandomAccommodations(List<Accommodation> accommodations) {
		Random rand = new Random();
		int numberOfAccommodations = rand.nextInt(4);
		List<Accommodation> randomAccommodations = new ArrayList<Accommodation>(); 
		
		// Create a set of random array indices 
		Set<Integer> indices = new HashSet<Integer>();
		for (int i = 0; i < numberOfAccommodations; i++) {
			int index = rand.nextInt(accommodations.size());
			indices.add(index);
		}
		// Pick accommodations based on generated indices
		for (int index : indices) {
			Accommodation accommodation = accommodations.get(index);
			randomAccommodations.add(accommodation);
		}
		return randomAccommodations;
	}
	
	
	
	/**
	 * Returns all owners.
	 * @return
	 */
	public List<Owner> getAllOwners() {
		return this.owners;
	}
	
	
	/**
	 * Returns all accommodations
	 * @return
	 */
	public List<Accommodation> getAllAccommodations() {
		return this.accommodations;
	}
	

	
	/**
	 * Adds an owner to the "database"
	 * @param owner
	 * @return
	 */
	public List<Owner> addOwner(Owner owner) {
		Owner newOwner = new Owner(this.nextOwnerId++, owner.getName(), owner.getAccommodations());
		this.owners.add(newOwner);
		return this.owners;
	}
	
	
	

}
