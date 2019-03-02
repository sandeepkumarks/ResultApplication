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
import com.zilker.beans.RevaluationData;
import com.zilker.delegates.FacultyDelegator;
import com.zilker.delegates.StudentDelegator;

@WebServlet("/RevaluationController")
public class RevaluationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RevaluationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		LoggedInUserData currentUser = (LoggedInUserData) session.getAttribute("currentUser");

		if (request.getParameter("action").equals("getNotAppliedResults")) {
			ArrayList<ResultData> resultList = new ArrayList<ResultData>();
			System.out.println(session.getAttribute("username"));
			long studentRegistrationNumber = currentUser.getRegistrationNumber();
			int semester = currentUser.getSemester() - 1;
			StudentDelegator studentdelegator = new StudentDelegator();
			try {
				resultList = studentdelegator.getResultById(studentRegistrationNumber, semester, 2);
				request.setAttribute("resultList", resultList);
				request.getRequestDispatcher("pages/apply-revaluation.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (request.getParameter("action").equals("checkRevaluationStatus")) {
			StudentDelegator studentdelegator = new StudentDelegator();
			ArrayList<RevaluationData> revaluationStatusList = new ArrayList<RevaluationData>();
			try {
				revaluationStatusList = studentdelegator.checkRevaluationStatusById(currentUser);
				request.setAttribute("revaluationStatusList", revaluationStatusList);
				request.getRequestDispatcher("pages/revaluation-status.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (request.getParameter("action").equals("getRevaluationListForFaculty")) {
			FacultyDelegator facultyDelegator = new FacultyDelegator();
			ArrayList<RevaluationData> revaluationRequestList = new ArrayList<RevaluationData>();
			try {
				revaluationRequestList = facultyDelegator.checkRevaluationStatusBySubject(currentUser);
				request.setAttribute("revaluationRequestList", revaluationRequestList);
				request.getRequestDispatcher("pages/approve-request.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LoggedInUserData currentUser = (LoggedInUserData) session.getAttribute("currentUser");
		if (request.getParameter("action").equals("applyRevaluation")) {
			ArrayList<Integer> resultIdList = new ArrayList<Integer>();
			StudentDelegator studentDelegator = new StudentDelegator();
			for (String id : request.getParameter("selectedCheckBox").toString().split(",")) {
				resultIdList.add(Integer.parseInt(id));
			}
			try {
				if (studentDelegator.findRevaluationCount(currentUser) + resultIdList.size() <= 5) {
					if (studentDelegator.applyRevaluation(resultIdList, currentUser)) {
						response.getWriter().write("true");
					} else {
						response.getWriter().write("Error in Applying Revaluation!");
						return;
					}
				} else {
					response.getWriter()
							.write("Limit Exceeded! Maximum revaluation application count per student is upto 5!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (request.getParameter("action").equals("approveRequestByFaculty")) {
			ArrayList<Integer> approvedIdList = new ArrayList<Integer>();
			ArrayList<Integer> rejectedIdList = new ArrayList<Integer>();
			FacultyDelegator facultyDelegator = new FacultyDelegator();
			if (request.getParameter("approvedIdList") != null) {
				for (String id : request.getParameter("approvedIdList").toString().split(",")) {
					approvedIdList.add(Integer.parseInt(id));
				}
				try {
					if (!facultyDelegator.changeStatusByFaculty(approvedIdList, "approved")) {
						response.getWriter().write("Error in Approving Request!");
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("rejectedIdList") != null) {
				for (String id : request.getParameter("rejectedIdList").toString().split(",")) {
					rejectedIdList.add(Integer.parseInt(id));
				}
				try {
					if (!facultyDelegator.changeStatusByFaculty(rejectedIdList, "rejected")) {
						response.getWriter().write("Error in Rejecting Request!");
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write("true");
		}
	}

}
