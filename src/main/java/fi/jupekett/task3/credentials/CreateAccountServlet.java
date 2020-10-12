package fi.jupekett.task3.credentials;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createAccount/createAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7487550108402617633L;
	
	
	/**
	 * Handles GET.
	 */
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/createAccount");
	}
	
	
	/**
	 * Handle POSTing form information.
	 */
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        CredentialService credentialService = new CredentialService();
        boolean creationWasSuccessful = credentialService.addCredentials(email, password, role);
        
        if (creationWasSuccessful) {
        	response.sendRedirect(request.getContextPath() + "/accountCreationSuccessful");
        } else {
        	// FIXME don't redirect or error? Never leaving the page would be best.
        	// TODO somehow send a message about existing email with the response.
        	response.sendError(
        			500, 
        			"User with that email is already in the database!");
        }
    }
}
