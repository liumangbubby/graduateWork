<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程表</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<!-- 可搜索的下拉列表CSS -->
<link href="../../js/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/chosen/chosen.jquery.js"></script>
<script src="../../js/nav.js"></script>
<script src="../../js/schedule_list.js"></script>
<style>
.info_table tr:nth-child(2n) {
	background-color: #E3FAE3;
}

.bbtn {
	color: #fff;
	width: 70px;
	display: block;
	line-height: 30px;
	text-align: center;
	background-color: #0cc;
	font-size: 14px;
	font-weight: bold;
}

.alink{
color: #0047B6;
}
.alink:hover{

	color: #C2006A;
	text-decoration: underline;
	cursor: pointer;
}
</style>
</head>

<body class="body">
	<div class="main_div"
		style="-moz-user-select: none; -webkit-user-select: none;">
		<jsp:include page="left_nav.jsp"></jsp:include>
		<div class="right_div">
			<form method="post" action="/stuenroll/jsp/backyard/ScheduleAction!search.action" id="form_1">
			<fieldset class="set" onmouseover="inSearch()" onmouseout="outSearch()">
				<legend class="leg">检索条件</legend>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="search_table">
					<tr>
						<td width="380" height="45">培训机构： 
						<%
							if (session.getAttribute("school") == null) {
						%>
							<select name="school" id='school' class="chzn-select"
							tabindex="2" style="width: 230px;">
								<option value="">- 选择 -</option>
								<s:iterator id="obj" value="eduList">
									<option value="${obj}">${obj}</option>
								</s:iterator>
						</select> 
						<%} else {%> ${sessionScope.school} <%}%>
						</td>
						<td>班级编号： <span id='tc'> <select name="classNo"
								id='classNo' class="chzn-select" tabindex="2"
								style="width: 230px;">
									<option value="">- 选择 -</option>
									<s:iterator id="obj" value="classList">
										<option value="${obj}">${obj}</option>
									</s:iterator>
							</select>
						</span>
						</td>
						<!-- 
						<td align="center">参与状态：</td>
						<td>
							<a class='op_btn' id='all'>全部</a>&nbsp;&nbsp;
							<a class='op_btn' id='upload'>已传<a />&nbsp;&nbsp;
							<a class='op_btn' id='unupload'>没传</a>
						</td>
						 -->
					</tr>
				</table>
			</fieldset>
			<input type="hidden" id="filter" name="filter" value=""/>
			</form>
			<form method="post" id="form_2" action="/stuenroll/jsp/backyard/ScheduleAction!schedule.action">
			<input type="hidden" name="classNo" id='sClassNo' />
			<input type="hidden" name="school" id='sSchool' />
			<table width="1052" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td width="97" height="35" id="daxuhao" align="center"
						class="cell cell_title" onclick="orderDaXuHao()"
						style="cursor: pointer">序号</td>
					<td width="187" align="center" class="cell cell_title">班级名称</td>
					<td width="296" align="center" class="cell cell_title">培训机构</td>
					<td width="141" align="center" class="cell cell_title">上传日期</td>
					<td width="147" align="center" class="cell cell_title">上传人</td>
					<td width="182" align="center" class="cell cell_title">已上传周数</td>
				</tr>
				<s:if test="scList!=null">
					<s:iterator var="one" value="scList" status="st">
						<tr>
							<td height="35" align="center" class="cell ">${st.count}</td>
							<td align="center" class="cell alink" name='${one.class_no}' lang="${one.school}"><a  class='ttt' name='ssClassNo'>${one.class_no}</a></td>
							<td align="center" class="cell">${one.school}</td>
							<td align="center" class="cell">${one.upload_time}</td>
							<td align="center" class="cell">${one.upload_user}</td>
							<td align="center" class="cell">
								${one.week }
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</form>
		</div>
	</div>
</body>
</html>