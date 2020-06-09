package com.example.project6.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project6.dao.IBankAccountDAO;
import com.example.project6.dao.ICreditCardDAO;
import com.example.project6.dao.IUserDAO;
import com.example.project6.model.BankAccount;
import com.example.project6.model.CreditCard;
import com.example.project6.model.TopUp;
import com.example.project6.model.Transaction;
import com.example.project6.model.Transfer;
import com.example.project6.model.User;
import com.example.project6.model.UserDto;
import com.example.project6.model.WithDraw;
import com.example.project6.util.Constant;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private ICardService cardService;
	@Autowired
	private ICreditCardDAO cardDAO;
	
	@Autowired
	private IBankAccountService bankAccountService;
	@Autowired
	private IBankAccountDAO bankAccountDAO;
	
	
	@Autowired
	private ITopUpService topUpService;
	
	@Autowired
	private IWithDrawService withDrawService;
	
	@Autowired
	private ITransferService transferService;
	
	
	private User userConnected;
	
	@BeforeAll
	public void init() {
		
		assertNotNull(userService);
		
		UserDto user = new UserDto("hello", "test", "test@test.com", "123");
		boolean created = userService.createUser(user);
		assertTrue(created);
		
		UserDto user2 = new UserDto("hello", "test", "test2@test.com", "123");
		created = userService.createUser(user2);
		assertTrue(created);
		
		userConnected = userService.connect(user.getEmail(), user.getPassword());
		
		userService.addContact(userConnected.getEmail(), user2.getEmail());
		
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
		User connected = userService.connect(user.getEmail(), user.getPassword());
		assertNotNull(connected);
	}
	
	@Test
	public void userAddNotExistingContact() {
		UserDto user = new UserDto("user1", "user1", "user1@b.com", "123");
		boolean created = userService.createUser(user);
		
		User connected = userService.connect(user.getEmail(), user.getPassword());
		boolean added = userService.addContact(connected.getEmail(), "notexisting@mail.com");
		assertFalse(added);
	}
	
	@Test
	public void userAddExistingContact() {
		UserDto user = new UserDto("user2", "user2", "user2@b.com", "123");
		boolean created = userService.createUser(user);
		
		UserDto friend = new UserDto("friend1", "friend1", "friend1@b.com", "123");
		boolean friendCreated = userService.createUser(friend);
		
		User connected = userService.connect(user.getEmail(), user.getPassword());
		boolean added = userService.addContact(connected.getEmail(), friend.getEmail());
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
		
		User connected = userService.connect(user.getEmail(), user.getPassword());
		boolean added = userService.addContact(connected.getEmail(), friend.getEmail());
		assertTrue(added);
		
		added = userService.addContact(connected.getEmail(), friend.getEmail());
		assertFalse(added);
		
		
		connected = userDAO.findByEmail(connected.getEmail());
		assertEquals(1,connected.getContacts().size());
		
		User contact = userDAO.findByEmail(friend.getEmail());
		assertEquals(1,contact.getContacts().size());
		
	}
	
	@Test
	public void userAddCreditCard() {
		CreditCard card = new CreditCard("abc");
		boolean added = cardService.addUserCard(userConnected.getEmail(), card);
		assertTrue(added);
		
		CreditCard c = cardService.getUserCard(userConnected.getEmail());
		assertNotNull(c);
	}
	
	@Test
	public void userRemoveCreditCard() {
		CreditCard card = cardService.getUserCard(userConnected.getEmail());
		assertNotNull(card);
		cardService.removeCard(card);
		card = cardService.getUserCard(userConnected.getEmail());
		assertNull(card);
	}
	
	
	@Test
	public void userTopUpAccountWithoutCard() {
		
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean top = userService.topUpMoneyToAccount(userConnected.getEmail(), amount);
		assertFalse(top);
		
		userService.findUserById(userConnected.getId());
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
	}
	
	@Test
	public void userTopUpAccountWithCard() {
		CreditCard card = new CreditCard("abc");
		cardService.addUserCard(userConnected.getEmail(), card);
		
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean top = userService.topUpMoneyToAccount(userConnected.getEmail(), amount);
		assertTrue(top);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance+amount, newBalance);
		
	}
	
	@Test
	public void userTopUpAccountWithCardTransactionIsCreated() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean top = userService.topUpMoneyToAccount(userConnected.getEmail(), amount);
		assertTrue(top);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance+amount, newBalance);
		
		TopUp topUp = topUpService.getLastTransaction(userConnected.getEmail());
		assertEquals(amount, topUp.getSum());
	}
	
	
	@Test
	public void userWithDrawMoneyWithoutBankAccount() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean draw = userService.withDrawMoneyFromAccount(userConnected.getEmail(), amount);
		assertFalse(draw);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
		
	}
	
	@Test
	public void userWithDrawMoneyWithBankAccount() {
		bankAccountService.addAccountForUser("","","",userConnected.getEmail());
		
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean draw = userService.withDrawMoneyFromAccount(userConnected.getEmail(), amount);
		assertFalse(draw);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
		
	}
	
	@Test
	public void userWithDrawMoneyWithBankAccountTransactionIsCreated() {
		bankAccountService.addAccountForUser("","","",userConnected.getEmail());
		
		double balance = userConnected.getBalance();
		double amount = 10.0;
		
		boolean draw = userService.withDrawMoneyFromAccount(userConnected.getEmail(), amount);
		assertFalse(draw);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
		
		WithDraw wd = withDrawService.getLastTransaction(userConnected.getEmail());
		assertEquals(amount, wd.getSum());
	}
	
	@Test
	public void userTransferMoneyGreaterThanBalanceToContact() {
		double balance = userConnected.getBalance();
		double amount = balance + 50.0;
		
		List<User> contacts = userConnected.getContacts();
		User toUser = contacts.get(0);
		boolean sent = userService.sendMoney(userConnected.getEmail(), toUser.getEmail(), amount);
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
		boolean sent = userService.sendMoney(userConnected.getEmail(), toUser.getEmail(), amount);
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
		boolean sent = userService.sendMoney(userConnected.getEmail(), toUser.getEmail(), amount);
		assertFalse(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
		
		double toUserNewBalance = toUser.getBalance();
		assertEquals(toUserBalance, toUserNewBalance);
		
	}
	
	@Test
	public void userTransferMoneyToContactTransactionIsCreated() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		List<User> contacts = userConnected.getContacts();
		User toUser = contacts.get(0);
		double toUserBalance = toUser.getBalance();
		boolean sent = userService.sendMoney(userConnected.getEmail(), toUser.getEmail(), amount, "for lunch");
		assertTrue(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance - amount, newBalance);
		
		double toUserNewBalance = toUser.getBalance();
		assertEquals(toUserBalance+amount, toUserNewBalance);
		
		Transfer transfer = transferService.getLastTransaction(userConnected.getEmail());
		assertEquals(amount, transfer.getSum());
	}
	
}
