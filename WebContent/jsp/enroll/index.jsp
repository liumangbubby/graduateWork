<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="../../css/index.css" rel="stylesheet"/>
<script type="text/javascript" src="../../js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="../../js/main_index.js"></script>
<title>游客主页</title>
</head>
<body>
<div id="container">
	<div id="login_flag" style="position: absolute;"></div>
	<div id="random_flag" style="position: absolute;"></div>
	<div id="login_window"></div>
	<div id="zhezhao"></div>
	<div id="top" class="layout_top">
		<div class="layout_top_main">
			<ul id="menu"> 
		    	<li class="logo"> 
			        <img style="float:left;" alt="" src="../../images/menu_left.png"/>
		    		<!--  
			        <div class="logodiv">一点通</div> 
			        <ul id="main"> 
			            <li>Welcome to <b>Create Vimeo-like top navigation</b> tutorial!</li> 
			        </ul> 
			        -->
			        <div style="background-color: rgb(124,183,227);width: 50px;color: rgb(124,183,227);float: left;">1</div>
		    	</li> 
			    <li><div class="menu_black"><a href="javascript:void(0);">我的一点通</a></div>
			    	<ul id="my"> 
				        <li> 
				            <img class="corner_inset_left" alt="" src="../../images/corner_inset_left.png"/> 
				            <a href="/stuenroll/jsp/enroll/stuenroll_note.jsp" class="login_chick">我的报名信息</a> 
				            <img class="corner_inset_right" alt="" src="../../images/corner_inset_right.png"/> 
				        </li> 
				        <li><a href="javascript:void(0);" class="login_chick">我的学习进程</a></li> 
				        <li><a href="javascript:void(0);" class="login_chick">我的错题库</a></li> 
				        <li class="last"> 
				            <img class="corner_left" alt="" src="../../images/corner_left.png"/> 
				            <img class="middle" alt="" src="../../images/dot.gif"/> 
				            <img class="corner_right" alt="" src="../../images/corner_right.png"/> 
				        </li> 
				    </ul>
			    </li> 
			    <li><div class="menu_black"><a href="javascript:void(0);">试题练习</a></div> 
				    <ul id="prictise">
				        <li> 
				            <img class="corner_inset_left" alt="" src="../../images/corner_inset_left.png"/> 
				            <a href="javascript:void(0);" class="login_chick">错题练习</a> 
				            <img class="corner_inset_right" alt="" src="../../images/corner_inset_right.png"/> 
				        </li> 
				        <li><a href="javascript:void(0);">顺序练习</a></li> 
				        <li><a id="ranTest" href="javascript:void(0);" class="login_chick">随机练习</a></li> 
				        <li><a href="javascript:void(0);">模拟测试</a></li>
				        <li class="last"> 
				            <img class="corner_left" alt="" src="../../images/corner_left.png"/> 
				            <img class="middle" alt="" src="../../images/dot.gif"/> 
				            <img class="corner_right" alt="" src="../../images/corner_right.png"/> 
				        </li> 
				    </ul> 
				</li>
				<li><div class="menu_black"><a href="javascript:void(0);" class="login_chick">参加考试</a></div> 
				    <ul id="prictise">
				    <!-- 
				        <li> 
				            <img class="corner_inset_left" alt="" src="../../images/corner_inset_left.png"/> 
				            <div>距离你的考试时间还有：</div> 
				            <img class="corner_inset_right" alt="" src="../../images/corner_inset_right.png"/> 
				        </li> 
				        <li><div></div></li> 
				        <li><a href="#">随机测试</a></li> 
				        <li><a href="#">模拟测试</a></li>
				        <li class="last"> 
				            <img class="corner_left" alt="" src="../../images/corner_left.png"/> 
				            <img class="middle" alt="" src="../../images/dot.gif"/> 
				            <img class="corner_right" alt="" src="../../images/corner_right.png"/> 
				        </li>
				         -->
				        <div id="schedule"></div> 
				    </ul> 
				</li>
				<li style="width: 62px;"><span style="background-color: #172322;color: #172322;">1</span></li> 
				<!--  
			    <li class="searchContainer"> 
			        <div> 
			        <input type="text" id="searchField" /> 
			        <img src="../../images/magnifier.png" alt="Search" onclick="alert('You clicked on search button')" /></div> 
			        <ul id="search"> 
			            <li><input id="cbxAll" type="checkbox" />All</li> 
			            <li><input id="Articles" type="checkbox" />Articles</li> 
			            <li><input id="Tutorials" type="checkbox" />Tutorials</li> 
			            <li><input id="Reviews" type="checkbox" />Reviews</li> 
			            <li><input id="Resources" type="checkbox" />Resources</li> 
			        </ul> 
			    </li>
			    --> 
			</ul> 
			<img style="float:left;" alt="" src="../../images/menu_right.png"/>
		</div>
	</div>
	<div id="middle" class="layout_middle">
	<!-- 
		<div id="left" class="layout_left"></div>
		<div id="main" class="layout_main"></div>
	-->
		<div class="layout_middle_main">
			<div id="time">
				<div id="total">当前第  <b>*</b> 题，共  <b>*</b> 题，转到第<input id="ques_no" type="text" size="5"/>题</div>
				<div id="time_sche"></div>
			</div>
			<div id="zhizhang">
			</div>
			<div id="menu_button">
				<button id="mark" class="orange">mark</button>
				<button id="help" class="orange">help</button>
				<button id="previous" class="orange">previous</button>
				<button id="next" class="orange">next</button>
			</div>
		</div>
	</div>
	<div id="footer" class="layout_footer">
	</div>
</div>
</body>
</html>