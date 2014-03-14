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
	height: 25px;
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
	
	<div class="main_div">
		<jsp:include page="left_nav.jsp"></jsp:include>
		<div class="right_div">
			<div style="overflow: auto; height: 750px;">
			<form name="form_1" method="post" action="/stuenroll/jsp/backyard/StuRecordAction!updateStuRecord.action">
				<input type="hidden" name="id" value="${enroll.id }"/>
				
				<fieldset class="set"  style="width: 690px;">
					<legend class="leg">申报信息修改</legend>
				
					<table width="650" border="0" cellpadding="0" cellspacing="0"
						class="modify_table">
						<tr id="tr_name">
							<td width="110" height="52" align="right" class="modify_td">姓名：</td>
							<td class="modify_td">
							 	<input name="name" type="text" class="modify_in_1" id="name" value="${enroll.name}" maxlength="4"/>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">性别：</td>
							<td class="modify_td" style="font-family: '新宋体','宋体';font-size: 12px;padding-left: 25px;">
								<input type="radio" name="sex" value="男" ${enroll.sex=='男'?'checked=checked':''}/>男 
								<input type="radio" name="sex" value="女" ${enroll.sex=='女'?'checked=checked':''}/>女
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">民族：</td>
							<td class="modify_td">
								<select name="nation" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="object" value="nationList">
										<option ${enroll.nation==object.nation?'selected=selected':''}>${object.nation}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr id="tr_pid">
							<td height="52" align="right" class="modify_td">身份证号：</td>
							<td class="modify_td">
								<input name="pid" type="text" id="pid" class="modify_in_1 modify_num" value="${enroll.pid}"/>
							</td>
						</tr>
						<tr id='tr_gradute_g'>
							<td height="52" align="right" class="modify_td">毕业院校：</td>
							<td class="modify_td">
								<input name="gradute_g" id="gradute_g" type="text" class="modify_in_1" value='${enroll.gradute_g }' />
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">毕业年届：</td>
							<td class="modify_td">
								<select name="gradute_y" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="yearList">
										<option ${obj==enroll.gradute_y?'selected=selected':''}>${obj}</option>
									</s:iterator>
							</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">毕业时间：</td>
							<td class="modify_td">
								<input type="text" id="datepicker" name="gradute_d" class="modify_date"  value="${enroll.gradute_d }"/>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">学历：</td>
							<td class="modify_td">
								<select name="education" class="modify_select chzn-select" tabindex="2">
									<option value="普通高校专科" ${enroll.education=='普通高校专科'?'selected=selected':'' }>普通高校专科</option>
									<option value="普通高校本科" ${enroll.education=='普通高校本科'?'selected=selected':'' }>普通高校本科</option>
									<option value="普通高校研究生及以上" ${enroll.education=='普通高校研究生及以上'?'selected=selected':'' }>普通高校研究生及以上</option>
							</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">所学专业：</td>
							<td class="modify_td">
								<select name="specialty" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="specialtyList">
										<option value="${obj.specialty}" ${enroll.specialty==obj.specialty?'selected=selected':'' }>${obj.specialty}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">政治面貌：</td>
							<td class="modify_td">
								<select name="politics" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="politicsList">
										<option value="${obj.politics}" ${enroll.politics==obj.politics?'selected=selected':'' }>${obj.politics}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">出生日期：</td>
							<td class="modify_td">
								<input type="text" id="birthday" name="birthday" class="modify_date" value="${enroll.birthday }"/>
							</td>
						</tr>
						<tr id="tr_address">
							<td height="52" align="right" class="modify_td">现居住地：</td>
							<td class="modify_td">
								<input name="address" id="address" type="text" class="modify_in_1" value="${enroll.address }" maxlength="30"/>
							</td>
						</tr>
						<tr id='tr_household'>
							<td height="52" align="right" class="modify_td">户籍所在地：</td>
							<td class="modify_td">
								<input name="household" id='household' type="text" class="modify_in_1" value="${enroll.household}" maxlength="30" />
							</td>
						</tr>
						<tr id='tr_tel'>
							<td height="52" align="right" class="modify_td">移动电话：</td>
							<td class="modify_td">
								<input name="tel" id='tel' type="text" class="modify_in_1 modify_num" value="${enroll.tel}" />
							</td>
						</tr>
						<tr id='tr_email'>
							<td height="52" align="right" class="modify_td">Email：</td>
							<td class="modify_td">
								<input name="email" id='email' type="text" class="modify_in_1 modify_num" value="${enroll.email}" />
							</td>
						</tr>
						<tr id='tr_home_address'>
							<td height="52" align="right" class="modify_td">家庭住址：</td>
							<td class="modify_td">
								<input name="home_address" id='home_address' type="text" class="modify_in_1" value="${enroll.home_address}" maxlength="30"/>
							</td>
						</tr>
						<tr id='tr_home_tel'>
							<td height="52" align="right" class="modify_td">家庭电话：</td>
							<td class="modify_td">
								<input name="home_tel" id='home_tel' type="text" class="modify_in_1 modify_num" value="${enroll.home_tel}" maxlength="30" />
							</td>
						</tr>
						<!-- 
						<tr>
							<td height="52" align="right" class="modify_td">有何特长：</td>
							<td class="modify_td">
								<input name="skilled" type="text" class="modify_in_1" value="${enroll.skilled}" />
							</td>
						</tr>
						 -->
						<tr id='tr_healthy'>
							<td height="52" align="right" class="modify_td">健康状况：</td>
							<td class="modify_td">
								<input name="healthy" id='healthy' type="text" class="modify_in_1" value="${enroll.healthy}" maxlength="4"/>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">拟申报专业：</td>
							<td class="modify_td">
								<select name="specialty_submit" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="reportList">
										<option value="${obj.name}" ${enroll.specialty_submit==obj.name?'selected=selected':'' }>${obj.name}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">拟培训地点：</td>
							<td class="modify_td">
								<select name="place" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="placeList">
										<option value="${obj.id}" ${enroll.place==obj.place?'selected=selected':'' }>${obj.place}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<!-- 
						<tr>
							<td height="52" align="right" class="modify_td">同意变更地点：</td>
							<td class="modify_td">
								<input type="checkbox" value="true" name="change_place" ${enroll.change_place=='true'?'checked=checked':'' }/>是
							</td>
						</tr>
						
						<tr id='tr_intention'>
							<td height="52" align="right" class="modify_td">拟求职意向：</td>
							<td class="modify_td">
								<input name="intention" id='intention' type="text" class="modify_in_1" value="${enroll.intention}" maxlength="10"/>
							</td>
						</tr>
						 -->
						<tr>
							<td height="52" align="right" class="modify_td">备注：</td>
							<td class="modify_td">
								<textarea name="remark" style="width:450px;height: 100px;margin: 10px 0 10px 0px;;">${enroll.remark}</textarea>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">培训学校：</td>
							<td class="modify_td">
								<select name="edu_school" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="eduList">
										<option value="${obj.school}" ${enroll.edu_school==obj.school?'selected=selected':'' }>${obj.school}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">班级：</td>
							<td class="modify_td">
								<select name="class_no" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="infoList">
										<option value="${obj.name}" ${enroll.class_no==obj.name?'selected=selected':'' }>${obj.name}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr id='tr_stu_no'>
							<td height="52" align="right" class="modify_td">小序号：</td>
							<td class="modify_td">
								<input name="stu_no" id="stu_no" type="text" class="modify_in_1" value="${enroll.stu_no}" />
							</td>
						</tr>
						<tr>
							<td height="52" align="right" class="modify_td">参与状态：</td>
							<td class="modify_td">
								<select name="join_state" class="modify_select chzn-select" tabindex="2">
									<s:iterator id="obj" value="joinList">
										<option value="${obj.state}" ${enroll.join_state==obj.state?'selected=selected':'' }>${obj.state}</option>
									</s:iterator>
								</select>
							</td>
						</tr>
					</table>
					<p align="center">
						<input type="button" class="btn_1" value="保存更新" onclick="submitUpdateEnroll()"/> 
						<input type="reset" class="btn_1" value="取消更改" />
					</p>
				</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>