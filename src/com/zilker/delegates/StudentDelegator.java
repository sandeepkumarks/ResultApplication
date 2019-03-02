package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.beans.LoggedInUserData;
import com.zilker.beans.ResultData;
import com.zilker.beans.RevaluationData;
import com.zilker.dao.*;

public class StudentDelegator {
	ResultDetailsDao resultDetailsDao = new ResultDetailsDao();
	RevaluationDetailsDao revaluationDetailsDao = new RevaluationDetailsDao();

	public ArrayList<ResultData> getResultById(long studentRegistrationNumber, int semester,
			int flag) throws SQLException {
		return resultDetailsDao.getResultById(studentRegistrationNumber, semester, flag);
	}
	
	public int findRevaluationCount(LoggedInUserData currentUser) throws SQLException {
		return revaluationDetailsDao.findRevaluationCount(currentUser);
	}

	public boolean applyRevaluation(ArrayList<Integer> resultIdList, LoggedInUserData currentUser) throws SQLException {
		return revaluationDetailsDao.applyRevaluation(resultIdList);

	}

	public ArrayList<RevaluationData> checkRevaluationStatusById(LoggedInUserData currentUser) throws SQLException {
		return revaluationDetailsDao.checkRevaluationStatusById(currentUser);
	}
	
	public ArrayList<ResultData> getResultsBySemester(long studentregistrationNumber, int semester) throws SQLException {
		return resultDetailsDao.getResultsBySemester(studentregistrationNumber, semester); 
	}
}
