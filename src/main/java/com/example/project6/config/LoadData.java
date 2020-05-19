package com.example.project6.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {
	private static final Logger logger = LogManager.getLogger("LoadData");
	
	
	
//	@Bean
//	CommandLineRunner initDataData(MedicalRepository medicalRepository) {
//		return args -> {
//			logger.info("loading data to db...");
//			ObjectMapper objectMapper = new ObjectMapper();
//			SafetyAlertJsonData jsonData = objectMapper.readValue(new File("data.json"), SafetyAlertJsonData.class);
//			jsonData.getMedicalrecords().forEach(record -> {
//				medicalRepository.save(record);
//			});
//			logger.info("medical records loaded");
//		};
//	}
}
