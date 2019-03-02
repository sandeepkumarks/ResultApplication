package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zilker.beans.*;
import com.zilker.dao.*;

public class AdminDelegator {
	LoginDetailsDao loginDetailsDao = new LoginDetailsDao();
	StudentDetailsDao studentDetailsDao = new StudentDetailsDao();
	FacultyDetailsDao facultyDetailsDao = new FacultyDetailsDao();
	SubjectDetailsDao subjectDetailsDao = new SubjectDetailsDao();
	CollegeDetailsDao collegeDetailsDao = new CollegeDetailsDao();
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();

	public boolean addStudentDetails(StudentData obj) throws SQLException {
		return studentDetailsDao.insertStudentDetails(obj);
	}

	public boolean registerStudent(StudentData obj) throws SQLException {
		return loginDetailsDao.registerStudent(obj);
	}

	public boolean addFacultyDetails(FacultyData obj) throws SQLException {
		return facultyDetailsDao.insertFacultyDetails(obj);
	}

	public boolean registerFaculty(FacultyData obj) throws SQLException {
		return loginDetailsDao.registerFaculty(obj);
	}

	public boolean addSubjectDetails(SubjectData obj) throws SQLException {
		return subjectDetailsDao.insertSubjectDetails(obj);
	}

	public boolean addCollegeDetails(CollegeData obj) throws SQLException {
		return collegeDetailsDao.insertCollegeDetails(obj);
	}

	public boolean addResults(ResultData obj) throws SQLException {
		return resultDetailsDao.insertResults(obj);
	}

	public boolean deleteStudentDetails(long studentRegistrationNumber) throws SQLException {
		return studentDetailsDao.deleteStudentDetails(studentRegistrationNumber);
	}

	public boolean deleteFacultyDetails(long facultyRegistrationNumber) throws SQLException {
		return facultyDetailsDao.deleteFacultyDetails(facultyRegistrationNumber);
	}

	public boolean deleteSubjectDetails(String subjectCode) throws SQLException {
		return subjectDetailsDao.deleteSubjectDetails(subjectCode);
	}

	public boolean deleteCollegeDetails(int collegeCode) throws SQLException {
		return collegeDetailsDao.deleteCollegeDetails(collegeCode);
	}

	public boolean updateStudentDetails(long currentRegistrationNumber, StudentData obj) throws SQLException {
		return (studentDetailsDao.updateStudentDetails(currentRegistrationNumber, obj));
	}

	public boolean updateFacultyDetails(long currentRegistrationNumber, FacultyData obj) throws SQLException {
		return (facultyDetailsDao.updateFacultyDetails(currentRegistrationNumber, obj));
	}

	public boolean updateCollegeDetails(int currentCollegeCode, CollegeData obj) throws SQLException {
		return (collegeDetailsDao.updateCollegeDetails(currentCollegeCode, obj));
	}

	public boolean updateSubjectDetails(String currentSubjectCode, SubjectData obj) throws SQLException {
		return (subjectDetailsDao.updateSubjectDetails(currentSubjectCode, obj));
	}

	public ArrayList<RevaluationData> getApprovedRevaluationList() throws SQLException {
		return revaluationDetailsDao.getApprovedRevaluationList();
	}

	public boolean updateGrade(Map<String, String> updateList) throws SQLException {
		char currentGrade;
		for (Map.Entry<String, String> data : updateList.entrySet()){
			String status = "No Change";
			char grade= data.getValue().charAt(0);
			currentGrade = revaluationDetailsDao.getCurrentGrade(Integer.parseInt(data.getKey()));
			if (currentGrade == 'o') {
				return false;
			} else if ((int) grade < (int) currentGrade || grade=='S') {
				status = "Change";
			} else {
				grade = currentGrade;
			}
			if (!revaluationDetailsDao.updateGrade(Integer.parseInt(data.getKey()), Character.toString(grade), status)) {
				return false;
			}
		}
		return true;
	}

	public boolean isSubjectNamePresent(SubjectData subject) throws SQLException {
		return subjectDetailsDao.isSubjectNamePresent(subject);
	}

	public boolean isSubjectCodePresent(SubjectData subject) throws SQLException {
		return subjectDetailsDao.isSubjectCodePresent(subject);
	}

	public boolean isCollegeNamePresent(CollegeData college) throws SQLException {
		return collegeDetailsDao.isCollegeNamePresent(college);
	}

	public boolean isCollegeCodePresent(CollegeData college) throws SQLException {
		return collegeDetailsDao.isCollegeCodePresent(college);
	}

	public ArrayList<StudentData> getAllStudentDetails() throws SQLException {
		return studentDetailsDao.getAllStudentDetails();
	}

	public ArrayList<FacultyData> getAllFacultyDetails() throws SQLException {
		return facultyDetailsDao.getAllFacultyDetails();
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(int regno, int flag) throws SQLException {
		return subjectDetailsDao.getSubjectDetailsById(regno, flag);
	}

	public ArrayList<SubjectData> getAllSubjectDetails() throws SQLException {
		return subjectDetailsDao.getAllSubjectDetails();
	}

	public ArrayList<CollegeData> getAllCollegeDetails() throws SQLException {
		return collegeDetailsDao.getAllCollegeDetails();
	}

	public ArrayList<ResultData> getAllResultDetails() throws SQLException {
		return resultDetailsDao.getAllResultDetails();
	}

	public long getLastStudentRegistrationNumber(int collegeCode) throws SQLException {
		return studentDetailsDao.getLastStudentRegistrationNumber(collegeCode);
	}

	public long getLastFacultyRegistrationNumber(int collegeCode) throws SQLException {
		return facultyDetailsDao.getLastFacultyRegistrationNumber(collegeCode);
	}
}
