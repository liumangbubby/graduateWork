<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<title>个人信息申报</title>
<!-- 弹出对话框CSS -->
<link href="../../css/popup.css" rel="stylesheet" type="text/css" />
<link href="../../css/enroll.css" rel="stylesheet" type="text/css" />
<link href="../../js/zebra/zebra_datepicker_metallic.css"
	rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/zebra/zebra_datepicker.js"></script>

<script src="../../js/stuenroll.js"></script>
<script>
$(document).ready(
	function() {
		//JQuery代码
		//$.Zebra_DatePicker('#datepicker');
		$('#datepicker').Zebra_DatePicker(
			{'days' : [ '周日', '周一', '周二', '周三', '周四','周五', '周六' ],
		     'months' : [ '1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月','11月', '12月' ]
			});
			$('#bi').Zebra_DatePicker(
				{'days' : [ '周日', '周一', '周二', '周三', '周四','周五', '周六' ],
				 'months' : [ '1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月','11月', '12月' ]
			});
			$('#birthday').Zebra_DatePicker(
					{'days' : [ '周日', '周一', '周二', '周三', '周四','周五', '周六' ],
				     'months' : [ '1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月','11月', '12月' ]
					});
					$('#bi').Zebra_DatePicker(
						{'days' : [ '周日', '周一', '周二', '周三', '周四','周五', '周六' ],
						 'months' : [ '1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月','11月', '12月' ]
					});
		});
		
	function printEnroll() {
		var pid = prompt("输入身份证编号，您的资料将被打印", "请输入");
		if (pid == null || pid == "" || pid == "请输入") {
			alert("请填写身份证编号！");
		} 
		else {
			var xmlhttp;
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {
				// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var date = new Date();
			xmlhttp.open("GET", "/stuenroll/ajax/PidAjax?pid=" + pid + "&s="
					+ date.getMilliseconds(), false);
			xmlhttp.send();
			var rs = xmlhttp.responseText;
			if (rs == null || rs == "" || rs == "false") {
				alert("报名系统中不存在您的身份证信息，请注册后再打印！");				
			} else if (rs == "havePid") {
				location.href="/stuenroll/jsp/enroll/StuEnrollAction!toPrint.action?pid="+pid;
			} 
		}
	}
	function changeType() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var date = new Date();
		var url = "/stuenroll/ajax/StuEnrollBatchAjax?s=" + date.getMilliseconds()
				+ "&t=specName&typeId=-1";
		xmlhttp.open("POST", url, false);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("spec="+document.getElementById("specialtySubmit").value);
		var rs = xmlhttp.responseText;
		var eduSchool = document.getElementById("eduSchool");
		var school = document.getElementById("school");
		eduSchool.length = 0;
		school.innerHTML="";
		var y = document.createElement('option');
		y.text = "-选择-";
		y.value = "";
		try {
			eduSchool.add(y, null); 
		} catch (ex) {
			eduSchool.add(y); // IE only
		}
		if (rs != null && rs != '') {
			
			var temp = rs.split("#");
			for (i = 0; i < temp.length; i++) {
				var y = document.createElement('option');
				y.text =temp[i];
				y.value =temp[i];
				try {
					eduSchool.add(y, null); // standards compliant
				} catch (ex) {
					eduSchool.add(y); // IE only
				}
			}
		} 

	}
</script>

</head>

