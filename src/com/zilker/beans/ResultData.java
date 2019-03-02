package com.zilker.beans;

public class ResultData {
	private int resultId;
	private long studentRegistrationNumber;
	private String subjectCode;
	private String subjectName;
	private String grade;
	public int writtenIn;
	public long getStudentRegistrationNumber() {
		return studentRegistrationNumber;
	}
	public void setStudentRegistrationNumber(long studentRegistrationNumber) {
		this.studentRegistrationNumber = studentRegistrationNumber;
	}
	public String getSubjectCode() {
		return subjectCode; 
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getWrittenIn() {
		return writtenIn;
	}
	public void setWrittenIn(int writtenIn) {
		this.writtenIn = writtenIn;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	
}
