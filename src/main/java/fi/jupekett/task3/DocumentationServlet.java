package fi.jupekett.task3;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * A servlet to redirect the user to documentation under web app root.
 * The only way I got documentation links working from ContainerRequestContexts.
 * @author Juho Kettunen jupekett
 */
@WebServlet("/webapi/documentation")
public class DocumentationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3289183387957116881L;


	/**
	 * Handles GET.
	 */
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/documentation");
	}
	
	
}
