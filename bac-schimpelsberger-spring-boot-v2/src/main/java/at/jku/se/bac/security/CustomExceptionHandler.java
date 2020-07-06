package at.jku.se.bac.security;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class CustomExceptionHandler{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest request) {
		log.warn("handleMethodArgumentNotValid called");
		Map<String,String> errors =new HashMap<>();
	       ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .forEach(x -> errors.put(x.getField(), x.getDefaultMessage()));

	        return ResponseEntity.badRequest().body(errors);
    }
	
//	@ExceptionHandler(AccessDeniedException.class)
//    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex,WebRequest request) {
//		log.warn("handleAccessDenied called");
//       Map<String,String> errors =new HashMap<>();
//       errors.put("message", ex.getMessage());
//       return ResponseEntity.status(401).body(errors);
//    }
	

    
}