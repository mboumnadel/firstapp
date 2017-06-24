package com.med.firstapp.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Post</h1>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Get</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}