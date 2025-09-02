package com.SpringExaminationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;
import com.SpringExaminationSystem.model.entity.user.AuthInfo;
import com.SpringExaminationSystem.repository.user.AuthInfoDao;
import com.SpringExaminationSystem.service.exam.StudentExamService;

@SpringBootApplication
public class SpringExaminationSystemApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SpringExaminationSystemApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(SpringExaminationSystemApplication.class, args);
		// AuthInfoDao authInfoDao = context.getBean(AuthInfoDao.class);
		// StudentExamService examService = context.getBean(StudentExamService.class);
		// AuthInfo authInfo = authInfoDao.findByUserId(1);
		// StudentExam studentExam = examService.createStudentExam(authInfo.getUserName(), 1);
		System.out.println("Application started successfully!");
		System.out.println("Access H2 console at: http://localhost:8081/h2-console");

	}

}