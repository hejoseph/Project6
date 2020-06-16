package com.example.project6.service;

import com.example.project6.model.BankAccount;

public interface IBankAccountService {
	boolean addAccountForUser(String iban, String bic, String swift, String email);
	BankAccount getUserAccount(String email);
}
