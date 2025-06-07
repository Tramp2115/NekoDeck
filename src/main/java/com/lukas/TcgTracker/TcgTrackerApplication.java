package com.lukas.TcgTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TcgTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcgTrackerApplication.class, args);
	}

}
