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
	
    /**
     * GET all owners 
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getOwners() {
    	OwnerService ownerService = new OwnerService();
    	List<Owner> owners = ownerService.getAllOwners();
		Gson gson = new Gson();
        return gson.toJson(owners);
    }
    
    
    @GET
    @Path("/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOwner(@PathParam("ownerId") int id) {
    	OwnerService ownerService = new OwnerService();
    	Owner owner = ownerService.getOwner(id);
    	Gson gson = new Gson();
    	return gson.toJson(owner);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Owner addOwner(Owner owner) {
    	return null; // TODO
    }
    
    
    @Path("/{ownerId}/accommodations")
    public AccommodationResource getAccommodationResource() {
    	return new AccommodationResource();
    }
    
}
