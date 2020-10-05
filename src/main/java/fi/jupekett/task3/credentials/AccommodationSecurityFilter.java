package fi.jupekett.task3.credentials;

import fi.jupekett.task3.ErrorMessage;
import fi.jupekett.task3.Owner;
import fi.jupekett.task3.OwnerService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AccommodationSecurityFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization"; 
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic "; 
	private static final String SECURED_URL_PREFIX= "accommodations"; 
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))
				&& (requestContext.getMethod().equals("GET"))) {
			
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = new String(Base64.getDecoder().decode(authToken));
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String email = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				CredentialService credentialService = new CredentialService();
				Role role = credentialService.getRoleWithEmail(email);
				
				if (role == Role.OWNER) {
					OwnerService ownerService = new OwnerService();
					Owner owner = ownerService.getOwnerByEmail(email);
					String accommodationPath = "owners/" + owner.getId() + "/accommodations";
					if (requestContext.getUriInfo().getPath().contains(accommodationPath)) {
						// everything was fine
						return; 
					}
				}
				ErrorMessage errorMessage = new ErrorMessage("You are not authorized for this method.", 401, "FIXME"); //FIX documentation url
				
				Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
													.entity(errorMessage)
													.build();
				requestContext.abortWith(unauthorizedStatus);
			}
		}
	}
}
