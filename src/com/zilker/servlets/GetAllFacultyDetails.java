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
import com.zilker.beans.FacultyData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/GetAllFacultyDetails")
public class GetAllFacultyDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetAllFacultyDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator= new AdminDelegator();
		if(request.getParameterMap().containsKey("flag")) {
			try {
				long registrationNumber= adminDelegator.getLastFacultyRegistrationNumber(Integer.parseInt(request.getParameter("collegeCode")));
				response.getWriter().write(Long.toString(registrationNumber));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			ArrayList<FacultyData> facultyList = new ArrayList<FacultyData>();
			ArrayList<CollegeData> collegeList = new ArrayList<CollegeData>();
			try {
				facultyList=adminDelegator.getAllFacultyDetails();
				collegeList=adminDelegator.getAllCollegeDetails();
				request.setAttribute("facultyDetailsList", facultyList);
				request.setAttribute("collegeDetailsList", collegeList);
				request.getRequestDispatcher("pages/faculty-details.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
