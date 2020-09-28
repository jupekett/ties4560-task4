package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Root resource (exposed at "owners" path)
 */
@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
public class OwnerResource {
	private OwnerService ownerService = new OwnerService();
	private static final boolean LOGGING = true;
	
    /**
     * GET all owners 
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Owner> getOwners() {
    	List<Owner> owners = ownerService.getAllOwners();
        return owners;
    }
    
    
    @GET
    @Path("/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Owner getOwner(@PathParam("ownerId") int id) {
    	Owner owner = ownerService.getOwner(id);
    	if (owner == null) {
    		// TODO what? 
    		//return APIHelpers.getErrorJson(404, "No owner with ID " + id);
    	}
    	return owner;
    }
    
    
    /**
     * POST an owner
     * @param owner
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Owner addOwner(Owner owner) {
    	if (LOGGING) System.out.println("OwnerResource.java - addOwner - " + owner);
//    	TODO error handling
    	return ownerService.addOwner(owner);
    }
    
    
    @Path("/{ownerId}/accommodations")
    public AccommodationResource getAccommodationResource() {
    	return new AccommodationResource();
    }
    
}
