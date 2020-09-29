package fi.jupekett.task3;

import java.util.List;


/**
 * A class to mediate customer information between the API and "database"
 * @author Juho Kettunen (jupekett)
 *
 */
public class CustomerService {
	private DatabaseMock database = DatabaseMock.getInstance();
	private List<Customer> CUSTOMERS = database.getAllCustomers();
	
	public CustomerService() {
		// default
	}
	
	
	/**
	 * @return all customers.
	 */
	public List<Customer> getAllCustomers() {
		return CUSTOMERS;
	}
	
	
	/**
	 * Returns a customer based on its ID.
	 * @param id
	 * @return
	 */
	public Customer getCustomer(int id) {
		for (Customer customer : CUSTOMERS) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		// TODO error response
		return null;
	}
	
	
	
	/**
	 * Adds a customer.
	 * @param customer
	 * @return Customer that was added to the database.
	 */
	public Customer addCustomer(Customer customer) {
		Customer addedCustomer = database.addCustomer(customer);
		// TODO errors?
		return addedCustomer;
	}
	
	
	/**
	 * Adds a customer based on a name.
	 * @param name
	 * @param email
	 * @return Customer that was added to the database.
	 */
	public Customer addCustomer(String name, String email) {
		Customer newCustomer = new Customer(name, email);
		Customer addedCustomer = database.addCustomer(newCustomer);
		return addedCustomer;
	}
	
	
	

}
