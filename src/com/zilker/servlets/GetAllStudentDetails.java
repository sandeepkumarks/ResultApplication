package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.CollegeData;
import com.zilker.beans.StudentData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/GetAllStudentDetails")
public class GetAllStudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetAllStudentDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator= new AdminDelegator();
		if(request.getParameterMap().containsKey("flag")) {
			try {
				long registrationNumber= adminDelegator.getLastStudentRegistrationNumber(Integer.parseInt(request.getParameter("collegeCode")));
				System.out.println(Long.toString(registrationNumber));
				response.getWriter().write(Long.toString(registrationNumber));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			ArrayList<StudentData> studentList = new ArrayList<StudentData>();
			ArrayList<CollegeData> collegeList = new ArrayList<CollegeData>();
			try {
				studentList=adminDelegator.getAllStudentDetails();
				collegeList=adminDelegator.getAllCollegeDetails();
				request.setAttribute("studentDetailsList", studentList);
				request.setAttribute("collegeDetailsList", collegeList);
				request.getRequestDispatcher("pages/student-details.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
