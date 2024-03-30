package com.bank.customer.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ACCOUNT-SERVICE", url = "http://127.0.0.1:8084")
public interface AccountClient {
	
	@PostMapping("/accounts/create")
	public String create();
	
    @DeleteMapping("/accounts/{customerId}")
    void deleteAccountByCustomerId(@PathVariable String customerId);


}
