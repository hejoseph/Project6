package com.example.project6.service;

import java.util.List;

import com.example.project6.model.Card;
import com.example.project6.model.User;

public interface ICardService {

	boolean addUserCard(User userConnected, Card card);
	List<Card> getUserCards(User userConnected);
	boolean removeCard(Card card);

}
