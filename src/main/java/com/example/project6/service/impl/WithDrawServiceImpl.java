package com.example.project6.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.BankAccount;
import com.example.project6.model.User;
import com.example.project6.model.WithDraw;
import com.example.project6.service.IWithDrawService;

@Service
@Transactional
public class WithDrawServiceImpl implements IWithDrawService{

	@Override
	public WithDraw getLastTransaction(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createTransaction(User user, BankAccount bankAccount, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
