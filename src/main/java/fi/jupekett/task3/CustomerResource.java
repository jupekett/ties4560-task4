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
	
	
	/**
	 * Update a customer. Eventually is only supposed to update the email field.
	 * @param customerId
	 * @param customer
	 * @return 204 if modified. 201 with location if added new. 
	 */
	@PUT
	@Path("/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("customerId") int customerId, Customer customer) {
		Customer addedCustomer = customerService.updateCustomer(customerId, customer);
		boolean addedNewCustomer = addedCustomer != null;
		if (addedNewCustomer) {
			String location = URI_STRING + addedCustomer.getId();
			return Response.status(Status.CREATED)
						   .header("Content-Type", MediaType.TEXT_PLAIN)
						   .entity(location)
						   .build();
		} else  {
			return Response.status(Status.NO_CONTENT)
						   .build();
		}
	}
	
			
}
