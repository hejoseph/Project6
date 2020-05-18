package service;

import java.util.List;

import model.Card;
import model.User;

public interface ICardService {

	boolean addUserCard(User userConnected, Card card);
	List<Card> getUserCards(User userConnected);
	boolean removeCard(Card card);

}
