$(document).ready(function() {
	$("#schedule").load("/stuenroll/jsp/enroll/myschedule.jsp");
	$("#zhizhang").load("/stuenroll/jsp/enroll/ExamAction!examQueryOne.action",{"id":"1"});
	/*
	$("#zhizhang").load("/stuenroll/jsp/enroll/paper.jsp");
	$("#time_sche").load("/stuenroll/jsp/enroll/timeSche.jsp");
	*/
	//下一题按钮响应
	$("#next").click(function() {
		var id = parseInt($("#ques_id").html()) + 1;
		if(id >= 973){
			alert("已经到最后了！");
			return false;
		}
		var url = "/stuenroll/ajax/ExamAjax?ids=" + id;
		/*
		var url = "/stuenroll/ajax/ExamAjax?ids=29";
		*/
		$("#exam_img img").remove();
		$.getJSON(url,function(data){
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
				if(parseInt(data[0].right_answer_flag) > 0)
					$("#right_answer").text(data[0].right_answer_flag);
				if(parseInt(data[1].right_answer_flag) > 0)
					$("#right_answer").text(data[1].right_answer_flag);
				if(parseInt(data[2].right_answer_flag) > 0)
					$("#right_answer").text(data[2].right_answer_flag);
				if(parseInt(data[3].right_answer_flag) > 0)
					$("#right_answer").text(data[3].right_answer_flag);
			}else if(data[0].exam_type == 1){
				$("#chooseA span").text("正确");
				$("#chooseB span").text("错误");
				$("#chooseC").hide();
				$("#chooseD").hide();
				$("#right_answer").text(data[0].chooses[0].right_answer_flag);
			}
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
		/*
		var url = "/stuenroll/ajax/ExamAjax?ids=29";
		*/
		$("#exam_img img").remove();
		$.getJSON(url,function(data){
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
				if(parseInt(data[0].right_answer_flag) > 0)
					$("#right_answer").text(data[0].right_answer_flag);
				if(parseInt(data[1].right_answer_flag) > 0)
					$("#right_answer").text(data[1].right_answer_flag);
				if(parseInt(data[2].right_answer_flag) > 0)
					$("#right_answer").text(data[2].right_answer_flag);
				if(parseInt(data[3].right_answer_flag) > 0)
					$("#right_answer").text(data[3].right_answer_flag);
			}else if(data[0].exam_type == 1){
				$("#chooseA span").text("正确");
				$("#chooseB span").text("错误");
				$("#chooseC").hide();
				$("#chooseD").hide();
				$("#right_answer").text(data[0].chooses[0].right_answer_flag);
			}
		});
	});
	//选项按钮
	$("#chooseA,#chooseB,#chooseC,#chooseD").live('click', function(){
		var answer = parseInt($("#right_answer").text());
		alert("answer:" + (answer ==0));
		alert($(this).id == $("#chooseA").id);
		if((this).id == $("#chooseA").id){
			if(answer != 0){
				alert("错误");
				return false;
			}
		}
		if($(this).id == $("#chooseB").id){
			if(answer != 1){
				alert("错误");return false;
			}
		}
		if($(this).id == $("#chooseC").id){
			if(answer != 2){
				alert("错误");return false;
			}
		}
		if($(this).id == $("#chooseD").id){
			if(answer != 3){
				alert("错误");return false;
			}
		}
		$("#next").click();
	});
	//
});