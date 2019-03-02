package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.beans.LoggedInUserData;
import com.zilker.beans.RevaluationData;
import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;

public class RevaluationDetailsDao {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean applyRevaluation(ArrayList<Integer> resultIdList) throws SQLException {
		try {
			con = Config.getConnection();
			for (int resultId : resultIdList) {
				stmt = Config.conn.prepareStatement(SqlConstants.APPLY_REVAL);
				stmt.setInt(1, resultId);
				stmt.setString(2, "applied");
				int count = stmt.executeUpdate();
				if (count <= 0) {
					return false;
				}
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return true;
	}

	public int findRevaluationCount(LoggedInUserData currentUser) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.REVAL_COUNT);
			stmt.setLong(1, currentUser.getRegistrationNumber());
			stmt.setInt(2, (currentUser.getSemester()) - 1);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return 0;
	}

	public ArrayList<RevaluationData> checkRevaluationStatusById(LoggedInUserData currentUser) throws SQLException {
		ArrayList<RevaluationData> revaluationList = new ArrayList<RevaluationData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_REVAL_DETAILS_BY_STUDENT_ID);
			stmt.setLong(1, currentUser.getRegistrationNumber());
			stmt.setInt(2, currentUser.getSemester() - 1);
			rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					RevaluationData revaluation = new RevaluationData();
					revaluation.setRevaluationId(rs.getInt("revaluation_id"));
					revaluation.setRevaluationStatus(rs.getString("revaluation_status"));
					revaluation.setSubjectName(rs.getString("subject_name"));
					revaluation.setUpdatedGrade(rs.getString("updated_grade"));
					revaluation.setSubjectCode(rs.getString("subject_code"));
					revaluationList.add(revaluation);
				} while (rs.next());
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return revaluationList;
	}

	public ArrayList<RevaluationData> checkRevaluationStatusBySubject(LoggedInUserData currentUser)
			throws SQLException {
		ArrayList<RevaluationData> revaluationList = new ArrayList<RevaluationData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_REVAL_DETAILS_BY_SUBJECT);
			stmt.setInt(1, currentUser.getCollegeCode());
			stmt.setLong(2, currentUser.getRegistrationNumber());
			rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					RevaluationData revaluation = new RevaluationData();
					revaluation.setRevaluationId(rs.getInt("revaluation_id"));
					revaluation.setStudentRegistrationNumber(rs.getLong("student_registration_number"));
					revaluation.setSubjectCode(rs.getString("subject_code"));
					revaluation.setRevaluationStatus(rs.getString("revaluation_status"));
					revaluation.setUpdatedGrade(rs.getString("updated_grade"));
					revaluationList.add(revaluation);
				} while (rs.next());
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return revaluationList;
	}

	public boolean changeStatusbyFaculty(ArrayList<Integer> revaluationIdList, String status) throws SQLException {
		try {
			con = Config.getConnection();
			for (int revaluationId : revaluationIdList) {
				stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_STATUS_BY_FACULTY);
				stmt.setString(1, status);
				stmt.setInt(2, revaluationId);
				int count = stmt.executeUpdate();
				if (count <= 0) {
					return false;
				}
			}

		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return true;
	}

	public char getCurrentGrade(int revalId) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.CURRENT_GRADE);
			stmt.setInt(1, revalId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("grade").charAt(0);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return 'o';
	}

	public boolean updateGrade(int revalId, String grade, String status) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_GRADE);
			stmt.setString(1, grade);
			stmt.setString(2, status);
			stmt.setInt(3, revalId);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public ArrayList<RevaluationData> getApprovedRevaluationList() throws SQLException {
		ArrayList<RevaluationData> revaluationList = new ArrayList<RevaluationData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_APPROVED_REVAL_REQUEST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				RevaluationData revaluation = new RevaluationData();
				revaluation.setRevaluationId(rs.getInt("revaluation_id"));
				revaluation.setStudentRegistrationNumber(rs.getLong("student_registration_number"));
				revaluation.setSubjectCode(rs.getString("subject_code"));
				revaluation.setRevaluationStatus(rs.getString("revaluation_status"));
				revaluation.setUpdatedGrade(rs.getString("updated_grade"));
				revaluationList.add(revaluation);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return revaluationList;
	}
}