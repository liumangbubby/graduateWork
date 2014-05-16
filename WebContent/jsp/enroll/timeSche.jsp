<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/stuenroll/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/stuenroll/js/raphael.js"></script>
<script type="text/javascript" src="/stuenroll/js/jquery.uled.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var l0 = new uLED({
		id : "led1",
		type : "countdown",
		format : "mm:ss",
		color : "#FF0000",
		bgcolor : "#F4F4F4",
		size : 3,
		rounded : 1,
		led : "font1"
	});
});	
</script>
</head>
<body>
	<center>
		<div id="led1" class="autosize" style="background:#F4F4F4"></div>
	</center>
</body>
</html>