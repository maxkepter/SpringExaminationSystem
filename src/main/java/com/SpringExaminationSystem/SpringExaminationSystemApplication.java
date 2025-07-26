package com.SpringExaminationSystem;

import org.springframework.context.ApplicationContext;

import com.SpringExaminationSystem.model.user.User;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringExaminationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExaminationSystemApplication.class, args);

		ApplicationContext context = SpringApplication.run(SpringExaminationSystemApplication.class, args);

		// UserRepo userRepo = context.getBean(UserRepo.class);

		// List<User> user = userRepo.findAll();

		System.out.println();

	}
}