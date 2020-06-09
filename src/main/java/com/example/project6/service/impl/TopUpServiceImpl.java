package com.example.project6.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.CreditCard;
import com.example.project6.model.TopUp;
import com.example.project6.model.User;
import com.example.project6.service.ITopUpService;

@Service
@Transactional
public class TopUpServiceImpl implements ITopUpService{

	@Override
	public TopUp getLastTransaction(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createTransaction(User user, CreditCard card, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
