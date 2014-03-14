<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>
数据导出${url=="none"?"失败":"成功"}
</h1>
<s:if test="url eq 'none'">
<p>该条件下没有数据可以导出</p>
</s:if>
<s:if test="url!='none'">
<p>文件下载：<a href="../../${url}">下载</a></p>
</s:if>
</body>
</html>