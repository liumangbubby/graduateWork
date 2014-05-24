<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>login</title>
<script type="text/javascript">
	$(function() {
		$("#register").click(function() {
			$("#login_window").load("/stuenroll/jsp/enroll/register.jsp");
			//$("#login_window").animate({left:"400",width:"580",height:"430"},800);
		});
		$("#forgot").click(function() {
			$("#login_window").load("/stuenroll/jsp/enroll/forgot.jsp");
		});
		$(".checkImg").click(function(){
			var rnd = Math.random();
			$(".checkImg").attr("src","/stuenroll/simpleCaptcha.png?rnd="+rnd);
		});
		$("#cheNum").blur(function(){
			$.ajax(
				{
				url:"http://localhost:8080/stuenroll/ajax/CaptAjax?answer="+$("#cheNum").val(),
				type:"get",
				dataType:"json",
				success:function(data){
					if($.trim(data) == $.trim("true")){
						$("fieldset input[type=submit]").removeAttr("disabled");
					}else{
						alert("验证码错误");
					}
				}
			});
		});
		$("#sub").click(function(event){
			event.preventDefault();
			$.ajax(
					{
					url:"/stuenroll/jsp/enroll/UserLoginAction!userLogin.action",
					type:"post",
					data:{userName:$("fieldset input[name='userName']").val(),password:$("fieldset input[name='password']").val()},
					dataType:"json",
					success:function(data){
						if(parseInt(data.stutas) == 500){
							alert(data.msg);
						}else{
							$("#login_flag").attr("class","flag");
							$("#login_flag").text($.trim($("fieldset input[name='userName']").val()));
							alert("登录成功");
							$("#zhezhao").click();
						}
					}
			});
		});
	});
</script>
<link type="text/css"  href="/stuenroll/css/login.css" rel="stylesheet">
</head>

<body>
	<div class="wrap">
		<form action="javascript:void(0);"
			method="post">
			<section class="loginForm">
				<header>
					<h1>Log In</h1>
				</header>
				<div class="loginForm_content">
					<fieldset>
						<div class="inputWrap">
							<input type="text" name="userName" placeholder="邮箱"
								autofocus required>
						</div>
						<div class="inputWrap">
							<input type="password" name="password" placeholder="请输入密码"
								required>
						</div>
						<div class="checkNum">
							<input id="cheNum" type="text" name="answer" placeholder="请输入验证码" required/>
						</div>
						<img class="checkImg" alt="点击切换" src="/stuenroll/simpleCaptcha.png"/>
					</fieldset>
					<fieldset>
						<input type="checkbox" checked="checked" name="autologin"
							value="true"> <span>下次自动登录</span>
					</fieldset>
					<fieldset>
						<input type="submit" value="登录"  disabled="disabled" id="sub"> <a id="forgot"
							href="javascript:void(0);">忘记密码？</a> <a id="register"
							href="javascript:void(0);">注册</a>
					</fieldset>
				</div>
			</section>
		</form>
	</div>
</body>
</html>
