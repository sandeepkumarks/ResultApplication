package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.config.*;
import com.zilker.constants.SqlConstants;
import com.zilker.beans.*;
public class FacultyDetailsDao{
	
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;
	
	public boolean insertFacultyDetails(FacultyData obj)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_FACULTY);
			stmt.setLong(1, obj.getFacultyRegistrationNumber());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getCollegeCode());
			stmt.setString(4, obj.getDepartment());
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean deleteFacultyDetails(long facultyRegistrationNumber)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_FACULTY);
			stmt.setLong(1, facultyRegistrationNumber);
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean updateFacultyDetails(long currentRegistrationNumber, FacultyData obj)
			throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_FACULTY);
			stmt.setLong(1, obj.getFacultyRegistrationNumber());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getCollegeCode());
			stmt.setString(4, obj.getDepartment());
			stmt.setLong(5, currentRegistrationNumber);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	
	public ArrayList<FacultyData> getAllFacultyDetails()throws SQLException {
		ArrayList<FacultyData> facultyList = new ArrayList<FacultyData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_FACULTY_DETAILS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				FacultyData faculty=new FacultyData();
				faculty.setFacultyRegistrationNumber(rs.getLong("faculty_registration_number"));
				faculty.setName(rs.getString("faculty_name"));
				faculty.setCollegeCode(rs.getString("college_code"));
				faculty.setDepartment(rs.getString("department"));
				facultyList.add(faculty);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return facultyList;
	}
	
	public long getLastFacultyRegistrationNumber (int collegeCode) throws SQLException {
		long registrationNumber=0;
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement("SELECT max(faculty_registration_number) FROM result_app.faculty_details WHERE college_code=?");
			stmt.setInt(1, collegeCode);
			rs = stmt.executeQuery();
			if(rs.next()) {
				registrationNumber=rs.getLong("max(faculty_registration_number)")+1;
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
