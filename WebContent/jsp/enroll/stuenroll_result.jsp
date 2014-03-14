<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script src="../../js/broswer.js"></script>
<script src="../../js/jquery-1.6.4.js"></script>
<script>
	function fSend() {
		location.href = 'mailTo:yyyyttttwwww@163.com?subject=StuEnrollError';
	}
	function toPrint() {
		document.getElementById("type").value = "print";
		document.getElementById("form_1").submit();
	}
	function toPreView() {
		document.getElementById("type").value = "preview";
		document.getElementById("form_1").submit();
	}

	function printEnrollPDF(pid) {

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
		/**
		location.href = "/stuenroll/jsp/enroll/StuEnrollAction!toPDF.action?pid="
				+ pid;
		*/
		window.open("/stuenroll/jsp/enroll/StuEnrollAction!toPDF.action?pid="+ pid);
	}
	function viewProcess(){
		window.open("http://www.jyw.gov.cn/web/assembly/action/browsePage.do?channelID=1248220226600&contentID=1336577533585");
	}
</script>
</head>

<body class=" body">
	<div class="body_div">
		<div class="div_1">
			<div class="div_2">学员信息预登记成功</div>
			<!-- 
			<fieldset class="set_1">
				<legend class="leg_1">注意事项：</legend>
				<div style="position: relative; top: 20px" id="pop_1"></div>
				<div>
					<ul>
						<li class="li_1" style="color: red">如果需要修改报名信息，请拨打024-12345678电话，由信息维护人员完成修改。</li>
						<li class="li_1" style="color: red">辽宁省政府转换项目审核之日，请携带打印好的纸质报名表、身份证等相关证件，前往省就业办。</li>
						<li class="li_1">您需要下载您的报表文件，请点击下方的“下载报表”按钮。</li>
						<li class="li_1">请您将下载的报表文件打印，并且不可涂改，否则视为无效。</li>
					</ul>
				</div>
			</fieldset>
			 -->
			<hr class="hr_1" />
			<div class="div_3">
				<form id="form_1" method="post"
					action="/stuenroll/jsp/enroll/StuEnrollAction!toPrint.action">
					<p >
						<input type="hidden" name="pid" value="${enroll.pid}" /> 
						<input type="hidden" name="type" id="type" value="" /> 
						<!-- 
						<input type="button" class="btn_1" value="打印" onclick="toPrint()" /> 
						<input name="sd" type="button" class="btn_2" value="打印预览" onclick="toPreView()" />
						 -->
						<input name="sd" type="button" class="btn_1" value="下载及打印报名表" style="font-size: 13px;" onclick="printEnrollPDF('${enroll.pid}')" />
						<input name="sd" type="button" class="btn_2" value="报名流程查询" style="font-size: 13px;" onclick="viewProcess()"  /> 
						<!-- 
						<input name="sd" type="button" class="btn_3" value="报告错误" onclick="fSend()"/> 
						 -->
						<input name="sd" type="button" class="btn_4" value="关闭" style="font-size: 13px;"  onclick="window.close();"/> 
						
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>