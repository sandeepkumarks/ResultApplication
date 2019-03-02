package com.zilker.beans;

public class StudentData {
	private long studentRegistrationNumber;
	private String name;
	private String collegeCode;
	private String department;
	private int semester;
	public long getStudentRegistrationNumber() {
		return studentRegistrationNumber;
	}
	public void setStudentRegistrationNumber(long studentRegistrationNumber) {
		this.studentRegistrationNumber = studentRegistrationNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
}
