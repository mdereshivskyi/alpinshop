package test.project.exceptions;

public class BookingNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -7337339660334401235L;

	public BookingNotFoundException(String message) {
		super(message);
	}
	
}
