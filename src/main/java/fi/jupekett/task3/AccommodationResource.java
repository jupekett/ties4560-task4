package fi.jupekett.task3;

import java.net.URI;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Root resource (exposed at "owners/{ownerId}/accommodations" path)
 */
@Path("/owners/{ownerId}/accommodations")
@Produces(MediaType.APPLICATION_JSON)
public class AccommodationResource {
	
	private AccommodationService accommodationService = new AccommodationService();
	
	@Context
	private SecurityContext securityContext;
	

    /**
     * Get accommodations from an owner
     *
     * @return List<Accommodation> List of owner's accommodations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Accommodation> getAccommodations(@PathParam("ownerId") int ownerId) {
    	// Authorization
    	if (!securityContext.isUserInRole("owner")) {
    		throw new WebApplicationException("Not authorized", 401);
    	}
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
    	// Authorization
    	if (!securityContext.isUserInRole("owner")) {
    		throw new WebApplicationException("Not authorized", 401);
    	}
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
    public Response addAccommodation(
    		@PathParam("ownerId") int ownerId, 
    		Accommodation accommodation, 
    		@Context UriInfo uriInfo) {
    	
    	// Authorization
    	if (!securityContext.isUserInRole("owner")) {
    		throw new WebApplicationException("Not authorized", 401);
    	}

    	Accommodation newAccommodation = accommodationService.addAccommodation(ownerId, accommodation);
    	
    	String linkUri = uriInfo.getBaseUriBuilder()
    						.path(AccommodationResource.class)
    						.resolveTemplate("ownerId", ownerId)
    						.path(Integer.toString(newAccommodation.getId()))
    						.build()
    						.toString();
    	newAccommodation.addLink(linkUri, "self");
    	
    	String newId = String.valueOf(newAccommodation.getId());
    	URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri)
    					.entity(newAccommodation)
    					.build();
    }
    
}
