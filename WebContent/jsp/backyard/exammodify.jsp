<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改申报信息</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<link href="../../js/zebra/zebra_datepicker_metallic.css" rel="stylesheet" type="text/css" />
<!-- 可搜索的下拉列表CSS -->
<link href="../../js/chosen/chosen.css" rel="stylesheet" type="text/css" />

<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/zebra/zebra_datepicker.js"></script>
<!-- 可搜索的下俩列表JS -->
<script src="../../js/chosen/chosen.jquery.js"></script>
<script src="../../js/modify.js"></script>
<script src="../../js/nav.js"></script>
<style>
.modify_table {
	margin: 25px 0 0 20px;
	border: #CCC 1px solid;
	border-collapse: collapse;
}

.modify_in_1 {
	width: 300px;
	height: auto;
	padding-left: 5px;
	border-bottom: solid #333 1px;
	border-left: none;
	border-right: none;
	border-top: none;
	font-family: '新宋体','宋体';
	letter-spacing: 1px;
}
.modify_num{
	font-family: 'Arial';
	font-size: 13px;
	letter-spacing: 1px;
}
.modify_date{
	font-family: 'Arial';
	font-size: 13px;
	letter-spacing: 1px;
	height: 30px;
	padding-left:10px;
	width: 165px;
}

.modify_select {
	width: 180px;
	height: 30px;
	padding-left:10px;
	font-family: 'Arial';
	font-size: 13px;
	letter-spacing: 1px;
}

.modify_td {
	font-size: 13px;
	padding-right: 20px;
	letter-spacing: 2px;
	padding-left: 20px;
	border: #CCC 1px solid;
}

.btn_1 {
	background: #AE0057;
	border: solid 2px #FFF;
	height: 45px;
	width: 150px;
	filter: progid :     DXImageTransform.Microsoft.Shadow (     color =   
		 #909090, direction =     120, strength =     4 );
	-moz-box-shadow: 2px 2px 10px #909090;
	-webkit-box-shadow: 2px 2px 5px #909090;
	-webkit-border-radius: 5px;;
	-moz-border-radius: 5px;;
	border-radius: 5px;
	color: #EEE;
	margin-right: 13px;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 14px;
	letter-spacing: 2px;
}

.btn_1:hover {
	border: solid 2px;
}

.btn_1:active {
	border: solid 2px;
	background: #AE0057;
}
</style>
<script>
	$(document).ready(
		function() {
			//JQuery代码
			//$.Zebra_DatePicker('#datepicker');
			$('#datepicker').Zebra_DatePicker(
					{'days' : [ '周日', '周一', '周二', '周三', '周四','周五', '周六' ],
					'months' : [ '1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月','11月', '12月' ]
					});
			$('#birthday').Zebra_DatePicker(
					{'days' : [ '周日', '周一', '周二', '周三', '周四','周五', '周六' ],
					'months' : [ '1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月','11月', '12月' ]});
			
			//设置下俩列表
			$(".chzn-select").chosen(); $(".chzn-select-deselect").chosen({allow_single_deselect:true});
		});
</script>
</head>

<body class="body">
	
	<div class="main_div" style="-moz-user-select: none; -webkit-user-select: none; ">
		<jsp:include page="left_nav.jsp"></jsp:include>
		<div class="right_div">
			<div style="overflow: auto; height: 750px;">
			<form name="form_1" method="post" action="/stuenroll/jsp/backyard/ExamAction!updateExam.action">
				<input type="hidden" name="id" value="${enroll.id }"/>
				
				<fieldset class="set"  style="width: 690px;">
					<legend class="leg">试题信息修改</legend>
				
					<table width="650" border="0" cellpadding="0" cellspacing="0"
						class="modify_table">
						<tr id="tr_name">
							<td width="110" height="52" align="right" class="modify_td">题目：</td>
							<td class="modify_td">
							 	<textarea name="name" type="text" class="modify_in_1" id="name" cols="20" rows="4">${examlist[0].exam_body}</textarea>
							</td>
						</tr>
						<s:iterator id="obj" value="examlist" status="st">
							<tr>
								<td height="52" align="right" class="modify_td">选项${st.index+1 }：</td>
								<td class="modify_td">
									<input name="choose" type="text" class="modify_in_1" value="${obj.chooses[0].choose_body }" maxlength="40"/>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td height="52" align="right" class="modify_td">正确答案：</td>
							<td class="modify_td">
								<s:iterator id="obj" value="examlist" status="st">
									<s:if test="#obj.chooses[0].right_answer_flag > 0">
										<input name="rightanswer" type="text" class="modify_in_1" value="${obj.chooses[0].right_answer_flag }" maxlength="40"/>
									</s:if>
								</s:iterator>
							</td>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">试题内容类型：</td>
							<td class="modify_td">
								<select name="education" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="spList">
										<option value="${obj.id}" ${examlist[0].exam_class==obj.id?'selected=selected':'' }>${obj.name}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">ID：</td>
							<td class="modify_td">
								 ${examlist[0].exam_id}
							</td>
						</tr>
					</table>
					<p align="center">
						<input type="button" class="btn_1" value="保存更新" onclick="submitUpdateEnroll()"/> 
						<!-- 
						<input type="reset" class="btn_1" value="取消更改" />
						-->
					</p>
				</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>