$(document).ready(function() {
	$("#login_window,#zhezhao").hide();
	$("#schedule").load("/stuenroll/jsp/enroll/myschedule.jsp");
	$("#zhizhang").load("/stuenroll/jsp/enroll/ExamAction!examQueryOne.action",{"id":"1"});
	/*
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
		if(id >= 973){
			alert("已经到最后了！");
			return false;
		}
		var url = "/stuenroll/ajax/ExamAjax?ids=" + id;
		//控制随机
		if($("#random_flag").attr("class") == "flag"){
			url = "/stuenroll/ajax/ExamAjax?ids=-1&userid="+userid;
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
//		alert("answer:" + (answer != 0));
		if($(this).attr("id") == $("#chooseA").attr("id")){
			if(answer != 1){
				var right_ans;
				switch (answer) {
				case 1:right_ans = 'A';break;case 2:right_ans = 'B';break;case 3:right_ans = 'C';break;case 4:right_ans = 'D';break;
				default:break;
				}
				alert("回答错误！正确答案为" + right_ans);
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
			$("#login_window").fadeIn(700,function(){
				$(this).load("/stuenroll/jsp/enroll/login_window.jsp");
			});
		}
	});
	//登录取消
	$("#zhezhao").click(function() {
		$("#login_window,#zhezhao").hide(600);
	});
	//随机测试处理函数
	$("#ranTest").click(function(){
		var login_f = $("#login_flag").attr("class");
		var userid = $("#login_flag").text().trim();
		var url = "/stuenroll/ajax/ExamAjax?ids=-1&userid="+userid;
		if(login_f == "flag"){
			$.getJSON(url,function(data){
				if(data.stutas == 100){
					data = data.data;
					setText(data);
					$("#random_flag").attr("class","flag");
					$("#previous").attr("readonly","readonly");
				}else if(data.stutas == 500){
					alert("出错"+data.msg);
				}
			});
		}else{
			alert("没有登录");
		}
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
		$("#ques_body").text(data[0].exam_body);
		$("#ques_id").html(data[0].exam_id);
		if(data[0].body_img.length != 0)
			$("<img/>").attr("src",data[0].body_img).appendTo("#exam_img");
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