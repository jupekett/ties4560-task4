package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.google.gson.Gson;

/**
 * Root resource (exposed at "owners/{ownerId}/accommodations" path)
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AccommodationResource {
	private AccommodationService accommodationService = new AccommodationService();
	
    /**
     * Method handling HTTP GET requests. 
     *
     * @return String that will be returned.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccommodations(@PathParam("ownerId") int ownerId) {
    	List<Accommodation> accommodations = accommodationService.getOwnersAccommodations(ownerId);
		Gson gson = new Gson();
        return gson.toJson(accommodations);
    }
    
    
    @GET
    @Path("/{accommodationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccommodation(
    		@PathParam("ownerId") int ownerId, 
    		@PathParam("accommodationId") int accommodationId) 
    {
    	Accommodation accommodation = accommodationService.getAccommodation(ownerId, accommodationId);
    	Gson gson = new Gson();
    	return gson.toJson(accommodation);
    }
    
}
