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
import com.waydream.util.SleepThread;

@WebServlet("/q5")
public class ParseQuery5 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final String teamID = "Waydream";
	private final String acountNum="4362-5471-6694";
	
	final static double Q1BENCHMARK = 2200.0;
	final static double Q2BENCHMARK = 360;
	final static double Q3BENCHMARK = 12;
	final static double Q4BENCHMARK = 340.0;
	
	public void doGet(HttpServletRequest request, 
					HttpServletResponse response)
					throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString();
		timestamp = timestamp.substring(0, timestamp.length()-4);
		
		CountRequest.addCountQ1();
		double throughput = SleepThread.getPercent();
		
		int[] values = CountRequest.getValues();
		
		out.println(teamID+","+acountNum+"<br>");
		out.println(timestamp+"<br><br>");
		out.println("Query Number:\tq1:"+values[0]+"\tq2:"+values[1]+"\tq3:"+values[2]+"\tq4:"+values[3]+"<br>");
		out.println("Percent:\tq1:"+(int)(values[0]/Q1BENCHMARK)+"%\tq2:"+(int)(values[1]/Q2BENCHMARK)+"%\tq3:"+(int)(values[2]/Q3BENCHMARK)+"%\tq4:"+(int)(values[3]/Q4BENCHMARK)+"%<br>");
		out.println("Reqs per sec:\t"+throughput+"%<br>");

	}
	

}
