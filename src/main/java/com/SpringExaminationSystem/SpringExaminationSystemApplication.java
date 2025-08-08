package com.SpringExaminationSystem;

import org.springframework.context.ApplicationContext;

import com.SpringExaminationSystem.model.entity.user.User;
import com.SpringExaminationSystem.repository.user.UserDao;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringExaminationSystemApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SpringExaminationSystemApplication.class, args);

		ApplicationContext context = SpringApplication.run(SpringExaminationSystemApplication.class, args);

		UserDao userDao = context.getBean(UserDao.class);

		List<User> user = userDao.findAll();
		System.out.println(user);

	}

}