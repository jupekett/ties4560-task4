package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

/**
 * Root resource (exposed at "owners/{ownerId}/accommodations" path)
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AccommodationResource {
	private AccommodationService accommodationService = new AccommodationService();
	private String URI_STRING = "http://localhost:8080/task3/webapi/owners/";
	private static final boolean LOGGING = true;
	
    /**
     * Get accommodations from an owner
     *
     * @return List<Accommodation> List of owner's accommodations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Accommodation> getAccommodations(@PathParam("ownerId") int ownerId) {
    	List<Accommodation> accommodations = accommodationService.getOwnersAccommodations(ownerId);
        return accommodations;
    }
    
    
    /**
     * Get a certain accommodation from an owner.
     * @param ownerId
     * @param accommodationId
     * @return
     */
    @GET
    @Path("/{accommodationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Accommodation getAccommodation(
    		@PathParam("ownerId") int ownerId, 
    		@PathParam("accommodationId") int accommodationId) 
    {
    	Accommodation accommodation = accommodationService.getAccommodation(ownerId, accommodationId);
    	return accommodation;
    }
    
    
    /**
     * Handle POST as JSON
     * @param accommodation Accommodation as JSON
     * @return Resource location in response body
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAccommodation(@PathParam("ownerId") int ownerId, Accommodation accommodation) {
    	if (LOGGING) System.out.println("AccommodationResource.java - addAccommodation- " + accommodation);
    	Accommodation newAccommodation = accommodationService.addAccommodation(ownerId, accommodation);
    	String location = URI_STRING + ownerId + "/accommodations/" + newAccommodation.getId(); 
    	return Response.status(Status.CREATED)
    					.header("Content-Type", MediaType.TEXT_PLAIN)
    					.entity(location)
    					.build();
    }
    
    
    /**
     * Handle POST as plain text
     * @param name Accommodation name in plain text
     * @return Resource location in response body
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addAccommodation(@PathParam("ownerId") int ownerId, String name) {
    	if (LOGGING) System.out.println("AccommodationResource.java - addAccommodation- " + name);
    	Accommodation newAccommodation = new Accommodation(name);
    	Accommodation addedAccommodation = accommodationService.addAccommodation(ownerId, newAccommodation);
    	String location = URI_STRING + ownerId + "/accommodations/" + addedAccommodation.getId(); 
    	return Response.status(Status.CREATED)
    					.header("Content-Type", MediaType.TEXT_PLAIN)
    					.entity(location)
    					.build();
    }
    
    
}
