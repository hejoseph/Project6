package com.example.project6.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.dao.IUserDAO;
import com.example.project6.model.BankAccount;
import com.example.project6.model.CreditCard;
import com.example.project6.model.User;
import com.example.project6.model.UserDto;
import com.example.project6.service.IBankAccountService;
import com.example.project6.service.ICardService;
import com.example.project6.service.ITopUpService;
import com.example.project6.service.ITransferService;
import com.example.project6.service.IUserService;
import com.example.project6.service.IWithDrawService;
import com.example.project6.util.Util;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	private static final Logger logger = LogManager.getLogger("UserServiceImpl");
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private ICardService cardService;
	
	@Autowired
	private IBankAccountService bankAccountService;
	
	@Autowired
	private IWithDrawService withDrawService;
	
	@Autowired
	private ITopUpService topUpService;
	
	@Autowired
	private ITransferService transferService;
	
	private boolean userExists(User user) {
		User found = userDAO.findByEmail(user.getEmail());
		return found!=null;
	}
	
	@Override
	public boolean createUser(UserDto userDto) {
		User user = Util.copyObject(userDto, User.class);
		if(userExists(user)) {
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
		User connected = userDAO.findByEmail(connectedEmail);
		User friend = userDAO.findByEmail(email);
		if(connected==null || friend==null) {
			return false;
		}
		
		if(connected.getContacts().contains(friend)) {
			return false;
		}
		
		connected.getContacts().add(friend);
//		userDAO.save(connected);
		return true;
	}

	@Override
	public boolean topUpMoneyToAccount(String email, double amount) {
		User user = userDAO.findByEmail(email);
		CreditCard card = cardService.getUserCard(user.getEmail());
		if(user==null || card==null) {
			return false;
		}
		if(amount<=0) {
			logger.error("amount < 0");
			return false;
		}
		
		double balance = user.getBalance();
		user.setBalance(balance+amount);
		
		return topUpService.createTransaction(user, card, amount);
	}

	@Override
	public boolean withDrawMoneyFromAccount(String email, double amount) {
		User user = userDAO.findByEmail(email);
		BankAccount bankAccount = bankAccountService.getUserAccount(email);
		if(user==null||bankAccount==null) {
			return false;
		}
		
		if(amount<=0) {
			logger.error("amount < 0");
			return false;
		}
		
		double balance = user.getBalance();
		user.setBalance(balance-amount);
		
		return withDrawService.createTransaction(user, bankAccount, amount);
	}

	@Override
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount, String description) {
		User sender = userDAO.findByEmail(senderEmail);
		User receiver = userDAO.findByEmail(receiverEmail);
		
		if(sender==null | receiver==null) {
			return false;
		}

		if(amount<=0) {
			logger.error("amount<=0");
			return false;
		}
		
		return true;
		
	}

	@Override
	public User findUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
