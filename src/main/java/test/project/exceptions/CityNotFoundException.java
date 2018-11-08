package test.project.exceptions;

public class CityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4789423185332039009L;

	public CityNotFoundException(String message) {
		super(message);
	}
	
}
