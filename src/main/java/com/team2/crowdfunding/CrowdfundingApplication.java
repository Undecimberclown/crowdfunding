package com.team2.crowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CrowdfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrowdfundingApplication.class, args);
	}

}
