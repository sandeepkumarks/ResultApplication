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

import com.zilker.beans.FacultySubjectData;
import com.zilker.beans.LoggedInUserData;
import com.zilker.beans.SubjectData;
import com.zilker.delegates.FacultyDelegator;

@WebServlet("/FacultySubjectController")
public class FacultySubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FacultySubjectController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session=request.getSession(false);
		LoggedInUserData currentUser=(LoggedInUserData)session.getAttribute("currentUser");
		ArrayList<SubjectData> subjectList= new ArrayList<SubjectData>(); 
		ArrayList<FacultySubjectData> facultySubjectList =new ArrayList<FacultySubjectData>();
		FacultyDelegator facultyDelegator = new FacultyDelegator();
		try {
			subjectList=facultyDelegator.getAllSubjectDetailsNotEnrolled(currentUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			facultySubjectList=facultyDelegator.getAllFacultySubjectDetails(currentUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("facultySubjectList", facultySubjectList);
		request.getRequestDispatcher("pages/faculty-subject-details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		LoggedInUserData currentUser=(LoggedInUserData)session.getAttribute("currentUser");
		FacultyDelegator facultyDelegator = new FacultyDelegator();
		if(request.getParameter("action").equals("addFacultySubjectDetails")) {
			FacultySubjectData facultySubjectData = new FacultySubjectData();
			facultySubjectData.setSubjectCode(request.getParameter("subjectCode"));
			facultySubjectData.setFacultyRegistrationNumber(currentUser.getRegistrationNumber());
			try {
				if(facultyDelegator.insertFacultySubjectDetails(facultySubjectData)) {
					response.getWriter().write("true");
				}
				else {
					response.getWriter().write("Error in Adding Subject!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (request.getParameter("action").equals("deleteFacultySubjectDetails")) {
			int facultySubjectid=Integer.parseInt(request.getParameter("facultySubjectId"));
			try {
				if(facultyDelegator.deleteFacultySubjectDetails(facultySubjectid)) {
					response.getWriter().write("true");
				}
				else {
					response.getWriter().write("Error in Deleting Subject!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
