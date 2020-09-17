package com.example.project6.service.impl;

import com.example.project6.dao.ITopUpDAO;
import com.example.project6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.CreditCard;
import com.example.project6.model.TopUp;
import com.example.project6.model.User;
import com.example.project6.service.ITopUpService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TopUpServiceImpl implements ITopUpService{

	@Autowired
	private ITopUpDAO topUpDAO;

	@Autowired
	private IUserService userService;

	@Override
	public TopUp getLastTransaction(String email) {
		User user = userService.findUserByEmail(email);
		if(user==null){
			return null;
		}
		List<TopUp> topUps = user.getTopUps();
		if(topUps.size()==0){
			return null;
		}
		return topUps.get(topUps.size()-1);
	}

	@Override
	public boolean createTransaction(User user, CreditCard card, double amount) {
		TopUp topUp = new TopUp(new Date(), amount, card, user);
		topUpDAO.save(topUp);
		user.getTopUps().add(topUp);
		return true;
	}

}
