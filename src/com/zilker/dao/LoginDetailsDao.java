package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;
import com.zilker.beans.*;

public class LoginDetailsDao {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean registerStudent(StudentData obj) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.REGISTER_STUDENT);
			stmt.setLong(1, obj.getStudentRegistrationNumber());
			stmt.setString(2, "user123");
			stmt.setString(3, "student");
			stmt.executeUpdate();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return true;
	}

	public boolean registerFaculty(FacultyData obj) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.REGISTER_FACULTY);
			stmt.setLong(1, obj.getFacultyRegistrationNumber());
			stmt.setString(2, "user123");
			stmt.setString(3, "faculty");
			stmt.executeUpdate();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return true;
	}

	public LoggedInUserData isValidUser(long registrationNumber, String password) throws SQLException {
		LoggedInUserData currentUser = new LoggedInUserData();
		try {
			con = Config.getConnection();
			stmt = con.prepareStatement(SqlConstants.LOGIN);
			stmt.setLong(1, registrationNumber);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				long regno = rs.getLong("registration_number");
				if (rs.getString("role").equals("student")) {
					stmt = Config.conn.prepareStatement(SqlConstants.SELECT_STUDENT);
					stmt.setLong(1, regno);
					rs = stmt.executeQuery();
					if (rs.next()) {
						currentUser.setRegistrationNumber(rs.getLong("student_registration_number"));
						currentUser.setName(rs.getString("student_name"));
						currentUser.setCollegeCode(rs.getInt("college_code"));
						currentUser.setSemester(rs.getInt("semester"));
						currentUser.setRole("student");
					}

				} else if (rs.getString("role").equals("faculty")) {
					stmt = Config.conn.prepareStatement(SqlConstants.SELECT_FACULTY);
					stmt.setLong(1, regno);
					rs = stmt.executeQuery();
					if (rs.next()) {
						currentUser.setRegistrationNumber(rs.getLong("faculty_registration_number"));
						currentUser.setName(rs.getString("faculty_name"));
						currentUser.setCollegeCode(rs.getInt("college_code"));
						currentUser.setDepartment(rs.getString("department"));
						currentUser.setRole("faculty");
					}

				} else if (rs.getString("role").equals("admin")) {
					currentUser.setRole("admin");
					currentUser.setName("Admin");
				}
			} else {
				currentUser.setRole("invalidUser");
			}

		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return currentUser;
	}
}
