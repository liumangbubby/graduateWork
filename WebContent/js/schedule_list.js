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

	$('#school').change(function() {
		var school=$('#school').val();
		$('#tc').load("/stuenroll/jsp/backyard/ScheduleAction!getClassNoAjax.action",{'school':school},function(resp,status){
			if(status=='success'){
				var temp=resp.split('#');
				var html='';
				html+="<select name='classNo' id='classNo' class='chzn-select' tabindex='2' style='width:230px;'>";
				html+='<option value="">- 选择 -</option>';
				for(var i=0;i<temp.length;i++){
					html+="<option value='"+temp[i]+"'>"+temp[i]+"</option>";
				}
				html+="</select>";
				$('#tc').empty();
				$('#tc').append(html);
				$(".chzn-select").chosen();
				$(".chzn-select-deselect").chosen({
					allow_single_deselect : true
				});
			}
		});
	});
	
	$(".alink").click(function(){
		$('#sClassNo').val($(this).attr('name'));
		$('#sSchool').val($(this).attr('lang'));
		$('#form_2').submit();
	});
	$('#upload').click(function(){
		$('#filter').val("upload");
		$('#form_1').submit();
	});
	$('#unupload').click(function(){
		$('#filter').val("unupload");
		$('#form_1').submit();
	});
	$('#all').click(function(){
		$('#filter').val("");
		$('#form_1').submit();
	});
});