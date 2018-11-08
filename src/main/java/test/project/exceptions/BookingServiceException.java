package test.project.exceptions;

public class BookingServiceException extends RuntimeException{

	private static final long serialVersionUID = 6001587304944031147L;

	public BookingServiceException(String message) {
		super(message);
	}
	
}
