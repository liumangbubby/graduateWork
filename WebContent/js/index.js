$(document).ready(function(e) {
	$('#bt5').click(function() {
		if (array == null || array == "") {
			alert('请选择记录');
			return;
		}
		if (array.charAt(0) == '#') {
			array = array.substring(1,array.length);
		}
		var ids = array.split("#");
		var popID = $(this).attr('title'); 
		var popURL = $(this).attr('value'); 
		var query = popURL.split('?');
		var dim = query[1].split('&');
		var popWidth = dim[0].split('=')[1]; 
		$('#' + popID).fadeIn().css({'width' : Number(popWidth)}).prepend('<a href="#" class="close"><img style="border:none" src="../../pic/close_pop.png" class="btn_close" title="关闭" alt="Close" /></a>');
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;
		$('#' + popID).css({'margin-top' : -popMargTop,'margin-left' : -popMargLeft});
		$('body').append('<div id="fade"></div>'); 
		$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); 
		return false;
	});
	$('#bt66').click(function() {

		var popID = $(this).attr('title'); 
		var popURL = $(this).attr('value'); 
		var query = popURL.split('?');
		var dim = query[1].split('&');
		var popWidth = dim[0].split('=')[1]; 
		$('#' + popID).fadeIn().css({'width' : Number(popWidth)}).prepend('<a href="#" class="close"><img style="border:none" src="../../pic/close_pop.png" class="btn_close" title="关闭" alt="Close" /></a>');
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;
		$('#' + popID).css({'margin-top' : -popMargTop,'margin-left' : -popMargLeft});
		$('body').append('<div id="fade"></div>'); 
		$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); 
		return false;
	});
	$('#bt67').click(function(){
		var classNo=$('#classNoExport').val();
		if(classNo==null||classNo==''){
			return;
		}
		$('#form_4').submit();
		
	});
	$('a.close, #fade,#cancel').live('click', function() { 
		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();
		});
		return false;
	});
	//申报专业列表改变事件
	function changeSpec(){
		
		$('#specialtySubmit').change(function(){
			
			var type=$('#specialtySubmit').val();
			if(type==''||type==''){
				window.location='/stuenroll/jsp/backyard/ManageAction!index.action';
			}
			var url=location.href;
			var isRecord;
			if(url.indexOf("ManageAction!index.action")!=-1||url.indexOf("LoginAction!login.action")!=-1){
				//报名页面
				isRecord=false;
			}
			else{
				//归档画面
				isRecord=true;
			}
			if($('#isAdmin').val()=='false'){
				if(type==''||type==null){
					window.location='/stuenroll/jsp/backyard/ManageAction!index.action';
					return;
				}
				$('#a3').load("/stuenroll/jsp/backyard/ClassInfoAction!selectClassInfoAjax.action",{'type':type,"school":"","isRecord":false},function(resp,status){
					if(status=='success'){
						$('#a3').empty();
						var html = "<select name='classNo' id='classNo' class='class_select chzn-select' tabindex='2' style='width:230px;'>";
						html+="<option value=''>- 选择 -</option>";
						var temp=resp.split('#');
						for(i=0;i<temp.length;i++){
							html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
						}
						html+="</select>";
						$('#a3').append(html);
						$(".chzn-select").chosen(); 
						$(".chzn-select-deselect").chosen({allow_single_deselect:true});
						classChange();
					}
				});
				return;
			}
			
			//相关培训学校
			$('#a2').load('/stuenroll/jsp/backyard/EduSchoolAction!selectRefEduBySpecAjax.action',{"name":type,"isRecord":isRecord},function(resp,status){
				
				$('#a2').empty();

				var html = "<select name='eduSchool' id='eduSchool' class='class_select chzn-select' tabindex='2' style='width:230px;'>";
				html+="<option value=''>- 选择 -</option>";
				var temp=resp.split('#');
				for(i=0;i<temp.length;i++){
					html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
				}
				html+="</select>";
				$('#a2').append(html);
				$(".chzn-select").chosen(); 
				$(".chzn-select-deselect").chosen({allow_single_deselect:true});
				schoolChange();
			});
			
			//相关班级列表
			$('#a3').load("/stuenroll/jsp/backyard/ClassInfoAction!selectClassInfoAjax.action",{'type':type,"school":"","isRecord":isRecord},function(resp,status){
				if(status=='success'){
					$('#a3').empty();
					var html = "<select name='classNo' id='classNo' class='class_select chzn-select' tabindex='2' style='width:230px;'>";
					html+="<option value=''>- 选择 -</option>";
					var temp=resp.split('#');
					for(i=0;i<temp.length;i++){
						html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
					}
					html+="</select>";
					$('#a3').append(html);
					$(".chzn-select").chosen(); 
					$(".chzn-select-deselect").chosen({allow_single_deselect:true});
					classChange();
					
				}
			});
		});
	}
	
	changeSpec();
	
	schoolChange();
	
	function schoolChange(){
		$('#eduSchool').change(function(){
			loadClass();
		});
	}
	function loadClass(){
		var isRecord;
		var url=location.href;
		if(url.indexOf("ManageAction!index.action")!=-1||url.indexOf("LoginAction!login.action")!=-1){
			//报名页面
			isRecord=false;
		}
		else{
			//归档画面
			isRecord=true;
		}
		var type=$('#specialtySubmit').val();
		var school=$('#eduSchool').val();
		if(school==null||school==''){
			window.location='/stuenroll/jsp/backyard/ManageAction!index.action';
		}

		$('#a3').load("/stuenroll/jsp/backyard/ClassInfoAction!selectClassInfoAjax.action",{'type':type,'school':school,'name':'',"isRecord":isRecord},function(resp,status){
			if(status=='success'){

				$('#a3').empty();
				var html="<select name='classNo' id='classNo' class='class_select chzn-select' tabindex='2' style='width:230px;'>";
				html+="<option value=''>- 选择 -</option>";
				var temp=resp.split('#');
				for(var i=0;i<temp.length;i++){
					html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
				}
				html+="</select>";
				$('#a3').append(html);
				$(".chzn-select").chosen(); 
				$(".chzn-select-deselect").chosen({allow_single_deselect:true});
				classChange();
			}
		});
	}
	
	function classChange(){
		$('#classNo').change(function(){
			var name=$('#classNo').val();
			if(name==null||name==''){
				var url=location.href;
				if(url.indexOf("ManageAction!index.action")!=-1||url.indexOf("LoginAction!login.action")!=-1||url.indexOf("ManageAction!searchEnrolls.action")!=-1){
					window.location='/stuenroll/jsp/backyard/ManageAction!index.action';
					return;
				}
				else{
					window.location='/stuenroll/jsp/backyard/StuRecordAction!index.action';
					return;
				}
			}
			//查找班级对应的学校和专业
			$('#a1').load("/stuenroll/jsp/backyard/ClassInfoAction!getTypeAndSchoolAjax.action",{'name':name},function(resp,status){
				if(status=='success'){
					var temp=resp.split('#');
					var html="<select name='specialtySubmit' id='specialtySubmit' class='class_select chzn-select' tabindex='2' style='width:230px;'>";
					
					html+="<option value=''>- 选择 -</option>";
					html+="<option value='"+temp[1]+"'>"+temp[1]+"</option>";
					html+="</select>";
					
					$('#a1').empty();
					$('#a1').append(html);

					html="<select name='eduSchool' id='eduSchool' class='class_select chzn-select' tabindex='2' style='width:230px;'>";
					html+="<option value=''>- 选择 -</option>";
					html+="<option value='"+temp[0]+"'>"+temp[0]+"</option>";
					html+="</select>";
					if($('#isAdmin').val()=='true'){
						$('#a2').empty();
						$('#a2').append(html);
					}
					schoolChange();
					changeSpec();
					$(".chzn-select").chosen(); 
					$(".chzn-select-deselect").chosen({allow_single_deselect:true});
				}
			});
		});
	}
	
	classChange();	
});

