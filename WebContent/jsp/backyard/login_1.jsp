<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<style type="text/css">
.page{
	background-image: url(../../pic/car.jpg);
	background-size: cover;
}
.page .main{
	width:350px;
	height:600px;
	background-color:#FFF;
	margin-left:auto;
	margin-right:auto;
	margin-top:60px;
	border-top:#0CC solid 8px;
	box-shadow:#333 0px 2px 20px 0px;
}
.page .main .photo{
	width:200px;
	height:200px;
	border-radius:100px;
	border:#bbb solid 1px;
	margin-top:50px;
	margin-left:auto;
	margin-right:auto;
	box-shadow:#aaa 0px 0px 5px 0px;
}
.page .main .photo:after{
	content: "";
	height: 186px;
	width: 186px;
	background-image: url(../../pic/default.jpg);
	display: inline-block;
	border-radius: 90px;
	position: relative;
	top: 7px;
	left: 7px;	        
}
.page .main .login{
	width:230px;
	height:100px;
	border:#bbb solid 1px;
	border-radius:5px;
	margin-top:40px;
	margin-left:auto;
	margin-right:auto;
	padding-left:20px;
	padding-right:20px;
	font-family: Arial, Helvetica, sans-serif;
	color: #777;
}
.page .main .login p:first-child{
	border-bottom:#bbb solid 1px;
	padding-bottom:10px;
}
.page .main .login input{
	border:none;
	outline:none;
	color:#0C9;
	font-size:15px;
	width:145px;
	margin-left:5px;
}
.page .main .btn{
	height:37px;
	width:270px;
	margin-left:auto;
	margin-right:auto;
	margin-top:40px;
	text-align:center;
	background-image:-webkit-linear-gradient(top,#0cc,#00b0b0);
	border-radius:5px;
	color:#FFF;
	font-family:Arial, Helvetica, sans-serif;
	font-weight:bold;
	font-size:20px;
	padding-top:13px;
	box-shadow:#bbb 0px 2px 5px 0px;
}
.page .main .btn:active{
	box-shadow:#333 0px 0px 10px 0px inset;
}
</style>
<script type="text/javascript">
function loginQQ(){
	document.getElementsByName("website")[0].value="qq";
	document.forms[0].submit();
}

function loginSina(){
	document.getElementsByName("website")[0].value="sina";
	document.forms[0].submit();
}

function loginSystem(){
	var username=document.getElementsByName("username")[0].value;
	var password=document.getElementsByName("password")[0].value;
	if(username==null||username==""||password==null||password==""){
		alert("请输入登录信息");
	}
	else{
		document.forms[0].submit();
	}
}
</script>
</head>

<body class="page">
<form method="post" action="/stuenroll/jsp/backyard/LoginAction!login.action">
	<div class='main'>
    	<div class='photo'></div>
        <div class="login">
        	<p>username:<input type='text' name="username"></p>
            <p>password:<input type="password" name="password"></p>
        </div>
        <div class='btn' onclick="loginSystem()">Sign In</div>
    </div>
</form>
</body>

</html>