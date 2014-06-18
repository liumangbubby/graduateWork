<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script language="Javascript" type="text/javascript" src="/stuenroll/js/jquery.lwtCountdown-1.0.js"></script>
	<link rel="Stylesheet" type="text/css" href="/stuenroll/css/main.css"></link>
</head>
<body>
	<div id="container_cd">

		<!-- Countdown dashboard start -->
		<div id="countdown_dashboard">
		<!--
			<div class="dash weeks_dash">
				<span class="dash_title">weeks</span>
				<div class="digit">0</div>
				<div class="digit">0</div>
			</div>

			<div class="dash days_dash">
				<span class="dash_title">days</span>
				<div class="digit">0</div>
				<div class="digit">0</div>
			</div>

			<div class="dash hours_dash">
				<span class="dash_title">hours</span>
				<div class="digit">0</div>
				<div class="digit">0</div>
			</div>
		-->
			<div class="dash minutes_dash">
				<!-- <span class="dash_title">minutes</span> -->
				<div class="digit">0</div>
				<div class="digit">0</div>
			</div>

			<div class="dash seconds_dash">
				<!-- <span class="dash_title">seconds</span> -->
				<div class="digit">0</div>
				<div class="digit">0</div>
			</div>

		</div>
		<!-- Countdown dashboard end -->

		<script language="javascript" type="text/javascript">
			jQuery(document).ready(function() {
				$('#countdown_dashboard').countDown({
					targetOffset: {
						'day': 		0,
						'month': 	0,
						'year': 	0,
						'hour': 	0,
						'min': 		40,
						'sec': 		0
					},
					//完成后执行
					onComplete: function() { 
						alert('答题时间到，请提交考卷！'); 
						$('#paperTest').submit();	
					}
				});
			});
		</script>
	</div>
</body>
</html>