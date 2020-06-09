package com.example.project6.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.CreditCard;
import com.example.project6.service.ICardService;

@Service
@Transactional
public class CardServiceImpl implements ICardService{

	@Override
	public boolean addUserCard(String email, CreditCard card) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CreditCard getUserCard(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeCard(CreditCard card) {
		// TODO Auto-generated method stub
		return false;
	}

}
