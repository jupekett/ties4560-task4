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
public class DatabaseMock {
	private static DatabaseMock instance;

	private List<Accommodation> accommodations;
	private List<Customer> customers;
	private List<Owner> owners;
	private List<Reservation> reservations = new ArrayList<>();
	
	private int nextOwnerId;
	private int nextAccommodationId;
	private int nextCustomerId;
	private int nextReservationId;
	
	
	/**
	 * Private constructor. 
	 * To be called from the getInstance-function. 
	 */
	private DatabaseMock() {	
		this.nextAccommodationId = 0;
		this.accommodations = getInitialAccommodations(10, this);

		// accommodations need to be instantiated first
		this.nextOwnerId = 0;
		this.owners = getInitialOwners(4, this);
		
		this.nextReservationId = 0;
		this.reservations = getInitialReservations(15, this);
		
		// reservations need to be instantiated first (in this random mock)
		this.nextCustomerId = 0;
		this.customers = getInitialCustomers(15, this);
	}
	
	
	/**
	 * Returns the singleton database instance.
	 * @return
	 */
	public static synchronized DatabaseMock getInstance() {
		if (instance == null) {
			instance = new DatabaseMock();
		}
		return instance;
	}
	
	
	/**
	 * Initializes the list of accommodations with random data.
	 * @param count Number of initial accommodations.
	 * @param database
	 * @return
	 */
	private List<Accommodation> getInitialAccommodations(int count, DatabaseMock database) {
		List<Accommodation> accommodations = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int id = database.nextAccommodationId++;
			String name = APIHelpers.getRandomResortName();
			Accommodation accommodation = new Accommodation(id, name);
			accommodations.add(accommodation);
		}
		return accommodations;
	}
	
	
	/**
	 * Initializes the list of owners with random data.
	 * @param countNumber of initial owners
	 * @param database
	 * @return
	 */
	private List<Owner> getInitialOwners(int count, DatabaseMock database) {
		List<Owner> owners = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int id = database.nextOwnerId++;
			String name = APIHelpers.getRandomPersonName();
			List<Accommodation> accommodations = getRandomAccommodations(3, this.accommodations);
			Owner owner = new Owner(id, name, accommodations);
			owners.add(owner);
		}
		return owners;
	}
	
	
	/**
	 * Returns a random number of random accommodations from available accommodations.
	 * @param max Maximum number of accommodations.
	 * @param accommodations
	 * @return
	 */
	private List<Accommodation> getRandomAccommodations(int max, List<Accommodation> accommodations) {
		Random rand = new Random();
		int numberOfAccommodations = rand.nextInt(max + 1);
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
	 * Initializes the list of reservations with random data.
	 * @param count
	 * @param database
	 * @return
	 */
	private List<Reservation> getInitialReservations(int count, DatabaseMock database) {
		Random rand = new Random();
		List<Reservation> reservations = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int id = database.nextReservationId++;
			Accommodation accommodation = 
					this.accommodations.get(rand.nextInt(this.accommodations.size()));
			Reservation newReservation = new Reservation(id, accommodation.getId());
			reservations.add(newReservation);
		}
		return reservations;
	}
	
	
	
	/**
	 * Initializes the list for customers with random data
	 * @param count number of initial customers.
	 * @param database
	 * @return
	 */
	private List<Customer> getInitialCustomers(int count, DatabaseMock database) {
		List<Customer> customers= new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int id = database.nextCustomerId++;
			String name = APIHelpers.getRandomPersonName();
			String email = APIHelpers.getRandomEmail(name);
			List<Reservation> reservations = distributeReservations(2, this.reservations);
			Customer customer = new Customer(id, name, email, reservations);
			customers.add(customer);
		}
		return customers;
	}
	
	
	
	/**
	 * Returns a random number of accommodations
	 * @param max Maximum number of reservations.
	 * @param reservations
	 * @return
	 */
	private List<Reservation> distributeReservations(int max, List<Reservation> reservations) {
		Random rand = new Random();
		int numberOfReservations = rand.nextInt(max + 1);
		List<Reservation> randomReservations= new ArrayList<Reservation>(); 
		
		// Create a set of random array indices 
		Set<Integer> indices = new HashSet<Integer>();
		for (int i = 0; i < numberOfReservations; i++) {
			int index = rand.nextInt(reservations.size());
			indices.add(index);
		}
		// Pick accommodations based on generated indices
		for (int index : indices) {
			Reservation reservation = reservations.get(index);
			randomReservations.add(reservation);
		}
		return randomReservations;

	}
	
	
	/**
	 * @return All customers.
	 */
	public List<Owner> getAllOwners() {
		return this.owners;
	}
	
	
	/**
	 * @return All customers.
	 */
	public List<Accommodation> getAllAccommodations() {
		return this.accommodations;
	}
	
	
	/**
	 * @return All customers.
	 */
	public List<Customer> getAllCustomers() {
		return this.customers;
	}
	
	
	/**
	 * @return all reservations.
	 */
	public List<Reservation> getAllReservations() {
		return this.reservations;
	}
	
	
	/**
	 * Adds an owner to the "database"
	 * @param owner
	 * @return The added owner.
	 */
	public Owner addOwner(Owner owner) {
		Owner newOwner = new Owner(
				this.nextOwnerId++, 
				owner.getName(), 
				owner.getAccommodations()
				);
		this.owners.add(newOwner);
		return newOwner;
	}
	
	
	/**
	 * Adds an accommodation to a certain owner in the "database"
	 * @param ownerId
	 * @param accommodation
	 * @return The added accommodation.
	 */
	public Accommodation addAccommodation(int ownerId, Accommodation accommodation) {
		for (Owner owner : this.owners) {
			if (owner.getId() == ownerId) {
				Accommodation newAccommodation = new Accommodation(
						this.nextAccommodationId++, 
						accommodation.getName()
						);
				owner.addAccommodation(newAccommodation);
				return newAccommodation;
			}
		}
		// TODO response?
		return null;
	}
	
	
	/**
	 * Adds a customer to the "database"
	 * @param customer
	 * @return The added customer.
	 */
	public Customer addCustomer(Customer customer) {
		Customer newCustomer = new Customer(
				this.nextCustomerId++, 
				customer.getName(), 
				customer.getEmail(),
				customer.getReservations()
				);
		this.customers.add(newCustomer);
		return newCustomer;
	}
	
	
	
	/**
	 * Adds a reservation to a certain customer in the "database"
	 * @param customerId
	 * @param reservation
	 * @return the added reservation
	 */
	public Reservation addReservation(int customerId, Reservation reservation) {
		for (Customer customer : this.customers) {
			if (customer.getId() == customerId) {
				Reservation newReservation = new Reservation(
						this.nextReservationId++,
						reservation.getAccommodationId()
						);
				customer.addReservation(newReservation);
				return newReservation;
			}
		}
		// TODO response?
		return null;
	}
	

}
