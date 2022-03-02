package com.dvivas.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication

public class CardRkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardRkApplication.class, args);
	}

}
