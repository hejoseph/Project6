package com.example.project6.service;

public interface IBankAccountService {
	void addAccountForUser(String iban, String bic, String swift, String email);
}
