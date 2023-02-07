package sk.upjs.ics.votuj_rest.rest;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import sk.upjs.ics.votuj.storage.ObjectUndeletableException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleNoSuchElement(NoSuchElementException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleNullPointer(NullPointerException e) {
		return e.getMessage();
	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleNoSuchElement(MethodArgumentTypeMismatchException e) {
		return e.getMessage();
	}

	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public String handleNumberFormaException(NumberFormatException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(ObjectUndeletableException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public String handleObjectUndeletableException(ObjectUndeletableException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleObjectNotFoundException(ObjectNotFoundException e) {
		return e.getMessage();
	}
}
