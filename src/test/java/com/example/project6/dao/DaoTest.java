package com.example.project6.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		
	}
}
