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
<link href="../../css/enroll.css" rel="stylesheet" type="text/css" />
<script src="../../js/broswer.js"></script>
<script src="../../js/jquery-1.6.4.js"></script>
<script>
	var hkey_root, hkey_path, hkey_key;
	hkey_root = "HKEY_CURRENT_USER";
	hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	//配置网页打印的页眉页脚为空
	function pagesetup_null() {
		try {
			var RegWsh = new ActiveXObject("WScript.Shell");
			hkey_key = "header";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
			hkey_key = "footer";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
			//&b 第&p页/共&P页 &b
		} catch (e) {
		}
	}
	//配置网页打印的页眉页脚为默认值
	function pagesetup_default() {
		try {
			var RegWsh = new ActiveXObject("WScript.Shell");
			hkey_key = "header";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&b页码，&p/&P")
			hkey_key = "footer";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&u&b&d");
		} catch (e) {
		}
	}
	//打印选区内容
	function doPrint() {
		pagesetup_null();
		bdhtml = window.document.body.innerHTML;
		sprnstr = "<!--startprint-->";
		eprnstr = "<!--endprint-->";
		prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
		prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
		window.document.body.innerHTML = prnhtml;
		window.print();
	}
	//打印页面预览
	function printpreview() {
		pagesetup_null();
		//wb.printing.header = "居左显示&b居中显示&b居右显示页码，第&p页/共&P页";
		//wb.printing.footer = "居左显示&b居中显示&b居右显示页码，第&p页/共&P页";
		try {
			wb.execwb(7, 1);
		} catch (e) {
			alert("出于浏览器安全性考虑,请选择浏览器'打印'菜单里面的'打印预览'");
		}
	}
	//打印
	function prints() {
		pagesetup_null();
		//wb.printing.header = "居左显示&b居中显示&b居右显示页码，第&p页/共&P页";
		//wb.printing.footer = "居左显示&b居中显示&b居右显示页码，第&p页/共&P页";
		try {
			wb.execwb(6, 1);
		} catch (e) {
			alert("您的浏览器不支持此功能");
		}
	}
	
	function toPrint(){
		var type=document.getElementById("type").value;
		if(type=="print"){
			//打印
			prints();
		}
		else{
			//预览
			printpreview();
		}
	}
</script>
<style>
	.print_td{
		text-align: left;
		padding-left: 25px;
	}
	.td_1{
	height: 50px;
	}
</style>
</head>

<body class="body" onload="toPrint()">
	<input type="hidden" id="type" value="${type}"/>
	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" id="wb"
		width="0" height="0"></OBJECT>
	<s:if test="enroll!=null">
	<div class="body_div" style="border: none;width: 880px;">
		<div class="p_1">辽宁省高校毕业生专业转换及技能提升培训</div>
		<div class="p_2">学员登记表</div>
		<div style="margin-top: 20px;">
			<table width="88%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="table_1">
				<tr>
					<td width="113" class="td_1">姓名</td>
					<td width="122" class="td_1" id="t1">${enroll.name }</td>
					<td width="70" class="td_1">性别</td>
					<td width="70" class="td_1" id="t2">${enroll.sex}</td>
					<td width="67" class="td_1">民族</td>
					<td width="107" class="td_1" id="t3">${enroll.nation }</td>
					<td width="152" rowspan="4" class="td_3">
						<!-- 照片 -->
					</td>
				</tr>
				<tr>
					<td class="td_1">身份证号</td>
					<td colspan="5" class="td_1 print_td" id="t9">${enroll.pid }</td>
				</tr>
				<tr>
					<td class="td_1">毕业院校</td>
					<td colspan="3" class="td_1 print_td" id="t4">${enroll.gradute_g}</td>
					<td class="td_1">毕业年届</td>
					<td class="td_1" id='t13' class="td_1 print_td">${enroll.gradute_y}</td>
				</tr>
				<tr>
					<td class="td_1">毕业时间</td>
					<td colspan="3" class="td_1 print_td" id="t15" align="left">${enroll.gradute_d}</td>
					<td class="td_1">学历</td>
					<td class="td_1" id="t5">${enroll.education}</td>
				</tr>
				<tr>
					<td class="td_1">所学专业</td>
					<td colspan="3" class="td_1 print_td" onclick="clickSpecialty()" id='t16'>${enroll.specialty}</td>
					<td colspan="2" class="td_1">健康状况</td>
					<td class="td_3" >${enroll.healthy }</td>
				</tr>
				<tr>
					<td class="td_1">政治面貌</td>
					<td colspan="3" class="td_1 print_td" onclick="clickPolitics()" id="t17">${enroll.politics}</td>
					<td colspan="2" class="td_1">出生日期</td>
					<td class="td_3" onclick="clickBirthday()" id="t18">${enroll.birthday
						}</td>
				</tr>
				<tr>
					<td class="td_1">现居住地</td>
					<td colspan="3" class="td_1 print_td" id="t6">${enroll.address }</td>
					<td colspan="2" class="td_1">户籍所在地</td>
					<td class="td_3" id="t19" >${enroll.household }</td>
				</tr>
				<tr>
					<td class="td_1">移动电话</td>
					<td colspan="3" class="td_1 print_td" id="t7">${enroll.tel }</td>
					<td colspan="2" class="td_1">E-mail</td>
					<td class="td_3" id="t10">${enroll.email }</td>
				</tr>
				<tr>
					<td class="td_1">家庭住址</td>
					<td colspan="3" class="td_1 print_td" id="t20">${enroll.home_address }</td>
					<td colspan="2" class="td_1">家庭联系电话</td>
					<td class="td_3" id='t8'>${enroll.home_tel }</td>
				</tr>

				<tr>
					<td rowspan="1" class="td_1">拟申报专业</td>
					<td colspan="3" rowspan="1" class="td_1 print_td" id="t21">${enroll.specialty_submit}</td>
					<td colspan="2" class="td_1"><p align="center">拟求职意向</p></td>
					<td class="td_3" onclick="clickPlace()" id="t22">${enroll.intention}</td>
				</tr>
				<tr>
					<td class="td_1">拟求职意向</td>
					<td colspan="6" class="td_3 print_td" id="t12">${enroll.intention}</td>
				</tr>
				<tr>
					<td class="td_1" style="height: 280px">
					<p style="writing-mode:tb-lr;margin-left: 25px;font-size: 13px;letter-spacing: 5px;line-height: 28px;">省级审核机构<br>审核意见</p>
					</td>
					<td colspan="6" class="td_3">
						<div style="position: relative;left: 200px;top:100px;">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</div>
					</td>
				</tr>
				<tr>
					<td class="td_2">备注</td>
					<td colspan="4" class="td_4" style="border-right: #666 solid 1px;" ></td>
						<td class="td_4" style="border-right: #666 solid 1px;" >学生签名</td>
						<td class="td_4" ></td>
					</tr>
			</table>
		</div><br/>
		<p align="center" style="font-size: 14px;">此表格由"辽宁省就业转换报名网"打印</p>
	</div>
	</s:if>
	<s:if test="enroll==null">
		<p align="center">
			<center><h1>没有找到预报名信息</h1></center>
		</p>
	</s:if>
	


</body>
</html>
