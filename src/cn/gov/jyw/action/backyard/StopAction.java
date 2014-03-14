package cn.gov.jyw.action.backyard;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.bean.ExcelBean;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.StopService;

public class StopAction {
	private StopService stopService;
	private String school;
	private String classNo;
	private String name;
	private String pid;
	private String tel;
	private String number;
	private String joinDate;
	private String stopDate;
	private String reason;
	private String remark;
	private String year;
	private ExcelBean excelBean;
	private String[] stopId;
	private EduSchoolService eduSchoolService;
	private ClassInfoService classInfoService;
	private Gson gson = new Gson();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public String stop() {
		String clear = ServletActionContext.getRequest().getParameter("clear");
		if ("true".equals(clear)) {
			this.clear();
		}
		school = "".equals(school) ? null : school;
		classNo = "".equals(classNo) ? null : classNo;
		name = "".equals(name) ? null : name;
		pid = "".equals(pid) ? null : pid;

		Map session = ActionContext.getContext().getSession();
		String sch = (String) session.get("school");
		ValueStack stack = ActionContext.getContext().getValueStack();
		List<String> classList = null;
		List<cn.gov.jyw.pojo.stop> stopList = null;

		if (sch == null) {
			List<String> schList = stopService.selectStop_2();
			stack.set("schList", schList);
			classList = stopService.selectStop_3(null);
			stopList = stopService.selectStop_1(school, classNo, name, pid,null);
			List<edu_school> list = eduSchoolService.getEduSchoolAll();
			stack.set("showSchoolList", list);
		} else {
			classList = stopService.selectStop_3(sch);
			stopList = stopService.selectStop_1(sch, classNo, name, pid,null);
		}
		List<class_info> showClassList = classInfoService.selectClassInfo(
				classNo, null, sch);

		stack.set("classList", classList);
		stack.set("stopList", stopList);
		stack.set("showClassList", showClassList);
		clear();
		return "index";
	}

	public void getClassNoAjax() throws Exception {
		school = school.length() == 0 ? null : school;
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		Writer w = resp.getWriter();
		List<String> list = stopService.selectStop_3(school);
		String json = gson.toJson(list);
		w.write(json);
		w.close();
	}

	public String delete() {
		if (stopId != null && stopId.length > 0) {
			this.stopService.deleteStop_1(stopId);
		}

		return stop();
	}

	public String addStop() throws Exception {
		cn.gov.jyw.pojo.stop s = new cn.gov.jyw.pojo.stop();
		Map session = ActionContext.getContext().getSession();
		String sch = (String) session.get("school");
		if (sch == null) {
			s.setSchool(school);
		} else {
			s.setSchool(sch);
		}
		s.setClass_no(classNo);
		s.setName(name);
		s.setPid(pid);
		s.setTel(tel);
		s.setNumber(Integer.parseInt(number));
		s.setJoin_date(dateFormat.parse(joinDate));
		s.setStop_date(dateFormat.parse(stopDate));
		s.setReason(reason);
		s.setRemark(remark);
		long time = dateFormat.parse(stopDate).getTime()
				- dateFormat.parse(joinDate).getTime();
		s.setDays((int) Math.ceil(time / 24 / 60 / 60 / 1000));
		stopService.addStop(s);
		clear();
		return stop();
	}

