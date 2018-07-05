package com.nt.bo;

public class StudentRMBO {

	private StudentBO bo;

	public StudentBO getBo() {
		return bo;
	}

	public void setBo(StudentBO bo) {
		this.bo = bo;
	}

	@Override
	public String toString() {
		return "StudentRMBO [bo=" + bo + "]";
	}
	
}
