<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf8"> 
<title>Register</title> 
<link type="text/css"  href="/stuenroll/css/login.css" rel="stylesheet">
<script type="text/javascript">
	$(function() {
		$("#register").click(function() {
			$("#login_window").load("/stuenroll/jsp/enroll/login_window.jsp");
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
			var url = "http://localhost:8080/stuenroll/ajax/CaptAjax?answer="+$("#cheNum").val();
			$.getJSON(url,function(data){
				if($.trim(data) == $.trim("true")){
					$("fieldset input[type=submit]").removeAttr("disabled");
				}else{
					alert("验证码错误");
				}
			});
		});
	});
</script>
</head> 
 
<body> 
<div class="wrap"> 
  <form action="/stuenroll/jsp/enroll/UserRegisterAction!createUser.action" method="post"> 
    <section class="loginForm"> 
      <header> 
        <h1>Register</h1> 
      </header> 
      <div class="loginForm_content"> 
        <fieldset> 
          <div class="inputWrap"> 
            <input type="text" name="user.username" placeholder="邮箱" autofocus required> 
          </div> 
          <div class="inputWrap"> 
            <input type="password" name="user.password" placeholder="请输入密码" required> 
          </div> 
          <div class="inputWrap">
          	<input type="text" name="user.pid" placeholder="身份证号" required> 
          </div>
          <div class="checkNum">
			<input id="cheNum" type="text" name="answer" placeholder="请输入验证码" required/>
		</div>
		<img class="checkImg" alt="点击切换" src="/stuenroll/simpleCaptcha.png"/>
        </fieldset> 
        <fieldset> 
          <input type="submit" value="注册"  readonly="readonly" disabled="disabled">
          <a id="forgot" href="javascript:void(0);">忘记密码？</a> <a id="register" href="javascript:void(0);">已有账户</a> 
        </fieldset> 
      </div> 
    </section> 
  </form> 
</div> 
</body> 
</html> 