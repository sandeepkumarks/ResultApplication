package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.beans.LoggedInUserData;
import com.zilker.constants.RegexConstants;
import com.zilker.delegates.LoginDelegator;
import com.zilker.delegates.Validator;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		long registrationNumber=Long.parseLong(request.getParameter("registrationNumber").toString());
		String password=request.getParameter("password");
		if(registrationNumber==0|| password.length()==0) {
			response.getWriter().write("Please fill all the fields!");
			return;
		}
		if(!Validator.validate(Long.toString(registrationNumber), RegexConstants.NUMERIC_REGEX)) {
			response.getWriter().write("Registration Number can only have numbers!");
			return;
		}
		LoginDelegator loginDelegate = new LoginDelegator();
		LoggedInUserData currentUser= new LoggedInUserData();
		try {
			currentUser = loginDelegate.isValidUser(registrationNumber, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(currentUser.getRole().equals("invalidUser")) {
			response.getWriter().write("Invalid Login Credentials");
		}
		else {
			HttpSession session= request.getSession();
			session.setAttribute("currentUser", currentUser);
			session.setAttribute("username", currentUser.getName());
			System.out.println(currentUser.getName()+" "+currentUser.getRole()+" "+currentUser.getSemester());
			response.getWriter().write(currentUser.getRole());
		}
	}

}
