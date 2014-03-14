package cn.gov.jyw.action.backyard;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.bean.ExcelBean;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.schedule;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.ScheduleService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.StuEnrollService;

public class ScheduleAction {
	private ScheduleService scheduleService;
	private SpecialtyReportService specialtyReportService;
	private ClassInfoService classInfoService;
	private StuEnrollService stuEnrollService;
	private EduSchoolService eduSchoolService;
	private String school;
	private String classNo;
	private String week;
	private String formDay_1;
	private String formDay_2;
	private String formDay_3;
	private String formDay_4;
	private String formDay_5;
	private String formSubject;
	private String formNumber;
	private String formTeacher;

	private String formPlace;
	private String formRemark;
	private int formWeek;
	private String formClassNo;
	private String filter;
	private ExcelBean excelBean;

	public String index() {
		Map session = ActionContext.getContext().getSession();
		String school = (String) session.get("school");

		List<String> eduList = null;
		List<String> classList = scheduleService.selectSchedule_3(school);
		if (school == null) {
			eduList = scheduleService.selectSchedule_2(); // 培训学校列表
		}

		List<schedule> scList = scheduleService.selectSchedule_1(school, null, filter);
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("scList", scList);
		stack.set("eduList", eduList);
		stack.set("classList", classList);

		return "index";

	}

	public String search() {
		this.school = (this.school != null && this.school.length() == 0 ? null : this.school);
		classNo = (classNo != null && classNo.length() == 0 ? null : classNo);
		Map session = ActionContext.getContext().getSession();
		String school = (String) session.get("school");
		List<schedule> scList = null;
		List<String> eduList = null;
		List<String> classList = null;
		if (school == null) {
			scList = scheduleService.selectSchedule_1(this.school, classNo, filter);
			eduList = scheduleService.selectSchedule_2(); // 培训学校列表
			classList = scheduleService.selectSchedule_3(this.school);
		}
		else {
			scList = scheduleService.selectSchedule_1(school, classNo, filter);
			classList = scheduleService.selectSchedule_3(school);
		}
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("scList", scList);
		stack.set("eduList", eduList);
		stack.set("classList", classList);

		return "index";

	}

	public String schedule() {
		if (classNo != null && classNo.length() > 0) {
			List<schedule> list = this.scheduleService.selectSchedule_5(classNo);
			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("list", list);
			stack.set("school", school);
			return "classSchedule";
		}
		return null;
	}

	public String scheduleOne() {
		if (classNo != null && classNo.length() > 0) {
			schedule sch = this.scheduleService.selectSchedule_6(classNo, Integer.parseInt(week));
			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("sch", sch);
			return "scheduleOne";
		}
		return null;
	}

	public String save() {
		Map session = ActionContext.getContext().getSession();
		String username = (String) session.get("username");

		formDay_1 = formDay_1;
		formDay_2 = formDay_2;
		formDay_3 = formDay_3;
		formDay_4 = formDay_4;
		formDay_5 = formDay_5;
		formSubject = formSubject;
		formTeacher = formTeacher;
		formNumber = formNumber;
		formRemark = formRemark;
		formPlace = formPlace;

		schedule s = new schedule();
		s.setUpload_user(username);
		s.setWeek(formWeek);
		s.setDay_1(formDay_1);
		s.setDay_2(formDay_2);
		s.setDay_3(formDay_3);
		s.setDay_4(formDay_4);
		s.setDay_5(formDay_5);
		s.setTeacher(formTeacher);
		s.setNumber(formNumber);
		s.setPlace(formPlace);
		s.setRemark(formRemark);
		s.setSubject(formSubject);
		s.setClass_no(formClassNo);
		s.setSchool(school);

		boolean bool = scheduleService.selectSchedule_7(formClassNo, formWeek);
		if (bool) {
			this.scheduleService.updateSchedule_1(s);
		}
		else {
			this.scheduleService.addOne(s);
		}
		return schedule();
	}

