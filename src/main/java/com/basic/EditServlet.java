package com.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final String query="UPDATE USER SET name=?,email=?,mobile=?,dob=?,gender=?,city=? WHERE id=?";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get print writer
		PrintWriter pw=response.getWriter();
		
		//set content type
		response.setContentType("text/html");
		
		//get the values which was Updated
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String city=request.getParameter("city");

		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///usermgmt","root","KAJAL1234");
			PreparedStatement ps=con.prepareStatement(query);
			//set the value
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			ps.setString(5, gender);
			ps.setString(6, city);
			ps.setInt(7, id);
			//execute the query
			int count=ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:400px;margin-top:50px'>");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record is Edited Sucessfully</h2>");
			}else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record is not Edited Sucessfully</h2>");
			}
			pw.println("<button class='btn btn-outline-success d-block'><a href='home.html'>Home</a></button>");
			pw.println("<button class='btn btn-outline-success d-block'><a href='showdata'>Show Users</a></button>");
			pw.println("<div>");
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
