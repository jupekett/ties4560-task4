package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Root resource (exposed at "owners/{ownerId}/accommodations" path)
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AccommodationResource {
	private AccommodationService accommodationService = new AccommodationService();
	
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
    
}
