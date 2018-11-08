package test.project.exceptions;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 491443646242689421L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
