package com.team2.crowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//BaseTime 에 사용되는 jpa
@EnableJpaAuditing
@PropertySource("classpath:db.properties")
@SpringBootApplication
public class CrowdfundingApplication extends SpringBootServletInitializer {

	@Bean
	public BCryptPasswordEncoder encodePassword(){

		return new BCryptPasswordEncoder();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(CrowdfundingApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(CrowdfundingApplication.class, args);
	}

}
