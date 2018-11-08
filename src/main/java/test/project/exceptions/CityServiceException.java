package test.project.exceptions;

public class CityServiceException extends RuntimeException{

	private static final long serialVersionUID = 1145713901345050282L;

	public CityServiceException(String message) {
		super(message);
	}
	
}
