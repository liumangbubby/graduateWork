
var inSearchArea = false;

function inSearch() {
	inSearchArea = true;
}
function outSearch() {
	inSearchArea = false;
}
$(document).ready(function() {
	$(document).keyup(function(e) {
		key = e.which;
		if (key == 13 && inSearchArea == true) {


			$('#form_1').submit();
		}
	});
	
	// 设置下俩列表
	$(".chzn-select").chosen();
	$(".chzn-select-deselect").chosen({
		allow_single_deselect : true
	});
	
	$('#school').change(function(){
		var school=$(this).val();

		$('#tc').load("/stuenroll/jsp/backyard/StopAction!getClassNoAjax.action",{'school':school},function(response,status){
			if(status=='success'){
				var json=$.evalJSON(response);
				var html='<select name="classNo" id="classNo" class="chzn-select" tabindex="2" style="width: 230px;">';
				html+='<option value="">- 选择 -</option>';
				for(var i=0;i<json.length;i++){
					html+='<option value="'+json[i]+'">'+json[i]+'</option>';
				}
				html+="</select>";
				$('#tc').html(html);
				$(".chzn-select").chosen();
				$(".chzn-select-deselect").chosen({
					allow_single_deselect : true
				});
				
			}
		});
	});
	var ck=false;
	$('#btn_1').click(function(){
		ck=!ck;
		if(ck){
			$('*[name="stopId"]').attr("checked","checked");
		}
		else{
			$('*[name="stopId"]').removeAttr("checked");
		}
		
	});
	$('*[name="del"]').click(function(){
		var lang=$(this).attr("lang");
		$('#'+lang).attr("checked","checked");
		$('#form_2').submit();
	});
	
	$('#btn_2').click(function(){
		$('#form_2').submit();
	});
	$('a.close, #fade,#cancel').live('click', function() { 
		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();
		});
		return false;
	});
	$('#btn_3').click(function() {
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
	$('#btn_4').click(function() {
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
	$('#btn_5').click(function() {
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
	
	$('#showSchool').change(function(){
		var school=$(this).val();

		$('#ttsd').load("/stuenroll/jsp/backyard/ClassInfoAction!selectClassBySchoolAjax.action",{'school':school},function(response,status){
			if(status=='success'){
				var json=$.evalJSON(response);
				var html='<select name="classNo" id="showClassNo" class="chzn-select" tabindex="2" style="width: 260px;">';
				html+='<option value="">- 选择 -</option>';
				for(var i=0;i<json.length;i++){
					html+='<option value="'+json[i].name+'">'+json[i].name+'</option>';
				}
				html+="</select>";
				$('#ttsd').html(html);
				$(".chzn-select").chosen();
				$(".chzn-select-deselect").chosen({
					allow_single_deselect : true
				});
				
			}
		});
		
	});
	$('#showSchool2').change(function(){
		var school=$(this).val();

		$('#ttsc').load("/stuenroll/jsp/backyard/ClassInfoAction!selectClassBySchoolAjax.action",{'school':school},function(response,status){
			if(status=='success'){
				var json=$.evalJSON(response);
				var html='<select name="classNo" id="showClassNo2" class="chzn-select" tabindex="2" style="width: 260px;">';
				html+='<option value="">- 选择 -</option>';
				for(var i=0;i<json.length;i++){
					html+='<option value="'+json[i].name+'">'+json[i].name+'</option>';
				}
				html+="</select>";
				$('#ttsc').html(html);
				$(".chzn-select").chosen();
				$(".chzn-select-deselect").chosen({
					allow_single_deselect : true
				});
				
			}
		});
		
	});
	$('#showPid').blur(function(){
		var pid=$(this).val();
		$('#rsCk').load("/stuenroll/ajax/PidAjax",{"pid":pid},function(response,status){
			if(status=='success'){
				if(response==true||response=='true'){
					$('#rsCk').html("");
					$('#showPid').removeClass('right');
				}
				else{
					$('#rsCk').html("");
					$('#showPid').addClass('right');
				}
			}
		});
	});
	$('#bt67').click(function(){
		
		var classNo=$('#showClassNo').val();
		if(classNo==null||classNo==''){
			return;
		}
		
		var showName=$('#showName').val();
		if(showName==null||showName==''){
			return;
		}
		
		var showPid=$('#showPid').val();
		if(showPid==null||showPid==''){
			return;
		}
		$('#rsCk').load("/stuenroll/ajax/PidAjax",{"pid":showPid},function(response,status){
			if(status=='success'){
				if(response==true||response=='true'){
					$('#rsCk').html("");
					$('#showPid').removeClass('right');
				}
				else{
					$('#rsCk').html("");
					$('#showPid').addClass('right');
					return;
				}
			}
		});
		
		
		var showTel=$('#showTel').val();
		if(showTel==null||showTel==''){
			return;
		}
		var showNumber=$('#showNumber').val();
		if(showNumber==null||showNumber==''){
			return;
		}
		
		var showJoinDate=$('#showJoinDate').val();
		if(showJoinDate==null||showJoinDate==''){
			return;
		}
		
		var showStopDate=$('#showStopDate').val();
		if(showStopDate==null||showStopDate==''){
			return;
		}
		
		var showReason=$('#showReason').val();
		if(showReason==null||showReason==''){
			return;
		}
		
		$('#form_3').submit();
	});
	$('#bt68').click(function(){
		$('#form_4').submit();
	});
	$('#bt698').click(function(){
		var year=$('#year').val();
		var reg=new RegExp('^[1-9]\\d{3}$');
		if(year==null||year==''||reg.test(year)==false){
			return;
		}
		$('#form_5').submit();
	});
});