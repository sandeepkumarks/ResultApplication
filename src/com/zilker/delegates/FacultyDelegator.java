package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.FacultySubjectData;
import com.zilker.beans.LoggedInUserData;
import com.zilker.beans.ResultData;
import com.zilker.beans.RevaluationData;
import com.zilker.beans.SubjectData;
import com.zilker.dao.*;

public class FacultyDelegator {
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	SubjectDetailsDao subjectDetailsDao = new SubjectDetailsDao();
	FacultySubjectDetailsDao facultySubjectDetailsDao = new FacultySubjectDetailsDao();

	public ArrayList<RevaluationData> checkRevaluationStatusBySubject(LoggedInUserData currentUser)
			throws SQLException {
		return revaluationDetailsDao.checkRevaluationStatusBySubject(currentUser);
	}

	public boolean changeStatusByFaculty(ArrayList<Integer>revaluationidList, String status) throws SQLException {
		return revaluationDetailsDao.changeStatusbyFaculty(revaluationidList, status);
	}

	public ArrayList<LinkedHashMap<String, String>> getResultByDept(int collegeCode, String dept, int semester)
			throws SQLException {
		return resultDetailsDao.getResultByDept(collegeCode, dept, semester);
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(long facultyRegistrationNumber, int flag)
			throws SQLException {
		return subjectDetailsDao.getSubjectDetailsById(facultyRegistrationNumber, flag);
	}

	public boolean insertFacultySubjectDetails(FacultySubjectData facultySubjectData) throws SQLException {
		return facultySubjectDetailsDao.insertFacultySubjectDetails(facultySubjectData);
	}

	public boolean deleteFacultySubjectDetails(int facultySubjectId) throws SQLException {
		return facultySubjectDetailsDao.deleteFacultySubjectDetails(facultySubjectId);
	}

	public ArrayList<FacultySubjectData> getAllFacultySubjectDetails(LoggedInUserData currentUser)
			throws SQLException {
		return facultySubjectDetailsDao.getAllFacultySubjectDetails(currentUser);
	}

	public ArrayList<ResultData> getResultById(long studentRegistrationNumber, int semester, int flag)
			throws SQLException {
		return resultDetailsDao.getResultById(studentRegistrationNumber, semester, flag);
	}
	
	public ArrayList<SubjectData> getAllSubjectDetailsNotEnrolled(LoggedInUserData currentUser) throws SQLException {
		return facultySubjectDetailsDao.getAllSubjectDetailsNotEnrolled(currentUser);
	}
	
	
	
	

}
