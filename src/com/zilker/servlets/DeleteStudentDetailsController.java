package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.delegates.AdminDelegator;

@WebServlet("/DeleteStudentDetailsController")
public class DeleteStudentDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public DeleteStudentDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		try {
			if(adminDelegator.deleteStudentDetails(Long.parseLong(request.getParameter("studentRegistrationNumber")))) {
				response.getWriter().write("Student Deleted Successfully!");
			}
			else {
				response.getWriter().write("Error in Deleting Student");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