<body class="body">

	<div class="body_div">
		<div class="p_1">机动车驾驶证考试系统</div>
		<div class="p_2">学员登记表</div>
		<div>
			<form name="form_1" method="post"
				action="/stuenroll/jsp/enroll/StuEnrollAction!doEnroll.action">
				<table width="85%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="table_1">
					<tr>
						<td width="113" class="td_1">姓名</td>
						<td width="110" class="td_1" id="t1">
							<input name="name" type="text" class="in_1" id="name" maxlength="4" onchange="checkName()" onclick="blurAll()" />
						</td>
						<td width="70" class="td_1">性别</td>
						<td width="70" class="td_1" id="t2" onclick="clickSex()">
							<span id=sx></span> 
							<select name="sex" id="sex" class="in_11" onchange="checkSex();blurSex()" onblur="blurSex()">
								<option value="" style="line-height: 30px;">-选择-</option>
								<option value="男">男</option>
								<option value="女">女</option>
						</select>
						</td>
						<td width="77" class="td_1">民族</td>
						<td width="109" class="td_1" onclick="clickNation()" id="t3">
							<span id="nat" ></span>
							 <select id="nation" name="nation" class="in_11" onchange="checkNation();blurNation()" onblur="blurNation()">
								<option value=''>-选择-</option>
								<s:iterator id="object" value="nationList">
									<option value="${object.nation}">${object.nation}</option>
								</s:iterator>
							</select>
						</td>
						<td width="152" rowspan="4" class="td_3">
							<!-- 照片 -->
							照&nbsp;&nbsp;片
							
						</td>
					</tr>
					<tr>
						<td class="td_1">身份证号</td>
						<td colspan="5" class="td_1" id="t9"><input name="pid"
							type="text" class="in_4" id="pid" onchange="checkPid_2()"
							onclick="blurAll()" />
						</td>
					</tr>
					<tr>
						<td class="td_1">工作单位</td>
						<td colspan="5" class="td_1" id="t4" onclick="clickGraduteG()">
						<input name="graduteG" id="graduteG" type="text" class="in_5" onchange="checkGraduteG()" maxlength="30" onblur="blurGraduteG()" />
							<label id="grad" style="float: left;word-wrap: break-word;word-break: normal;width: 250px;text-align: left; margin-left: 10px;font-family: Helvetica, Arial, sans-serif;"></label>		
							
						</td>
						<!-- 
						<td class="td_1">报名时间</td>
						<td class="td_1" onclick="clickGraduteY()" id='t13'><span
							id="gradY"></span> <select id="graduteY" name="graduteY"
							class="in_12" onblur="blurGraduteY()" onchange="checkGraduteY();blurGraduteY()">
								<option value="">-选择-</option>
								<%
									if (application.getAttribute("yearEnroll") != null) {
										String yearEnroll = (String) application.getAttribute("yearEnroll");
										int year = Integer.parseInt(yearEnroll);
								%>
								<option value="<%=year - 2%>"><%=year - 2%>届
								</option>
								<option value="<%=year - 1%>"><%=year - 1%>届
								</option>
								<option value="<%=year%>"><%=year%>届
								</option>
								<%
									}
								%>
						</select>
						</td>
						 -->
					</tr>
					<tr>
						<td class="td_1">报名时间</td>
						<td colspan="3" class="td_1" onclick="clickGraduteD()" id="t15" align="left">
							<span id='gradT' style="position: relative; left: -40px;font-family: Helvetica, Arial, sans-serif;font-size: 14px;"></span> 
							<span style="position: relative; left: -35px; display: none;" id="da" tabindex="2" onblur="blurGraduteD()"> 
								<input type="text" id="datepicker" name="datepicker" class="in_15" onchange="checkDatepicker()" /> 
							</span>
						</td>
						<td class="td_1">工作类型</td>
						<td class="td_1" id="t5" onclick="clickEducation()"><span
							id="edu"></span> 
							<select class="in_12" name="education" id="education" onblur="blurEducation();checkEducation();" onchange="checkEducation()">
								<option value="">-选择-</option>
								<option value="市场营销">市场营销</option>
								<option value="媒体广告">媒体广告</option>
								<option value="软件网络">软件网络</option>
								<option value="后勤文秘">后勤文秘</option>
								<option value="企业高管">企业高管</option>
								<option value="生产质检">生产质检</option>
								<option value="机械工程">机械工程</option>
								<option value="财会审计">财会审计</option>
								<option value="金融投资">金融投资</option>
								<option value="房产物业">房产物业</option>
								<option value="交通物流">交通物流</option>
								<option value="劳动家政">劳动家政</option>
								<option value="店员导购">店员导购</option>
								<option value="教育教练">教育教练</option>
								<option value="资讯情报">资讯情报</option>
								<option value="学术科研">学术科研</option>
								<option value="法律产权">法律产权</option>
								<option value="艺术设计">艺术设计</option>
								<option value="影视新闻">影视新闻</option>
								<option value="餐饮娱乐">餐饮娱乐</option>
								<option value="化工资源">化工资源</option>
								<option value="医疗保健">医疗保健</option>
								<option value="语言翻译">语言翻译</option>
								<option value="公务员">公务员</option>
								<option value="环保园林">环保园林</option>
								<option value="农林牧渔">农林牧渔</option>
								<option value="学生及其他">学生及其他</option>
						</select>
						</td>
					</tr>
					<tr>
					<!-- 
						<td class="td_1">工作类型</td>
						<td colspan="3" class="td_1" onclick="clickSpecialty()" id='t16'>
							<span id='spec' class='in_13'></span> 
							<select id="specialty" class="in_13" name="specialty" onblur="blurSpecialty()" onchange="checkSpecialty()">
								<option value="">-选择-</option>
								<option value="哲学">哲学</option>
								<option value="经济学">经济学</option>
								<option value="法学">法学</option>
								<option value="教育学">教育学</option>
								<option value="文学">文学</option>
								<option value="历史学">历史学</option>
								<option value="理学">理学</option>
								<option value="农学">农学</option>
								<option value="医学">医学</option>
								<option value="军事学">军事学</option>
								<option value="管理学">管理学</option>
							</select>
						</td>
						<td class="td_1" colspan="2">健康状况</td>
						<td class="td_3"  id="t11" onclick="clickHealthy()">
							<span id="heal" style="display: none;"></span>
							<select id='healthy' name='healthy' class="in_17"  onblur="blurHealthy()"  onchange="checkHealthy();blurHealthy()">
								<option value="">-选择-</option>
								<option value="健康">健康</option>
								<option value="残疾">残疾</option>
							</select>
						</td>
						 -->
						<td class="td_1">健康状况</td>
						<td class="td_1"  id="t11" onclick="clickHealthy()" colspan="3">
							<span id="heal" style="display: none;"></span>
							<select id='healthy' name='healthy' class="in_17"  onblur="blurHealthy()"  onchange="checkHealthy();blurHealthy()">
								<option value="">-选择-</option>
								<option value="健康">健康</option>
								<option value="残疾">残疾</option>
							</select>
						</td>
					<!-- 
						<td class="td_1">政治面貌</td>
						<td colspan="3" class="td_1" onclick="clickPolitics()" id="t17">
							<span id="poli" class="in_14"></span> 
							<select id="politics" name="politics" class="in_14" onblur="blurPolitics()" onchange="checkPolitics();blurPolitics()">
								<option value="">-选择-</option>
								<option value="中共党员">中共党员</option>
								<option value="中共预备党员">中共预备党员</option>
								<option value="共青团员">共青团员</option>
								<option value="民革党员">民革党员</option>
								<option value="民盟盟员">民盟盟员</option>
								<option value="民建会员">民建会员</option>

								<option value="民进会员">民进会员</option>
								<option value="农工党党员">农工党党员</option>
								<option value="致公党党员">致公党党员</option>
								<option value="九三学社社员">九三学社社员</option>
								<option value="台盟盟员">台盟盟员</option>
								<option value="无党派民主人士">无党派民主人士</option>
								<option value="群众">群众</option>
						</select>
						</td>
						 -->
						<td colspan="2" class="td_1">出生日期</td>
						<td class="td_3" id="t18" onclick="clickBirt()">
						<span id='birtT' style="font-family: Helvetica, Arial, sans-serif;font-size: 14px;"></span>
						<span style="position: relative; left: -35px; display: none;" id="birt" tabindex="2" onblur="blurBirt()"> 
								<input type="text" id="birthday" name="birthday" class="in_15" onchange="checkBirt()" /> 
						</span>
						</td>
					</tr>
					<tr>
						<td class="td_1">现居住地</td>
						<td colspan="3" class="td_1" id="t6" onclick="clickAddress()">
							<label id="addr" style="float: left;word-wrap: break-word;word-break: normal;width: 250px;text-align: left; margin-left: 10px;font-family: Helvetica, Arial, sans-serif; "></label>
							<input name="address" id="address" type="text" class="in_5" maxlength="30" onblur="blurAddress()" onchange="checkAddress()" />
						</td>
						<td colspan="2" class="td_1">户籍所在地</td>
						<td class="td_3" id="t19" onclick="clickHousehold()">
							<label id="hous"  style="float: left;word-wrap: break-word;word-break: normal;width: 150px;text-align: left; margin-left: 10px; font-family: Helvetica, Arial, sans-serif;"></label>
							<input id="household" name="household" type="text" class="in_7" onblur="blurHousehold();" maxlength="30" onchange="checkHousehold()" />
						</td>
					</tr>
					<tr>
						<td class="td_1">移动电话</td>
						<td colspan="3" class="td_1" id="t7"><input name="tel"
							id="tel" type="text" class="in_5" maxlength="11"
							onblur="blurAll()" onclick="blurAll()" onchange="checkTel()" />
						</td>
						<td colspan="2" class="td_1">E-mail</td>
						<td class="td_3" id="t10" onclick="clickEmail()">
							<label id="eml"  style="float: left;word-wrap: break-word;word-break: normal;width: 140px;text-align: left; margin-left: 10px; font-family: Helvetica, Arial, sans-serif;"></label>
							<input name="email" id='email' type="text" class="in_7" maxlength="20" onblur="blurEmail();" onchange="checkEmail()" />
						</td>
					</tr>
					<tr>
						<td class="td_1">家庭住址</td>
						<td colspan="3" class="td_1" id="t20" onclick="clickHomeAddress()">
							<label id="haddr" style="float: left;word-wrap: break-word;word-break: normal;width: 250px;text-align: left; margin-left: 10px;font-family: Helvetica, Arial, sans-serif; "></label>
							<input name="homeAddress" id="homeAddress" type="text" class="in_5" maxlength="30" onblur="blurHomeAddress()" onchange="checkHomeAddress()"/>
						</td>
						<td colspan="2" class="td_1">家庭联系电话</td>
						<td class="td_3" id='t8'><input name="homeTel" id="homeTel"
							type="text" class="in_7" maxlength="30" onblur="blurAll()"
							onclick="blurAll()" onchange="checkHomeTel()" />
						</td>
					</tr>
					<tr>
						<td rowspan="1" class="td_1">拟报考驾<br />驶证类型</td>
						<td colspan="3"class="td_1" onclick="clickSpecr()" id="t21">
							<span id="specr" class="in_14"></span> 
							<select name="specialtySubmit" id="specialtySubmit" class="in_14" tabindex="2" 
							style="width: 180px; position: relative; left: -32px; background-color: #ffffff" onblur="blurSpecr()" onchange="checkSpecr();blurSpecr();changeType();">
								<option value="">- 选择 -</option>
								<s:iterator id="obj" value="spList">
									<option value="${obj.name}">${obj.name}</option>
								</s:iterator>
						</select>
						</td>
						<td class="td_1" colspan="2">拟考试地点</td>
						<!-- 
						<td class="td_3" id="t12">
						<input name="intention"
							type="text" class="in_6" id="intention" maxlength="10"
							onchange="checkIntention()" onclick="blurAll()" />
						</td>
						 -->
						<td class="td_3"  id="t22" onclick="clickPlace()">
							<span id="pla" style="display: none;"></span>
							<select id='place' name='place' class="in_17"  onblur="blurPlace()"  onchange="checkPlace();blurPlace()">
								<option value="">-选择-</option>
								<s:iterator id="obj" value="placeList">
									<option value="${obj.place}">${obj.place}</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td rowspan="1"  class="td_1">备注</td>
						<td colspan="6" id="t30" class="td_3" onclick="blurAll()">
							<input type="text" name="remark" class="in_4" style="width: 580px;" maxlength="43"/>
						</td>
					</tr>
					<tr>
						<td class="td_1" style="height: 260px">
						<p style="writing-mode:tb-lr;margin-left: 20px;font-size: 13px;letter-spacing: 5px;line-height: 28px;">省级审核机构<br>审核意见</p>
						</td>
						<td colspan="6" class="td_3">
							<div style="position: relative;left: 200px;top:100px;">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</div>
						</td>
					</tr>
					<tr>
						<td class="td_2">备注</td>
						<td colspan="4" class="td_4" style="border-right: #666 solid 1px;" >
							
						</td>
						<td class="td_4" style="border-right: #666 solid 1px;" >学生签名</td>
						<td class="td_4" ></td>
					</tr>
				</table>
			</form>
		</div>
		<center>
			<div class="hr"></div>
		</center>
		<div align="center" class="div_1">
			<button class="btn_1" id="btnEnroll" title="popup_name"
				value="#?w=420">确认提交</button>
			<button class="btn_2">报名须知</button>
			<!-- 
			<button class="btn_3" onclick="printEnroll()">报名打印</button>
			 -->
			<input name="sd" type="button" class="btn_4" value="帮助说明" />
		</div>
		<center>
			<div class="hr"></div>
		</center>
		<div align="center" class="div_2">
			<p>北京市公安局交通警察支队车辆管理所</p>
			<p>地址：北京市*********</p>
		</div>
		<p></p>
	</div>

	<!-- 添加班级对话框 -->
	<div id="popup_name" class="popup_block">
		<p style="font-size: 28px; font-weight: bold; margin: 0px 0 10px 0">输入验证码</p>

		<table border="0" cellpadding="0" cellspacing="0"
			style="font-size: 14px; letter-spacing: 2px;">
			<tr>
				<td height="50" width="40"></td>
				<td><input name="answer" id="answer"
					style="width: 295px; height: 28px; margin-bottom: 10px; margin-top: 10px;" />
					<span id="capic"><img src="/stuenroll/simpleCaptcha.png"
						onclick="changePic();" /> </span>
				</td>
			</tr>
			<tr>
				<td></td>
				<td style="font-size: 14px;" height="25">提示：点击验证码图片即可更新图片</td>
			</tr>
			<tr>
				<td height="60"></td>
				<td><input type="button" value="确定" onclick="checkCapt()"
					class="btn_1" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