function changeType() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var date = new Date();
	var url = "/stuenroll/ajax/StuEnrollBatchAjax?s=" + date.getMilliseconds()+ "&t=edu&typeId=" + document.getElementById("type").value;
	xmlhttp.open("POST", url, false);
	xmlhttp.send();
	var rs = xmlhttp.responseText;
	var edu = document.getElementById("edu");
	edu.length = 0;
	var classObj = document.getElementById("class");
	classObj.length = 1;
	if (rs != null && rs != '') {
		var y = document.createElement('option');
		y.text = "-选择-";
		y.value = "-1";
		try {
			edu.add(y, null);
		} catch (ex) {
			edu.add(y);
		}
		var temp = rs.split("#");
		for (i = 0; i < temp.length; i++) {
			var y = document.createElement('option');
			var s = temp[i].split('@');
			y.text = s[0];
			y.value = s[1];
			try {
				edu.add(y, null);
			} catch (ex) {
				edu.add(y);
			}
		}
	} else {
		var y = document.createElement('option');
		y.text = "没有对应的培训学校";
		y.value = "-1";
		try {
			edu.add(y, null); 
		} catch (ex) {
			edu.add(y);
		}
	}

}

function changeEdu(bool) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var date = new Date();
	var url = "/stuenroll/ajax/StuEnrollBatchAjax?s=" + date.getMilliseconds()
			+ "&t=class&typeId=" + document.getElementById("type").value
			+ "&eduId=" + document.getElementById("edu").value + "&archives="
			+ bool;
	xmlhttp.open("POST", url, false);
	xmlhttp.send();
	var rs = xmlhttp.responseText;
	var classObj = document.getElementById("class");
	classObj.length = 0;
	if (rs != null && rs != '') {
		var y = document.createElement('option');
		y.text = "-选择-";
		y.value = "-1";
		try {
			classObj.add(y, null);
		} catch (ex) {
			classObj.add(y);
		}
		var temp = rs.split("#");

		for (i = 0; i < temp.length; i++) {
			var y = document.createElement('option');
			var s = temp[i].split('@');
			y.text = s[0];
			y.value = s[1];
			try {
				classObj.add(y, null); 
			} catch (ex) {
				classObj.add(y);
			}
		}
	} else {

		var y = document.createElement('option');
		y.text = "没有对应的班级";
		y.value = "";
		try {
			classObj.add(y, null); 
		} catch (ex) {
			classObj.add(y); 
		}
	}
}

function submitUpdate() {
	var type = document.getElementById("type").value;
	var edu = document.getElementById("edu").value;
	var cc = document.getElementById("class").value;
	var joinState = document.getElementById("joinState").value;
	if (type == null || type == "" || edu == null || edu == '' || cc == null || cc == "" || joinState == null || joinState == '') {
		alert("请选择具体内容");
	} else {
		var temp = array.split('#');
		var classArray="";
		for ( var i = 0; i < temp.length; i++) {
			classArray+=document.getElementById("class_no_"+temp[i]).innerHTML;
			if(i<temp.length-1){
				classArray+='#';
			}
		}
		document.getElementById("classArray").value=classArray;
		document.getElementById("ids_update").value = array;
		document.getElementById("form_3").submit();
	}
}
