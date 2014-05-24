$(function() {
	$('#btnEnroll')
			.click(
					function() {
						blurAll();
						var s = checkPid();
						rs = checkName() 
								& checkSex() 
								& checkNation()
								& checkPid() 
								& checkGraduteG()
								// & checkGraduteY()
								& checkBirt() 
								& checkDatepicker()
								& checkEducation()
								// & checkSpecialty()
								// & checkPolitics()
//								& checkBirthday() 
								& checkAddress()
								& checkHousehold() 
								& checkTel() 
								& checkEmail()
								& checkPlace() 
								& checkHomeAddress()
								& checkHomeTel() 
								& checkHealthy()
								& checkSpecr();

						// alert(checkIntention());
						if (rs == false) {
							alert('请核对各项报名信息！');
							return;
						}

						var popID = $(this).attr('title');

						var popURL = $(this).attr('value');

						var query = popURL.split('?');
						var dim = query[1].split('&');
						var popWidth = dim[0].split('=')[1];

						$('#' + popID)
								.fadeIn()
								.css({
									'width' : Number(popWidth)
								})
								.prepend(
										'<a href="#" class="close"><img style="border:none" src="../../pic/close_pop.png" class="btn_close" title="关闭" alt="Close" /></a>');

						var popMargTop = ($('#' + popID).height() + 80) / 2;
						var popMargLeft = ($('#' + popID).width() + 80) / 2;

						$('#' + popID).css({
							'margin-top' : -popMargTop,
							'margin-left' : -popMargLeft
						});

						$('body').append('<div id="fade"></div>');
						$('#fade').css({
							'filter' : 'alpha(opacity=80)'
						}).fadeIn();

						return false;
					});

	$('a.close, #fade,#cancel').live('click', function() {

		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();
		});
		return false;
	});

	window.checkName = function() {
		var name = document.getElementById('name').value;
		// 正则表达式对象
		var re = new RegExp("^[\\u4e00-\\u9fa5]{1,4}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(name);
		if (yesorno == false) {
			document.getElementById("t1").style.backgroundColor = "#FFC1E0";
			document.getElementById('name').style.backgroundColor = "#FFC1E0";
		} else {
			document.getElementById("t1").style.backgroundColor = "#EEEEEE";
			document.getElementById('name').style.backgroundColor = "#EEEEEE";
		}
		return yesorno;
	}
	window.checkGraduteG = function() {
		var name = document.getElementById('graduteG').value;
		// 正则表达式对象
		var re = new RegExp("^[\\u4e00-\\u9fa5]{1,30}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(name);
		if (yesorno == false) {
			document.getElementById("t4").style.backgroundColor = "#FFC1E0";
			document.getElementById('graduteG').style.backgroundColor = "#FFC1E0";
		} else {
			document.getElementById("t4").style.backgroundColor = "#EEEEEE";
			document.getElementById('graduteG').style.backgroundColor = "#EEEEEE";
		}
		return yesorno;
	}
	window.checkPid = function() {
		var pid = document.getElementById("pid").value;
		if (pid == null || pid == '') {
			document.getElementById("t9").style.backgroundColor = "#FFC1E0";
			document.getElementById("pid").style.backgroundColor = "#FFC1E0";
			return false;
		}
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var date = new Date();
		xmlhttp.open("GET", "/stuenroll/ajax/PidAjax?pid=" + pid + "&s="
				+ date.getMilliseconds(), false);
		xmlhttp.send();
		var rs = xmlhttp.responseText;
		if (rs == null || rs == "" || rs == "false") {
			// 身份证验证错误
			document.getElementById("t9").style.backgroundColor = "#FFC1E0";
			document.getElementById("pid").style.backgroundColor = "#FFC1E0";
			return false;
		} else if (rs == "havePid") {
			document.getElementById("t9").style.backgroundColor = "#FFC1E0";
			document.getElementById("pid").style.backgroundColor = "#FFC1E0";
			alert("身份信息重复，不能注册！");
			return false;
		} else {
			document.getElementById("t9").style.backgroundColor = "#EEEEEE";
			document.getElementById("pid").style.backgroundColor = "#EEEEEE";
			return true;
		}
	}
	window.checkPid_2 = function() {
		var bool = checkPid();
		document.getElementById("birt").style.display = "inline";
		$('#birthday').val("");
//		document.getElementById("birt").innerHTML = "";
		if (bool) {
			var pid = document.getElementById("pid").value;
			var xmlhttp;
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {
				// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var date = new Date();
			xmlhttp.open("GET", "/stuenroll/ajax/BirthdayAjax?pid=" + pid
					+ "&s=" + date.getMilliseconds(), false);
			xmlhttp.send();
			var rs = xmlhttp.responseText;
			$('#birthday').val($.trim(rs));
			document.getElementById("birtT").innerHTML = $.trim(rs);
		}
	}
	window.checkAddress = function() {

		var name = document.getElementById('address').value;
		// 正则表达式对象
		var re = new RegExp("^[0-9A-Za-z\\u4e00-\\u9fa5-,，]{1,30}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(name);
		if (yesorno == false) {
			document.getElementById("t6").style.backgroundColor = "#FFC1E0";
			document.getElementById('address').style.backgroundColor = "#FFC1E0";
		} else {
			document.getElementById("t6").style.backgroundColor = "#EEEEEE";
			document.getElementById('address').style.backgroundColor = "#EEEEEE";
		}
		return yesorno;
	}
	window.checkTel = function() {
		var name = document.getElementById('tel').value;
		// 正则表达式对象
		var re = new RegExp("^[1-9][0-9]{10}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(name);
		if (yesorno == false) {
			document.getElementById("t7").style.backgroundColor = "#FFC1E0";
			document.getElementById('tel').style.backgroundColor = "#FFC1E0";
		} else {
			document.getElementById("t7").style.backgroundColor = "#EEEEEE";
			document.getElementById('tel').style.backgroundColor = "#EEEEEE";
		}
		return yesorno;
	}
	window.checkHomeTel = function() {
		var name = document.getElementById('homeTel').value;
		// 正则表达式对象
		var re = new RegExp("^[0-9a-zA-Z\\(\\)\\-\\—]{1,30}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(name);
		if (yesorno == false) {
			document.getElementById("t8").style.backgroundColor = "#FFC1E0";
			document.getElementById('homeTel').style.backgroundColor = "#FFC1E0";
		} else {
			document.getElementById("t8").style.backgroundColor = "#EEEEEE";
			document.getElementById('homeTel').style.backgroundColor = "#EEEEEE";
		}
		return yesorno;
	}
	window.blurAll = function() {
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}

	window.clickNation = function() {
		document.getElementById("nat").style.display = "none";
		document.getElementById("nation").style.display = "inline";
		// 下拉列表取消
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();

	}
	window.blurNation = function() {
		document.getElementById("nation").style.display = "none";
		document.getElementById("nat").style.display = "inline";
		document.getElementById("nat").innerHTML = document
				.getElementById("nation").value;
	}

	window.clickAddress = function() {
		document.getElementById("addr").innerHTML = "";
		document.getElementById("address").style.display = "inline";
		document.getElementById("address").focus();
		// document.getElementById("address").setSelectionRange(100, 100);
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurAddress = function() {
		document.getElementById("address").style.display = "none";
		document.getElementById("addr").innerHTML = document
				.getElementById("address").value;
	}

	window.clickHousehold = function() {
		document.getElementById("hous").innerHTML = "";
		document.getElementById("household").style.display = "inline";
		document.getElementById("household").focus();
		// document.getElementById("household").setSelectionRange(100, 100);
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurHousehold = function() {
		document.getElementById("household").style.display = "none";
		document.getElementById("hous").innerHTML = document
				.getElementById("household").value;
	}

	window.clickGraduteG = function() {
		document.getElementById("grad").style.display = "none";
		document.getElementById("graduteG").style.display = "inline";
		document.getElementById("graduteG").focus();
		// document.getElementById("graduteG").setSelectionRange(100, 100);
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHomeAddress();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurGraduteG = function() {
		document.getElementById("graduteG").style.display = "none";
		document.getElementById("grad").style.display = "inline";
		document.getElementById("grad").innerHTML = document
				.getElementById("graduteG").value;
	}

	window.clickHomeAddress = function() {
		document.getElementById("haddr").innerHTML = "";
		document.getElementById("homeAddress").style.display = "inline";
		document.getElementById("homeAddress").focus();
		document.getElementById("homeAddress").setSelectionRange(100, 100);
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurHomeAddress = function() {
		document.getElementById("homeAddress").style.display = "none";
		document.getElementById("haddr").innerHTML = document
				.getElementById("homeAddress").value;
	}

	window.clickSex = function() {
		document.getElementById("sex").style.display = "inline";
		document.getElementById("sx").style.display = "none";
		// 下拉列表取消
		blurNation();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurSex = function() {
		document.getElementById("sx").innerHTML = document
				.getElementById("sex").value;
		document.getElementById("sex").style.display = "none";
		document.getElementById("sx").style.display = "inline";

	}

	window.clickGraduteY = function() {
		document.getElementById("graduteY").style.display = "inline";
		document.getElementById("gradY").style.display = "none";
		// document.getElementById("gradY").innerHTML=document.getElementById("graduteY").value;
		// 下拉列表取消
		blurNation();
		blurSex();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}

	window.clickEducation = function() {
		document.getElementById("education").style.display = "inline";
		document.getElementById("edu").style.display = "none";
		// 下拉列表取消
		// blurGraduteY();
		blurNation();
		blurSex();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurEducation = function() {
		document.getElementById("edu").innerHTML = document
				.getElementById("education").value;
		document.getElementById("education").style.display = "none";
		document.getElementById("edu").style.display = "inline";

	}
	/*
	 * function clickSpecialty() {
	 * document.getElementById("specialty").style.display = "inline";
	 * document.getElementById("spec").style.display = "none"; // 下拉列表取消
	 * blurNation(); blurSex(); //blurGraduteY(); blurEducation();
	 * blurPolitics(); blurGraduteD(); blurBirthday(); blurPlace(); blurSpecr();
	 * blurAddress(); blurHousehold(); blurHomeAddress(); blurGraduteG();
	 * blurHealthy(); //blurSchool(); blurEmail(); }
	 */
	/*
	 * function blurSpecialty() { document.getElementById("spec").innerHTML =
	 * document .getElementById("specialty").value;
	 * document.getElementById("specialty").style.display = "none";
	 * document.getElementById("spec").style.display = "inline"; }
	 */
	/*
	 * function clickPolitics() {
	 * 
	 * document.getElementById("politics").style.display = "inline";
	 * document.getElementById("poli").style.display = "none"; // 下拉列表取消
	 * blurNation(); blurSex(); //blurGraduteY(); blurEducation();
	 * //blurSpecialty(); blurGraduteD(); blurBirthday(); blurPlace();
	 * blurSpecr(); blurAddress(); blurHousehold(); blurHomeAddress();
	 * blurGraduteG(); blurHealthy(); //blurSchool(); blurEmail(); }
	 */

	window.clickSpecr = function() {

		document.getElementById("specialtySubmit").style.display = "inline";
		document.getElementById("specialtySubmit").style.backgroundColor = "#ffffff";
		document.getElementById("specr").style.display = "none";
		// 下拉列表取消
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	/*
	 * function blurPolitics() { document.getElementById("poli").innerHTML =
	 * document .getElementById("politics").value;
	 * document.getElementById("politics").style.display = "none";
	 * document.getElementById("poli").style.display = "inline"; }
	 */

	window.blurSpecr = function() {
		document.getElementById("specr").innerHTML = document
				.getElementById("specialtySubmit").value;
		document.getElementById("specialtySubmit").style.display = "none";
		document.getElementById("specr").style.display = "inline";
	}

	window.clickGraduteD = function() {
		document.getElementById("da").style.display = "inline";
		document.getElementById("gradT").style.display = "none";
		// 下拉列表消失
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.clickBirt = function() {
		// alert("da");
		document.getElementById("birt").style.display = "inline";
		document.getElementById("birtT").style.display = "none";
		// 下拉列表消失
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
	}
	window.blurGraduteD = function() {
		/*
		 * document.getElementById("grad").innerHTML = document
		 * .getElementById("datepicker").value;
		 * document.getElementById("da").style.display = "none";
		 * document.getElementById("grad").style.display = "inline";
		 */
		document.getElementById("gradT").innerHTML = document
				.getElementById("datepicker").value;
		document.getElementById("da").style.display = "none";
		document.getElementById("gradT").style.display = "inline";
	}
	window.blurBirt = function() {
		document.getElementById("birt").style.display = "inline";
		document.getElementById("birtT").style.display = "inline";
		document.getElementById("birtT").innerHTML = $("#birthday").val();
		document.getElementById("birt").style.display = "none";
	}
	window.clickBirthday = function() {
		document.getElementById("br").style.display = "inline";
		document.getElementById("birt").style.display = "none";
		// 下拉列表消失
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurBirthday = function() {
		// document.getElementById("birt").innerHTML =
		// document.getElementById("bi").value;
		// document.getElementById("br").style.display = "none";
		// document.getElementById("birt").style.display = "inline";
	}
	window.clickEmail = function() {
		document.getElementById("email").style.display = "inline";
		document.getElementById("eml").style.display = "none";
		document.getElementById("email").setSelectionRange(100, 100);
		// 下拉列表消失
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		blurHealthy();
		// blurSchool();
		blurBirt();
	}
	window.blurEmail = function() {
		document.getElementById("eml").innerHTML = document
				.getElementById("email").value;
		document.getElementById("email").style.display = "none";
		document.getElementById("eml").style.display = "inline";
	}
	window.checkEmail = function() {
		var email = document.getElementById("email").value;
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var s = new Date();
		xmlhttp.open("GET", "/stuenroll/ajax/EmailAjax?email=" + email + "&s="
				+ s.getMilliseconds(), false);
		xmlhttp.send();
		var rs = xmlhttp.responseText;
		if (rs == null || rs == "" || rs == "false") {
			// 身份证验证错误
			document.getElementById("t10").style.backgroundColor = "#FFC1E0";
			document.getElementById("email").style.backgroundColor = "#FFC1E0";
			return false;
		} else {
			document.getElementById("t10").style.backgroundColor = "#EEEEEE";
			document.getElementById("email").style.backgroundColor = "#EEEEEE";
			return true;
		}
	}
	window.checkHealthy = function() {
		var healthy = document.getElementById("healthy").value;
		// // 正则表达式对象
		// var re = new RegExp("^[\\u4e00-\\u9fa5]{1,4}$", "");
		//
		// // 验证是否刚好匹配
		// var yesorno = re.test(healthy);
		// if (yesorno == false) {
		// document.getElementById("t11").style.backgroundColor = "#FFC1E0";
		// document.getElementById('healthy').style.backgroundColor = "#FFC1E0";
		// } else {
		// document.getElementById("t11").style.backgroundColor = "#EEEEEE";
		// document.getElementById('healthy').style.backgroundColor = "#EEEEEE";
		// }
		// return yesorno;

		if (healthy != null && healthy != "" && healthy == "健康"
				|| healthy == "残疾") {
			document.getElementById("t11").style.backgroundColor = "#EEEEEE";
			document.getElementById('healthy').style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t11").style.backgroundColor = "#FFC1E0";
			document.getElementById('healthy').style.backgroundColor = "#FFC1E0";
			return false;
		}
	}

	// function checkIntention() {
	// var intention = document.getElementById("intention").value;
	// // 正则表达式对象
	// if (intention == null || intention == '') {
	// document.getElementById("t12").style.backgroundColor = "#FFC1E0";
	// document.getElementById('intention').style.backgroundColor = "#FFC1E0";
	// return false;
	// } else {
	// document.getElementById("t12").style.backgroundColor = "#EEEEEE";
	// document.getElementById('intention').style.backgroundColor = "#EEEEEE";
	// return true;
	// }
	// }
	window.clickPlace = function() {
		document.getElementById("pla").innerHTML = "";
		document.getElementById("place").style.display = "inline";
		document.getElementById("place").style.backgroundColor = "#FFFFFF";
		// 下拉列表取消
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurSpecr();
		blurAddress();
		blurHousehold();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurPlace = function() {
		document.getElementById("place").style.display = "none";
		document.getElementById("pla").style.display = "inline";
		document.getElementById("pla").innerHTML = document
				.getElementById("place").value;
	}

	// ///////////////////////////////////////
	window.clickHealthy = function() {
		document.getElementById("heal").style.display = "none";
		document.getElementById("healthy").style.display = "inline";
		document.getElementById("healthy").style.backgroundColor = "#FFFFFF";
		// 下拉列表取消
		blurNation();
		blurSex();
		// blurGraduteY();
		blurEducation();
		// blurSpecialty();
		// blurPolitics();
		blurGraduteD();
		blurBirthday();
		blurPlace();
		blurSpecr();
		blurAddress();
		blurHousehold();
		blurHomeAddress();
		blurGraduteG();
		// blurSchool();
		blurEmail();
		blurBirt();
	}
	window.blurHealthy = function() {
		document.getElementById("healthy").style.display = "none";
		document.getElementById("heal").style.display = "inline";
		document.getElementById("heal").innerHTML = document
				.getElementById("healthy").value;
	}

	window.checkSex = function() {
		var sex = document.getElementById("sex").value;

		if (sex != null && sex.length != 0) {
			document.getElementById("t2").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t2").style.backgroundColor = "#FFC1E0";
			// document.getElementById('sex').style.backgroundColor = "#FFC1E0";
			return false;
		}
	}

	window.checkNation = function() {
		var nation = document.getElementById("nation").value;

		if (nation != null && nation != '') {
			document.getElementById("t3").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t3").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}
	/*
	 * function checkGraduteY() { var graduteY =
	 * document.getElementById("graduteY").value;
	 * 
	 * if (graduteY != null && graduteY.length != 0) {
	 * document.getElementById("t13").style.backgroundColor = "#EEEEEE"; return
	 * true; } else { document.getElementById("t13").style.backgroundColor =
	 * "#FFC1E0"; return false; } }
	 */
	window.checkDatepicker = function() {
		var datepicker = document.getElementById("datepicker").value;
		if (datepicker != null && datepicker.length != 0) {
			document.getElementById("t15").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t15").style.backgroundColor = "#FFC1E0";
			return false;
		}

	}
	window.checkBirt = function() {
		var exp = new RegExp("\\d{4}-\\d{2}-\\d{2}");
		var birt = $('#birthday').val();
		if (exp.test(birt)) {
			document.getElementById("t18").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t18").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}
	window.checkEducation = function() {
		var education = document.getElementById("education").value;
		if (education != null && education.length != 0) {
			document.getElementById("t5").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t5").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}
	/*
	 * function checkSpecialty() { var specialty =
	 * document.getElementById("specialty").value; if (specialty != null &&
	 * specialty.length != 0) {
	 * document.getElementById("t16").style.backgroundColor = "#EEEEEE"; return
	 * true; } else { document.getElementById("t16").style.backgroundColor =
	 * "#FFC1E0"; return false; } }
	 */
	/*
	 * function checkPolitics() { var politics =
	 * document.getElementById("politics").value;
	 * 
	 * if (politics != null && politics.length != 0) {
	 * document.getElementById("t17").style.backgroundColor = "#EEEEEE"; return
	 * true; } else { document.getElementById("t17").style.backgroundColor =
	 * "#FFC1E0"; return false; } }
	 */

	window.checkSpecr = function() {
		var politics = document.getElementById("specialtySubmit").value;

		if (politics != null && politics.length != 0) {
			document.getElementById("t21").style.backgroundColor = "#EEEEEE";
			document.getElementById("specialtySubmit").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t21").style.backgroundColor = "#FFC1E0";
			document.getElementById("specialtySubmit").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}

	window.checkPlace = function() {
		var politics = document.getElementById("place").value;

		if (politics != null && politics.length != 0) {
			document.getElementById("t22").style.backgroundColor = "#EEEEEE";
			document.getElementById("place").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t22").style.backgroundColor = "#FFC1E0";
			document.getElementById("place").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}

	window.checkBirthday = function() {
		return true;
	}

	window.checkHousehold = function() {
		var household = document.getElementById("household").value;
		// 正则表达式对象
		var re = new RegExp("^[0-9A-Za-z\\u4e00-\\u9fa5-,，]{1,30}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(household);
		if (yesorno) {
			document.getElementById("t19").style.backgroundColor = "#EEEEEE";
			document.getElementById("household").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t19").style.backgroundColor = "#FFC1E0";
			document.getElementById("household").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}
	window.checkHomeAddress = function() {
		var homeAddress = document.getElementById("homeAddress").value;
		var re = new RegExp("^[0-9A-Za-z\\u4e00-\\u9fa5-,，]{1,30}$", "");

		// 验证是否刚好匹配
		var yesorno = re.test(homeAddress);
		if (yesorno) {
			document.getElementById("t20").style.backgroundColor = "#EEEEEE";
			document.getElementById("homeAddress").style.backgroundColor = "#EEEEEE";
			return true;
		} else {
			document.getElementById("t20").style.backgroundColor = "#FFC1E0";
			document.getElementById("homeAddress").style.backgroundColor = "#FFC1E0";
			return false;
		}
	}

	window.changePic = function() {
		document.getElementById("capic").innerHTML = '<img src="/stuenroll/simpleCaptcha.png?s='
				+ new Date().getMilliseconds() + '" onclick="changePic();"/>';
	}

	// 提交验证码
	window.checkCapt = function() {

		var answer = document.getElementById("answer").value;
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var date = new Date();
		var url = "/stuenroll/ajax/CaptAjax?s=" + date.getMilliseconds()
				+ "&answer=" + answer;
		xmlhttp.open("GET", url, false);
		xmlhttp.send();
		var rs = xmlhttp.responseText;
		if (rs == false || rs == "false") {
			alert("验证码错误");
			return;
		} else {
			document.getElementsByName("form_1")[0].submit();
		}
	}
});