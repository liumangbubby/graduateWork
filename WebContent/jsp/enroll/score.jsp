<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>机动车驾驶理论考试</title>
<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
<script src="/stuenroll/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/stuenroll/js/icheck.min.js" type="text/javascript"></script>
<link href="/stuenroll/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="/stuenroll/css/bootstrap-theme.css" rel="stylesheet"
	type="text/css">
<link href="/stuenroll/js/skins/all.css" rel="stylesheet"
	type="text/css">
<link href="/stuenroll/css/myboot.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color: rgb(238,238,238);">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">机动车驾驶员理论考试</a>
			</div>
			<div class="navbar-collapse collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="jumbotron">
		<div class="container">
			<h1>考试结束,您的成绩是:</h1><br/>
		</div>
	</div>
	<center><div style="font-size: 250px;font-weight: 900;color: #FF1A1A;">${score}</div></center>
</body>
</html>
