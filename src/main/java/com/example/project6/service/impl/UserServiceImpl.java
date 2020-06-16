package com.example.project6.service.impl;

import com.example.project6.util.Constant;
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

import java.util.List;

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

		List<User> contacts = user.getContacts();
		if (contacts != null) {
			contacts.forEach(c->logger.info(""));
		}

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
		
		if(connected.getContacts().contains(friend) || friend.getContacts().contains(connected)) {
			return false;
		}
		
		connected.getContacts().add(friend);
		friend.getContacts().add(connected);
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

		double balance = user.getBalance();
		if(amount<=0 || amount>balance) {
			logger.error("error amount");
			return false;
		}

		user.setBalance(balance-amount);
		return withDrawService.createTransaction(user, bankAccount, amount);
	}

	@Override
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount) {
		return sendMoney(senderEmail, receiverEmail, amount, "");
	}

	@Override
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount, String description) {
		User sender = userDAO.findByEmail(senderEmail);
		User receiver = userDAO.findByEmail(receiverEmail);
		
		if(sender==null | receiver==null) {
			return false;
		}

		double balance = sender.getBalance();
		if(amount>balance || amount <= 0){
			return false;
		}

		double commission = amount * Constant.TRANSFER_COMMISSION;
		if(commission+amount>balance){
			logger.info("cannot have balance < 0");
			return false;
		}
		sender.setBalance(balance - (amount+commission));
		receiver.setBalance(receiver.getBalance() + amount);
		transferService.createTransaction(senderEmail, receiverEmail, amount, description);
		return true;

	}

	@Override
	public User findUserById(Long userId) {
		return userDAO.findOneById(userId);
	}

	@Override
	public User findUserByEmail(String email) {
		return userDAO.findByEmail(email);
	}

}
