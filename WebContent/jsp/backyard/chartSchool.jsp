<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
.btnn {
	padding-top: 10px;
	padding-bottom: 10px;
	width: 100px;
	border: #0099CF solid 1px;
	background: -moz-linear-gradient(top, #00BFFF, #00ACE6);
	background: -webkit-linear-gradient(top, #00ACE8, #0086B6);
	color: #FFF;
	text-shadow: #666 0px 1px 1px;
	font-size: 14px;
	box-shadow: #888 0px 2px 3px;
	border-radius: 3px;
	margin-bottom: 20px;
}

.btnn:active {
	position: relative;
	top: 1px;
}
</style>
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/highcharts.js"></script>
<script src="../../js/exporting.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
	$('#back').click(function(){
		location.href='/stuenroll/jsp/backyard/StopAction!stop.action';
	});					

						
						
						var num = parseInt($('#num').val());
						if (num != 0) {
							var ss = parseInt($('#ss').val());
							var school = $('#school').val();
							chart = new Highcharts.Chart(
									{
										chart : {
											renderTo : 'container',
											plotBackgroundColor : null,
											plotBorderWidth : null,
											plotShadow : false
										},
										title : {
											text :$('#year').val()+'年'+ school + '中退统计图',
											y : 40,
											style : {
												fontSize : '24px',
												fontWeight : 'bold',
											}
										},
										tooltip : {
											pointFormat : '{series.name}: <b>{point.percentage}%</b>',
											percentageDecimals : 1
										},
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												dataLabels : {
													enabled : true,
													color : '#000000',
													connectorColor : '#000000',
													formatter : function() {
														return '<b>'
																+ this.point.name
																+ '</b>: '
																+ this.y + '人';
													},
													style : {
														fontSize : '14px'
													}
												}
											}
										},
										series : [ {
											type : 'pie',
											name : '占比',
											data : [ [ '本届报名总人数', num], {
												name : '本届中退总人数',
												y : ss,
												sliced : true,
												selected : true
											}

											]
										} ]
									});
						} else {
							$('#container').html('无数据');
						}

					});
</script>
</head>
<body>
	<div id="container"
		style="min-width: 650px; height: 650px; margin: 0 auto"></div>
	<input type="hidden" id="ss" value="${stop}">
	<input type="hidden" id="num" value="${count}">
	<input type="hidden" id="school" value="${chartShcool}">
	<input type="hidden" id="year" value="${year}">
	<center>
		<input type="button" id='back' value="后 退" class="btnn">

	</center>
</body>
</body>
</html>