package com.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
	 private static final String query="Select id,name,email,mobile,dob,gender,city FROM USER";
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//get print writer
			PrintWriter pw=response.getWriter();
			
			//set content type
			response.setContentType("text/html");
			
			//link the bootstrap
			pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("<marquee class='text-primary'><h3>User Data</h3></marquee>");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///usermgmt","root","KAJAL1234");
				PreparedStatement ps=con.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				pw.println("<div class='card' style='margin:auto ;width:1000px; margin-top:40px'>");
				pw.println("<table class='table table-hover table-striped'>");
				pw.println("<tr>");
				pw.println("<th>Id</th>");
				pw.println("<th>Name</th>");
				pw.println("<th>Email</th>");
				pw.println("<th>Mobile</th>");
				pw.println("<th>DOB</th>");
				pw.println("<th>Gender</th>");
				pw.println("<th>City</th>");
				pw.println("<th>Edit</th>");
				pw.println("<th>Delete</th>");
				pw.println("</tr>");
				while(rs.next()) {
					pw.println("<tr>");
					pw.println("<td>"+rs.getInt(1)+"</td>");
					pw.println("<td>"+rs.getString(2)+"</td>");
					pw.println("<td>"+rs.getString(3)+"</td>");
					pw.println("<td>"+rs.getString(4)+"</td>");
					pw.println("<td>"+rs.getString(5)+"</td>");
					pw.println("<td>"+rs.getString(6)+"</td>");
					pw.println("<td>"+rs.getString(7)+"</td>");
					pw.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
					pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
					pw.println("<tr>");
				}
				pw.println("</table><br>");
				pw.println("<button class='btn btn-outline-success'><a href='home.html'>Home</a></button>");
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
