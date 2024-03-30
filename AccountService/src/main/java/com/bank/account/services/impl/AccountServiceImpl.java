package com.bank.account.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.account.dto.AccountDto;
import com.bank.account.entities.Account;
import com.bank.account.entities.Customer;
import com.bank.account.exceptions.InsufficientBalanceException;
import com.bank.account.exceptions.ResourceNotFoundException;
import com.bank.account.external.CustomerClient;
import com.bank.account.repository.AccountRepository;
import com.bank.account.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerClient customerClient;

	@Override
	public String create() {
		Account account = new Account();
		long accountNumbers = ((long) Math.floor(Math.random() * 9000000000L));
		account.setAccountNumber(Long.toString(accountNumbers));
		account.setBalance(new BigDecimal("0.00"));
		accountRepository.save(account);
		return account.getAccountNumber();
	}

	@Override
	public AccountDto addMoney(String accountNumber, BigDecimal amount) {
		Account account = getAccountById(accountNumber);
		validateCustomerDetails(accountNumber);
		account.setBalance(account.getBalance().add(amount));
		accountRepository.save(account);
		AccountDto accountDto = new AccountDto();
		accountDto.setMessage(amount + "Rs. is successfully credited in your " + accountNumber);
		return accountDto;
	}

	@Override
	public AccountDto withdrawMoney(String accountNumber, BigDecimal amount) {
		Account account = getAccountById(accountNumber);
		validateCustomerDetails(accountNumber);
		if (account.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException();
		}
		account.setBalance(account.getBalance().subtract(amount));
		accountRepository.save(account);
		AccountDto accountDto = new AccountDto();
		accountDto.setMessage(amount + "Rs. is successfully debited from your " + accountNumber);
		return accountDto;
	}

	@Override
	public Account getAccountDetails(String accountNumber) {
		Account account = getAccountById(accountNumber);
		Customer customer = (customerClient.validateCustomer(accountNumber)).getBody();
		account.setCustomer(customer);
		return account;
	}

	@Override
	public void deleteAccount(String accountNumber) {
		accountRepository.deleteById(accountNumber);
	}

	private Account getAccountById(String accountNumber) {
		return accountRepository.findById(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Number", accountNumber));
	}

	private void validateCustomerDetails(String accountNumber) {
		customerClient.validateCustomer(accountNumber);
	}
}
