package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.delegates.AdminDelegator;

@WebServlet("/DeleteCollegeDetailsController")
public class DeleteCollegeDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteCollegeDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		try {
			if(adminDelegator.deleteCollegeDetails(Integer.parseInt(request.getParameter("collegeCode")))) {
				response.getWriter().write("College Deleted Successfully!");
			}
			else {
				response.getWriter().write("Error in Deleting College!");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
