var isSelectAll = false;
function selectAll() {
	isSelectAll = !isSelectAll;
	document.getElementById("btn_1").value = (isSelectAll == true ? '取消' : '全选');
	var array = document.getElementsByName("userId");
	for (i = 0; i < array.length; i++) {
		array[i].checked = isSelectAll;
	}
}
function deleteUser(id) {
	var s = confirm("确认要删除这些记录吗?");
	if (s == true) {
		if (id != null && id != "") {
			var array = document.getElementsByName("userId");
			for (i = 0; i < array.length; i++) {
				array[i].checked = false;
			}
			document.getElementById("userId_" + id).checked = true;
		}
		document.getElementById("form_1").submit();
	} else {
		return;
	}
}

function addUser() {
	var name = prompt("输入需要添加的用户名称");
	if (name == null || name == '') {
		alert("请填写用户名称");

	} else {
		var array = document.getElementsByName("username");
		for (i = 0; i < array.length; i++) {
			if (array[i].innerHTML == name) {
				alert("该用户已经存在！");
				return;
			}
		}
	}
	var password = prompt("输入需要添加的密码");
	if (password == null || password == '') {
		alert("请填写密码");
	} else {
		document.getElementById("st1").value = name;
		document.getElementById("st2").value = password;
		document.getElementById("form_1").action = "/stuenroll/jsp/backyard/UserAction!insert.action";
		document.getElementById("form_1").submit();
	}
}

$(document).ready(function(e) {
	$(".chzn-select").chosen(); 
	$(".chzn-select-deselect").chosen({allow_single_deselect:true});
	$('#bt5').click(function() {
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
	$('a.close, #fade,#cancel').live('click', function() { 
		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();
		});
		return false;
	});
	$('#st1').blur(function(){
		if ($('#st1').val() == null || $('#st1').val() == '') {
			alert("请填写用户名称");
			return;
		}
		var array = document.getElementsByName("username");
		for (i = 0; i < array.length; i++) {
			if (array[i].innerHTML == $('#st1').val()) {
				alert("该用户已经存在！");
				return;
			}
		}
	});
	$('#st2').blur(function(){
		if ($('#st2').val() == null || $('#st2').val() == '') {
			alert("请填写密码");
			return;
		}
	});
	
	$('#btn_6').click(function(){
		$("#form_3").submit();
	})

});