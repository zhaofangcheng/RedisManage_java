package com.smart.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  精准删除使用
 * @author zfc
 *
 */
public class Del extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key=request.getQueryString();
		RedisTools tools=new RedisTools();
		long count=tools.del(key);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>删除key"+key+"</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("  已删除： ");
		out.print(key+"影响行数"+count);
		out.println("  </br>");
		out.println("刷新时间:"+DateUtils.getCurrDateStr(DateUtils.ORA_DATE_TIMES3_FORMAT));
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
