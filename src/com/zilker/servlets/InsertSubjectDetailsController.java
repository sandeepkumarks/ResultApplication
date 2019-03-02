package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.SubjectData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/InsertSubjectDetailsController")
public class InsertSubjectDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public InsertSubjectDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator=new AdminDelegator();
		SubjectData subjectData= new SubjectData();
		subjectData.setSubjectCode(request.getParameter("subjectCode"));
		subjectData.setSubjectName(request.getParameter("subjectName"));
		try {
			if(adminDelegator.isSubjectCodePresent(subjectData)) {
				response.getWriter().write("SubjectCode Already Present!");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(adminDelegator.isSubjectNamePresent(subjectData)){
				response.getWriter().write("Subject Name Already Present!");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(adminDelegator.addSubjectDetails(subjectData)) {
				response.getWriter().write("Subject Added Successfully!");
			}
			else {
				response.getWriter().write("Error in Adding Subject!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
