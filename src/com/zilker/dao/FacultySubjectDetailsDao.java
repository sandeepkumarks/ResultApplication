package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.beans.FacultySubjectData;
import com.zilker.beans.LoggedInUserData;
import com.zilker.beans.SubjectData;
import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;

public class FacultySubjectDetailsDao {
	
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;
	
	public boolean insertFacultySubjectDetails(FacultySubjectData facultySubjectData)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_FACULTY_SUBJECT);
			stmt.setLong(1, facultySubjectData.getFacultyRegistrationNumber());
			stmt.setString(2, facultySubjectData.getSubjectCode());
			int count=stmt.executeUpdate();
			System.out.println(facultySubjectData.getFacultyRegistrationNumber());
			System.out.println(facultySubjectData.getSubjectCode());
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean deleteFacultySubjectDetails(int facultySubjectId)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_FACULTY_SUBJECT);
			stmt.setInt(1, facultySubjectId);
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public ArrayList<FacultySubjectData> getAllFacultySubjectDetails(LoggedInUserData currentUser)throws SQLException {
		ArrayList<FacultySubjectData> facultySubjectList=new ArrayList<FacultySubjectData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_SUBJECTS_ENROLLED);
			stmt.setLong(1, currentUser.getRegistrationNumber());
			rs = stmt.executeQuery();
			while (rs.next()){
				FacultySubjectData facultySubjectData = new FacultySubjectData();
				facultySubjectData.setFacultyRegistrationNumber(rs.getLong("faculty_registration_number"));
				facultySubjectData.setFacultySubjectId(rs.getInt("faculty_subject_id"));
				facultySubjectData.setSubjectCode(rs.getString("subject_code"));
				facultySubjectData.setSubjectName(rs.getString("subject_name"));
				facultySubjectList.add(facultySubjectData);
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return facultySubjectList;
	}
	
	public ArrayList<SubjectData> getAllSubjectDetailsNotEnrolled(LoggedInUserData currentUser) throws SQLException {
		ArrayList<SubjectData> subjectList = new ArrayList<SubjectData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_SUBJECT_DETAILS_NOT_ENROLLED);
			stmt.setLong(1, currentUser.getRegistrationNumber());
			rs = stmt.executeQuery();
			while (rs.next()) {
				SubjectData subject=new SubjectData();
				subject.setSubjectCode(rs.getString("subject_code"));
				subject.setSubjectName(rs.getString("subject_name"));
				subjectList.add(subject);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return subjectList;
	}
}
