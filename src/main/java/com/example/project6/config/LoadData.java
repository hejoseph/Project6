package com.example.project6.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.project6.dao.IUserDAO;

@Configuration
public class LoadData {
	private static final Logger logger = LogManager.getLogger("LoadData");
	@Bean
	CommandLineRunner initDataData(IUserDAO userDAO) {
		return args -> {
			logger.info("loading data to db...");
			
			
			
			logger.info("user records loaded");
		};
	}
}
