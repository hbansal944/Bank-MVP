package com.bank.account.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account_Details")
public class Account {

	@Id
	@Column(name = "accountNumber")
	@NotBlank(message = "Please enter your account number")
	private String accountNumber;

	@Column(name = "balance")
	private BigDecimal balance;
	
	@Transient
	private Customer customer;
	
	
	
	
}
