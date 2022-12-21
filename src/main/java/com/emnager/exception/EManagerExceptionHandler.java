package com.emnager.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EManagerExceptionHandler {

	private ErrorMessage errorMessage;

	@Autowired
	public EManagerExceptionHandler(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponseOutput> HandleException(BadCredentialsException exception) {

		ErrorResponseOutput error = new ErrorResponseOutput();
		error.setiError(ErrorCode.InavlidUsername);
		error.setsMessage(errorMessage.getErrorMessage(ErrorCode.InavlidUsername));
		error.setdTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponseOutput> HandleException(
			FileSizeLimitExceededException fileSizeLimitExceededException) {

		ErrorResponseOutput error = new ErrorResponseOutput();
		error.setiError(ErrorCode.NO_Error);
		error.setsMessage(errorMessage.getErrorMessage(ErrorCode.FileSizeExceeded));
		error.setdTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
