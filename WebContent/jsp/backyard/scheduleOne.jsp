<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/backyard.css" rel="stylesheet" type="text/css" />
<link href="../../css/scheduleOne.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery-1.6.4.js"></script>
<script src="../../js/jquery.json-2.4.min.js"></script>
<script src="../../js/backyard.js"></script>
<script src="../../js/scheduleOne.js"></script>
</head>

<body class="body">
<div class="main_div">
  <jsp:include page="left_nav.jsp"></jsp:include>
  <div class="right_div spe_div">
  	<form action="" id="" method="post"> 
    <p class="spe_title">辽宁省离校未就业高校毕业生专业转换及技能培训 第（<input type="text" name="week" id='week' value="${requestScope.week}"/>）周 课程表</p>
    <hr />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table">
      <tr>
        <td width="100" height="60" rowspan="2" class="th_1 td">科目</td>
        <td width="100" height="30" class="td th_1">星期一</td>
        <td width="100" class="td th_1">星期二</td>
        <td width="100" class="td th_1">星期三</td>
        <td width="100" class="td th_1">星期四</td>
        <td width="100" class="td th_1">星期五</td>
        <td width="100" rowspan="2" class="td th_1">教师</td>
        <td width="100" rowspan="2" class="td th_1">上课人数</td>
        <td width="100" rowspan="2" class="td th_1">上课地点</td>
        <td width="143" rowspan="2" class="td th_1">备注</td>
      </tr>
      <tr>
        <td height="30" class="td th_1 day_1" name='t1' id='tt1'></td>
        <td class="td th_1 day_2" name='t1' id='tt2'></td>
        <td class="td th_1 day_3" name='t1' id='tt3'></td>
        <td class="td th_1 day_4" name='t1' id='tt4'></td>
        <td class="td th_1 day_5" name='t1' id='tt5'></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d1" name="n1"></td>
        <td class="td day_1" id="d2" name="n1"></td>
        <td class="td day_2" id="d3" name="n1"></td>
        <td class="td day_3" id="d4" name="n1"></td>
        <td class="td day_4" id="d5" name="n1"></td>
        <td class="td day_5" id="d6" name="n1"></td>
        <td class="td teacher" id="d7" name="n1"></td>
        <td class="td number" id="d8"  name="n1"></td>
        <td class="td place" id="d9" name="n1"></td>
        <td class="td remark" id="d10" name="n1" style="width:143px;"></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d11" name="n1"></td>
        <td class="td day_1" id="d12" name="n1"></td>
        <td class="td day_2" id="d13" name="n1"></td>
        <td class="td day_3" id="d14" name="n1"></td>
        <td class="td day_4" id="d15" name="n1"></td>
        <td class="td day_5" id="d16" name="n1"></td>
        <td class="td teacher" id="d17" name="n1"></td>
        <td class="td number" id="d18" name="n1"></td>
        <td class="td place" id="d19" name="n1"></td>
        <td class="td remark" id="d20" name="n1"></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d21" name="n1"></td>
        <td class="td day_1" id="d22" name="n1"></td>
        <td class="td day_2" id="d23" name="n1"></td>
        <td class="td day_3" id="d24" name="n1"></td>
        <td class="td day_4" id="d25" name="n1"></td>
        <td class="td day_5" id="d26" name="n1"></td>
        <td class="td teacher" id="d27" name="n1"></td>
        <td class="td number" id="d28"  name="n1"></td>
        <td class="td place" id="d29" name="n1"></td>
        <td class="td remark" id="d30" name="n1"></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d31" name="n1"></td>
        <td class="td day_1" id="d32" name="n1"></td>
        <td class="td day_2" id="d33" name="n1"></td>
        <td class="td day_3" id="d34" name="n1"></td>
        <td class="td day_4" id="d35" name="n1"></td>
        <td class="td day_5" id="d36" name="n1"></td>
        <td class="td teacher" id="d37" name="n1"></td>
        <td class="td number" id="d38"  name="n1"></td>
        <td class="td place" id="d39" name="n1"></td>
        <td class="td remark" id="d40" name="n1"></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d41" name="n1"></td>
        <td class="td day_1" id="d42" name="n1"></td>
        <td class="td day_2" id="d43" name="n1"></td>
        <td class="td day_3" id="d44" name="n1"></td>
        <td class="td day_4" id="d45" name="n1"></td>
        <td class="td day_5" id="d46" name="n1"></td>
        <td class="td teacher" id="d47" name="n1"></td>
        <td class="td number" id="d48" name="n1"></td>
        <td class="td place" id="d49" name="n1"></td>
        <td class="td remark" id="d50" name="n1"></td>
      </tr>
     <tr>
        <td height="60" class="td subject" id="d51" name="n1"></td>
        <td class="td day_1" id="d52" name="n1"></td>
        <td class="td day_2" id="d53" name="n1"></td>
        <td class="td day_3" id="d54" name="n1"></td>
        <td class="td day_4" id="d55" name="n1"></td>
        <td class="td day_5" id="d56" name="n1"></td>
        <td class="td teacher" id="d57" name="n1"></td>
        <td class="td number" id="d58" name="n1"></td>
        <td class="td place" id="d59" name="n1"></td>
        <td class="td remark" id="d60" name="n1"></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d71" name="n1"></td>
        <td class="td day_1" id="d72" name="n1"></td>
        <td class="td day_2" id="d73" name="n1"></td>
        <td class="td day_3" id="d74" name="n1"></td>
        <td class="td day_4" id="d75" name="n1"></td>
        <td class="td day_5" id="d76" name="n1"></td>
        <td class="td teacher" id="d77" name="n1"></td>
        <td class="td number" id="d78"  name="n1"></td>
        <td class="td place" id="d79" name="n1"></td>
        <td class="td remark" id="d80" name="n1"></td>
      </tr>
      <tr>
        <td height="60" class="td subject" id="d91" name="n1"></td>
        <td class="td day_1" id="d92" name="n1"></td>
        <td class="td day_2" id="d93" name="n1"></td>
        <td class="td day_3" id="d94" name="n1"></td>
        <td class="td day_4" id="d95" name="n1"></td>
        <td class="td day_5" id="d96" name="n1"></td>
        <td class="td teacher" id="d97" name="n1"></td>
        <td class="td number" id="d98" name="n1"></td>
        <td class="td place" id="d99" name="n1"></td>
        <td class="td remark" id="d100" name="n1"></td>
      </tr>
    </table>
    <p>
    	<input type="button" class="btnn" id='submit' value="提&nbsp;交"/>
        <input type="button" class="btnn" value="返&nbsp;回" id='back'/>
    </p>
    </form>
    <form id="form_2" method="post" action="/stuenroll/jsp/backyard/ScheduleAction!save.action">
    <input type="hidden" id="subject" name="formSubject" value='${sch.subject}' />
    <input type="hidden" id="day_1" name='formDay_1' value='${sch.day_1}' />
    <input type="hidden" id="day_2" name="formDay_2" value='${sch.day_2}' />
    <input type="hidden" id="day_3" name="formDay_3" value='${sch.day_3}' />
    <input type="hidden" id="day_4" name="formDay_4" value='${sch.day_4}' />
    <input type="hidden" id="day_5" name="formDay_5" value='${sch.day_5}' />
    <input type="hidden" id="teacher" name="formTeacher" value='${sch.teacher}' />
    <input type="hidden" id="number" name='formNumber' value='${sch.number}' />
    <input type="hidden" id="place" name='formPlace' value='${sch.place}' />
    <input type="hidden" id="remark" name='formRemark' value='${sch.remark}' />
    <input type="hidden" id='formWeek' name='formWeek' value='${requestScope.week}' />
    <input type="hidden" name='formClassNo' value='${requestScope.classNo}' />
    </form>
  </div>
</div>
</body>
</html>
    