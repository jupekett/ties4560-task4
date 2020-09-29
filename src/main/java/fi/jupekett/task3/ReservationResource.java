package fi.jupekett.task3;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;


/**
 * Root resource (exposed at "customers/{customerId}/reservations" path)
 * @author Juho Kettunen (jupekett)
 */
@Path("/customers/{customerId}/reservations")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {
	private ReservationService reservationService = new ReservationService();
	private String URI_STRING = "http://localhost:8080/task3/webapi/customers/";
	private static final boolean LOGGING = true;
	
	
    /**
     * Handle GET request of a customer's reservations
     *
     * @return List<Reservation> List of customer's reservations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations(@PathParam("customerId") int customerId) {
    	List<Reservation> reservations = reservationService.getCustomersReservations(customerId);
        return reservations;
    }
    
    
    /**
     * Handle GET request of customer's certain accommodation
     * @param customerId
     * @param reservationId
     * @return
     */
    @GET
    @Path("/{reservationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation getReservation(
    		@PathParam("customerId") int customerId, 
    		@PathParam("reservationId") int reservationId) 
    {
    	Reservation reservation = reservationService.getReservation(customerId, reservationId);
    	return reservation;
    }
    
    
    /**
     * Handle POST as JSON
     * @param reservation Reservation as JSON
     * @return Resource location in response body
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReservation(
    		@PathParam("customerId") int customerId, 
    		Reservation reservation) 
    {
    	if (LOGGING) System.out.println("ReservationResource.java - addReservation- " + reservation);
    	Reservation newReservation = reservationService.addReservation(customerId, reservation);
    	if (newReservation == null) {
    		// TODO reservation was faulty (wrong accommodation ID?) -> throw error
    	}
    	String location = URI_STRING + customerId + "/reservations/" + newReservation.getId(); 
    	return Response.status(Status.CREATED)
    					.header("Content-Type", MediaType.TEXT_PLAIN)
    					.entity(location)
    					.build();
    }
    
    
    
    @DELETE
    @Path("/{reservationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteReservation(@PathParam("customerId") int customerId, 
    		@PathParam("reservationId") int reservationId) 
    {
    	if (LOGGING) System.out.println(
    			"ReservationResource.java - deleteReservation- "+customerId+" "+reservationId);
    	Reservation deletedReservation = reservationService.deleteReservation(customerId, reservationId);
    	if (deletedReservation == null) {
    		// TODO custom error?
    		return Response.status(Status.NOT_FOUND)
    					   .build();
    	}
    	return Response.status(Status.OK)
    				   .header("Content-Type", MediaType.APPLICATION_JSON)
    				   .entity(deletedReservation)
    				   .build();
    }
}
