package com.example.project6.service;

import java.util.List;

import com.example.project6.model.CreditCard;
import com.example.project6.model.User;

public interface ICardService {

	boolean addUserCard(User userConnected, CreditCard card);
	List<CreditCard> getUserCards(User userConnected);
	boolean removeCard(CreditCard card);

}
