package com.bank.customer.services;

import java.util.List;

import com.bank.customer.entities.Customer;
import com.bank.customer.exceptions.CustomerAlreadyExistsException;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(String customerId);

	public Customer updateCustomer(String customerId,Customer customerDetails);

	public void deleteCustomer(String customerId) throws CustomerAlreadyExistsException;

}
