package com.uniquedeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/register")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		Connection con =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginregistration?useSSL=false","root","root");
			PreparedStatement ps= con.prepareStatement("insert into users(uname,uemail,upwd,umobile) values(?,?,?,?)");
			
			ps.setString(1, uname);
			ps.setString(2, uemail);
			ps.setString(3, upwd);
			ps.setString(4, umobile);
			
			int rs=ps.executeUpdate();
			
			dispatcher =request.getRequestDispatcher("registration.jsp");
			if(rs>0) {
				request.setAttribute("status","Success");
				
			}
			else {
				request.setAttribute("status", "failed");
				
			}
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
