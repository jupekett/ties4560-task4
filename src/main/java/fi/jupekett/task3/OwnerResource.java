package fi.jupekett.task3;

import java.net.URI;
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
     * Handle getting all owners. 
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Owner> getOwners() {
    	List<Owner> owners = ownerService.getAllOwners();
        return owners;
    }
    
    
    /**
     * Handle getting a certain owner based on ID.
     * @param id
     * @return
     */
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
     * Handle POST with an owner in request body as JSON
     * @param owner
     * @return Resource location in response body.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOwner(Owner owner, @Context UriInfo uriInfo) {
    	if (LOGGING) System.out.println("OwnerResource.java - addOwner - "+owner);
    	
    	Owner newOwner = ownerService.addOwner(owner);
    	String selfUri = uriInfo.getBaseUriBuilder()
    					.path(OwnerResource.class)
    					.path(Integer.toString(newOwner.getId()))
    					.build()
    					.toString();
    	newOwner.addLink(selfUri,  "self");
    	
    	String accommUri = uriInfo.getBaseUriBuilder()
    						.path(AccommodationResource.class)
    						.resolveTemplate("ownerId", newOwner.getId())
    						.build()
    						.toString();
		newOwner.addLink(accommUri, "accommodations");
    	
    	String newId = String.valueOf(newOwner.getId());
    	URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri)
    					.entity(newOwner)
    					.build();
    }
    

//    /**
//     * Delegates accommodation handling to appropriate class.
//     * @return
//     */
//    @Path("/{ownerId}/accommodations")
//    public AccommodationResource getAccommodationResource() {
//    	return new AccommodationResource();
//    }
    
}
