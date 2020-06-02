package com.example.project6.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project6.model.CreditCard;
import com.example.project6.model.Connection;
import com.example.project6.model.Transaction;
import com.example.project6.model.User;

@SpringBootTest
@Transactional
public class DaoTest {
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IConnectionDAO connectionDAO;
	
	@Autowired
	private ICardDAO cardDAO;
	
	@Autowired
	private ITransactionDAO transactionDAO;
	
	@Test
	public void testUserTransaction() {
		
		
		User user = new User("last", "first", "email", "pass");
		Transaction t = new Transaction("co", "desc", "20.0", user);
		Transaction t2 = new Transaction("co2", "desc2", "20.0", user);
		
//		assertNull(user.getTransactions());
		assertNull(user.getId());
		
		user = userDAO.save(user);
		assertNotNull(user.getId());
		
		t = transactionDAO.save(t);
		t2 = transactionDAO.save(t2);
		
		user = userDAO.findOneById(user.getId());
		assertNotNull(user.getTransactions());
		assertEquals(2,user.getTransactions().size());
		
		Transaction last = user.getTransactions().get(1);
		assertEquals("co2",last.getConnection());
	}
	
	@Test
	public void testUserCard() {
		User user = new User("last", "first", "email", "pass");
		CreditCard c = new CreditCard("abc", user);
//		assertNull(user.getCards());
		
		user = userDAO.save(user);
		c = cardDAO.save(c);
		
//		assertNull(user.getCards());
		user = userDAO.findOneById(user.getId());
//		assertNotNull(user.getCards());
//		assertEquals(1,user.getCards().size());
		
		c = cardDAO.findOneById(c.getId());
		assertNotNull(c.getUser());
		
	}
	
	@Test
	public void testUserConnection() {
		User u1 = new User("u1", "u1", "email", "pass");
		User u2 = new User("u2", "u2", "email", "pass");
		
		u1 = userDAO.save(u1);
		u2 = userDAO.save(u2);
		
		Connection c = new Connection(u1,u2);
		c = connectionDAO.save(c);
		
//		assertNull(u1.getContacts());
//		assertNull(u2.getContacts());
		
		u1 = userDAO.findOneById(u1.getId());
//		assertNotNull(u1.getContacts());
//		User contact1 = u1.getContacts().get(0).getTo();
//		assertEquals("u2",contact1.getFirstName());
		
		
		u2 = userDAO.findOneById(u2.getId());
//		assertNull(u2.getContacts());
		
	}
}
