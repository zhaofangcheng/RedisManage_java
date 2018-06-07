package com.smart.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  精准查询使用
 * @author zfc
 *
 */
public class Query extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key=request.getQueryString();
		RedisTools tools=new RedisTools();
		String value=tools.get(key);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>"+key+"查询结果</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print(value);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


}
