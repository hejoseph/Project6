package com.example.project6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project6.dao.IUserDAO;
import com.example.project6.model.User;
import com.example.project6.model.UserDto;
import com.example.project6.service.IUserService;
import com.example.project6.util.Util;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDAO userDAO;
	
	private boolean userExists(User user) {
		User found = userDAO.findByEmail(user.getEmail());
		return found!=null;
	}
	
	@Override
	public boolean createUser(UserDto userDto) {
		User user = Util.copyObject(userDto, User.class);
		if(!userExists(user)) {
			return false;
		}
		userDAO.save(user);
		return true;
	}

	@Override
	public User connect(String email, String password) {
		User user = userDAO.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public boolean addContact(String connectedEmail, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean topUpMoneyToAccount(String email, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withDrawMoneyFromAccount(String email, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount, String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
