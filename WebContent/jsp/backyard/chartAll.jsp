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
<script src="../../js/jquery.json-2.4.min.js"></script>
<script src="../../js/highcharts.js"></script>
<script src="../../js/exporting.js"></script>

		<script type="text/javascript">
$(function () {
	
    var chart;
    $(document).ready(function() {
    	$('#back').click(function(){
    		location.href='/stuenroll/jsp/backyard/StopAction!stop.action';
    	});		
        var json = $.evalJSON( $('#everSchool').val() );
		var a1=new Array();
		var a2=new Array();
		var a3=new Array();
        for(var i=0;i<json.length;i++){
			var one=json[i];
			a1.push(one.school);
			a2.push(one.stop);
			a3.push(one.count);
		}

        
        
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container1',
                type: 'bar'
            },
            title: {
                text: ''
            },
            xAxis: {
                categories: a1
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            legend: {
                backgroundColor: '#FFFFFF',
                reversed: true
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.series.name +': '+ this.y +'';
                }
            },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
                series: [{
                name: '总中退人数',
                data: a2
            }, {
                name: '总开班人数',
                data: a3
            }]
        });
        var c1=parseInt($('#c1').val());
        var c2=parseInt($('#c2').val());
        var c3=parseInt($('#c3').val());
        chart = new Highcharts.Chart(
				{
					chart : {
						renderTo : 'container2',
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : ''
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
						data : [ [ '享受补贴人数', c3-c2 ], {
							name : '中退总人数',
							y : c1,
							sliced : true,
							selected : true
						}

						]
					} ]
				});
        

    });
    
});
		</script>	
</head>
<body>
<h1 align="center">${year}年各家培训机构开班人数&中退人数统计图</h1>
	<div id="container1"
		style="min-width: 650px;min-height:650px; margin: 0 auto"></div>
		<input type="hidden" id="everSchool" value='${everSchool}'>
		<input type="hidden" id="year" value='${year}'>
		<input type="hidden" id="c1" value='${c1}'>
		<input type="hidden" id="c2" value='${c2}'>
		<input type="hidden" id="c3" value='${c3}'>
<h1 align="center">${year}年全省享受补贴&中退学生统计图</h1>
	<div id="container2"
		style="width: 650px;height:650px; margin: 0 auto"></div>
	<center>
		<input type="button" id='back' value="后 退" class="btnn">

	</center>
</body>
</body>
</html>