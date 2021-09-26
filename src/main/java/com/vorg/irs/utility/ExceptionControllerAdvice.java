package com.vorg.irs.utility;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vorg.irs.exception.EmployeeException;

//@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	Environment environment;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception){
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setTimeStamp(LocalDate.now());
		errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		System.out.println("+++++++++++++++++++++++++++++++++++exception");
		
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorInfo> employeeExceptionHandler(EmployeeException exception){
		
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorInfo.setErrorMessage(exception.getMessage());
		errorInfo.setTimeStamp(LocalDate.now());
		System.out.println("++++++++++++++++++++++++++++++++++++INFexception");
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> validException(MethodArgumentNotValidException exception){
		
		ErrorInfo errorInfo=new ErrorInfo();
		
		errorInfo.setTimeStamp(LocalDate.now());
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		
		String error = exception.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
		errorInfo.setErrorMessage(error);
		
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.BAD_REQUEST);
		
	}

}
