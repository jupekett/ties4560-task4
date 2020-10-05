package fi.jupekett.task3.credentials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/loginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4674041973892581885L;
	private static final boolean LOGGING = true;
	
	
	/**
	 * Handles GET. 
	 */
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/login");
	}

	
	/**
	 * Handle POSTing login information.
	 */
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        CredentialService credentialService = new CredentialService();
        boolean credentialsMatch = credentialService.doCredentialsMatch(email, password, role);

        if (!credentialsMatch) {
        	// FIXME don't redirect or error? Never leaving the page would be best.
        	// TODO somehow send a message about failed login with the response.
        	response.sendRedirect(request.getContextPath() + "/login");
        } else {
        	// TODO SUCCESS! What then?
        	response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
