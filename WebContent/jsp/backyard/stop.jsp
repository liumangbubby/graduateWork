<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache, must-revalidate"/> 
<title>中退管理</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<link href="../../css/popup.css" rel="stylesheet" type="text/css" />
<link href="../../js/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/nav.js"></script>
<script src="../../js/chosen/chosen.jquery.js"></script>
<script src="../../js/jquery.json-2.4.min.js"></script>

<script src="../../js/stop.js"></script>

<style>
.spe_div{
	overflow:auto;padding-left: 20px;
}
.spe_table {
	margin: 20px;
	border: 1px solid #ccc;
	border-collapse: collapse;
}
.spe_title{
	font-size:24px;
	font-weight:bold;
	font-family:"新宋体",'宋体';
}
.inn{
	width: 100px;
	height: 24px;
	font-family: "Arial"
}
.right{
background-color: #FACEE9;
}
</style>
</head>

<body class="body">
<div class="main_div" style="-moz-user-select:none;-webkit-user-select:none">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div spe_div">
  <form id="form_1" method="post" action="/stuenroll/jsp/backyard/StopAction!stop.action">
    <fieldset class="set" onmouseover="inSearch()" onmouseout="outSearch()">
				<legend class="leg">检索条件</legend>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="search_table">
					<tr>
						<td  height="45">培训机构： 
						<%
							if (session.getAttribute("school") == null) {
						%>
							<select name="school" id='school' class="chzn-select"
							tabindex="2" style="width: 230px;">
								<option value="">- 选择 -</option>
								<s:iterator id="obj" value="schList">
									<option value="${obj}">${obj}</option>
								</s:iterator>
						</select> 
						<%} else {%> ${sessionScope.school} <%}%>
						</td>
						<td>班级编号： <span id='tc'> 
						<select name="classNo" id='classNo' class="chzn-select" tabindex="2" style="width: 230px;">
									<option value="">- 选择 -</option>
									<s:iterator id="obj" value="classList">
										<option value="${obj}">${obj}</option>
									</s:iterator>
							</select>
						</span>
						</td>
						<td>姓名：<input type='text' id="name" name="name" class='inn'/></td>
						<td>身份证：<input type='text' id="pid" name="pid" class='inn'/></td>
					</tr>
				</table>
			</fieldset>
			</form>
	<form id="form_2" method="post" action="/stuenroll/jsp/backyard/StopAction!delete.action">
    <table width="1000" border="0" cellpadding="0" cellspacing="0" class="spe_table">
      <tr>
        <td width="40" height="35" align="center" class="cell_title">
        </td>
        <td width="70" align="center" class="cell_title">序号</td>
        <td width="170" align="center" class="cell_title">班级编号</td>
        <td width="120" align="center" class="cell_title">姓名</td>
        <td width="270" align="center" class="cell_title">培训机构</td>
        <td width="110" align="center" class="cell_title">退出日期</td>
        <td width="100" align="center" class="cell_title">累计时间</td>
        <td  align="center" class="cell_title">操&nbsp;&nbsp;作</td>
      </tr>

      <s:iterator id="obj" value="stopList" status="st">
      <tr style="background-color: ${st.index%2!=0?'#fcfcfc':'#E3FAE3'}">
      	<td height="32" align="center" class="cell">
        	<input name="stopId" type="checkbox" id='stopId_${obj.id }' value="${obj.id }" />
        </td>
        <td align="center" class="cell_num">${st.count}</td>
        <td align="center" class="cell">${class_no }</td>
        <td align="center" class="cell">${name }</td>
        <td align="center" class="cell">${school }</td>
        <td align="center" class="cell"><fmt:formatDate value='${stop_date }' pattern='yyyy-MM-dd'/></td>
        <td align="center" class="cell">${days }天</td>
        <td align="center" class="cell">
        <img src="../../pic/delete.png" width="22" name='del' lang='stopId_${obj.id }' style="cursor: pointer;"/></td>
      </tr>
      </s:iterator>
    </table>
    </form>
    <div class="spe_div">
      	<input type="button" value="全选" id="btn_1" class="op_btn" />
      	<input type="button" value="删除" id="btn_2" class="op_btn"/>
      	<button id="btn_3"  class="op_btn" title="insertOne" value="#?w=420" style="background-color: #FFB5CA">新增</button>
      	<button id="btn_4"  class="op_btn" title="chart" value="#?w=420" style="background-color: #FFB5CA">统计</button>
      	<button id="btn_5"  class="op_btn" title="export" value="#?w=420" style="background-color: #FFB5CA">导出</button>
     </div>
  </div>
