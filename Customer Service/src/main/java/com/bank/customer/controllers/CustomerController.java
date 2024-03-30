package com.bank.customer.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customer.entities.Customer;
import com.bank.customer.exceptions.CustomerAlreadyExistsException;
import com.bank.customer.payload.ApiResponse;
import com.bank.customer.services.CustomerService;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer) throws CustomerAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") @NotBlank(message = "Account ID cannot be blank")  String customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(customerId));
	}

	@PutMapping("/{customerId}")
	public Customer updateCustomer(@PathVariable("customerId") @NotBlank(message = "Account ID cannot be blank") String customerId, @RequestBody @Valid Customer customerDetails) {
		return customerService.updateCustomer(customerId, customerDetails);
	}

	@DeleteMapping("/{customerId}")
	public ApiResponse deleteCustomer(@PathVariable String customerId) throws CustomerAlreadyExistsException {
		customerService.deleteCustomer(customerId);
		 return new ApiResponse("Account is Successfully Deleted", true,HttpStatus.OK);
	}

}
