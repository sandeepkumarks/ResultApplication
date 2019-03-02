package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.beans.LoggedInUserData;
import com.zilker.beans.ResultData;
import com.zilker.beans.StudentData;
import com.zilker.beans.SubjectData;
import com.zilker.delegates.AdminDelegator;
import com.zilker.delegates.StudentDelegator;
import com.google.gson.*;

@WebServlet("/ResultsController")
public class ResultsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ResultsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("getAllResults")) {
			ArrayList<ResultData> resultList = new ArrayList<ResultData>();
			ArrayList<StudentData> studentList = new ArrayList<StudentData>();
			ArrayList<SubjectData> subjectList = new ArrayList<SubjectData>();
			AdminDelegator adminDelegator= new AdminDelegator();
			try {
				resultList=adminDelegator.getAllResultDetails();
				studentList=adminDelegator.getAllStudentDetails();
				subjectList=adminDelegator.getAllSubjectDetails();
				request.setAttribute("resultDetailsList", resultList);
				request.setAttribute("studentDetailsList", studentList);
				request.setAttribute("subjectDetailsList", subjectList);
				request.getRequestDispatcher("pages/result-details.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("action").equals("getResultsBySemester")) {
			ArrayList<ResultData> resultList = new ArrayList<ResultData>();
			HttpSession session=request.getSession();
			LoggedInUserData currentUser= new LoggedInUserData();
			currentUser=(LoggedInUserData)session.getAttribute("currentUser");
			long studentRegistrationNumber= currentUser.getRegistrationNumber();
			int semester=Integer.parseInt(request.getParameter("semester"));
			StudentDelegator studentdelegator = new StudentDelegator();
			try {
				resultList=studentdelegator.getResultsBySemester(studentRegistrationNumber, semester);
				response.setContentType("application/json");
				new Gson().toJson(resultList, response.getWriter());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
