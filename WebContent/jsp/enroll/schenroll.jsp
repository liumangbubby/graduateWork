<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<!-- 弹出对话框CSS -->
<link href="../../css/popup.css" rel="stylesheet" type="text/css" />
<!-- 气泡CSS -->
<link href="../../js/jamescryer/css/grumble.min.css" rel="stylesheet"
	type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<!--气泡口JS -->
<script src="../../js/jamescryer/js/jquery.grumble.min.js"></script>
<script>
	var isError=true;
	function checkFile(name) {
		var filePath = document.getElementsByName(name)[0].value;
		//alert(filePath);
		var i = filePath.lastIndexOf(".");
		if (i == -1) {
			isError==true;
			document.getElementById("rs").innerHTML = "<img src='../../pic/error.png' class='img_1'/>";
		} else {
			var fileType = filePath.substring(i + 1, filePath.length);
			if (fileType != "xls") {
				isError=true;
				document.getElementById("rs").innerHTML = "<img src='../../pic/error.png'  class='img_1'/>";
			} else {
				isError=false;
				document.getElementById("rs").innerHTML = "<img src='../../pic/tick.png'  class='img_1'/>";
			}
		}
	}
	function submitForm(){
		//alert(isError);
		if(isError==false){
			document.getElementsByName("form_1")[0].submit();
		}
	}
	function downLoadTemple(){
		window.open ('../../excel/EnrollTemplet.xls') ;  
	}
</script>
<script>
	jQuery(function() {
		$('#pop_1').grumble({
			text : '阅读说明',
			angle : 60,
			distance : 20,
			showAfter : 500,
			hideAfter : 1000
		});
		/*
		$('#btn_1').grumble({
			text : '阅读说明',
			angle : 0,
			type: 'alt-', 
			distance : 40,
			showAfter : 3000,
			hideAfter : 2000
			
		});
		 */
		$('#btn_1')
				.click(
						function() {
							var popID = $(this).attr('title'); //Get Popup Name
							var popURL = $(this).attr('value'); //Get Popup href to define size

							//Pull Query & Variables from href URL
							var query = popURL.split('?');
							var dim = query[1].split('&');
							var popWidth = dim[0].split('=')[1]; //Gets the first query string value

							//Fade in the Popup and add close button
							$('#' + popID)
									.fadeIn()
									.css({
										'width' : Number(popWidth)
									})
									.prepend(
											'<a href="#" class="close"><img style="border:none" src="../../pic/close_pop.png" class="btn_close" title="关闭" alt="Close" /></a>');

							//Define margin for center alignment (vertical   horizontal) - we add 80px to the height/width to accomodate for the padding  and border width defined in the css
							var popMargTop = ($('#' + popID).height() + 80) / 2;
							var popMargLeft = ($('#' + popID).width() + 80) / 2;

							//Apply Margin to Popup
							$('#' + popID).css({
								'margin-top' : -popMargTop,
								'margin-left' : -popMargLeft
							});

							//Fade in Background
							$('body').append('<div id="fade"></div>'); //Add the fade layer to bottom of the body tag.
							$('#fade').css({
								'filter' : 'alpha(opacity=80)'
							}).fadeIn(); //Fade in the fade layer - .css({'filter' : 'alpha(opacity=80)'}) is used to fix the IE Bug on fading transparencies 

							return false;
						});

		//Close Popups and Fade Layer
		$('a.close, #fade,#btn_3').live('click', function() { //When clicking on the close or fade layer...
			$('#fade , .popup_block').fadeOut(function() {
				$('#fade, a.close').remove(); //fade them both out
			});
			return false;
		});
	});
</script>

</head>

<body class=" body">
	<div class="body_div">
		<div class="div_1">
			<div class="div_2">学员信息预登记</div>
			<fieldset class="set_1">
				<legend class="leg_1">规则说明</legend>
				<div style="position: relative; top: 20px" id="pop_1"></div>
				<div>
					<ul>
						<li class="li_1">学员信息排重录入功能具有可以从Excel文件中把学生报名信息导入到系统预登记数据中。</li>
						<li class="li_1">如果培训机构上传的Excel文件中某几名学员的身份证号码已经存在于预报名系统中，则这几名学员将不能重复报名。</li>
						<li class="li_1">如果培训机构上传的Excel数据存在问题那么系统将会生成文件标明错误的数据，到时请下载该文件即可。</li>
						<li class="li_1">当培训机构上传Excel文件，系统会把所有符合条件的学员信息录入到预登记数据库中，不符合条件的学员信息在Excel文件中有所标明。</li>
					</ul>
				</div>
			</fieldset>
			<hr class="hr_1" />
			<div class="div_3">
				<p>
					<button class="btn_1" id="btn_1" title="popup_name" value="#?w=500">排重录入</button>
					<input name="sd" type="button" class="btn_2" value="Excel模板下载" onclick="downLoadTemple()"/>
					<input name="sd" type="button" class="btn_3" value="Excel模板说明" />
					<input name="sd" type="button" class="btn_4" value="常见错误说明" />
				</p>
			</div>
		</div>
	</div>
	<div id="popup_name" class="popup_block">
		<h2 align="center" class="font_1">学员信息数据预登记</h2>
		<form action="/stuenroll/jsp/enroll/SchoolEnrollAction.action"  enctype="multipart/form-data" method="post" name="form_1">
			<table align="center" class="font_2">
				<tr>
					<td>上传文件：</td>
					<td>
						<input name="upload" type="file"class="file_input" onchange="checkFile('upload')" />
					</td>
					<td width="50"><span id="rs"></span></td>

				</tr>
			</table>
		</form>
		<p align="center">
			<button class="btn_5" id="btn_2" onclick="submitForm()">确定</button>
			<button class="btn_5" id="btn_3">取消</button>
		</p>


	</div>
</body>
</html>