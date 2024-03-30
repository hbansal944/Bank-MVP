package com.bank.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bank.customer.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
    
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handlerCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(false).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler({  MethodArgumentNotValidException.class }) 
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse> handleCustomBadRequestException(MethodArgumentNotValidException ex) {
    	String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(false).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
