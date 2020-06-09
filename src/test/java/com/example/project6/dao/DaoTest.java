package com.example.project6.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.CreditCard;
import com.example.project6.model.Transaction;
import com.example.project6.model.Transfer;
import com.example.project6.model.User;

@SpringBootTest
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DaoTest {
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private ICreditCardDAO cardDAO;
	
	@Autowired
	private ITransferDAO transferDAO;
	
	@Test
	public void testUserTransfer() {
		
		User user = new User("last", "first", "email", "pass");
		user = userDAO.save(user);
		assertNotNull(user.getId());
		
//		Transfer t = new Transfer();
		
//		assertNull(user.getTransactions());
//		assertNull(user.getId());
		
//		t = transactionDAO.save(t);
//		t2 = transactionDAO.save(t2);
		
//		List<Transaction> transactions = new ArrayList<Transaction>();
//		transactions.add(t);
//		transactions.add(t2);
//		user.setTransactions(transactions);
		
		
//		user = userDAO.findOneById(user.getId());
//		assertNotNull(user.getTransactions());
//		assertEquals(2,user.getTransactions().size());
//		
//		Transaction last = user.getTransactions().get(1);
//		assertEquals("co2",last.getConnection());
//		
//		userDAO.delete(user);
		
//		transactionDAO.findOneById(t.getI);
		
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
	
//	@Test
//	public void testUserConnection() {
//		User u1 = new User("u1", "u1", "email", "pass");
//		User u2 = new User("u2", "u2", "email", "pass");
//		
//		u1 = userDAO.save(u1);
//		u2 = userDAO.save(u2);
//		
//		Connection c = new Connection(u1,u2);
//		c = connectionDAO.save(c);
//		
////		assertNull(u1.getContacts());
////		assertNull(u2.getContacts());
//		
//		u1 = userDAO.findOneById(u1.getId());
////		assertNotNull(u1.getContacts());
////		User contact1 = u1.getContacts().get(0).getTo();
////		assertEquals("u2",contact1.getFirstName());
//		
//		
//		u2 = userDAO.findOneById(u2.getId());
////		assertNull(u2.getContacts());
//		
//	}
}
