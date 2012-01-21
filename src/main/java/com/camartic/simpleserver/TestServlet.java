package com.camartic.simpleserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Order Page</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Executing Order ...</h1>");
        out.println("</body>");
        out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost called!!!!!!!!!!!!!!!!!");
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
