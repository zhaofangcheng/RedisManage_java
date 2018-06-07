package com.smart.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 模糊查询使用
 * @author zfc
 *
 */
public class Like extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path=request.getContextPath();
		String key=request.getQueryString();
		RedisTools tools=new RedisTools();
		List result=tools.like(key);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>模糊多个key</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print(likeBody(result,path));
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public String likeBody(List result,String path){
		String list="<table cellSpacing='0' border='1px' width='1600px' align='center'>";
	    list += "<tr bgcolor='lightgray'><td colspan=4 align='center'><h3>找到"+result.size()+"条记录</h3></td></tr>";
	    list += "<tr bgcolor='lightgray' align='center'><td style='5%'>&nbsp;序号&nbsp;</td><td style='15%'>*****key*****</td><td >*****value*****</td><td style='7%'>**操作**</td></tr>";
	    for(int i=0;i<result.size();i++){
	    	Map valueMap=(Map) result.get(i);
	        list += "<tr>";
	        list +="<td width=20px align=center >"+(i+1)+ "</td>";
	        list +="<td width=20px>"+valueMap.get("key")+ "</td>";
	        list += "<td widht=900px><textarea style=width:100%;height:200px>"+valueMap.get("value")+ "</textarea></td>";
	        list += "<td  align=center > <a href="+path+"/query?"+valueMap.get("key")+ ">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="+path+"/del?"+valueMap.get("key")+ "> 删除</a></td>";
	        list += "</tr>";
	    }
	    list += "</table>";
	    return list;
	}
}
