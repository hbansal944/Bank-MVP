package com.bank.account.services;

import java.math.BigDecimal;

import com.bank.account.dto.AccountDto;
import com.bank.account.entities.Account;

public interface AccountService {

	public String create();

	public AccountDto addMoney(String accountNumber, BigDecimal amount);

	public AccountDto withdrawMoney(String accountNumber, BigDecimal amount);

	public Account getAccountDetails(String accountNumber);

	public void deleteAccount(String accountNumber);

}
