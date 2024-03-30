package com.bank.customer.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@Column(name = "accountNumber")
	private String accountNumber;

	@Column(name = "firstName")
	@NotBlank(message = "Please enter your first name")
	private String firstName;

	@Column(name = "lastName")
	@NotBlank(message = "Please enter your last name")
	private String lastName;

	@Column(name = "email")
	@Email(message = "Please enter your email")
	private String email;

	@Column(name = "phone")
	@Pattern(regexp = "^[0-9]{10}$", message = "Please enter a valid 10-digit mobile number")
	@Size(min = 10, max = 10, message = "Mobile number must be 10 digits long")
	private String phone;

	@Column(name = "address")
	@NotBlank(message = "Please enter your address")
	private String address;

	@Column(name = "createdDate")
	private Date createdAt;

}
