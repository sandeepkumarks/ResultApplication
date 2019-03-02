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
import com.zilker.delegates.AdminDelegator;

@WebServlet("/GetAllCollegeDetails")
public class GetAllCollegeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public GetAllCollegeDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<CollegeData> collegeList = new ArrayList<CollegeData>();
		AdminDelegator adminDelegator= new AdminDelegator();
		try {
			collegeList=adminDelegator.getAllCollegeDetails();
			request.setAttribute("collegeDetailsList", collegeList);
			request.getRequestDispatcher("pages/college-details.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
