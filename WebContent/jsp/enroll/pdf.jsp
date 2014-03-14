<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下载我的报表</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body class=" body">
	<!-- 
	<div class="body_div">
		<div class="div_1">
			<div class="div_2">学员信息预登记</div>
			<div class="div_4" style="padding: 40px 10px 0px 30px;">
				<img alt="" src="../../pic/head_1.png"> 
				<span style="position: relative; top: -70px; left: 15px; font-size: 32px; color: #333; letter-spacing: 2px; font-family: 黑体; padding-right: 10px;">
					您的报名表格已经成功下载
				</span>
				<div
					style="position: relative; top: -40px; left: 155px; margin-bottom: 0px;">
					如果浏览器没有自动下载请手动下载：<a href="/stuenroll/pdf/${fileName}">下载我的报表</a>
				</div>

			</div>
		</div>
	</div>
	 -->
	 <script type="text/javascript">
	 	window.location.href="/stuenroll/pdf/${fileName}";
	 </script>
</body>
</html>