<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>培训地点管理</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/place.js"></script>
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

</style>
</head>

<body class="body">
<div class="main_div">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div spe_div">
    <p class="spe_title">培训地点管理</p>
    <hr />
    <form id="form_1" method="post" action="/stuenroll/jsp/backyard/PlaceAction!delete.action">
    <table width="700" border="0" cellpadding="0" cellspacing="0" class="spe_table">
      <tr>
        <td width="41" height="35" align="center" class="cell_title">
        </td>
        <td width="111" align="center" class="cell_title">序&nbsp;&nbsp;号</td>
        <td width="289" align="center" class="cell_title">培训地点</td>
        <td width="157" align="center" class="cell_title">操&nbsp;&nbsp;作</td>
      </tr>
      <s:if test="placeList.size>0">
      <s:iterator id="obj" value="placeList" status="st">
      <tr style="background-color: ${st.index%2!=0?'#fcfcfc':'#E3FAE3'}">
      	<td height="32" align="center" class="cell">
        	<input name="placeId" id="placeId_${obj.id}" type="checkbox" value="${obj.id }" />
        </td>
        <td align="center" class="cell_num"><s:property value='#st.index+1'/></td>
        <td align="center" class="cell" name="placeName">${obj.place }</td>
        <td align="center" class="cell"><img src="../../pic/delete.png" width="22" onclick="deletePlace('${obj.id }')"/></td>
      </tr>
      </s:iterator>
      </s:if>
      <s:if test="placeList.size==0">
      	<tr style="background-color:rgb(217,249,255)">
			<td colspan="4" align="center" height="30" style="font-size: 15px;letter-spacing: 5px;">无数据</td>
		</tr>
      </s:if>
    </table>
    <input type="hidden" name="place" id="st" value=""/>
    </form>
    <div class="spe_div">
      	<input type="button" value="全选" id="btn_1" class="op_btn" onclick="selectAll()"/>
      	<input type="button" value="删除" class="op_btn" onclick="deletePlace()"/>
      	<input type="button" value="添加" class="op_btn" onclick="addPlace()"/>
     </div>
  </div>
</div>
</body>
</html>