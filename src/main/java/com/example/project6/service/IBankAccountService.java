package com.example.project6.service;

import com.example.project6.model.BankAccount;

public interface IBankAccountService {
	void addAccountForUser(String iban, String bic, String swift, String email);
	BankAccount getUserAccount(String email);
}
