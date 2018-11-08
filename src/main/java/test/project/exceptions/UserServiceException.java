package test.project.exceptions;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = 6602087351702188949L;

	public UserServiceException(String message) {
		super(message);
	}
	
}
