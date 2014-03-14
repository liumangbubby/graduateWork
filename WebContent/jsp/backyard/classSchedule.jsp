<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/backyard.js">
</script>
<style>
.spe_div{
	overflow:auto;
}
.spe_table {
	margin-top:20px;
	border: 1px solid #ccc;
	border-collapse: collapse;
}
.spe_title{
	font-size:24px;
	font-weight:bold;
	font-family:"新宋体",'宋体';
}
body{
	-moz-user-select:none;
	-webkit-user-select:none;
	cursor: default;
}
.spe_table tr:nth-child(2n) {
	background-color: #E3FAE3;
}
.cc{
	color: #0047B6;
	cursor: pointer;
}
.cc:hover{
	color: #C2006A;
	text-decoration: underline;
}
.btnn{
	padding-top:10px;
	padding-bottom:10px;
	width:100px;
	border:#0099CF solid 1px;
	background:-moz-linear-gradient(top,#00BFFF,#00ACE6);
	background:-webkit-linear-gradient(top,#00ACE8,#0086B6);
	color:#FFF;
	text-shadow:#666 0px 1px 1px;
	font-size:14px;
	box-shadow:#888 0px 2px 3px;
	border-radius:3px;
	margin-bottom: 20px;
}
.btnn:active{
	position:relative;
	top:1px;
}
</style>
<script>
$(document).ready(function(){
	$(".cc").click(function(){
		$('#week').val($(this).attr('id'));
		$('#form_1').submit();
	});
	$('#back').click(function(){
		history.go(-1);
	});
	$('#creat').click(function(){
		var temp=$('.cell_num:last').html();
		temp=temp==''||temp==null?'0':temp;
		var count=parseInt(temp)+1;
		var line="<tr>"+
		"<td height='40' align='center' class='cell_num'>"+count+"</td>"+
		"<td align='center' class='cell cc'  id='"+count+"'>"+$('#tttt').val()+"班/第"+count+"周课表</td>"+
		"<td align='center' class='cell'></td>"+
		"<td align='center' class='cell'></td>"+
		"<td align='center' class='cell'>"+count+"</td>"+
		"<td align='center' class='cell'></td></tr>";
		$('.spe_table').append(line);
		$(".cc").click(function(){
			$('#week').val($(this).attr('id'));
			$('#form_1').submit();
		});
	});
	$('*[name="delete"]').click(function(){
		$('#week').val($(this).attr('lang'));
		$('#form_1').attr("action","/stuenroll/jsp/backyard/ScheduleAction!delete.action");
		$('#form_1').submit();
	});
	$('*[name="export"]').click(function(){
		$('#week').val($(this).attr('lang'));
		$('#form_1').attr("action","/stuenroll/jsp/backyard/ScheduleAction!export.action");
		$('#form_1').submit();
		
	});
});
</script>
</head>

<body class="body">
<div class="main_div">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div spe_div">
    <p class="spe_title">${requestScope.classNo}班课程表</p>
    <hr />
    <table border="0" cellpadding="0" cellspacing="0" align="left" class="spe_table">
      <tr>
       
        <td width="72" height="40" align="center" class="cell_title">序号</td>
        <td width="300" align="center" class='cell_title'>名称</td>
        <td width="202" align="center" class="cell_title">上传时间</td>
        <td width="183" align="center" class="cell_title">上传人</td>
        <td width="100" align="center" class="cell_title">周数</td>
        <td  width="138" align="center" class="cell_title">操&nbsp;&nbsp;作</td>
      </tr>
      <s:iterator var="one" value="list" status="st">
      <tr>
        <td align="center" height="40" class="cell_num">${st.count }</td>
        <td align="center" class="cell cc"  id='${one.week}'><a name='view'>${requestScope.classNo}第${one.week}周课程表</a></td>
        <td align="center" class="cell">${one.upload_time}</td>
        <td align="center" class="cell">${one.upload_user}</td>
        <td align="center" class="cell">${week}</td>
        <td align="center" class="cell">
        	<!-- 
        	<img src="../../pic/edit.png" title="编辑"/>&nbsp;&nbsp;
        	 -->
        	 <img src="../../pic/export.png" title="导出" name='export' width='26' style="cursor: pointer;" lang='${week}'/>&nbsp;&nbsp;
        	 <%
        	 String school=(String)session.getAttribute("school");
        	 if(school==null){
        		 %><img src="../../pic/delete.png" name='delete' style="cursor: pointer;" lang="${week}" title="删除"/><%
        	 }
        	 %>
        	
        </td>
      </tr>
      </s:iterator>
    </table>
   	<input type="button" id='creat' class="btnn" value="创&nbsp;建"  style="margin-top: 20px;"/>
    <input type="button" class="btnn" value="返&nbsp;回" id='back' style="margin-top: 20px;"/>
    
    <form id="form_1" action="/stuenroll/jsp/backyard/ScheduleAction!scheduleOne.action" method="post">
    	<input type="hidden" name="classNo" id="classNo" value="${requestScope.classNo}"/>
    	<input type="hidden" name="week"  id='week'/>
    	<input type="hidden" name='school' value="${school}"/>
    </form>
    <input type="hidden" id="tttt" value="${requestScope.classNo}" />
  </div>
</div>
</body>
</html>
