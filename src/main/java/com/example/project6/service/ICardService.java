package com.example.project6.service;

import com.example.project6.model.CreditCard;

public interface ICardService {
	boolean addUserCard(String email, CreditCard card);
	CreditCard getUserCard(String email);
	boolean removeCardFromUser(String email);

}