</div>
<div id="insertOne" class="popup_block" style="-moz-user-select:none;-webkit-user-select:none">
<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">新增中退人员记录</p>
		<form method="post" id="form_3" action="/stuenroll/jsp/backyard/StopAction!addStop.action">
  			<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;">
  			<tr>
  				<c:if test="${sessionScope.school==null}">
  				<td height="50" width="85">学校名称：</td>
  				<td>
  					<select name="school" id='showSchool' class="chzn-select" tabindex="2" style="width:260px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="showSchoolList">
							<option value="${obj.school}">${obj.school}</option>
						</s:iterator>
					</select>
  				</td>
  				</c:if>
  			</tr>
  			<tr>
  				<td height="50" width="85">班级编号：</td>
  				<td>
  					<span id='ttsd'>
  					<select name="classNo" id='showClassNo' class="chzn-select" tabindex="2" style="width:260px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="showClassList">
							<option value="${obj.name}">${obj.name}</option>
						</s:iterator>
					</select>
					</span>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">学生姓名：</td>
  				<td>
  					<input type="text" name="name" id='showName' class="input" style="width: 253px;"/>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">身份证号：</td>
  				<td>
  					<input type="text" name="pid" id='showPid' class="input" style="width: 253px;"/><label id='rsCk'></label>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">联系电话：</td>
  				<td>
  					<input type="text" name="tel" id='showTel' class="input" style="width: 253px;"/>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">开班人数：</td>
  				<td>
  					<input type="text" name="number" title="格式：2012/5/25" id='showNumber' class="input" style="width: 253px;"/>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">开班日期：</td>
  				<td>
  					<input type="text" name="joinDate" title="格式：2012/5/25" id='showJoinDate' class="input" style="width: 253px;"/>
  				</td>
  			</tr>
  			
  			<tr>
  				<td height="50" width="85">中退日期：</td>
  				<td>
  					<input type="text" name="stopDate" title="格式：2012/5/25" id='showStopDate' class="input" style="width: 253px;"/>
  				</td>
  			</tr>
  			
  			<tr>
  				<td height="50" width="85">中退原因：</td>
  				<td>
  					<textarea name="reason" id='showReason' style="width: 256px;height: 80px;margin: 10px 0 10px 0;" ></textarea>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">备注信息：</td>
  				<td>
  					<textarea name="remark" id="showRemark" style="width: 256px;height: 80px;margin: 10px 0 10px 0;" ></textarea>
  				</td>
  			</tr>
  			</table>
  		</form>
  		<center>
	 	<input type="button" value="保存" class="btn_1" id='bt67' style="margin: 10px 0 10px 0"/>
  		<input type="button" id="cancel" value="取消" class="btn_1"/>
  		</center>
</div>

<div id="chart" class="popup_block" style="-moz-user-select:none;-webkit-user-select:none">
<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">新增中退人员记录</p>
		<form method="post" id="form_4" action="/stuenroll/jsp/backyard/StopAction!chartStop.action">
  			<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;">
  			<tr>
  				<c:if test="${sessionScope.school==null}">
  				<td height="50" width="85">学校名称：</td>
  				<td>
  					<select name="school" id='showSchool2' class="chzn-select" tabindex="2" style="width:260px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="showSchoolList">
							<option value="${obj.school}">${obj.school}</option>
						</s:iterator>
					</select>
  				</td>
  				</c:if>
  			</tr>
  			<tr>
  				<td height="50" width="85">班级编号：</td>
  				<td>
  					<span id='ttsc'>
  					<select name="classNo" id='showClassNo2' class="chzn-select" tabindex="2" style="width:260px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="showClassList">
							<option value="${obj.name}">${obj.name}</option>
						</s:iterator>
					</select>
					</span>
  				</td>
  			</tr>
  			</table>
  		</form>
  		<center>
	 	<input type="button" value="统计" class="btn_1" id='bt68' style="margin: 10px 0 10px 0"/>
  		<input type="button" id="cancel" value="取消" class="btn_1"/>
  		</center>
</div>
<div id="export" class="popup_block" style="-moz-user-select:none;-webkit-user-select:none">
<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">导出中退人员记录</p>
		<form method="post" id="form_5" action="/stuenroll/jsp/backyard/StopAction!exportExcel.action">
  			<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;">
  			<tr>
  				<c:if test="${sessionScope.school==null}">
  				<td height="50" width="85">学校名称：</td>
  				<td>
  					<select name="school" id='showSchool3' class="chzn-select" tabindex="2" style="width:260px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="showSchoolList">
							<option value="${obj.school}">${obj.school}</option>
						</s:iterator>
					</select>
  				</td>
  				</c:if>
  			</tr>
  			<tr>
  				<td height="50" width="85">导出年份：</td>
  				<td>
  					<input type="text" name="year" id='year' class="input" style="width: 253px;"/>
  				</td>
  			</tr>
  			</table>
  		</form>
  		<center>
	 	<input type="button" value="导出" class="btn_1" id='bt698' style="margin: 10px 0 10px 0"/>
  		<input type="button" id="cancel" value="取消" class="btn_1"/>
  		</center>
</div>

</body>
</html>