	public void getClassNoAjax() throws Exception {
		school = (school != null && school.length() == 0 ? null : school);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		List<String> list = scheduleService.selectSchedule_3(school);
		StringBuffer buffer = new StringBuffer(0);
		for (int i = 0; i < list.size(); i++) {
			buffer.append(list.get(i));
			if (i != list.size() - 1) {
				buffer.append("#");
			}
		}
		writer.write(buffer.toString());
		writer.flush();
		writer.close();
	}

	// private String formateString(String arg) {
	// String[] temp = arg.split(",");
	// StringBuffer buff = new StringBuffer();
	// buff.append("[");
	// for (int i = 0; i < temp.length; i++) {
	// buff.append("'");
	// buff.append(temp[i]);
	// buff.append("'");
	// if (i < temp.length - 1) {
	// buff.append(",");
	// }
	// }
	// buff.append("]");
	// return buff.toString();
	// }
	public String delete() {
		this.scheduleService.deleteSchedule_2(classNo, Integer.parseInt(week));
		return schedule();
	}
	
	public void export() throws Exception{
		schedule s=this.scheduleService.selectSchedule_6(classNo, Integer.parseInt(week));
		ServletContext context = ServletActionContext.getServletContext();
		Map session=ActionContext.getContext().getSession();
		String targetDirectory = context.getRealPath("/");
		String path=excelBean.exportSchedule(targetDirectory, s);
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.addHeader("Content-Disposition", "attachment;filename="+new Date().getTime()+".xls");
		OutputStream out=resp.getOutputStream();
		FileInputStream in=new FileInputStream(path);
		int temp=in.read();
		while(temp!=-1){
			out.write(temp);
			temp=in.read();
		}
		in.close();
		out.close();
		
	}

	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

	public ClassInfoService getClassInfoService() {
		return classInfoService;
	}

	public void setClassInfoService(ClassInfoService classInfoService) {
		this.classInfoService = classInfoService;
	}

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

	public EduSchoolService getEduSchoolService() {
		return eduSchoolService;
	}

	public void setEduSchoolService(EduSchoolService eduSchoolService) {
		this.eduSchoolService = eduSchoolService;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getFormDay_1() {
		return formDay_1;
	}

	public void setFormDay_1(String formDay_1) {
		this.formDay_1 = formDay_1;
	}

	public String getFormDay_2() {
		return formDay_2;
	}

	public void setFormDay_2(String formDay_2) {
		this.formDay_2 = formDay_2;
	}

	public String getFormDay_3() {
		return formDay_3;
	}

	public void setFormDay_3(String formDay_3) {
		this.formDay_3 = formDay_3;
	}

	public String getFormDay_4() {
		return formDay_4;
	}

	public void setFormDay_4(String formDay_4) {
		this.formDay_4 = formDay_4;
	}

	public String getFormDay_5() {
		return formDay_5;
	}

	public void setFormDay_5(String formDay_5) {
		this.formDay_5 = formDay_5;
	}

	public String getFormSubject() {
		return formSubject;
	}

	public void setFormSubject(String formSubject) {
		this.formSubject = formSubject;
	}

	public String getFormNumber() {
		return formNumber;
	}

	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}

	public String getFormTeacher() {
		return formTeacher;
	}

	public void setFormTeacher(String formTeacher) {
		this.formTeacher = formTeacher;
	}

	public String getFormPlace() {
		return formPlace;
	}

	public void setFormPlace(String formPlace) {
		this.formPlace = formPlace;
	}

	public String getFormRemark() {
		return formRemark;
	}

	public void setFormRemark(String formRemark) {
		this.formRemark = formRemark;
	}

	public int getFormWeek() {
		return formWeek;
	}

	public void setFormWeek(int formWeek) {
		this.formWeek = formWeek;
	}

	public String getFormClassNo() {
		return formClassNo;
	}

	public void setFormClassNo(String formClassNo) {
		this.formClassNo = formClassNo;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public ExcelBean getExcelBean() {
		return excelBean;
	}

	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

}
