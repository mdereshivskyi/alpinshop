package test.project.exceptions;

public class ApartmentServiceException extends RuntimeException{

	private static final long serialVersionUID = -3944267080270878737L;

	public ApartmentServiceException(String message) {
		super(message);
	}
}
