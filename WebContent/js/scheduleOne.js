
$(document).ready(function(e) {
	$("#week").focus();
    $("*[name='n1']").dblclick(function(e) {
        var temp=this.innerHTML;
		var id=this.id;
		$(this).empty();
		$(this).append("<input type='text' value='"+temp+"' class='input' id='input'>");

		moveEnd(document.getElementById('input'))
		$("#input").blur(function(e) {
             recover(id);
        });
		$('#input').keydown(function(e){
			if(e.keyCode==13){
				recover(id);
			}
		})
    });
	function recover(obj){
        var temp=$('#input').val();
    	$("#input").remove();
		$('#'+obj).append(temp);

	}
	$("*[name='t1']").dblclick(function(e) {
        var temp=this.innerHTML;
		var id=this.id;
		$(this).empty();
		$(this).append("<input type='text' value='"+temp+"' class='input_date' id='input'>");

		moveEnd(document.getElementById('input'))
		$("#input").blur(function(e) {
             recover(id);
        });
		$('#input').keydown(function(e){
			if(e.keyCode==13){
				recover(id);
			}
		})
    });
	/**
 	* 将光标移至文本框的结尾处
 	* 
 	* @param obj 文本框对象
 	*/
	function moveEnd(obj) {
		obj.focus();
		var len = obj.value.length;
		if (document.selection) {
			var sel = obj.createTextRange();
			sel.moveStart('character', len);
			sel.collapse();
			sel.select();
		} else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') {
			obj.selectionStart = obj.selectionEnd = len;
		}
	}
	$('#back').click(function(){
		history.go(-1);
	});
	var subject=$('#subject').val();
	var sub=$('.subject');
	
	var subject =subject!=null&&subject!=''?eval('(' + subject + ')'):['','','','','','','',''];

	

	for(i=0;i<sub.length;i++){
		sub[i].innerHTML=subject[i];
	}
	
	var day_1=$('#day_1').val();
	var d1=$('.day_1');
	var day_1 = day_1!=null&&day_1!=''?eval('(' + day_1 + ')'):['','','','','','','','',''];
	for(i=0;i<d1.length;i++){
		d1[i].innerHTML=day_1[i];
	}
	
	var day_2=$('#day_2').val();
	var d2=$('.day_2');
	var day_2 = day_2!=null&&day_2!=''?eval('(' + day_2 + ')'):['','','','','','','','',''];
	for(i=0;i<d2.length;i++){
		d2[i].innerHTML=day_2[i];
	}
	
	var day_3=$('#day_3').val();
	var d3=$('.day_3');
	var day_3 = day_3!=null&&day_3!=''?eval('(' + day_3 + ')'):['','','','','','','','',''];
	for(i=0;i<d3.length;i++){
		d3[i].innerHTML=day_3[i];
	}
	
	var day_4=$('#day_4').val();
	var d4=$('.day_4');
	var day_4 = day_4!=null&&day_4!=''?eval('(' + day_4 + ')'):['','','','','','','','',''];
	for(i=0;i<d4.length;i++){
		d4[i].innerHTML=day_4[i];
	}
	
	var day_5=$('#day_5').val();
	var d5=$('.day_5');
	var day_5 = day_5!=null&&day_5!=''?eval('(' + day_5 + ')'):['','','','','','','','',''];
	for(i=0;i<d5.length;i++){
		d5[i].innerHTML=day_5[i];
	}
	
	var teacher=$('#teacher').val();
	var tea=$('.teacher');
	var teacher = teacher!=null&&teacher!=''?eval('(' + teacher + ')'):['','','','','','','',''];
	for(i=0;i<tea.length;i++){
		tea[i].innerHTML=teacher[i];
	}
	
	var number=$('#number').val();
	var num=$('.number');
	var number = number!=null&&number!=''?eval('(' + number + ')'):['','','','','','','',''];
	for(i=0;i<num.length;i++){
		num[i].innerHTML=number[i];
	}
	
	var place=$('#place').val();
	var pla=$('.place');
	var place = place!=null&&place!=''?eval('(' + place + ')'):['','','','','','','',''];
	for(i=0;i<pla.length;i++){
		pla[i].innerHTML=place[i];
	}
	
	var remark=$('#remark').val();
	var rem=$('.remark');
	var remark = remark!=null&&remark!=''?eval('(' + remark + ')'):['','','','','','','',''];
	for(i=0;i<rem.length;i++){
		rem[i].innerHTML=remark[i];
	}
	
	$('#submit').click(function(){
		 var subject=$('.subject');
		 var sub=new Array();
		 for(i=0;i<subject.length;i++){
			 sub.push(subject[i].innerHTML);
		 }
		 
		 var number=$('.number');
		 var num=new Array();
		 for(i=0;i<number.length;i++){
			 num.push(number[i].innerHTML);
		 }
		 
		 var teacher=$('.teacher');
		 var tea=new Array();
		 for(i=0;i<teacher.length;i++){
			 tea.push(teacher[i].innerHTML);
		 }
		 
		 var place=$('.place');
		 var pla=new Array();
		 for(i=0;i<place.length;i++){
			 pla.push(place[i].innerHTML);
		 }
		 
		 var remark=$('.remark');
		 var rem=new Array();
		 for(i=0;i<remark.length;i++){
			 rem.push(remark[i].innerHTML);
		 }
		 
		 var day_1=$('.day_1');
		 var d1=new Array();
		 for(i=0;i<day_1.length;i++){
			 d1.push(day_1[i].innerHTML);
		 }
		 var day_2=$('.day_2');
		 var d2=new Array();
		 for(i=0;i<day_2.length;i++){
			 d2.push(day_2[i].innerHTML);
		 }
		 var day_3=$('.day_3');
		 var d3=new Array();
		 for(i=0;i<day_3.length;i++){
			 d3.push(day_3[i].innerHTML);
		 }
		 var day_4=$('.day_4');
		 var d4=new Array();
		 for(i=0;i<day_4.length;i++){
			 d4.push(day_4[i].innerHTML);
		 }
		 var day_5=$('.day_5');
		 var d5=new Array();
		 for(i=0;i<day_5.length;i++){
			 d5.push(day_5[i].innerHTML);
		 }
		 
		 $('#subject').val($.toJSON(sub));
		 $('#number').val($.toJSON(num));
		 $('#teacher').val($.toJSON(tea));
		 $('#place').val($.toJSON(pla));
		 $('#remark').val($.toJSON(rem));
		 $('#day_1').val($.toJSON(d1));
		 $('#day_2').val($.toJSON(d2));
		 $('#day_3').val($.toJSON(d3));
		 $('#day_4').val($.toJSON(d4));
		 $('#day_5').val($.toJSON(d5));
		 $('#formWeek').val($("#week").val());
		 $('#form_2').submit();
	});

});