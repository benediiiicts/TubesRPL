package com.rpl.portal_ta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PortalTaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalTaApplication.class, args);
	}

}
