<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>机动车驾驶理论考试</title>
<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
<script src="/stuenroll/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/stuenroll/js/icheck.min.js" type="text/javascript"></script>
<link href="/stuenroll/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="/stuenroll/css/bootstrap-theme.css" rel="stylesheet"
	type="text/css">
<link href="/stuenroll/js/skins/all.css" rel="stylesheet"
	type="text/css">
<link href="/stuenroll/css/myboot.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">机动车驾驶员理论考试</a>
			</div>
			<div class="navbar-collapse collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div id="content" class="bs-header">
		<div class="container">
			<h1>倒计时</h1>
			<p>您只有40分钟来完成您的试卷，请您合理安排您的时间！</p>
		</div>
	</div>
	<div class="container bs-docs-container">
		<div class="row">
			<div class="col-md-3 col-sm-3 hidden-sm hidden-xs">
				<div class="bs-sidebar hidden-print affix" role="complementary">
					<ul class="nav bs-sidenav">
						<li><center>
								<h3>您好！${username }</h3>
								<hr style="border: 1 dashed #987cb9" width="80%" color=#987cb9 SIZE=1 />
								<h3>距离考试结束还有：</h3>
							</center></li>
						<li><div id='countdown'></div></li>
						<hr style="border: 1 dashed #987cb9" width="80%" color=#987cb9
							SIZE=1 />
						<li><center>
								<button class="btn btn-danger" type="button" id="subbut">提交试卷</button>
								<center></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9 col-sm-9" role="main">
				<div class='maincontext'>
				<form action="/stuenroll/jsp/TextPaperAction!submitPaper.action" id="paperTest">
					<div class="panel-group" id="accordion">
						<c:forEach items="${examlist}" var="l" varStatus="index">

							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-toggle="collapse"
											data-parent="#accordion" href="#panel${index.count}">
											${index.count}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</a>
									</h4>
								</div>
								<div id="panel${index.count}" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="exam_body">${l.bean[0].exam_body}</div>
										<div class="row">
											<div class="col-md-6 col-sm-6">
												<div class="exam_img"><center><img alt="" src="${l.bean[0].body_img }"></img></center></div>
											</div>
											<div class="col-md-6 col-sm-6 chooses_text">
												<div class="btn-group-vertical">
													<c:if test="${l.bean[0].exam_type == 2 }">
														<c:forEach items="${l.bean }" var="choose"
															varStatus="chooseindex">
															<input type="radio" name="answer${index.count}" value="${l.bean[0].exam_id }@${chooseindex.count }">${choose.chooses[0].choose_body }</label>
															<br />
															<hr style="border: 1 dashed #987cb9" width="230px" color=#987cb9 SIZE=1 />
														</c:forEach>
													</c:if>
													<c:if test="${l.bean[0].exam_type == 1 }">
														<input type="radio" name="answer${index.count}" value="${l.bean[0].exam_id }@1">正确</input>
														<br /><hr style="border: 1 dashed #987cb9" width="230px" color=#987cb9 SIZE=1 />
														<input type="radio" name="answer${index.count}" value="${l.bean[0].exam_id }@2">错误</input>
														<br /><hr style="border: 1 dashed #987cb9" width="230px" color=#987cb9 SIZE=1 />
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</c:forEach>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#countdown').load('/stuenroll/jsp/enroll/countdown.jsp');
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-pink',
				radioClass : 'iradio_square-pink',
				increaseArea: '20%',
				handle: 'radio'
			});
			$('#subbut').click(function(){
				$('#paperTest').submit();				
			});
		});
	</script>
</body>
</html>
