package com.example.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.nt.controller.StudentController;
import com.nt.dao.StudentDAO;
import com.nt.service.StudentService;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StudentBordApplication.class);
	}
//	@Bean
//    public UrlBasedViewResolver urlBasedViewResolver()
//    {
//        UrlBasedViewResolver res = new InternalResourceViewResolver();
//        res.setViewClass(JstlView.class);
//        res.setPrefix("/WEB-INF/pages/");
//        res.setSuffix(".jsp");
//        return res;
//    }
}
