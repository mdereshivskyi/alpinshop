package test.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import test.project.domain.response.ErrorMessage;

@ControllerAdvice
public class ServerExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleExceptions(Exception ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ApartmentServiceException.class)
	public ResponseEntity<ErrorMessage> handleApartmentExceptions(ApartmentServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ApartmentNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleApartmentNotFoundException(ApartmentNotFoundException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<ErrorMessage> hangleUserServiceException(UserServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
