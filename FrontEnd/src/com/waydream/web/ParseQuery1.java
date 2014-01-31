package com.waydream.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waydream.util.CountRequest;

@WebServlet("/q1")
public class ParseQuery1 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final String teamID = "Waydream";
	private final String acountNum="4362-5471-6694";
	
	public void doGet(HttpServletRequest request, 
					HttpServletResponse response)
					throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString();
		timestamp = timestamp.substring(0, timestamp.length()-4);
	
		out.println(teamID+","+acountNum);
		out.println(timestamp);
		CountRequest.addCountQ1();
		//out.println("Query Number:\t"+num+"<br>");
	}
	

}
