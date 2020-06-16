package com.example.project6.service.impl;

import com.example.project6.dao.IWithDrawDAO;
import com.example.project6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.BankAccount;
import com.example.project6.model.User;
import com.example.project6.model.WithDraw;
import com.example.project6.service.IWithDrawService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WithDrawServiceImpl implements IWithDrawService{

	@Autowired
	private IWithDrawDAO withDrawDAO;

	@Autowired
	private IUserService userService;

	@Override
	public WithDraw getLastTransaction(String email) {
		User user = userService.findUserByEmail(email);
		if(user==null){
			return null;
		}
		List<WithDraw> wds = user.getWithDraws();
		if(wds.size()==0){
			return null;
		}
		return wds.get(wds.size()-1);
	}

	@Override
	public boolean createTransaction(User user, BankAccount bankAccount, double amount) {
		WithDraw wd = new WithDraw(new Date(), amount, bankAccount, user);
		wd = withDrawDAO.save(wd);
		user.getWithDraws().add(wd);
		return true;
	}

}
