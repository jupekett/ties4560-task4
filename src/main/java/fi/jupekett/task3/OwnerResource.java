package fi.jupekett.task3;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

/**
 * Root resource (exposed at "owners" path)
 */
@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
public class OwnerResource {
	private OwnerService ownerService = new OwnerService();
	private String URI_STRING = "http://localhost:8080/task3/webapi/owners/";
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
     * POST an owner in JSON form
     * @param owner
     * @return Resource location in response body
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOwner(Owner owner) {
    	if (LOGGING) System.out.println("OwnerResource.java - addOwner - " + owner);
    	Owner newOwner = ownerService.addOwner(owner);
    	String location = URI_STRING + newOwner.getId(); 
    	return Response.status(Status.CREATED)
    					.header("Content-Type", MediaType.TEXT_PLAIN)
    					.entity(location)
    					.build();
    }
    
    
    /**
     * POST an owner with a name in plain text.
     * @param owner
     * @return Resource location in response body
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addOwner(String name) {
    	if (LOGGING) System.out.println("OwnerResource.java - addOwner - " + name);
    	Owner newOwner = new Owner(name);
    	Owner addedOwner = ownerService.addOwner(newOwner);
    	String location = URI_STRING + addedOwner.getId(); 
    	return Response.status(Status.CREATED)
    					.header("Content-Type", MediaType.TEXT_PLAIN)
    					.entity(location)
    					.build();
    }
    
    
    /**
     * Delegates accommodation handling to appropriate class.
     * @return
     */
    @Path("/{ownerId}/accommodations")
    public AccommodationResource getAccommodationResource() {
    	return new AccommodationResource();
    }
    
}
