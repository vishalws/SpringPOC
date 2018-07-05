package com.nt.dto;

import com.nt.bo.StudentBO;

public class StudentRMDTO {

	private StudentDTO dto;
	private StudentBO bo;

	public StudentBO getBo() {
		return bo;
	}

	public void setBo(StudentBO bo) {
		this.bo = bo;
	}

	public StudentDTO getDto() {
		return dto;
	}

	public void setDto(StudentDTO dto) {
		this.dto = dto;
	}

	@Override
	public String toString() {
		return "StudentRMDTO [dto=" + dto + ", bo=" + bo + "]";
	}
	
}
