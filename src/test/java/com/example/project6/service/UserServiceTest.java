package com.example.project6.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project6.dao.ICardDAO;
import com.example.project6.dao.IConnectionDAO;
import com.example.project6.dao.IUserDAO;
import com.example.project6.model.CreditCard;
import com.example.project6.model.Connection;
import com.example.project6.model.Transaction;
import com.example.project6.model.User;
import com.example.project6.model.UserDto;
import com.example.project6.util.Constant;

public class UserServiceTest {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IConnectionService connectionService;
	
	@Autowired
	private ICardService cardService;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IConnectionDAO connectionDAO;
	
	@Autowired
	private ICardDAO cardDAO;
	
	private User userConnected;
	private User adminConnected;
	
	@BeforeAll
	public void init() {
		
		UserDto admin = new UserDto("admin", "admin", "admin@admin.com", "123");
		userService.createUser(admin);
		adminConnected = userService.connect(admin);
		
		UserDto user = new UserDto("hello", "test", "test@test.com", "123");
		boolean created = userService.createUser(user);
		assertTrue(created);
		
		UserDto user2 = new UserDto("hello", "test", "test2@test.com", "123");
		created = userService.createUser(user2);
		assertTrue(created);
		
		userConnected = userService.connect(user);
		
		connectionService.addContact(userConnected, user2.getEmail());
		
	}
	
	@Test
	public void createExistingUserTest() {
		UserDto user = new UserDto("test", "test", "testExisting@b.com", "123");
		boolean created = userService.createUser(user);
		assertTrue(created);
		created = userService.createUser(user);
		assertFalse(created);
	}
	
	@Test
	public void createUserThenConnectTest() {
		UserDto user = new UserDto("test2", "test2", "test2@b.com", "123");
		boolean created = userService.createUser(user);
		assertTrue(created);
		User connected = userService.connect(user);
		assertNotNull(connected);
	}
	
	@Test
	public void userAddNotExistingContact() {
		UserDto user = new UserDto("user1", "user1", "user1@b.com", "123");
		boolean created = userService.createUser(user);
		
		User connected = userService.connect(user);
		boolean added = connectionService.addContact(connected, "notexisting@mail.com");
		assertFalse(added);
		
	}
	
	@Test
	public void userAddExistingContact() {
		UserDto user = new UserDto("user2", "user2", "user2@b.com", "123");
		boolean created = userService.createUser(user);
		
		UserDto friend = new UserDto("friend1", "friend1", "friend1@b.com", "123");
		boolean friendCreated = userService.createUser(friend);
		
		User connected = userService.connect(user);
		boolean added = connectionService.addContact(connected, friend.getEmail());
		assertTrue(added);
		
		
		connected = userDAO.findByEmail(connected.getEmail());
		assertEquals(1,connected.getContacts().size());
		
		User contact = userDAO.findByEmail(friend.getEmail());
		assertEquals(1,contact.getContacts().size());
		
	}
	
	@Test
	public void userAddSameContact() {
		UserDto user = new UserDto("user3", "user3", "user3@b.com", "123");
		boolean created = userService.createUser(user);
		
		UserDto friend = new UserDto("friend3", "friend3", "friend3@b.com", "123");
		boolean friendCreated = userService.createUser(friend);
		
		User connected = userService.connect(user);
		boolean added = connectionService.addContact(connected, friend.getEmail());
		assertTrue(added);
		
		added = connectionService.addContact(connected, friend.getEmail());
		assertFalse(added);
		
		
		connected = userDAO.findByEmail(connected.getEmail());
		assertEquals(1,connected.getContacts().size());
		
		User contact = userDAO.findByEmail(friend.getEmail());
		assertEquals(1,contact.getContacts().size());
		
	}
	
	@Test
	public void userAddCreditCard() {
		CreditCard card = new CreditCard("abc");
		boolean added = cardService.addUserCard(userConnected, card);
		assertTrue(added);
		
		List<CreditCard> cards = cardService.getUserCards(userConnected);
		assertEquals(1,cards.size());
		
		int nbCards = userConnected.getCards().size();
		assertEquals(1,nbCards);
	}
	
