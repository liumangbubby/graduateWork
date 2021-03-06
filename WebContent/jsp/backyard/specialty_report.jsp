<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>申报专业管理</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/backyard.js"></script>
<script src="../../js/nav.js"></script>
<style>
.spe_div{
	overflow:auto;
	padding-left: 20px;
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

</style>
</head>

<body class="body">
<div class="main_div">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div spe_div">
    <p class="spe_title">申报专业管理</p>
    <hr />
    <form id="form_1" method="post" action="/stuenroll/jsp/backyard/SpecialtyReporAction!delete.action">
    <table width="700" border="0" cellpadding="0" cellspacing="0" class="spe_table">
      <tr>
        <td width="41" height="35" align="center" class="cell_title"></td>
        <td width="111" align="center" class="cell_title">序&nbsp;&nbsp;号</td>
        <td width="289" align="center" class="cell_title">专&nbsp;&nbsp;业</td>
        <td width="157" align="center" class="cell_title">删&nbsp;&nbsp;除</td>
      </tr>
      <s:if test="rpList.size>0">
      <s:iterator id="obj" value="rpList" status="st">
      <tr style="background-color: ${st.index%2!=0?'#fcfcfc':'#E3FAE3'}">
      	<td height="32" align="center" class="cell">
        	<input name="specialtyReportId" id="speId_${obj.id }" type="checkbox" value="${obj.id }" />
        </td>
        <td align="center" class="cell_num"><s:property value='#st.index+1'/></td>
        <td align="center" class="cell" name="specialtyReportName" style="letter-spacing: 2px;">${obj.name }</td>
        <td align="center" class="cell"><img src="../../pic/delete.png" width="22" onclick="delete_1('${obj.id }')"/></td>
      </tr>
      </s:iterator>

      </s:if>
      <s:if test="rpList.size==0">
      	<tr style="background-color:rgb(217,249,255)">
			<td colspan="4" align="center" height="30" style="font-size: 15px;letter-spacing: 5px;">无数据</td>
		</tr>
      </s:if>
    </table>
    <input type="hidden" name="name" id="sname" value=""/>
    </form>
     <div class="spe_div">
      	<input type="button" value="全选" id="btn_1" class="op_btn" onclick="checkAll_1()"/>
      	<input type="button" value="删除" class="op_btn" onclick="deleteAll_1()"/>
      	<input type="button" value="添加" class="op_btn" onclick="insert_1()"/>
     </div>
  </div>
</div>
</body>
</html>
