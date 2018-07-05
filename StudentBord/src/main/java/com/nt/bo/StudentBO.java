package com.nt.bo;

public class StudentBO {
	private String name ;
	private String rollNumber;
	private int Maths;
	private int English;
	private int Science;
	@Override
	public String toString() {
		return "StudentDTO [name=" + name + ", rollNumber=" + rollNumber + ", Maths=" + Maths + ", English=" + English
				+ ", Science=" + Science + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public int getMaths() {
		return Maths;
	}
	public void setMaths(int maths) {
		Maths = maths;
	}
	public int getEnglish() {
		return English;
	}
	public void setEnglish(int english) {
		English = english;
	}
	public int getScience() {
		return Science;
	}
	public void setScience(int science) {
		Science = science;
	}
	public StudentBO(String name, String rollNumber, int maths, int english, int science) {
		
		this.name = name;
		this.rollNumber = rollNumber;
		Maths = maths;
		English = english;
		Science = science;
	} 
	public StudentBO() {
		

	}
}
