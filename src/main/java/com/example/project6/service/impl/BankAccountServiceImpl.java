package com.example.project6.service.impl;

import com.example.project6.dao.IBankAccountDAO;
import com.example.project6.model.User;
import com.example.project6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.BankAccount;
import com.example.project6.service.IBankAccountService;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService{

	@Autowired
	private IUserService userService;

	@Autowired
	private IBankAccountDAO bankAccountDAO;

	@Override
	public boolean addAccountForUser(String iban, String bic, String swift, String email) {
		User user = userService.findUserByEmail(email);
		if(user==null){
			return false;
		}
		BankAccount account = new BankAccount(iban, bic, swift, user);
		bankAccountDAO.save(account);
		user.setBankAccount(account);
		return true;
	}

	@Override
	public BankAccount getUserAccount(String email) {
		User user = userService.findUserByEmail(email);
		return user.getBankAccount();
	}

}
