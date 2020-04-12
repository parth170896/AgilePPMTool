package io.agile.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler	{

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exc, WebRequest request){
		ProjectIdExceptionResponse exceptionResponse=new ProjectIdExceptionResponse(exc.getMessage());
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUsernameAlreadyExistException(UserAlreadyExistException exc, WebRequest request){
		UserAlreadyExistsResponse response=new UserAlreadyExistsResponse(exc.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
