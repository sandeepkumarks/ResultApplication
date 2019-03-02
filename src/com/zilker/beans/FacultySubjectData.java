package com.zilker.beans;

public class FacultySubjectData {
	private long facultyRegistrationNumber;
	private int facultySubjectId;
	private String subjectCode;
	private String subjectName;
	public long getFacultyRegistrationNumber() {
		return facultyRegistrationNumber;
	}
	public void setFacultyRegistrationNumber(long facultyRegistrationNumber) {
		this.facultyRegistrationNumber = facultyRegistrationNumber;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getFacultySubjectId() {
		return facultySubjectId;
	}
	public void setFacultySubjectId(int facultySubjectId) {
		this.facultySubjectId = facultySubjectId;
	}
}
