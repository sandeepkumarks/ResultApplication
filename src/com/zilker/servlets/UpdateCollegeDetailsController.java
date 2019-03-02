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

@WebServlet("/UpdateCollegeDetailsController")
public class UpdateCollegeDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCollegeDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		CollegeData collegeData= new CollegeData();
		collegeData.setCollegeCode(Integer.parseInt(request.getParameter("collegeCode")));
		collegeData.setCollegeName(request.getParameter("collegeName"));
		try {
			if(adminDelegator.updateCollegeDetails(Integer.parseInt(request.getParameter("currentCollegeCode")), collegeData)) {
				response.getWriter().write("COllege Details Updated Successfully!");
			}
			else {
				response.getWriter().write("Error in Updating College Details!");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
