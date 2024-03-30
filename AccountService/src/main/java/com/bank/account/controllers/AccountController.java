package com.bank.account.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.dto.AccountDto;
import com.bank.account.entities.Account;
import com.bank.account.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/create")
	public String create() {
		return accountService.create();
	}

	@PostMapping("/add-money/{accountNumber}")
	public ResponseEntity<AccountDto> addMoney(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.addMoney(accountNumber, amount));
	}

	@PostMapping("/withdraw-money/{accountNumber}")
	public ResponseEntity<AccountDto> withdrawMoney(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawMoney(accountNumber, amount));
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountDetails(@PathVariable String accountNumber) {
		return ResponseEntity.status(HttpStatus.OK).body( accountService.getAccountDetails(accountNumber));
	}

	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable String accountNumber) {
		accountService.deleteAccount(accountNumber);
	}
}
