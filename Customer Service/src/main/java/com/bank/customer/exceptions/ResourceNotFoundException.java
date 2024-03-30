package com.bank.customer.exceptions;

import lombok.NoArgsConstructor;

	@NoArgsConstructor
	public class ResourceNotFoundException extends RuntimeException {
	    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
	        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
	    }
	}

