<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排重验证成功</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body class=" body">
	<div class="body_div">
		<div class="div_1">
			<div class="div_2">学员信息预登记</div>
			<div class="div_4"
				style="background-color: #FFD0E8; padding: 40px 10px 0px 30px;">
				<img alt="" src="../../pic/head_1.png"> 
				<span style="position: relative; top: -70px; left: 15px; font-size: 32px; color: #333; letter-spacing: 2px; font-family: 黑体; padding-right: 10px;">
					数据导入完成
				</span>
				<div
					style="position: relative; top: -40px; left: 155px; margin-bottom: 0px;">
					这里下载导入错误的数据：<a href="/stuenroll/upload/${requestScope.fileName}">错误数据</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>