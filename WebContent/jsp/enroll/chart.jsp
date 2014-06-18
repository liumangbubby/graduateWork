<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/stuenroll/js/ichart.1.2.min.js"></script>
	<script>
	$(document).ready(function(){
		/*
		var userid = $("#login_flag").text().trim();
		var url = "/stuenroll/ajax/ChartInfoAjax?userid=" + userid;
		$.getJSON(url,function(data){
			
		});
		*/
		var flow=[];
			for(var i=0;i<7;i++){
				flow.push(Math.floor(Math.random()*(10+((i%16)*5)))+10);
			}
			
			var data = [
			         	{
			         		name : 'PV',
			         		value:flow,
			         		color:'#ec4646',
			         		line_width:2
			         	}
			         ];
	        
			var labels = ["星期一","星期二","星期三","星期四","星期五","星期六","2014-06-10"];
			
			var chart = new iChart.LineBasic2D({
				render : 'tongji_chart',
				data: data,
				align:'center',
				title : {
					text:'一周内您需复习的错题总数统计',
					font : '微软雅黑',
					fontsize:24,
					color:'#b4b4b4'
				},
				subtitle : {
					text:'请根据错题信息规划您的复习计划',
					font : '微软雅黑',
					color:'#b4b4b4'
				},
				width : 800,
				height : 400,
				shadow:true,
				shadow_color : '#202020',
				shadow_blur : 8,
				shadow_offsetx : 0,
				shadow_offsety : 0,
				background_color:'#2e2e2e',
				animation : true,//开启过渡动画
				animation_duration:600,//600ms完成动画
				tip:{
					enable:true,
					shadow:true,
					listeners:{
						 //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
						parseText:function(tip,name,value,text,i){
							 return "<span style='color:#005268;font-size:12px;'>"+labels[i]+"错题数为:<br/>"+
								"</span><span style='color:#005268;font-size:20px;'>"+value+"</span>";
						}
					}
				},
				crosshair:{
					enable:true,
					line_color:'#ec4646'
				},
				sub_option : {
					smooth : true,
					label:false,
					hollow:false,
					hollow_inside:false,
					point_size:8
				},
				coordinate:{
					width:640,
					height:260,
					striped_factor : 0.18,
					grid_color:'#4e4e4e',
					axis:{
						color:'#252525',
						width:[0,0,4,4]
					},
					scale:[{
						 position:'left',	
						 start_scale:0,
						 end_scale:100,
						 scale_space:10,
						 scale_size:2,
						 scale_enable : false,
						 label : {color:'#9d987a',font : '微软雅黑',fontsize:11,fontweight:600},
						 scale_color:'#9f9f9f'
					},{
						 position:'bottom',	
						 label : {color:'#9d987a',font : '微软雅黑',fontsize:11,fontweight:600},
						 scale_enable : false,
						 labels:labels
					}]
				}
			});
			//利用自定义组件构造左侧说明文本
			chart.plugin(new iChart.Custom({
					drawFn:function(){
						//计算位置
						var coo = chart.getCoordinate(),
							x = coo.get('originx'),
							y = coo.get('originy'),
							w = coo.width,
							h = coo.height;
						//在左上侧的位置，渲染一个单位的文字
						chart.target.textAlign('start')
						.textBaseline('bottom')
						.textFont('600 11px 微软雅黑')
						.fillText('错题总数',x-40,y-12,false,'#9d987a')
						.textBaseline('top')
						.fillText('(日期)',x+w+33,y+h+10,false,'#9d987a');
						
					}
			}));
		//开始画图
		chart.draw();
		var data = [
			{name : '错误三次以上',value : 25,color:'#cbab4f'},
			{name : '错误三次',value : 25,color:'#76a871'},
			{name : '错误两次',value : 25,color:'#9f7961'},
			{name : '错误一次',value : 25,color:'#a56f8f'}
		];
		new iChart.Pie2D({
		render : 'error_chart',
		data: data,
		title : '详细错题统计信息',
		legend : {
			enable : true
		},
		sub_option : {
			label : {
				background_color:null,
				sign:false,//设置禁用label的小图标
				padding:'0 4',
				border:{
					enable:false,
					color:'#666666'
				},
				fontsize:11,
				fontweight:600,
				color : '#4572a7'
			},
			border : {
			width : 2,
			color : '#ffffff'
		}
		},
		animation:true,
		showpercent:true,
		decimalsnum:2,
		width : 800,
		height : 400,
		radius:140
		}).draw();
});
</script>
</head>
<body>
	<div id="tongji_chart"></div>
	<div id="error_chart"></div>
</body>
</html>