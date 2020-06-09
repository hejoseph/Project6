package com.example.project6.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.BankAccount;
import com.example.project6.service.IBankAccountService;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService{

	@Override
	public void addAccountForUser(String iban, String bic, String swift, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BankAccount getUserAccount(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
