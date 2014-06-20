$(document).ready(function() {
	//全局变量，用于保存用户想访问的页面
	var visURL = null;
	//全局变量，保存题目总数
	var total_exam = 0;
	//全局变量，保存错题信息
	var errorlist = new Array();
//	$("#total").hide();
	//设置导航栏信息
	$.getJSON('/stuenroll/ajax/QuesInfoAjax',function(data){
		if(data.stutas == 100){
			total_exam = data.data;
			$("#total_qu_no").text(total_exam);
		}
	});
//	$("#time_sche").hide();
	$("#login_window,#zhezhao").hide();
	$("#schedule").load("/stuenroll/jsp/enroll/myschedule.jsp");
	$("#zhizhang").load("/stuenroll/jsp/enroll/ExamAction!examQueryOne.action",{"id":"1"});
	/*
	$("#time_sche").load("/stuenroll/jsp/enroll/countdown.jsp");
	$("#zhizhang").load("/stuenroll/jsp/enroll/paper.jsp");
	$("#time_sche").load("/stuenroll/jsp/enroll/timeSche.jsp");
	*/
	//遮罩层
	var $modal = $('<div><center style="font-size: large;color: red;">出错了！！！</center></div>')
	    .attr('id', 'modal')
	    .css({
		    background: '#fff',
		    zIndex: 9999,
		    padding: '10px',
		    width: '640px',
		    height: '240px',
		    margin: '0 auto',
		    opacity: 1,
		    position: 'absolute',
		    top: '10%',
		    left: '35%'
	    });
	//下一题按钮响应
	$("#next").click(function() {
		var id = parseInt($("#ques_id").html()) + 1;
		var userid = $("#login_flag").text().trim();
		if(id >= total_exam){
			alert("已经到最后了！");
			return false;
		}
		var url = "/stuenroll/ajax/ExamAjax?ids=" + id;
		//控制随机
		if($("#random_flag").attr("class") == "flag"){
			url = "/stuenroll/ajax/ExamAjax?ids=-1&userid="+userid;
		}
		if($('#re_error_list').attr('flag') == 'flag'){
			if(errorlist.length > 0)
				url = "/stuenroll/ajax/ExamAjax?ids=" + errorlist.pop();
			else{
				$('#container').jalert('没有错题了！');
				return;
			}
		}
		/*
		var url = "/stuenroll/ajax/ExamAjax?ids=29";
		*/
		$("#exam_img img").remove();
		$.getJSON(url,function(data){
			setText(data);
		});
	});
	//上一题按钮响应
	$("#previous").click(function() {
		var id = parseInt($("#ques_id").html()) - 1;
		if(id < 1){
			alert("没有之前的了！");
			return false;
		}
		var url = "/stuenroll/ajax/ExamAjax?ids=" + id;
		//控制随机
		if($("#random_flag").attr("class") == "flag")
			url = url + "&userid";
		/*
		var url = "/stuenroll/ajax/ExamAjax?ids=29";
		*/
		$("#exam_img img").remove();
		$.getJSON(url,function(data){
			setText(data);
		});
	});
	//选项按钮
	$("#chooseA,#chooseB,#chooseC,#chooseD").live('click', function(){
		var answer = parseInt($("#right_answer").text());
		var userid = $("#curent_qu_no").text().trim();
//		alert("answer:" + (answer != 0));
		if($(this).attr("id") == $("#chooseA").attr("id")){
			if(answer != 1){
				var right_ans;
				switch (answer) {
				case 1:right_ans = 'A';break;case 2:right_ans = 'B';break;case 3:right_ans = 'C';break;case 4:right_ans = 'D';break;
				default:break;
				}
				alert("回答错误！正确答案为" + right_ans);
				if($("#login_flag").attr("class") == "flag"){
					$("#error_list").text($("#error_list").text()+userid+'#');
				}
				return false;
			}
		}
		else if($(this).attr("id") == $("#chooseB").attr("id")){
			if(answer != 2){
				var right_ans;
				switch (answer) {
				case 1:right_ans = 'A';break;case 2:right_ans = 'B';break;case 3:right_ans = 'C';break;case 4:right_ans = 'D';break;
				default:break;
				}
				alert("回答错误！正确答案为" + right_ans);
				if($("#login_flag").attr("class") == "flag"){
					$("#error_list").text($("#error_list").text()+userid+'#');
				}
				return false;
			}
		}
		else if($(this).attr("id") == $("#chooseC").attr("id")){
			if(answer != 3){
				var right_ans;
				switch (answer) {
				case 1:right_ans = 'A';break;case 2:right_ans = 'B';break;case 3:right_ans = 'C';break;case 4:right_ans = 'D';break;
				default:break;
				}
				alert("回答错误！正确答案为" + right_ans);
				if($("#login_flag").attr("class") == "flag"){
					$("#error_list").text($("#error_list").text()+userid+'#');
				}
				return false;
			}
		}
		else if($(this).attr("id") == $("#chooseD").attr("id")){
			if(answer != 4){
				var right_ans;
				switch (answer) {
				case 1:right_ans = 'A';break;case 2:right_ans = 'B';break;case 3:right_ans = 'C';break;case 4:right_ans = 'D';break;
				default:break;
				}
				alert("回答错误！正确答案为" + right_ans);
				if($("#login_flag").attr("class") == "flag"){
					$("#error_list").text($("#error_list").text()+userid+'#');
				}
				return false;
			}
		}
		$("#next").click();
	});
	//超链接点击登录
	$(".login_chick").click(function(evt) {
//		alert($("#login_flag").attr("class") == "flag");
		if($("#login_flag").attr("class") != "flag"){
			evt.preventDefault();
			$("#zhezhao").fadeIn(1000);
			$("#login_window").fadeIn(700);
			$("#login_window").load("/stuenroll/jsp/enroll/login_window.jsp");
			visURL = $(this).attr("id");
		}else{
			if($.trim($(this).text()) == "我的报名信息"){
				//640 520 1250
				var url = "/stuenroll/ajax/TbUserAjax?userid="+$("#login_flag").text();
				$.getJSON(url,function(data){
					if(data.stutas == 100){
						url = "/stuenroll/jsp/enroll/StuEnrollAction!toPDFAjax.action?pid="+data.data.pid;
						$.getJSON(url,function(data){
							if(data.stutas == 100){
								$("#container").css("height","1420");
								$(".layout_middle_main").css("height","1385");
								$(".layout_top_main").css("height","1385");
								var src = '/stuenroll/pdf/'+data.data.filename;
								var embed = $('<div id="showpdf"><center><embed width="910" height="1300" src='+src+'> </embed><center></div>');
								$("#time").hide();
								$("#zhizhang").hide();
								$("#menu_button").hide();
								$(".layout_middle_main center").remove();
								embed.appendTo(".layout_middle_main");
							}
						});
					}else{
						//提示没有报名信息，询问是否要报名
						$('#container').jconfirm('您还没有填写报名信息，您要注册吗?', '/stuenroll/jsp/enroll/stuenroll_note.jsp', 
								{ title : '消息框', width : 300, height : 100, maskcolor : 'gray',maskopacity : 0.9 });
					}
				});
				
			}
			else if($.trim($(this).text()) == "我的学习进程"){
				$("#showpdf").remove();
				$("#container").css("height","1035");
				$(".layout_middle_main").css("height","1000");
				$(".layout_top_main").css("height","1000");
				$("#time").hide();
				$("#zhizhang").hide();
				$("#menu_button").hide();
				$("<center></center>").appendTo(".layout_middle_main");
				/*$(".layout_middle_main center").load('/stuenroll/html/chart.html #tongji_chart,#error_chart,script',function(){
					$.getScript('/stuenroll/js/ichart.1.2.min.js');
				});*/
				$(".layout_middle_main center").load('/stuenroll/jsp/enroll/chart.jsp');
			}
			else if($(this).attr("id") == "moni"){
				window.location.href="/stuenroll/jsp/TextPaperAction!toPaper.action?userid="+$("#login_flag").text();
			}else if($(this).attr("id") == "ct"){
				var url = "/stuenroll/ajax/ExamErrorAjax?userid="+$("#login_flag").text();
				$.getJSON(url,function(data){
					if(data.stutas == 100){
						errorlist = new Array();
						$('#re_error_list').attr('flag','flag');
						$("#random_flag").removeAttr("class");
						$('#previous').attr('disabled','disabled');
						for(var i = 0; i < data.data.length ; i++){
							errorlist.push(data.data[i].exam_id);
						}
						url = "/stuenroll/ajax/ExamAjax?ids=" + errorlist.pop();
						$.getJSON(url,function(data){
							setText(data);
						});
					}
				});
			}
		}
	});
	//登录取消
	$("#zhezhao").click(function() {
		var login_f = $("#login_flag").attr("class");
		$("#login_window,#zhezhao").hide(600);
		if(login_f == "flag" && visURL != null){
			document.getElementById(visURL).click();
			visURL = null;
		}
	});
	//随机测试处理函数
	$("#ranTest").click(function(){
		$("#showpdf").remove();
		$(".layout_middle_main center").remove();
		$("#time").show();
		$("#zhizhang").show();
		$("#menu_button").show();
		$("#container").css("height","555");
		$(".layout_middle_main").css("height","520");
		$(".layout_top_main").css("height","600");
		var login_f = $("#login_flag").attr("class");
		var userid = $("#login_flag").text().trim();
		var url = "/stuenroll/ajax/ExamAjax?ids=-1&userid="+userid;
		if(login_f == "flag"){
			$.getJSON(url,function(data){
				if(data.stutas == 100){
					data = data.data;
					setText(data);
					$("#random_flag").attr("class","flag");
					$('#re_error_list').removeAttr('flag');
					$("#previous").attr("disabled","disabled");
				}else if(data.stutas == 500){
					alert("出错"+data.msg);
				}
			});
			$("#sx_flag").text('');
		}else{
			if(visURL == null){
				visURL = 'ranTest';
			}
			alert("没有登录");
		}
	});
	//顺序练习
	$("#sx").click(function(){
		$("#showpdf").remove();
		$(".layout_middle_main center").remove();
		$("#time").show();
		$("#zhizhang").show();
		$("#menu_button").show();
		$("#container").css("height","555");
		$(".layout_middle_main").css("height","520");
		$(".layout_top_main").css("height","600");
		var login_f = $("#login_flag").attr("class");
		var userid = $("#login_flag").text().trim();
		if(login_f == "flag"){
			var url = "/stuenroll/jsp/enroll/UserDetailsAction!queryUserQuesNo.action?user_id="+userid;
			$.getJSON(url,function(data){
				if(data.stutas == 100){
					url="/stuenroll/ajax/ExamAjax?ids="+data.data;
					$("#exam_img img").remove();
					$.getJSON(url,function(data){
						setText(data);
					});
				}
			});
			$("#sx_flag").text('type:0');
		}else{
			 if(confirm("您现在没有登录，登录后可以记录您的答题信息。您要登录吗？")){
				visURL = $(this).attr("id");
				$("#zhezhao").fadeIn(1000);
				$("#login_window").fadeIn(700);
				$("#login_window").load("/stuenroll/jsp/enroll/login_window.jsp");
			 }
		}
	});
	//跳转题目
	$("#next_ques_no").change(function(){
		var next_no = $("#next_ques_no").val();
		if(next_no > 0 && next_no < total_exam){
			var url = "/stuenroll/ajax/ExamAjax?ids=" + next_no;
			$("#exam_img img").remove();
			$.getJSON(url,function(data){
				setText(data);
			});
		}
	});
	$("#help").click(function(){
		var msg = $("#answer_info").text();
		if(msg.length == 0){
			msg = "此题暂无详解信息！";
		}
		$("#container").jalert(msg,{
				title: '答案详解',
				width: '350',
				height: '300',
				mask : false});
	});
	//页面设置内容函数
	function setText(data){
		if(data.stutas != undefined && data.stutas == 500){
			alert("出错"+data.msg);
			return false;
		}
		if(data.data != undefined){
			data = data.data;
		}
		$("#answer_info").text(data[0].answer_info);
		$("#ques_body").text(data[0].exam_body);
		$("#ques_id").html(data[0].exam_id);
		$("#curent_qu_no").text(data[0].exam_id);
		if(data[0].body_img.length != 0){
			$("#exam_img").html('');
			$("<img/>").attr("src",data[0].body_img).appendTo("#exam_img");
		}
		if(data[0].exam_type == 2){
			$("#chooseC").show();
			$("#chooseD").show();
			$("#chooseA span").text(data[0].chooses[0].choose_body);
			$("#chooseB span").text(data[1].chooses[0].choose_body);
			$("#chooseC span").text(data[2].chooses[0].choose_body);
			$("#chooseD span").text(data[3].chooses[0].choose_body);
			$("#right_answer").html("undefine");
			
			if(parseInt(data[0].chooses[0].right_answer_flag) > 0)
				$("#right_answer").html(data[0].chooses[0].right_answer_flag);
			if(parseInt(data[1].chooses[0].right_answer_flag) > 0)
				$("#right_answer").html(data[1].chooses[0].right_answer_flag);
			if(parseInt(data[2].chooses[0].right_answer_flag) > 0)
				$("#right_answer").html(data[2].chooses[0].right_answer_flag);
			if(parseInt(data[3].chooses[0].right_answer_flag) > 0)
				$("#right_answer").html(data[3].chooses[0].right_answer_flag);
		}else if(data[0].exam_type == 1){
			$("#chooseA span").text("正确");
			$("#chooseB span").text("错误");
			$("#chooseC").hide();
			$("#chooseD").hide();
			$("#right_answer").text(data[0].chooses[0].right_answer_flag);
		}
	}
});
$(window).bind('beforeunload',function(){
	var ques_id = $("#ques_id").text().trim();
	var userid = $("#login_flag").text().trim();
	var errorlist = $("#error_list").text().trim();
	var if_sx = $("#sx_flag").text();
	var login_f = $("#login_flag").attr("class");
	var url = '/stuenroll/jsp/enroll/UserDetailsAction!insertDetails.action';
	if(login_f == "flag" && if_sx == 'type:0'){
		$("#sx_flag").text('');
		$.ajax({
			  url: url,
			  type: 'post',
			  data: {'user_id':userid,'exam_no':ques_id,'type_no':'0','errorlist':errorlist},
			  async: false
//			  success: function(data, textStatus){alert('success');},
		});
	}else{
		$.ajax({
			  url: url,
			  type: 'post',
			  data: {'user_id': userid,'exam_no':'-1','type_no':'0','errorlist':errorlist},
			  async: false
		});
	}
//	return '您输入的内容尚未保存，确定离开此页面吗？';
});