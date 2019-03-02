package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.FacultyData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/InsertFacultyDetailsController")
public class InsertFacultyDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public InsertFacultyDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator=new AdminDelegator();
		FacultyData facultyData= new FacultyData();
		facultyData.setFacultyRegistrationNumber(Long.parseLong(request.getParameter("facultyRegistrationNumber")));
		facultyData.setName(request.getParameter("facultyName"));
		facultyData.setCollegeCode(request.getParameter("collegeCode"));
		facultyData.setDepartment(request.getParameter("department"));
		try {
			if(adminDelegator.addFacultyDetails(facultyData) && adminDelegator.registerFaculty(facultyData)) {
				response.getWriter().write("Faculty Added Successfully!");
			}
			else {
				response.getWriter().write("Error in Adding Faculty!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
