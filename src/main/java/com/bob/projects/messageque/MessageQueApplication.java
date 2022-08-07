package com.bob.projects.messageque;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MessageQueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageQueApplication.class, args);
	}

}
