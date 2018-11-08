package test.project.exceptions;

public class CategoryNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -7485253602719277861L;

	public CategoryNotFoundException(String message) {
		super(message);
	}
}
