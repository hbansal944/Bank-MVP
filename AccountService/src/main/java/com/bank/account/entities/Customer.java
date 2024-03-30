package com.bank.account.entities;

import java.util.Date;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customer_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private String accountNumber;

	private String firstName;

	private String lastName;
	
	private String email;

	private String phone;

	private String address;

	private Date createdAt;

}
