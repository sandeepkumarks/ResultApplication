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

@WebServlet("/UpdateFacultyDetailsController")
public class UpdateFacultyDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateFacultyDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		FacultyData facultyData= new FacultyData();
		facultyData.setFacultyRegistrationNumber(Long.parseLong(request.getParameter("facultyRegistrationNumber")));
		facultyData.setName(request.getParameter("facultyName"));
		facultyData.setCollegeCode(request.getParameter("collegeCode"));
		facultyData.setDepartment(request.getParameter("department"));
		try {
			if(adminDelegator.updateFacultyDetails(Long.parseLong(request.getParameter("currentRegistrationNumber")), facultyData)) {
				response.getWriter().write("Faculty Details Updated Successfully!");
			}
			else {
				response.getWriter().write("Error in Updating Faculty Details!");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
