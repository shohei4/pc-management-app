package com.example.pc_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PcManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcManagementAppApplication.class, args);
	}

}
