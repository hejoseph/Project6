package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dao.IUserDAO;
import model.User;
import model.UserDto;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserDAO userDAO;
	
	@BeforeAll
	public void init() {
		
	}
	
	@Test
	public void createUserTest() {
		UserDto user = new UserDto("test", "test", "test@b.com", "123");
		boolean created = userService.createUser(user);
		assertTrue(created);
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
		boolean added = userService.addContact(connected, "notexisting@mail.com");
		assertFalse(added);
		
	}
	
	@Test
	public void userAddExistingContact() {
		UserDto user = new UserDto("user2", "user2", "user2@b.com", "123");
		boolean created = userService.createUser(user);
		
		UserDto friend = new UserDto("friend1", "friend1", "friend1@b.com", "123");
		boolean friendCreated = userService.createUser(friend);
		
		User connected = userService.connect(user);
		boolean added = userService.addContact(connected, friend.getEmail());
		assertTrue(added);
		
		
		connected = userDAO.findByEmail(connected.getEmail());
		assertEquals(1,connected.getContacts().size());
		
		User contact = userDAO.findByEmail(friend.getEmail());
		assertEquals(1,contact.getContacts().size());
		
	}
	
	@Test
	public void userTopUpAccountWithoutCard() {
		
	}
	
	@Test
	public void userTopUpAccount() {
		
	}
	
	@Test
	public void userTransferMoneyGreaterThanBalanceToContact() {
		
	}
	
	@Test
	public void userTransferMoneyToContact() {
		
	}
	
}
