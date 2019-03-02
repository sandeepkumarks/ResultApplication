package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zilker.beans.RevaluationData;
import com.zilker.delegates.AdminDelegator;

@WebServlet("/GetAllApprovedRequest")
public class GetAllApprovedRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAllApprovedRequest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		AdminDelegator adminDelegator = new AdminDelegator();
		ArrayList<RevaluationData> revaluationList = new ArrayList<RevaluationData>();
		try {
			revaluationList = adminDelegator.getApprovedRevaluationList();
			request.setAttribute("revaluationList", revaluationList);
			request.getRequestDispatcher("pages/update-grade.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		Gson gson = new Gson();
		String updateData = request.getParameter("updateList");
		System.out.println(updateData);
		Map<String, String> updateList = new HashMap<String, String>();
		updateList = (Map<String, String>) gson.fromJson(updateData, updateList.getClass());
		for (Map.Entry<String, String> data : updateList.entrySet()){
			System.out.println(data.getKey()+"  "+data.getValue());
		}
		try {
			if(adminDelegator.updateGrade(updateList)) {
				response.getWriter().write("true");
			}
			else {
				response.getWriter().write("false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
