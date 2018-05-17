package com.assign.telstra.Assignment.main;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assign.telstra.Assignment.aop.logging.MyLogger;

/**
 * @author saujanya.jaiswal
 *
 */


@SpringBootApplication(scanBasePackages = { "com.assign.telstra.Assignment" })
@EnableAutoConfiguration
public class AssignmentApplication {
	 private static final Logger LOGGER = LogManager.getLogger(MyLogger.class);
	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
		LOGGER.info("Spring boot application is started");
		
	}
}
