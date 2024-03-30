package com.bank.account.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.account.entities.Customer;

@FeignClient(name = "CUSTOMER-SERVICE", url = "http://127.0.0.1:8084")
public interface CustomerClient {

	@GetMapping("/customers/{customerId}")
	ResponseEntity<Customer> validateCustomer(@PathVariable String customerId);
}
