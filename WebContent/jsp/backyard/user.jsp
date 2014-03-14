<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<!-- 弹出对话框CSS -->
<link href="../../css/popup.css" rel="stylesheet" type="text/css" />
<!-- 可搜索的下拉列表CSS -->
<link href="../../js/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<!-- 可搜索的下拉列表JS -->
		<script src="../../js/chosen/chosen.jquery.js"></script>
<script src="../../js/user.js"></script>
<script src="../../js/nav.js"></script>
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

.input{
	width: 270px;
	height: 26px;
	border:solid 1px #999;
	box-shadow:0px 1px 2px 0px #d0d0d0 inset;
	padding-left: 8px;
}

</style>
</head>

<body class="body">
<div class="main_div">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div spe_div">
    <p class="spe_title">用户管理</p>
    <hr />
    <form id="form_1" method="post" action="/stuenroll/jsp/backyard/UserAction!delete.action">
    <table width="900" border="0" cellpadding="0" cellspacing="0" class="spe_table">
      <tr>
        <td width="41" height="35" align="center" class="cell_title">
        </td>
        <td width="111" align="center" class="cell_title">序号</td>
        <td width="209" align="center" class="cell_title">用户名</td>
        <td width="280" align="center" class="cell_title">培训机构</td>
        <td width="157" align="center" class="cell_title">操作</td>
      </tr>
      <s:if test="userList.size>0">
      <s:iterator id="obj" value="userList" status="st">
      <tr style="background-color: ${st.index%2!=0?'#fcfcfc':'#E3FAE3'}">
      	<td height="32" align="center" class="cell">
        	<input name="userId" id="userId_${obj.id}" type="checkbox" value="${obj.id }" />
        </td>
        <td align="center" class="cell_num"><s:property value='#st.index+1'/></td>
        <td align="center" class="cell" name="username">${obj.username }</td>
        <td align="center" class="cell">${school }</td>
        <td align="center" class="cell"><img src="../../pic/delete.png" width="22" onclick="deleteUser('${obj.id }')"/></td>
      </tr>
      </s:iterator>
      </s:if>
      <s:if test="userList.size==0">
      	<tr style="background-color:rgb(217,249,255)">
			<td colspan="4" align="center" height="30" style="font-size: 15px;letter-spacing: 5px;">无数据</td>
		</tr>
      </s:if>
    </table>
    </form>
    <div class="spe_div">
      	<input type="button" value="全选" id="btn_1" class="op_btn" onclick="selectAll()"/>
      	<input type="button" value="删除" class="op_btn" onclick="deleteUser()"/>
      	<button id="bt5"  class="op_btn" title="popup_name" value="#?w=420">添加</button>
     </div>
     
      
     
     <div id="popup_name" class="popup_block">
		<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">添加新用户</p>
		<form method="post" id="form_3" action="/stuenroll/jsp/backyard/UserAction!insert.action">
  			<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;letter-spacing: 2px;">
  			<tr>
  				<td height="50" width="85">用户名：</td>
  				<td>
  					<input type="text" id='st1' name="username" class='input'/>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">密码</td>
  				<td>
  					<input type="password" id='st2' name="password" class='input'/>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">培训机构：</td>
  				<td>
  					<select name="school" id='school' class="chzn-select" tabindex="2" style="width:278px; ">
						<option value="">- 选择   -</option>
						<s:iterator id='obj' value="schoolList" >
							<option value="${obj.school}">${obj.school}</option>
						</s:iterator>
						
					</select>
  				</td>
  			</tr>
  			</table>
  		</form>
  		<center>
	 	<input type="button" value="保存" id='btn_6' class="btn_1" style="margin: 10px 0 10px 0"/>
  		<input type="button" id="cancel" value="取消" class="btn_1"/>
  		</center>
	 </div>
     
     
  </div>
</div>
</body>
</html>