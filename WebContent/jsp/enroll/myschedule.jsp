<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="../../js/raphael.js"></script>
<script type="text/javascript" src="../../js/jquery.uled.js"></script>
<!-- 
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="js/raphael.js"></script>
 -->
<style type="text/css">
#msg {
	font-family: "Courier New", Courier, monospace;
	font-size: 20px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var l0 = new uLED({
		id : "led0",
		type : "countdown",
		format : "mm:ss",
		color : "#FF0000",
		bgcolor : "#222",
		size : 3,
		rounded : 1,
		led : "font1"
	});
});	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<br/>
		<div id="msg">离你参加考试的时间还有：</div>
		<br/>
		<div id="led0" class="autosize" style="background:#333"></div>
	</center>
</body>
</html>