	@Test
	public void userRemoveCreditCard() {
		List<CreditCard> cards = cardService.getUserCards(userConnected);
		int before = cards.size();
		
		cardService.removeCard(cards.get(0));
		
		cards = cardService.getUserCards(userConnected);
		int after = cards.size();
		assertEquals(after,before-1);
		
		int nbCards = userConnected.getCards().size();
		assertEquals(0,nbCards);
	}
	
	
	@Test
	public void userTopUpAccountWithoutCard() {
		
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean top = userService.topUpMoneyToAccount(userConnected, amount);
		assertFalse(top);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
	}
	
	@Test
	public void userTopUpAccountWithCard() {
		CreditCard card = new CreditCard("abc");
		cardService.addUserCard(userConnected, card);
		
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean top = userService.topUpMoneyToAccount(userConnected, amount);
		assertTrue(top);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance+amount, newBalance);
		
		
		User admin = userService.findUserById(adminConnected);
		List<Transaction> transactions = admin.getTransactions();
		Transaction last = transactions.get(transactions.size()-1);
		assertEquals(Constant.TOPUP+ " "+userConnected.getEmail(),last.getDescription());
	}
	
	@Test
	public void userWithDrawMoney() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean draw = userService.withDrawMoneyFromAccount(userConnected, amount);
		assertTrue(draw);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance-amount, newBalance);
		
		User admin = userService.findUserById(adminConnected);
		List<Transaction> transactions = admin.getTransactions();
		Transaction last = transactions.get(transactions.size()-1);
		assertEquals(Constant.WITHDRAW+ " "+userConnected.getEmail(),last.getDescription());
	}
	
	@Test
	public void userTransferMoneyGreaterThanBalanceToContact() {
		double balance = userConnected.getBalance();
		double amount = balance + 50.0;
		
		List<User> contacts = userConnected.getContacts();
		User toUser = contacts.get(0);
		boolean sent = userService.sendMoney(userConnected, toUser, amount);
		assertFalse(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
	}
	
	@Test
	public void userTransferMoneyToContact() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		
		List<User> contacts = userConnected.getContacts();
		User toUser = contacts.get(0);
		double toUserBalance = toUser.getBalance();
		boolean sent = userService.sendMoney(userConnected, toUser, amount);
		assertTrue(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance - amount, newBalance);
		
		double toUserNewBalance = toUser.getBalance();
		assertEquals(toUserBalance+amount, toUserNewBalance);
		
	}
	
	@Test
	public void userTransferNegativeMoneyToContact() {
		double balance = userConnected.getBalance();
		double amount = -50.0;

		List<User> contacts = userConnected.getContacts();
		User toUser = contacts.get(0);
		double toUserBalance = toUser.getBalance();
		boolean sent = userService.sendMoney(userConnected, toUser, amount);
		assertFalse(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
		
		double toUserNewBalance = toUser.getBalance();
		assertEquals(toUserBalance, toUserNewBalance);
		
	}
	
	@Test
	public void userTransferMoneyToContactCheckTransactionList() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		
		List<User> contacts = userConnected.getContacts();
		User toUser = contacts.get(0);
		double toUserBalance = toUser.getBalance();
		boolean sent = userService.sendMoney(userConnected, toUser, amount, "for lunch");
		assertTrue(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance - amount, newBalance);
		
		double toUserNewBalance = toUser.getBalance();
		assertEquals(toUserBalance+amount, toUserNewBalance);
		
		List<Transaction> transactions = userConnected.getTransactions();
		assertEquals(1, transactions.size());
		
	}
	
	@Test
	public void addMoneyGenerateTransaction() {
		userService.generateTransaction(adminConnected, "connection","description", 20.0);
		User admin = userService.findUserById(adminConnected);
		List<Transaction> transactions = admin.getTransactions();
		Transaction last = transactions.get(transactions.size()-1);
		assertEquals("connection",last.getConnection());
		assertEquals("description",last.getDescription());
		assertEquals("+20.0€",last.getAmount());
	}
	
	@Test
	public void substractMoneyGenerateTransaction() {
		userService.generateTransaction(adminConnected, "connection","description", -20.0);
		User admin = userService.findUserById(adminConnected);
		List<Transaction> transactions = admin.getTransactions();
		Transaction last = transactions.get(transactions.size()-1);
		assertEquals("connection",last.getConnection());
		assertEquals("description",last.getDescription());
		assertEquals("-20.0€",last.getAmount());
	}
	
}
