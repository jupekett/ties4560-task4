package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;



@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	private CustomerService customerService = new CustomerService();
	private String URI_STRING = "http://localhost:8080/task3/webapi/customers/";
	private static final boolean LOGGING = true;
	
	
	/**
	 * Handle getting all customers
	 * @return
	 */
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return customers;
	}
	
	
	
	/**
	 * Handle getting a customer based on ID.
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{customerId}")
	public Customer getCustomer(@PathParam("customerId") int id) {
		Customer customer = customerService.getCustomer(id);
		// TODO error handling
		return customer;
	}
	
	
	/**
	 * Handle POST with a customer as JSON in request body-
	 * @param customer
	 * @return Resource location as plain text in response body.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer) {
		if (LOGGING) System.out.println("CustomerResource.java - addcustomer - " + customer);
		Customer newCustomer = customerService.addCustomer(customer);
		String location = URI_STRING + newCustomer.getId();
		return Response.status(Status.CREATED)
					   .header("Content-Type", MediaType.TEXT_PLAIN)
					   .entity(location)
					   .build();
	}
	
	
//	/**
//	 * Delegates reservation handling to appropriate class
//	 * @return
//	 */
//	@Path("/{customerId}/reservations")
//	public ReservationResource getReservationResource() {
//		return new ReservationResource();
//	}
			
}
