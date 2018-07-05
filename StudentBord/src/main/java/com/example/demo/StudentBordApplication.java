package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import com.nt.controller.StudentController;
import com.nt.dao.StudentDAO;
import com.nt.dao.StudentDaoImpl;
import com.nt.service.StudentService;
import com.nt.service.StudentServiceImpl;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@Import(value={StudentController.class,StudentServiceImpl.class,StudentDaoImpl.class})
@ComponentScan(basePackages="com.nt.controller.StudentController,com.nt.service.StudentService,com.nt.dao.StudentDAO")

public class StudentBordApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentBordApplication.class, args);
	}
}
