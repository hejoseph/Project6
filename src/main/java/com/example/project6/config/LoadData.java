package com.example.project6.config;

import com.example.project6.model.CreditCard;
import com.example.project6.model.User;
import com.example.project6.model.UserDto;
import com.example.project6.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.project6.dao.IUserDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Transactional
public class LoadData {
	private static final Logger logger = LogManager.getLogger("LoadData");

	@Autowired
	private IUserService userService;

	@Autowired
	private ICardService cardService;

	@Autowired
	private IBankAccountService bankAccountService;

	@Autowired
	private ITopUpService topUpService;

	@Autowired
	private IWithDrawService withDrawService;

	@Autowired
	private ITransferService transferService;

	@Bean
	CommandLineRunner initDataData() {
		return args -> {
			logger.info("loading data to db...");

			UserDto user = new UserDto("hello", "test", "test@test.com", "123");
			boolean created = userService.createUser(user);
			logger.info("create user1");

			UserDto user2 = new UserDto("hello", "test", "test2@test.com", "123");
			created = userService.createUser(user2);
			logger.info("create user2");


			User userConnected = userService.connect(user.getEmail(), user.getPassword());

			userService.addContact(userConnected.getEmail(), user2.getEmail());
			logger.info("user1 add user2");

			CreditCard card = new CreditCard("abc");
			boolean added = cardService.addUserCard(userConnected.getEmail(), card);
			logger.info("user1 add credit card");

			double amount = 50.0;
			boolean top = userService.topUpMoneyToAccount(userConnected.getEmail(), amount);
			logger.info("user1 top 50 to account");

			bankAccountService.addAccountForUser("abc","abc","abc",userConnected.getEmail());

			amount = 10.0;
			boolean draw = userService.withDrawMoneyFromAccount(userConnected.getEmail(), amount);
			logger.info("user1 withdraw 10 from account");

			userConnected = userService.connect(user.getEmail(), user.getPassword());
//			UserDto dto = userService.findUserByEmailDto(user.getEmail());
			amount = 10.0;
			List<User> contacts = userConnected.getContacts();
			if (contacts != null) {
				contacts.forEach(c->logger.info(""));
			}
			User toUser = contacts.get(0);
			userService.sendMoney(userConnected.getEmail(), toUser.getEmail(), amount);
			logger.info("user1 send 10 to first contact");

			logger.info("user records loaded");
		};
	}
}