	public String chartStop() {
		Map session = ActionContext.getContext().getSession();
		String sch = (String) session.get("school");
		ValueStack stack = ActionContext.getContext().getValueStack();
		// 查询某个班级
		if (classNo != null && classNo.length() > 0) {
			HashMap map = this.stopService.selectStop_4(classNo);
			long num = (Long) map.get("num");
			long ss = (Long) map.get("ss");
			stack.set("num", num);
			stack.set("ss", ss);
			stack.set("classNo", classNo);
			clear();
			return "chartClass";
		}
		// 查询某个学校本年度的报名信息
		else if ((classNo == null || classNo.length() == 0)
				&& (school != null && school.length() > 0)) {
			GregorianCalendar calendar = new GregorianCalendar();
			int year = calendar.get(calendar.YEAR);

			long count = 0;
			long stop = 0;
			if (sch != null) {
				stack.set("shcool", sch);
				count = stopService.selectStop_6(year, sch);
				stop = stopService.selectStop_5(year, sch);
			} else if (sch == null && school != null && school.length() > 0) {
				stack.set("chartShcool", school);
				count = stopService.selectStop_6(year, school);
				stop = stopService.selectStop_5(year, school);
			}
			stack.set("count", count); // 学校开班总人数
			stack.set("stop", stop); // 学校中退总人数
			stack.set("year", year); // 学校中退总人数
			clear();
			return "chartSchool";

		} else if (sch == null && (school == null || school.length() == 0)) {
			// 查询各家学校的开班人数和中退人数比例和全省中退总人数和补贴总人数
			GregorianCalendar calendar = new GregorianCalendar();
			int year = calendar.get(calendar.YEAR);
			List<edu_school> list = eduSchoolService.getEduSchoolAll();
			ArrayList<HashMap> data = new ArrayList<HashMap>();
			stack.set("year", year);
			for (edu_school one : list) {
				String s = one.getSchool();
				long count = stopService.selectStop_6(year, s);
				long stop = stopService.selectStop_5(year, s);
				HashMap map = new HashMap();
				map.put("school", s);
				map.put("count", count);
				map.put("stop", stop);
				data.add(map);
			}
			String json = gson.toJson(data);
			stack.set("everSchool", json);

			long c1 = stopService.selectStop_7(year);
			long c2 = stopService.selectStop_8(year);
			long c3 = stopService.selectStop_9(year);

			stack.set("c1", c1);
			stack.set("c2", c2);
			stack.set("c3", c3);

			return "chartAll";
		}
		return null;

	}
	public void exportExcel() throws Exception{
		ServletContext context = ServletActionContext.getServletContext();
		Map session=ActionContext.getContext().getSession();
		String sch=(String) session.get("school");
		List<cn.gov.jyw.pojo.stop> list=null;
		String temp=null;
		if(sch==null){
			temp=school;
			list=stopService.selectStop_1(school, null, null,null, Integer.parseInt(year));
		}
		else{
			temp=sch;
			list=stopService.selectStop_1(sch, null, null,null, Integer.parseInt(year));
		}
		
		String targetDirectory = context.getRealPath("/");
		String path=excelBean.exportStop(targetDirectory, list,temp);
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.addHeader("Content-Disposition", "attachment;filename="+new Date().getTime()+".xls");
		OutputStream out=resp.getOutputStream();
		FileInputStream in=new FileInputStream(path);
		int ttt=in.read();
		while(ttt!=-1){
			out.write(ttt);
			ttt=in.read();
		}
		in.close();
		out.close();
		clear();
	}
	private void clear() {
		school = null;
		classNo = null;
		name = null;
		pid = null;
		stopId = null;
		joinDate = null;
		name = null;
		number = null;
		pid = null;
		reason = null;
		remark = null;
		stopDate = null;
		tel = null;
	}

	public StopService getStopService() {
		return stopService;
	}

	public void setStopService(StopService stopService) {
		this.stopService = stopService;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getClassNo() {
		return classNo;
	}

	public String[] getStopId() {
		return stopId;
	}

	public void setStopId(String[] stopId) {
		this.stopId = stopId;
	}

	public EduSchoolService getEduSchoolService() {
		return eduSchoolService;
	}

	public void setEduSchoolService(EduSchoolService eduSchoolService) {
		this.eduSchoolService = eduSchoolService;
	}

	public ClassInfoService getClassInfoService() {
		return classInfoService;
	}

	public void setClassInfoService(ClassInfoService classInfoService) {
		this.classInfoService = classInfoService;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getStopDate() {
		return stopDate;
	}

	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ExcelBean getExcelBean() {
		return excelBean;
	}

	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

}
