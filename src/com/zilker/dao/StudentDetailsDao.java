package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.config.*;
import com.zilker.constants.SqlConstants;
import com.zilker.beans.*;

public class StudentDetailsDao {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean insertStudentDetails(StudentData obj) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_STUDENT);
			stmt.setLong(1, obj.getStudentRegistrationNumber());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getCollegeCode());
			stmt.setString(4, obj.getDepartment());
			stmt.setInt(5, obj.getSemester());
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public boolean deleteStudentDetails(long studentRegistrationNumber) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_STUDENT);
			stmt.setLong(1, studentRegistrationNumber);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public boolean updateStudentDetails(long currentRegistrationNumber, StudentData obj)
			throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_STUDENT);
			stmt.setLong(1, obj.getStudentRegistrationNumber());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getCollegeCode());
			stmt.setString(4, obj.getDepartment());
			stmt.setInt(5, obj.getSemester());
			stmt.setLong(6, currentRegistrationNumber);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public ArrayList<StudentData> getAllStudentDetails() throws SQLException {
		ArrayList<StudentData> studentList = new ArrayList<StudentData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_STUDENT_DETAILS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StudentData student=new StudentData();
				student.setStudentRegistrationNumber(rs.getLong("student_registration_number"));
				student.setName(rs.getString("student_name"));
				student.setCollegeCode(rs.getString("college_code"));
				student.setDepartment(rs.getString("department"));
				student.setSemester(rs.getInt("semester"));
				studentList.add(student);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return studentList;
	}
	
	public long getLastStudentRegistrationNumber (int collegeCode) throws SQLException {
		long registrationNumber=0;
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement("SELECT max(student_registration_number) FROM result_app.student_details WHERE college_code=?");
			stmt.setInt(1, collegeCode);
			rs = stmt.executeQuery();
			if(rs.next()) {
				registrationNumber=rs.getLong("max(student_registration_number)")+1;
			}
			if(registrationNumber==1) {
				registrationNumber=9999;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return registrationNumber;
	}
}
