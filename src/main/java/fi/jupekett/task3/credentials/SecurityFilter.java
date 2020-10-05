package fi.jupekett.task3.credentials;

import fi.jupekett.task3.ErrorMessage;
import fi.jupekett.task3.Owner;
import fi.jupekett.task3.OwnerService;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization"; 
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic "; 
	private static final String SECURED_URL_PREFIX= "owner"; 
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		CredentialService credentialService = new CredentialService();
		Credentials credentials = null;
		
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		
		if (authHeader != null && authHeader.size() > 0) {
			String authToken = authHeader.get(0);
			
			// Digest Access is in use -> redirect to correct servlet
			if (authToken.startsWith("Digest")) {
				UriInfo uriInfo = requestContext.getUriInfo();
				URI uri = uriInfo.getBaseUriBuilder()
								 .path(HttpDigestAuthServlet.class)
								 .build();
		    	Response digestAuthResponse = Response.status(305) // "use proxy"
		    										  .location(uri)
		    										  .build();
				requestContext.abortWith(digestAuthResponse);
				//requestContext.setRequestUri(uri);
				return;
				
			} else if (authToken.startsWith("Basic")) {
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = new String(Base64.getDecoder().decode(authToken));
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String email = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				Role role = credentialService.getRoleWithEmail(email);
				
				if (credentialService.doCredentialsMatch(email, password, role)) {
					credentials = credentialService.getCredentials(email);
					String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
					requestContext.setSecurityContext(new CustomSecurityContext(credentials, scheme));
				}
			}
		}
		
		if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))
				&& ((requestContext.getMethod().equals("GET"))
						|| requestContext.getMethod().equals("POST"))) {
			if (credentials != null) return;
			
			ErrorMessage errorMessage = new ErrorMessage("You are not authorized.", 401, "FIXME"); //FIX documentation url
			
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity(errorMessage)
					.build();
			requestContext.abortWith(unauthorizedStatus);
		}
	}
}
