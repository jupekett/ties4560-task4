package fi.jupekett.task3;

import java.util.List;

/**
 * A class to mediate reservation data between the API and "database"
 * @author Juho Kettunen (jupekett)
 */
public class ReservationService {
	private DatabaseMock database = DatabaseMock.getInstance();
	private List<Reservation> RESERVATIONS = database.getAllReservations();
	
	
	public ReservationService() {
		// default
	}
	
	
	/**
	 * @return all accommodations in the system.
	 */
	public List<Reservation> getAllReservations() {
		return RESERVATIONS;
	}
	
	
	/**
	 * Returns a list of customer's reservations based on customer ID.
	 * @param customerId
	 * @return
	 */
	public List<Reservation> getCustomersReservations(int customerId) {
		CustomerService customerService = new CustomerService();
		List<Customer> customers = customerService.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				return customer.getReservations();
			}
		}
		// TODO some status rather than null?
		return null;
	}
	
	
	/**
	 * Returns a reservation based on customer ID and reservation ID.
	 * @param customerId
	 * @param reservationId
	 * @return
	 */
	public Reservation getReservation(int customerId, int reservationId) {
		CustomerService customerService = new CustomerService();
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			// TODO response
			return null;
		}
		List<Reservation> reservations = customer.getReservations();
		for (var reservation : reservations) {
			if (reservation.getId() == reservationId) {
				return reservation;
			}
		}
		// TODO response
		return null;
	}
	
	
	/**
	 * Adds a new reservation to a certain customer.
	 * @param customerId
	 * @param reservation
	 * @return the added reservation.
	 */
	public Reservation addReservation(int customerId, Reservation reservation) {
		Reservation addedReservation = database.addReservation(customerId, reservation);
		return addedReservation;
	}

}
