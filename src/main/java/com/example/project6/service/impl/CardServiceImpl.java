package com.example.project6.service.impl;

import com.example.project6.dao.ICreditCardDAO;
import com.example.project6.model.User;
import com.example.project6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.CreditCard;
import com.example.project6.service.ICardService;

@Service
@Transactional
public class CardServiceImpl implements ICardService{

	@Autowired
	private ICreditCardDAO cardDAO;

	@Autowired
	private IUserService userService;

	@Override
	public boolean addUserCard(String email, CreditCard card) {
		User user = userService.findUserByEmail(email);
		if(user==null || card==null){
			return false;
		}
		card.setUser(user);
		user.setCard(card);
		cardDAO.save(card);
		return true;
	}

	@Override
	public CreditCard getUserCard(String email) {
		User user = userService.findUserByEmail(email);
		return user.getCard();
	}

	@Override
	public boolean removeCardFromUser(String email) {
		CreditCard card = getUserCard(email);
		if(card==null){
			return false;
		}
		card.getUser().setCard(null);
		cardDAO.delete(card);
		return true;
	}

}
