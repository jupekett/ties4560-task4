package fi.jupekett.task3;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Oleksiy Khriyenko
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
	
	private static final String DOC_URL = Utilities.getDocumentationUrl();
	
	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, DOC_URL);
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}

}
