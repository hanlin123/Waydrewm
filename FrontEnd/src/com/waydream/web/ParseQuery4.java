package com.waydream.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waydream.util.CountRequest;
import com.waydream.util.QueryHB4;

@WebServlet("/q4")
public class ParseQuery4 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final String teamID = "WayDream";
	private final String acountNum="1234-5678-9012";
	
	public void doGet(HttpServletRequest request, 
					HttpServletResponse response)
					throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String query = request.getQueryString();
		String[] args = query.split("=");
		String[] elemnt = QueryHB4.queryHB4(args[1]);
		
		out.println(teamID+","+acountNum);
		if(elemnt!=null)
			for(String s:elemnt)
				out.println(s);
			
		CountRequest.addCountQ4();
	}
}
