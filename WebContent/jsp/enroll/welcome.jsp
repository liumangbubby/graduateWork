<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎报名</title>
<link href="../../css/welcome.css" rel="stylesheet" type="text/css" />
<script src="../../js/broswer.js"></script>
<script>
function printEnroll() {
	var pid = prompt("输入身份证编号，您的资料将被打印", "请输入");
	if (pid == null || pid == "" || pid == "请输入") {
		alert("请填写身份证编号！");
	} else {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var date = new Date();
		xmlhttp.open("GET", "/stuenroll/ajax/PidAjax?pid=" + pid + "&s="
				+ date.getMilliseconds(), false);
		xmlhttp.send();
		var rs = xmlhttp.responseText;
		if (rs == null || rs == "" || rs == "false") {
			alert("报名系统中不存在您的身份证信息，请注册后再打印！");				
		} else if (rs == "havePid") {
			location.href="/stuenroll/jsp/enroll/StuEnrollAction!toPrint.action?pid="+pid;
		} 
	}
}
function printEnrollPDF() {
	var pid = prompt("输入身份证编号，您的资料将被打印", "请输入");
	if (pid == null || pid == "" || pid == "请输入") {
		alert("请填写身份证编号！");
	} else {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var date = new Date();
		xmlhttp.open("GET", "/stuenroll/ajax/PidAjax?pid=" + pid + "&s="
				+ date.getMilliseconds(), false);
		xmlhttp.send();
		var rs = xmlhttp.responseText;
		if (rs == null || rs == "" || rs == "false") {
			alert("报名系统中不存在您的身份证信息，请注册后再打印！");				
		} else if (rs == "havePid") {
			location.href="/stuenroll/jsp/enroll/StuEnrollAction!toPDF.action?pid="+pid;
		} 
	}
}
</script>
<script>
	function linkTo() {
		window.location.href = "/stuenroll/jsp/enroll/stuenroll_note.jsp";
	}
</script>
</head>

<body class="page">
	<div class="div_1">
		<div class="font_1 div_2">
			<strong>辽宁省离校未就业<br />专业技能培训
			</strong>
		</div>
		<div class="div_3 font_2" onclick="linkTo()">我要报名</div>
		<!-- 
		<div class="div_4 font_3" onclick="printEnroll()">我要打印</div>
		 -->
		<div class="div_4 font_4" onclick="printEnrollPDF()">下载我的报表</div>
	</div>
</body>
</html>