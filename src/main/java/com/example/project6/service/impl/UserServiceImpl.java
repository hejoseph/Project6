package com.example.project6.service.impl;

import com.example.project6.model.*;
import com.example.project6.util.Constant;
import com.example.project6.util.CycleAvoidingMappingContext;
import com.example.project6.util.SimpleSourceDestinationMapper;
import org.apache.commons.lang.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.dao.IUserDAO;
import com.example.project6.service.IBankAccountService;
import com.example.project6.service.ICardService;
import com.example.project6.service.ITopUpService;
import com.example.project6.service.ITransferService;
import com.example.project6.service.IUserService;
import com.example.project6.service.IWithDrawService;
import com.example.project6.util.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

	@Autowired
	private SimpleSourceDestinationMapper mapper;


	private boolean userExists(User user) {
		User found = userDAO.findByEmail(user.getEmail());
		return found!=null;
	}
	
	@Override
	public boolean createUser(UserDto userDto) {
		User user = mapper.destinationToSource(userDto);
		if(userExists(user)) {
			return false;
		}
		String encrypted = encryptPassword(user.getPassword());
		user.setPassword(encrypted);
		userDAO.save(user);
		return true;
	}

	private String encryptPassword(String password) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			//Add password bytes to digest
			md.update(password.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}

	@Override
	public User connect(String email, String password) {
		password = encryptPassword(password);
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
//		if(commission+amount>balance){
//			logger.info("cannot have balance < 0");
//			return false;
//		}
		sender.setBalance(balance - (amount));
		receiver.setBalance(receiver.getBalance() + amount - commission);
		transferService.createTransaction(senderEmail, receiverEmail, amount, description);
		return true;

	}

	@Override
	public User findUserById(Long userId) {
		return userDAO.findOneById(userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userDAO.findByEmail(email);
		user.setContacts(user.getContacts());
		return user;
	}

	public UserDto findUserByEmailDto(String email) {
		User user = userDAO.findByEmail(email);
		UserDto user2 = mapper.sourceToDestination(user, new CycleAvoidingMappingContext());

		return user2;
	}

}
