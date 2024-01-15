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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }
    private static final String query="INSERT INTO USER(name,email,mobile,dob,gender,city) VALUES (?,?,?,?,?,?)";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get print writer
		PrintWriter pw=response.getWriter();
		
		//set content type
		response.setContentType("text/html");
		
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
		
		//get the values
		String name=request.getParameter("userName");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String city=request.getParameter("city");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///usermgmt","root","KAJAL1234");
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			ps.setString(5, gender);
			ps.setString(6, city);
			int count=ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:400px;margin-top:50px'>");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record is Registered Sucessfully</h2>");
			}else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record is not Registered Sucessfully</h2>");
			}
			pw.println("<button class='btn btn-outline-success d-block'><a href='home.html'>Home</a></button>");
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
