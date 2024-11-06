package com.example.test_task_agencyamazon2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class TestTaskAgencyamazon2Application {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskAgencyamazon2Application.class, args);
	}

}
