package com.enigma.agrox.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.enigma.agrox.dtos.ErrorResponseDto;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	/**
	 * Resource not found exception
	 * @param exception
	 * @param request
	 * @return Error Response
	 */
	
	// If not getting these custome errors, make sure exception.getCause().getMessage() is not throwing a null pointer exception
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponseDto handleResourceNotFoundException(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(exception.getMessage());
		if(exception.getCause() != null)
			error.setDetails(exception.getCause().getMessage());

		return error;
	}
	
	@ExceptionHandler(DatabaseException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponseDto handleDatabaseException(final DatabaseException exception,
			final HttpServletRequest request) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(exception.getMessage());

		if(exception.getCause() != null)
			error.setDetails(exception.getCause().getMessage());

		return error;
	}
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto ValidationException(final ValidationException exception,
			final HttpServletRequest request) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(exception.getMessage());

		if(exception.getCause() != null)
			error.setDetails(exception.getCause().getMessage());

		return error;
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto InvalidParameterException(final InvalidParameterException exception,
			final HttpServletRequest request) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(exception.getMessage());
		
		if(exception.getCause() != null)
			error.setDetails(exception.getCause().getMessage());

		return error;
	}
}
