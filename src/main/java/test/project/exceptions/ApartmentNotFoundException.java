package test.project.exceptions;

public class ApartmentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -7648320730844129474L;

	public ApartmentNotFoundException(String message) {
		super(message);
	}
	
}
