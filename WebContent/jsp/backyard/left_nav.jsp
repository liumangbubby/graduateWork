<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.left_div {
	width: 200px;
	float: left;
	border: solid 1px #999;
	height: 800px;
	margin-right: 10px;
	background-color: #FFF;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	padding-top: 2px;
}	
.left-tag {
	background-color: rgb(174, 0, 87);
	color: #FFF;
	height: 30px;
	padding-top: 15px;
	text-align: center;
	border: #FFF 1px solid;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	cursor: pointer;
	font-size: 14px;
	letter-spacing: 2px;
	font-family: '新宋体', '宋体';
	margin-bottom: 1px;
	margin-left: 2px;
	margin-right: 2px;
}

.left-tag:hover {
	background-color: #008BCE;
}
</style>
<script type="text/javascript" src="/stuenroll/js/nav.js"></script>
</head>
<body>
	<div class="left_div" style="-moz-user-select: none; -webkit-user-select: none; ">
		<div class="left-tag" onclick="nav_1();">报名信息</div>
		<!-- 
		<div class="left-tag" onclick="nav_10();">归档数据</div>
		<div class="left-tag" onclick="nav_13();">课程表</div>
		<div class="left-tag" onclick="nav_14();">中退管理</div>
		<div class="left-tag" onclick="nav_9();">数据导入</div>
		<div class="left-tag" onclick="nav_7();">年届管理</div>
		 -->
		<div class="left-tag" onclick="nav_15();">试题管理</div>
		<div class="left-tag" onclick="nav_2();">申报类型</div>
		<div class="left-tag" onclick="nav_3();">审核状态</div>
		<div class="left-tag" onclick="nav_5();">驾校管理</div>
		<div class="left-tag" onclick="nav_4();">班级管理</div>
		<div class="left-tag" onclick="nav_6();">用户管理</div>
		<div class="left-tag" onclick="nav_11();">报考地点</div>
		<div class="left-tag" onclick="nav_8();">系统管理</div>
		<div class="left-tag" onclick="nav_12();">退出系统</div>
	</div>
</body>
</html>