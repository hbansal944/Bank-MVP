package com.bank.customer.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.customer.entities.Customer;
import com.bank.customer.exceptions.CustomerAlreadyExistsException;
import com.bank.customer.exceptions.ResourceNotFoundException;
import com.bank.customer.external.services.AccountClient;
import com.bank.customer.repositories.CustomerRepository;
import com.bank.customer.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private AccountClient accountClient;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
	Customer customerExist =customerRepository.findByPhone(customer.getPhone());
	if(customerExist != null) {
		throw new CustomerAlreadyExistsException("This phone no. is already exist");
	}
		Date currentDate = new Date();
		String accountNumber = accountClient.create();
		customer.setAccountNumber(accountNumber);
		customer.setCreatedAt(currentDate);
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(String customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
	}

	@Override
	public Customer updateCustomer(String customerId, Customer customerDetails) {
		getCustomerById(customerDetails.getAccountNumber());
		return customerRepository.save(customerDetails);
	}

	@Override
	public void deleteCustomer(String accountNumber) throws CustomerAlreadyExistsException {
		 customerRepository.findById(accountNumber);
		accountClient.deleteAccountByCustomerId(accountNumber);
		customerRepository.deleteById(accountNumber);
	}
}
