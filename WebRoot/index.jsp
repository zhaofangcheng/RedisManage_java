<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ReidsManage</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	<div style="text-align: left;">
	    <ul><h2>使用说明<%=basePath%></h2>
		   <li> 模糊查询:<a href="<%=basePath%>like?eshop"><%=basePath%>like?eshop</a></li>
		   <li>精准查询:<a href="<%=basePath%>query?eshop_floor"><%=basePath%>query?eshop_floor</a></li>
		   <li> 精准删除:<a href="<%=basePath%>del?eshop_floor"><%=basePath%>del?eshop_floor</a></li>
		</ul>
	</div>
  </body>
</html>
