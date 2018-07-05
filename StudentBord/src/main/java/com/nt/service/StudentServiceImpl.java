package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bo.StudentRMBO;
import com.nt.dao.StudentDAO;
import com.nt.dto.StudentRMDTO;

@Service
public class StudentServiceImpl implements StudentService {
     @Autowired
	private StudentDAO dao;
      @Override
      public List<StudentRMDTO>  getAllStudent() {
   	  List<StudentRMDTO> ldto=new ArrayList<StudentRMDTO>();
    	 List<StudentRMBO> lrmbo=dao.getAllStudent();
    	 for(StudentRMBO rmbo: lrmbo ) {
   		 StudentRMDTO dto=new StudentRMDTO();
    		 
    		 dto.setBo(rmbo.getBo());
    		 ldto.add(dto);
    	 }
    	 System.out.println("service "+ ldto);
    	    /*dto.setBo( lrmbo.getBo());*/
    	  
		return ldto;
    	  
      }
	
}
