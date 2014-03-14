<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>班级状态管理</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<!-- 可搜索的下拉列表CSS -->
<link href="../../js/chosen/chosen.css" rel="stylesheet" type="text/css" />
<!-- 弹出对话框CSS -->
<link href="../../css/popup.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<!-- 可搜索的下俩列表JS -->
<script src="../../js/chosen/chosen.jquery.js"></script>
<script src="../../js/nav.js"></script>
<script src="../../js/class_info.js"></script>
<style>
.class_div{
	overflow:auto;padding-left: 20px;
}
.class_table {
	margin: 20px;
	border: 1px solid #ccc;
	border-collapse: collapse;
}
.class_title{
	font-size:24px;
	font-weight:bold;
	font-family:"新宋体",'宋体';
}
.class_select {
	width: 235px;
	height: 30px;
	padding-left:10px;
	font-family: 'Arial';
	font-size: 13px;
	letter-spacing: 1px;
}
</style>
<script>
	$(document).ready(
		function() {			
			//设置下俩列表
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true});
			//专业下拉列表改变
			$('#type').change(function(){
				var type=$('#type').val();
				$('#ssh').load("/stuenroll/jsp/backyard/EduSchoolAction!selectRefEduBySpecAjax.action",{"name":type,"isRecord":"false"},function(resp,status){
					if(status=="success"){
						$('#ssh').empty();
						var html="<select id='school' name='school' class='class_select chzn-select' tabindex='2'>";
						html+="<option value=''>-选择-</option>";
						var temp=resp.split('#');
						for(i=0;i<temp.length;i++){
							html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
						}
						$('#ssh').append(html);
						$(".chzn-select").chosen(); 
						$(".chzn-select-deselect").chosen({allow_single_deselect:true});
						schoolChange();
						loadClass();
					}
				});
				
				function schoolChange(){
					$('#school').change(function(){
						loadClass();
						
					});
				}
				function loadClass(){
					var type=$('#type').val();
					var school=$('#school').val();
					$('#ssc').load("/stuenroll/jsp/backyard/ClassInfoAction!selectClassInfoAjax.action",{'type':type,'school':school,"isRecord":"false"},function(resp,status){
						if(status=='success'){
							$('#ssc').empty();
							var html="<select name='name' class='class_select chzn-select' tabindex='2' style='width:180px;'>";
							html+="<option value=''>-选择-</option>";
							var temp=resp.split('#');
							for(var i=0;i<temp.length;i++){
								html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
							}
							html+="</select>";
							$('#ssc').append(html);
							$(".chzn-select").chosen(); 
							$(".chzn-select-deselect").chosen({allow_single_deselect:true});
						}
					});
					
				}
				
			});
		});
</script>
</head>

