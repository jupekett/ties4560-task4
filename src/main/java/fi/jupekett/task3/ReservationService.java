package fi.jupekett.task3;

import java.util.List;

/**
 * A class to mediate reservation data between the API and "database"
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
				var reservations = customer.getReservations();
				if (reservations == null || reservations.size() == 0) {
					throw new DataNotFoundException("Customer with ID "+customerId+" has no reservations.");
				}
				return customer.getReservations();
			}
		}
		throw new DataNotFoundException("Customer with ID "+customerId+" not found.");
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
		List<Reservation> reservations = customer.getReservations();
		if (reservations.size() == 0) {
			throw new DataNotFoundException("Customer with ID "+customerId+" has no reservations.");
		}
		for (var reservation : reservations) {
			if (reservation.getId() == reservationId) {
				return reservation;
			}
		}
		throw new DataNotFoundException(
				"Reservation with customer ID "+customerId+
				" and reservation ID "+reservationId+" not found.");
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
	
	
	/**
	 * Deletes a reservation from a customer.
	 * @param customerId
	 * @param reservationId
	 * @return the deleted reservation.
	 */
	public Reservation deleteReservation(int customerId, int reservationId) {
		Reservation deletedReservation = database.deleteReservation(customerId, reservationId);
		return deletedReservation;
	}
	

}
