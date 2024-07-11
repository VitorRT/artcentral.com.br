package br.com.artcentral.mvc.system.globalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.artcentral.mvc.system.exception.dto.RestException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestGlobalHandlerException {
	
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<RestException> entityNotFoundException(EntityNotFoundException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(returnRestExcpetion(status, e.getMessage()));
	}
	
	// generico
	@ExceptionHandler({Exception.class})
	public ResponseEntity<RestException> exception(Exception e) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(status).body(returnRestExcpetion(status, e.getMessage()));
	}
	
	private RestException returnRestExcpetion(HttpStatus status, String message) {
		return new RestException(true, message, status.value());
	}
}
