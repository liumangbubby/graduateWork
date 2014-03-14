<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script>
	function toEnroll(){
		window.location.href="/stuenroll/jsp/enroll/StuEnrollAction!toEnroll.action";
	}
</script>
<style>
	.font_1{
		color: red;
		font-size: 16px;
		font-weight: bold;
	}
	.font_2{
		color: #0099ff;
		font-size: 16px;
		font-weight: bold;
	}
</style>
</head>

<body class=" body">
	<div class="body_div">
		<div class="div_1" style="position: relative;top:-80px;">
			<div class="div_2">学员登记表填表须知</div>
			<fieldset class="set_1">
				<legend class="leg_1">注意事项：</legend>
				<div style="position: relative; top: 10px;left:35px;font-size: 16px;color: red;height: 20px;font-weight: bold;" id="pop_1">*&nbsp;填表前必须详细阅读以下内容，若出现错误操作将影响报名审核结果&nbsp;*
				</div>
				<div>
					<ol >
						<li class="li_1" style="font-size: 13px">已参加过2009、2010、2011、2012年转换培训的毕业生不允许重复报名参加。</li>
						<li class="li_1" style="font-size: 13px;">报名页面必须使用IE8.0以上版本的IE浏览器进行登陆和填写报名表。</li>
						<li class="li_1" style="font-size: 13px;">报名表只允许填写一次，不能重复填写，确认提交前须详细核实各项填表信息，如有错误将影响报名结果。</li>
						<li class="li_1" style="font-size: 13px;">报名表内均为必填项，要求报名毕业生认真填写，保证填写信息正确无误、真实有效。</li>
						<li class="li_1" style="font-size: 13px;">需要填写数字的表项，必须以英文半角格式填写（如，123），用全角格式填写会提示填写错误（如，１２３）。</li>
						<li class="li_1" style="font-size: 13px;">报名表格以本网站提供为准，不允许以其他方式填写或打印。</li>
						<li class="li_1" style="font-size: 13px;">关闭报名页面后，如需再次打印报名表，请点击转换培训页面的“报名搜索打印”按钮，再点击“下载我的报表”，
										在输入栏填写身份证号来查询和打印报名表格。
						</li>
						<li class="li_1"  style="font-weight: bold;font-size: 13px;">注：在点击“下载我的报表”后，可能会出现信任脚本的窗口提示，
						请选择<font color="red">“临时允许执行脚本的窗口”</font>，然后再次点击“下载我的报表”输入身份证号。</li>
						<li class="li_1" style="font-size: 13px;">打印报名表后，毕业生必须在表格右下角的“学生签名”处进行亲笔签名，否则视为无效报名表。</li>
						<li class="li_1" style="font-size: 13px;">填表打印后，毕业生要按照转换培训页面的报名流程中的相关要求进行后续的报名材料准备和资格审核工作。</li>
						<li class="li_1" style="font-size: 13px;">由于网站提供的报表打印文件为PDF格式，所以需要确定电脑中已经安装“PDF阅读器”，再进行网上报名及实现正常的打印功能。
						<a class="font_2" href="http://www.skycn.com/soft/3076.html" target="_blank" style="color: red;">“下载PDF阅读器”</a></li>
						<li class="li_1" style="font-size: 13px;">在填写或打印报名表期间，如出现显示错误、连接失败、无法提交、打印等系统功能问题，请拨打咨询电话：024-31977890、31977891。</li>
					</ol>
				</div>
			</fieldset>

			<hr class="hr_1"/>
			<div class="div_3" align="center" style="position: relative;left: -20px;">
					<p>
						<input type="button" class="btn_1" value="下一步" onclick="toEnroll()" />
					</p>
			</div>
		</div>
	</div>
</body>
</html>