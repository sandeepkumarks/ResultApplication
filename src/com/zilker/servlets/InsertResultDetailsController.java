package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.beans.ResultData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/InsertResultDetailsController")
public class InsertResultDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertResultDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator=new AdminDelegator();
		ResultData resultData= new ResultData();
		resultData.setStudentRegistrationNumber(Long.parseLong(request.getParameter("studentRegistrationNumber")));
		resultData.setSubjectCode(request.getParameter("subjectCode"));
		resultData.setGrade(request.getParameter("grade"));
		resultData.setWrittenIn(Integer.parseInt(request.getParameter("writtenIn")));
		try {
			if(adminDelegator.addResults(resultData)) {
				response.getWriter().write("Result Added Successfully!");
			}
			else {
				response.getWriter().write("Error in Adding Result!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
