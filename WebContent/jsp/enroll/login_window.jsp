<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.login p input{
	border:none;
	outline:none;
	color:#0C9;
	font-size:25px;
	width:145px;
	margin-left:5px;
	height: 35px;
}
.login p:first-child{
	margin-top: 80px;
}
.login p:last-child{
	margin-top: 10px;
}
.login p{
	padding-bottom:10px;
	font-size: large;
	font-weight: bolder;
}
.login{
	margin:40px auto auto auto;
	width: 290px;
	height: 250px;
	border-radius: 50px;
	border: solid 1px #606060;
}
button {
	border-radius: 0.5em;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
    cursor: pointer;
    display: inline-block;
    font: 14px/100% Arial,Helvetica,sans-serif;
    outline: medium none;
    padding: 0.5em 2em 0.55em;
    text-align: center;
    text-decoration: none;
    text-shadow: 0 1px 1px rgba(0, 0, 0, 0.3);
}
form button{
	margin-top: 15px;
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
<body>
	<center>
		<form method="post" action="/stuenroll/jsp/backyard/LoginAction!login.action">
		    <div class="login">
		        	<p>username:<input type='text' name="username"></p><hr/>
		            <p>password:<input type="password" name="password"></p>
		    </div>
		    <button class='orange' onclick="loginSystem()">Sign In</button>
		    <button class='orange' onclick="loginSystem()">Sign In</button>
		</form>
	</center>
</body>
</html>