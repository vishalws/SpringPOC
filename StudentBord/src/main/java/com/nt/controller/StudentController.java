package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.StudentDTO;
import com.nt.dto.StudentRMDTO;
import com.nt.service.StudentService;

@RestController
public class StudentController {
     @Autowired
	private StudentService service;
     @RequestMapping(value="getAlltudent.htm",  method = RequestMethod.GET)
     public @ResponseBody
     Object doGetAlltudent() {
    	 System.out.println("hi");
    	 
    	 StudentDTO dto=new StudentDTO();
    	List<StudentRMDTO> lrmdto= service.getAllStudent();
    	for(StudentRMDTO rmdto:lrmdto) {
    		
    	}
    	  System.out.println("controller"+  lrmdto);
		return  lrmdto;
     }
}
