package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.CollegeData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/InsertCollegeDetailsController")
public class InsertCollegeDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public InsertCollegeDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator=new AdminDelegator();
		CollegeData collegeData= new CollegeData();
		collegeData.setCollegeCode(Integer.parseInt(request.getParameter("collegeCode")));
		collegeData.setCollegeName(request.getParameter("collegeName"));
		try {
			if(adminDelegator.isCollegeCodePresent(collegeData)) {
				response.getWriter().write("CollegeCode Already Present!");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(adminDelegator.isCollegeNamePresent(collegeData)) {
				response.getWriter().write("College Name Already Present!");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(adminDelegator.addCollegeDetails(collegeData)) {
				response.getWriter().write("College Added Successfully!");
			}
			else {
				response.getWriter().write("Error in Adding College!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
