<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/stuenroll/js/jquery.msgbox.js"></script>
<style type="text/css">
		*{margin: 0;padding:0;}  
        body {margin: 0; padding: 20px 100px;background-color: #f4f4f4;}  
        pre{max-height:200px;overflow:auto;}  
        div.demo {overflow:auto;height: 370px;}  
        .box {  
            width: 839px;  
            min-height: 307px;  
            margin: 30px;  
            display: inline-block;  
            background: #fff;  
            border: 1px solid #ccc;  
            position:relative;  
        }  
        .box p {  
            margin: 30px;  
            color: #aaa;  
            outline: none;  
        }  
        /*=========BOX15======*/
        .box15{
            padding: 0 0 1px 0;
            background:#fff;
            background: -webkit-gradient(linear, 100% 100%, 50% 10%, from(#fff), to(#f3f3f3), color-stop(.1,#fff));
            background: -webkit-linear-gradient(100% 50% 90deg, #fff, #fff 10%, #f3f3f3);
            background: -o-linear-gradient(100% 50% 90deg, #fff, #fff 10%, #f3f3f3);
            background: -moz-linear-gradient(100% 50% 90deg, #fff, #fff 10%, #f3f3f3);
            border: 1px solid #ccc;
            -webkit-box-shadow: 1px 1px 4px rgba(0,0,0, 0.1);
            -moz-box-shadow: 1px 1px 4px rgba(0,0,0,0.1);
            box-shadow: 1px 1px 4px rgba(0,0,0,0.1);
            -webkit-border-radius: 0 0 60px 0 / 0 0 5px 0;
            -moz-border-radius: 0 0 60px 0 / 0 0 5px 0;    
            border-radius: 0 0 60px 0 / 0 0 5px 0;    
        }

        .box15:before{
            content: '';
            width: 98%;
            z-index:-1;
            height: 100%;
            padding: 0 0 1px 0;
            position: absolute;
            bottom:0; right:0;
            background: #fff;
            background: -webkit-gradient(linear, 0% 20%, 0% 92%, from(#fff), to(#f9f9f9), color-stop(.1,#fff));
            background: -webkit-linear-gradient(0 0 270deg, #fff, #fff 10%, #f9f9f9);
            background: -o-linear-gradient(0 0 270deg, #fff, #fff 10%, #f9f9f9);
            background: -moz-linear-gradient(0 0 270deg, #fff, #fff 10%, #f9f9f9);
            border: 1px solid #ccc;
            -webkit-box-shadow: 1px 1px 8px rgba(0,0,0, 0.1);
            -moz-box-shadow: 1px 1px 8px rgba(0,0,0,0.1);
            box-shadow: 1px 1px 8px rgba(0,0,0,0.1);
            -webkit-border-radius: 0 0 60px 0 / 0 0 5px 0;
            -moz-border-radius: 0 0 60px 0 / 0 0 5px 0;    
            border-radius: 0 0 60px 0 / 0 0 5px 0;
            -webkit-transform: skew(2deg,2deg) translate(3px,8px);
            -moz-transform: skew(2deg,2deg) translate(3px,8px);
            -o-transform: skew(2deg,2deg) translate(3px,8px);                        
            transform: skew(2deg,2deg) translate(3px,8px);
        }
        .box15:after{
            content: '';
            width: 98%;
            z-index:-1;
            height: 98%;
            padding: 0 0 1px 0;
            position: absolute;
            bottom:0; right:0;
            background: #f3f3f3;
            background: -webkit-gradient(linear, 0% 20%, 0% 100%, from(#f3f3f3), to(#f6f6f6), color-stop(.1,#fff));
            background: -webkit-linear-gradient(0% 0% 360deg, #f3f3f3, #fff, #f6f6f6);
            background: -o-linear-gradient(0% 0% 360deg, #f3f3f3, #fff, #f6f6f6);
            background: -moz-linear-gradient(0% 0% 360deg, #f3f3f3, #fff, #f6f6f6);
            border: 1px solid #ccc;
            -webkit-box-shadow: 0px 0px 8px rgba(0,0,0, 0.1);
            -moz-box-shadow: 0px 0px 8px rgba(0,0,0,0.1);
            box-shadow: 0px 0px 8px rgba(0,0,0,0.1);
            -webkit-transform: skew(2deg,2deg) translate(-1px,2px);
            -moz-transform: skew(2deg,2deg) translate(-1px,2px);
            -o-transform: skew(2deg,2deg) translate(-1px,2px);                        
            transform: skew(2deg,2deg) translate(-1px,2px);
        }

        .box15 .ribbon{
            position:absolute;
            top:0; left: 0;
            width: 130px;
            height: 40px;
            background:rgba(0, 0, 0, 0.1);
            background: -webkit-gradient(linear, 555% 20%, 0% 92%, from(rgba(0, 0, 0, 0.1)), to(rgba(0, 0, 0, 0.0)), color-stop(.1,rgba(0, 0, 0, 0.2)));
            background: -webkit-linear-gradient(555% 0 180deg, rgba(0,0,0,0.1), rgba(0,0,0,0.2) 10%, rgba(0,0,0,0.0));
            background: -o-linear-gradient(555% 0 180deg, rgba(0,0,0,0.1), rgba(0,0,0,0.2) 10%, rgba(0,0,0,0.0));
            background: -moz-linear-gradient(555% 0 180deg, rgba(0,0,0,0.1), rgba(0,0,0,0.2) 10%, rgba(0,0,0,0.0));
            border-left: 1px dashed rgba(0, 0, 0, 0.1);
            border-right: 1px dashed rgba(0, 0, 0, 0.1);
            -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.2);
            -moz-box-shadow: 0px 0px 12px rgba(0,0,0,0.2);
            box-shadow: 0px 0px 12px rgba(0,0,0,0.2);
            -webkit-transform: rotate(-30deg) skew(0,0) translate(-30px,-20px);
            -moz-transform: rotate(-30deg) skew(0,0) translate(-30px,-20px);
            -o-transform: rotate(-30deg) skew(0,0) translate(-30px,-20px);
            transform: rotate(-30deg) skew(0,0) translate(-30px,-20px);
        }
</style>
</head>
<body>
<div class="demo">
        <div class="box box15"><div class="ribbon"></div>
				<div id="ques_id" style="display: none;">${examlist[0].exam_id}</div>
				<div id="ques_body">${examlist[0].exam_body}</div>
				<div id="ques">
					<div id="choose_body">
						<div id="chooseA">
							<span class="choose_text">${examlist[0].chooses[0].choose_body}</span>
							<div class="choose_flag"></div>
						</div>
						<div id="chooseB">
							<span class="choose_text">${examlist[1].chooses[0].choose_body}</span>
							<div class="choose_flag"></div>
						</div>
						<div id="chooseC">
							<span class="choose_text">${examlist[2].chooses[0].choose_body}</span>
							<div class="choose_flag"></div>
						</div>
						<div id="chooseD">
							<span class="choose_text">${examlist[3].chooses[0].choose_body}</span>
							<div class="choose_flag"></div>
						</div>
					</div>
					<div id="right_answer" style="display:none;position: absolute;top: 20px;left: 20px;z-index: -1">
						<c:forEach items="${examlist}" var="exam">
							<c:if test="${exam.chooses[0].right_answer_flag > 0}">${exam.chooses[0].right_answer_flag}</c:if>
						</c:forEach>
					</div>
				</div>
				<div id="exam_img"></div>
		</div>
		<div class="choose_msg"></div>
</div>
</body>
</html>