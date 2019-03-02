package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.delegates.AdminDelegator;

@WebServlet("/DeleteFacultyDetailsController")
public class DeleteFacultyDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteFacultyDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		try {
			if(adminDelegator.deleteFacultyDetails(Long.parseLong(request.getParameter("facultyRegistrationNumber")))) {
				response.getWriter().write("Faculty Deleted Successfully!");
			}
			else {
				response.getWriter().write("Error in Deleting Faculty!");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
