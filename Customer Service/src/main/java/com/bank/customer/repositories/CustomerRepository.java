package com.bank.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.customer.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>
{
	public Customer findByPhone(String phone);
}