<body class="body">
<div class="main_div">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div class_div">
    <p class="class_title">班级信息管理</p>
    <hr />
    <form id="form_2" method="post" action="/stuenroll/jsp/backyard/ClassInfoAction!select.action">
    	<table class='search_table' style="margin:20px;" onmouseover="inSearchArea=true;" onmouseout="inSearchArea=false;">
    		<tr>
    			<td width="60">专业方向</td>
    			<td width="250">
    				<select id='type' name='type' class="class_select chzn-select" tabindex="2" style="width:180px;">
    					<option value="">-选择-</option>
    					<s:iterator id="obj" value="reportList">
    						<option value="${obj.name }" ${type==obj.name?'selected=selected':'' }>${obj.name }</option>
    					</s:iterator>
    				</select>
    			</td>
    			<td width="60">学校名称</td>
    			<td width="295">
    				<s:set id="sch" value="school"></s:set>
    				<span id="ssh">
    				<select id='school' name='school' class="class_select chzn-select" tabindex="2">
    					<option value="">-选择-</option>
    					<s:iterator id="obj" value="eduList">
    						<option value="${obj.school}" ${sch==obj.school?'selected=selected':'' }>${obj.school}</option>
    					</s:iterator>
    				</select>
    				</span>
    			</td>
    			<td width="60">班级名称</td>
    			<td width="250">
    				<s:set id="sn" value="name"></s:set>
    				<span id="ssc">
    				<select name='name' class="class_select chzn-select" tabindex="2" style="width:180px;">
    					<option value="">-选择-</option>
    					<s:iterator id="obj" value="classInfoList">
    						<option value="${obj.name}" ${sn==obj.name?'selected=selected':'' }>${obj.name}</option>
    					</s:iterator>
    				</select>
    				</span>
    			</td>
    		</tr>
    	</table>
    </form>
    <hr />
    <form id="form_1" method="post" action="/stuenroll/jsp/backyard/ClassInfoAction!delete.action">
    <table width="950" border="0" cellpadding="0" cellspacing="0" class="class_table">
      <tr>
        <td width="60" height="35" align="center" class="cell_title">
        </td>
        <td width="8%" align="center" class="cell_title">序&nbsp;&nbsp;号</td>
        <td width="15%" align="center" class="cell_title">班级名称</td>
        <td width="12%" align="center" class="cell_title">专业方向</td>
        <td width="24%" align="center" class="cell_title">培训学校</td>
        <td width="10%" align="center" class="cell_title">状&nbsp;&nbsp;态</td>
        <td width="10%" align="center" class="cell_title">人&nbsp;&nbsp;数</td>
        <td align="center" class="cell_title">操&nbsp;&nbsp;作</td>
      </tr>
      <s:if test="classList.size>0">
      	<s:iterator id="obj" value="classList" status="st">
      	<tr style="background-color: ${st.index%2!=0?'#fcfcfc':'#E3FAE3'}">
      		<td height="32" align="center" class="cell">
        		<input name="classId" id="classId_${obj.id}" type="checkbox" value="${obj.id }" />
        	</td>
        	<td align="center" class="cell_num"><s:property value='#st.index+1'/></td>
        	<td align="center" class="cell" name="className">${obj.name }</td>
        	<td align="center" class="cell" name="classType">${obj.type }</td>
        	<td align="center" class="cell" name="classSchool">${obj.school }</td>
        	<td align="center" class="cell" name="classSchool">${obj.archives=="true"?"已归档":"未归档" }</td>
        	<td align="center" class="cell" name="classSchool">
        	${obj.archives=="true"?obj.num:"报名中"}${obj.archives=="true"?"人/班":""}</td>
        	<td align="center" class="cell">
         		
        		<a style="position: relative;cursor: pointer;;top: -5px;${obj.archives=='true'?'visibility: hidden;':'' }" 
        			onclick="submitFormArchives('${obj.name }')">归档该班级</a>&nbsp;
        		<img src="../../pic/delete.png" width="22" onclick="deleteClass('${obj.id }')"/>
        	</td>
      	</tr>
      	</s:iterator>
      	</s:if>
      	<s:if test="classList.size==0">
      	<tr style="background-color:rgb(217,249,255)">
			<td colspan="8" align="center" height="30" style="font-size: 15px;letter-spacing: 5px;">无数据</td>
		</tr>
      	</s:if>
    </table>
    <input type="hidden" name="state" id="st" value=""/>
    </form>
    <div class="class_div">
      	<input type="button" value="全选" class="op_btn" onclick="selectAll()"/>
      	<input type="button" value="删除" class="op_btn" onclick="deleteClass()"/>
      	<button class="op_btn" id="btn_1" title="popup_name" value="#?w=420">添加</button>
     </div>
  </div>
  <!-- 添加班级对话框 -->
  <div id="popup_name" class="popup_block">
  	<p style="font-size: 28px;font-weight: bold;margin: 0px 0 10px 0">班级信息添加</p>
  	<form method="post" id="form_3" action="/stuenroll/jsp/backyard/ClassInfoAction!add.action">
  	<table border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;">
  		<tr>
  			<td height="50">专业方向：</td>
  			<td id="sp">
  				<select name='type' id="classType" class="class_select chzn-select" tabindex="2" style="width: 286px;">
    					<option value="">-选择-</option>
    					<s:iterator id="obj" value="reportList">
    						<option value="${obj.name }">${obj.name }</option>
    					</s:iterator>
    			</select>
  			</td>
  		</tr>
  		<tr>
  			<td height="50">培训机构：</td>
  			<td><span id='csl'>
  				<select id="classSchool"  class="class_select chzn-select" tabindex="2" style="width: 286px;" >
    				<option value="">-选择-</option>
    				<s:iterator id="obj" value="eduList">
    					<option value="${obj.school}" ${sch==obj.school?'selected=selected':'' }>${obj.school}</option>
    				</s:iterator>
    			</select>
    			</span>
    			<input type="hidden" name="school" id='sh'/>
  			</td>
  		</tr>
  		<tr>
  			<td height="50" width="85">班级编号：</td>
  			<td><input name="name" id='className' class="input" style="width: 290px;" readonly="readonly"/></td>
  		</tr>
  		<tr>
  			<td height="60"></td>
  			<td>
  				<input type="button" value="添加" onclick="addClass()" class="btn_1"/>
  				<input type="button" id="cancel" value="取消" class="btn_1"/>
  			</td>
  		</tr>
  	</table>
  	</form>
  </div>
</div>
<form action="/stuenroll/jsp/backyard/ClassInfoAction!updateArchivesClass.action" method="post" id="form_4">
	<input type="hidden" name="classNo" value="" id="classNo"/>
</form>
</body>
</html>