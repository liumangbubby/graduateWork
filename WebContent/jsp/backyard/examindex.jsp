<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>预报名记录管理</title>
		<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
		<!-- 可搜索的下拉列表CSS -->
		<link href="../../js/chosen/chosen.css" rel="stylesheet" type="text/css" />
		<!-- 弹出对话框CSS -->
		<link href="../../css/popup.css" rel="stylesheet" type="text/css" />
		<script src="../../js/jquery-1.6.4.js"></script>
		<!-- 可搜索的下俩列表JS -->
		<script src="../../js/chosen/chosen.jquery.js"></script>
		<script src="../../js/backyard2.js"></script>
		<script src="../../js/index.js"></script>
		<script>
			$(document).ready(function() {			
				//设置下俩列表
				$(".chzn-select").chosen(); 
				$(".chzn-select-deselect").chosen({allow_single_deselect:true});
			});
		</script>
	</head>

<body class="body">
	<input type="hidden" id="isAdmin" value="${sessionScope.school==null?true:false}">
	<div class="main_div">
		<jsp:include page="left_nav.jsp"></jsp:include>
		<div class="right_div" style="-moz-user-select: none; -webkit-user-select: none; ">
			<form
				action="/stuenroll/jsp/backyard/ExamAction!searchExams.action"
				name="searchForm" method="post" id="searchForm">
				<input type="hidden" name="order" value="${order==null||order==''?1:order}" id="order">
				<input type="hidden" name="stype" id="stype"> <input
					type="hidden" name="toPage" id="to">
				<fieldset class="set" onmouseover="inSearch()"
					onmouseout="outSearch()">
					<legend class="leg">检索条件</legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="search_table">
						<tr>
							<td width="10%" align="center">题目内容</td>
							<td width="23%">
								<input name="context" type="text" class="input" style="width:220px;" id="textfield3" value="${pid}" title="点击回车键搜索记录"/>
							</td>
							<td width="11%" align="center">试题内容类型</td>
							<td width="24%">
								<span id='a1'>
								<select name="specialtySubmit" id='specialtySubmit' class="chzn-select" tabindex="2" style="width:230px;">
									<option value="">- 选择 -</option>
									<s:iterator id="obj" value="spList">
										<option value="${obj.name}" ${specialtySubmit==obj.name?'selected="selected"':'' } >${obj.name}</option>
									</s:iterator>
								</select>
								</span>
							</td>
						</tr>
						<tr>
							<td height="45" align="center">试题类型</td>
							<td>
								<span id='a2'>
								<%if(session.getAttribute("school")==null){%>
								<select name="eduSchool" id='eduSchool' class="chzn-select" tabindex="2" style="width:230px;">
									<option value="">- 选择 -</option>
									<option value="1" >判断题</option>
									<option value="2" >选择题</option>
								</select>
								<%}else{
									%>
									<input name="eduSchool" id='eduSchool' type="text" class="input" style="width:220px;" value='${sessionScope.school }' readonly="readonly">
									<%
								} %>

								</span>
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
			<table width="1050" border="0" cellpadding="0" cellspacing="0" class="info_table" style="-moz-user-select: none;-webkit-user-select: none;">
				<tr>
					<td width="80" height="30" id="daxuhao" align="center" class="cell cell_title" onclick="orderDaXuHao()" style="cursor: pointer">
					${order==null||order==''||order==1?'大序号↑':''}
					${order==2?'大序号↓':''}
					${order==3||order==4||order=='7'||order=="8"?'大序号':''}
					</td>
					<td width="222" align="center" class="cell cell_title">题目内容</td>
					<td width="106" align="center" class="cell cell_title">题目种类</td>
					<td width="106" align="center" class="cell cell_title">题目类型</td>
				</tr>

				<s:iterator id="obj" value="enrollList" status="st">
					<tr class="row" id="pid_${obj.exam_id}" name='rw' onclick="clickRow('${obj.exam_id}')" style="background-color: ${st.index%2!=0?'#fcfcfc':'#E3FAE3'}">
						<td height="30" align="center" name="t" class="cell cell_num">${obj.exam_id}</td>
						<td align="center" id='js_${obj.exam_id }' class="cell" >${obj.exam_body}</td>
						<td align="center" class="cell" ><s:if test="#obj.exam_type == 2">选择题</s:if><s:elseif test="#obj.exam_type == 1">判断题</s:elseif></td>
						<td class="cell cell_num" align="center" id='class_no_${obj.exam_id}'>
							<s:if test="#obj.exam_class == 1">A1</s:if>
							<s:elseif test="#obj.exam_class == 2">A2</s:elseif>
							<s:elseif test="#obj.exam_class == 3">A3</s:elseif>
							<s:elseif test="#obj.exam_class == 4">B1</s:elseif>
							<s:elseif test="#obj.exam_class == 5">B2</s:elseif>
							<s:elseif test="#obj.exam_class == 6">C1</s:elseif>
							<s:elseif test="#obj.exam_class == 7">C2</s:elseif>
							<s:elseif test="#obj.exam_class == 8">C3</s:elseif>
							<s:elseif test="#obj.exam_class == 9">C4</s:elseif>
							<s:elseif test="#obj.exam_class == 10">C5</s:elseif>
						</td>
					</tr>
				</s:iterator>

				<s:if test="enrollList.size==0">
					<tr style="background-color: rgb(217, 249, 255)">
						<td colspan="9" align="center" height="590"
							style="font-size: 100px; letter-spacing: 5px;">无数据</td>
					</tr>
				</s:if>
			</table>

			<diV>
				<s:if test="enrollList.size>0">
					<table width="100%" height="50" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="36%">
								 <%
								 if(session.getAttribute("school")==null){
								 %>
								 <input type="button" class="op_btn" id="bt4" style="background-color:#B0DEF8" value="单选" title="单选/多选切换" />
								 <input type="button" class="op_btn" id="bt5" value="添加" title='添加一条记录'/> 
								 <input type="button" class="op_btn" id="bt2" value="删除" title="删除记录"/> 
								 <input type="button" class="op_btn" id="bt3" value="修改" title='修改一条记录'/> 
								<%
								 }
								 %>
								
							</td>
							<td width="48%">
								<s:if test="pageBean.totalPage>0">
									<input name="button5" type="submit" class="page_btn" id="button5" value="首页" onclick="toPageNow('1')"/>
								</s:if> 

								<s:if test="pageBean.hasPrevPage==true">
									<input name="button11" type="submit" class="page_btn" onclick="toPageNow('${pageBean.currentPage-1}')" value="上一页" />
								</s:if> 
								<s:if test="pageBean.hasPrevPage==false">
									<input name="button11" type="submit" class="page_btn" value="上一页" />
								</s:if> 
								<s:if test="pageBean.hasNextPage==true">
									<input name="button12" type="submit" class="page_btn" onclick="toPageNow('${pageBean.currentPage+1}')" value="下一页" />
								</s:if> 
								<s:if test="pageBean.hasNextPage==false">
									<input name="button12" type="submit" class="page_btn"  value="下一页" />
								</s:if> 
								<s:if test="pageBean.totalPage>0">
									<input name="button13" type="submit" class="page_btn" id="button13" onclick="toPageNow('${pageBean.totalPage}')" value="尾页" />
								</s:if>

							</td>
							<td class="go" >
								跳转
								<input id="toPage" type="text" class="page_in" value="${pageBean.currentPage }" onmouseover="inToPageArea=true;" onmouseout="inToPageArea=false;" title='输入页码按回车'/>
								/<span id="totlePage">${pageBean.totalPage}</span>页
							</td>
						</tr>
					</table>
				</s:if>
			</diV>

		</div>
	</div>
	<form name="option" id="option" method="post" action="/stuenroll/jsp/backyard/ExamAction!deleteExam.action">
		<input type="hidden" name="type" id="type_1"> 
		<input type="hidden" name="ids" id="ids">
	</form>
	 <div id="popup_name" class="popup_block">
		<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">试题信息维护</p>
		<form method="post" id="form_3" action="/stuenroll/jsp/backyard/ManageAction!updateStuEnrollBacth.action">
  			<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;">
  			<tr>
  				<td height="50" width="85">申报类型：</td>
  				<td>
  					<select name="specialtySubmit" id='type' style="width:280px;height:32px;" onchange="changeType()">
						<option value="-1">- 选择 -</option>
						<s:iterator id="obj" value="spList">
							<option value="${obj.id}" ${specialtySubmit==obj.name?'selected="selected"':'' } >${obj.name}</option>
						</s:iterator>
					</select>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">培训学校</td>
  				<td>
  					<select name="eduSchool" id="edu"  style="width:280px;height:32px;" onchange="changeEdu('false')">
						<option value="-1">- 选择 -</option>
					</select>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">班级编号：</td>
  				<td>
  					<select name="classNo" id='class'  style="width:280px;height:32px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="classList">
						<option value="${obj.name}" ${classNo==obj.name?'selected="selected"':'' } >${obj.name}</option>
						</s:iterator>
					</select>
  				</td>
  			</tr>
  			<tr>
  				<td height="50" width="85">审核状态：</td>
  				<td>
  					<select name="joinState" id="joinState" style="width:280px;height:32px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="joList">
						<option value="${obj.id}" ${joinState==obj.state?'selected="selected"':'' }>${obj.state}</option>
						</s:iterator>
					</select>
  				</td>
  			</tr>
  			</table>
  			<input type="hidden" name="ids" id="ids_update">
  			<input type="hidden" name="classArray" id="classArray">
  		</form>
  		<center>
	 	<input type="button" value="修改" onclick="submitUpdate()" class="btn_1" style="margin: 10px 0 10px 0"/>
  		<input type="button" id="cancel" value="取消" class="btn_1"/>
  		</center>
	 </div>
	 <div id="exportExcel" class="popup_block">
		<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">导出开班花名册</p>
		<form method="post" id="form_4" action="/stuenroll/jsp/backyard/StuEnrollAction!exportEnroll.action">
  			<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;">
  			<tr>
  				<td height="50" width="85">班级名称：</td>
  				<td>
  					<select name="classNo" id='classNoExport' class="chzn-select" tabindex="2" style="width:230px;">
						<option value="">- 选择 -</option>
						<s:iterator id="obj" value="classList">
							<option value="${obj.name}" ${classNo==obj.name?'selected="selected"':'' } >${obj.name}</option>
						</s:iterator>
					</select>
  				</td>
  			</tr>
  			
  			</table>
  		</form>
  		<center>
	 	<input type="button" value="导出" class="btn_1" id='bt67' style="margin: 10px 0 10px 0"/>
  		<input type="button" id="cancel" value="取消" class="btn_1"/>
  		</center>
	 </div>
</body>

</html>