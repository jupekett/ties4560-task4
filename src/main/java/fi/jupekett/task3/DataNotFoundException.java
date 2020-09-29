package fi.jupekett.task3;


/**
 * @author Oleksiy Khrienko
 */
public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7156528878215267547L;
	
	public DataNotFoundException(String message) {
		super(message);
	}


}
