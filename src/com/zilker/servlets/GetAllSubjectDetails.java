package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.SubjectData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/GetAllSubjectDetails")
public class GetAllSubjectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllSubjectDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<SubjectData> subjectList = new ArrayList<SubjectData>();
		AdminDelegator adminDelegator= new AdminDelegator();
		try {
			subjectList=adminDelegator.getAllSubjectDetails();
			request.setAttribute("subjectDetailsList", subjectList);
			request.getRequestDispatcher("pages/subject-details.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
