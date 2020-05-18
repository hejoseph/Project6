package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dao.ICardDAO;
import dao.IConnectionDAO;
import dao.IUserDAO;
import model.Card;
import model.Connection;
import model.Transaction;
import model.User;
import model.UserDto;

@SpringBootTest
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
	
	@BeforeAll
	public void init() {
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
		Card card = new Card("abc");
		boolean added = cardService.addUserCard(userConnected, card);
		assertTrue(added);
		
		List<Card> cards = cardService.getUserCards(userConnected);
		assertEquals(1,cards.size());
		
		int nbCards = userConnected.getCards().size();
		assertEquals(1,nbCards);
	}
	
	@Test
	public void userRemoveCreditCard() {
		List<Card> cards = cardService.getUserCards(userConnected);
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
		
		boolean top = userService.topUpAccount(userConnected, amount);
		assertFalse(top);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
	}
	
	@Test
	public void userTopUpAccountWithCard() {
		Card card = new Card("abc");
		cardService.addUserCard(userConnected, card);
		
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		boolean top = userService.topUpAccount(userConnected, amount);
		assertTrue(top);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance+amount, newBalance);
		
	}
	
	@Test
	public void userTransferMoneyGreaterThanBalanceToContact() {
		double balance = userConnected.getBalance();
		double amount = balance + 50.0;
		
		List<Connection> contacts = userConnected.getContacts();
		Connection contact = contacts.get(0);
		User toUser = contact.getTo();
		boolean sent = userService.sendMoney(userConnected, toUser, amount);
		assertFalse(sent);
		
		double newBalance = userConnected.getBalance();
		assertEquals(balance, newBalance);
	}
	
	@Test
	public void userTransferMoneyToContact() {
		double balance = userConnected.getBalance();
		double amount = 50.0;
		
		
		List<Connection> contacts = userConnected.getContacts();
		Connection contact = contacts.get(0);
		User toUser = contact.getTo();
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

		List<Connection> contacts = userConnected.getContacts();
		Connection contact = contacts.get(0);
		User toUser = contact.getTo();
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
		
		
		List<Connection> contacts = userConnected.getContacts();
		Connection contact = contacts.get(0);
		User toUser = contact.getTo();
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
	
}
