package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.google.gson.Gson;

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
    public String getOwners() {
    	List<Owner> owners = ownerService.getAllOwners();
		Gson gson = new Gson();
        return gson.toJson(owners);
    }
    
    
    @GET
    @Path("/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOwner(@PathParam("ownerId") int id) {
    	Owner owner = ownerService.getOwner(id);
    	Gson gson = new Gson();
    	if (owner == null) {
    		return APIHelpers.getErrorJson(404, "No owner with ID " + id);
    	}
    	return gson.toJson(owner);
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
