package com.zilker.beans;

public class RevaluationData {
	private int revaluationId;
	private int resultId;
	private long studentRegistrationNumber;
	private String revaluationStatus;
	private String subjectName;
	private String updatedGrade;
	private String subjectCode;
	public int getRevaluationId() {
		return revaluationId;
	}
	public void setRevaluationId(int revaluationId) {
		this.revaluationId = revaluationId;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public String getRevaluationStatus() {
		return revaluationStatus;
	}
	public void setRevaluationStatus(String revaluationStatus) {
		this.revaluationStatus = revaluationStatus;
	}
	public String getUpdatedGrade() {
		return updatedGrade;
	}
	public void setUpdatedGrade(String updatedGrade) {
		this.updatedGrade = updatedGrade;
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
	public long getStudentRegistrationNumber() {
		return studentRegistrationNumber;
	}
	public void setStudentRegistrationNumber(long studentRegistrationNumber) {
		this.studentRegistrationNumber = studentRegistrationNumber;
	}
	
}
