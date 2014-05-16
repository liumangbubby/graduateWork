<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>遮罩层</title>
<script type="text/javascript" src="../../js/jquery-1.6.4.js"></script>
<style type="text/css">
/* 半透明的遮罩层 */
#overlay {    background: #000;    filter: alpha(opacity=50); /* IE的透明度 */    opacity: 0.5;  /* 透明度 */    display: none;    position: absolute;    top: 0px;    left: 0px;    width: 100%;    height: 100%;    z-index: 100; /* 此处的图层要大于页面 */    display:none;}
</style>
<script type="text/javascript">
/* 显示遮罩层 */function showOverlay() {    $("#overlay").height(pageHeight());    $("#overlay").width(pageWidth());    // fadeTo第一个参数为速度，第二个为透明度    // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题   
$("#overlay").fadeTo(200, 0.5);}/* 隐藏覆盖层 */function hideOverlay() {    $("#overlay").fadeOut(200);}/* 当前页面高度 */function pageHeight() {    return document.body.scrollHeight;}/* 当前页面宽度 */function pageWidth() {    return document.body.scrollWidth;}
</script>
</head>
<body>
<div id="overlay" style="margin: auto;height: 300px;width: 300px;">

</div>
<button onclick="showOverlay();">显示</button>
<button onclick="hideOverlay();">隐藏</button>
</body>
</html>