package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.StudentData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/InsertStudentDetailsController")
public class InsertStudentDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertStudentDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator=new AdminDelegator();
		StudentData studentData= new StudentData();
		studentData.setStudentRegistrationNumber(Long.parseLong(request.getParameter("studentRegistrationNumber")));
		studentData.setName(request.getParameter("studentName"));
		studentData.setCollegeCode(request.getParameter("collegeCode"));
		studentData.setDepartment(request.getParameter("department"));
		studentData.setSemester(Integer.parseInt(request.getParameter("semester")));
		try {
			if(adminDelegator.addStudentDetails(studentData) && adminDelegator.registerStudent(studentData)) {
				response.getWriter().write("Student Added Successfully!");
			}
			else {
				response.getWriter().write("Error in Adding Student!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
