package com.zilker.beans;

public class FacultyData {
	private long facultyRegistrationNumber;
	private String name;
	private String collegeCode;
	private String department;
	public long getFacultyRegistrationNumber() {
		return facultyRegistrationNumber;
	}
	public void setFacultyRegistrationNumber(long facultyRegistrationNumber) {
		this.facultyRegistrationNumber = facultyRegistrationNumber;
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
}
