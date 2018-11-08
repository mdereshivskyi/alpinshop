package test.project.exceptions;

public class CategoryServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 6541149834671015139L;

	public CategoryServiceException(String message) {
		super(message);
	}
}
