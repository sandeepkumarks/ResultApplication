package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;
import com.zilker.beans.*;
public class CollegeDetailsDao {
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean insertCollegeDetails(CollegeData college)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_COLLEGE);
			stmt.setInt(1, college.getCollegeCode());
			stmt.setString(2, college.getCollegeName());
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean deleteCollegeDetails(int collegeCode)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_COLLEGE);
			stmt.setInt(1, collegeCode);
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public ArrayList<CollegeData> getAllCollegeDetails()throws SQLException {
		ArrayList<CollegeData> collegeList = new ArrayList<CollegeData>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_COLLEGE_DETAILS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				CollegeData college=new CollegeData();
				college.setCollegeCode(Integer.parseInt(rs.getString("college_code")));
				college.setCollegeName(rs.getString("college_name"));
				collegeList.add(college);
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return collegeList;
	}
	
	public boolean updateCollegeDetails(int currentCollegeCode, CollegeData college)
			throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_COLLEGE);
			stmt.setInt(1, college.getCollegeCode());
			stmt.setString(2, college.getCollegeName());
			stmt.setInt(3, currentCollegeCode);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean isCollegeCodePresent(CollegeData college) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement("SELECT * FROM college_details WHERE college_code=?");
			stmt.setInt(1, college.getCollegeCode());
			rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean isCollegeNamePresent(CollegeData college) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement("SELECT * FROM college_details WHERE college_name=?");
			stmt.setString(1, college.getCollegeName());
			rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
}
