package cn.gov.jyw.action.backyard;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.specialty_report;

import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.SchoolEnrollService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.StuRecordService;

public class ClassInfoAction {
	private SpecialtyReportService specialtyReportService;
	private ClassInfoService classInfoService;
	private EduSchoolService eduSchoolService;
	private StuRecordService stuRecordService;
	private String[] classId;
	private String name;
	private String school;
	private String type;
	private String classNo;
	private String isRecord;
	private StuEnrollService stuEnrollService;
	private Gson gson = new Gson();;

	public String index() {
		clear();
		List<specialty_report> reportList = specialtyReportService.getAll();
		List<class_info> classList = classInfoService.getClassInfoAll(null);
		List<edu_school> eduList = eduSchoolService.getEduSchoolAll();
		List<class_info> classInfoList = classInfoService.getClassInfoAll(null);

		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("reportList", reportList);
		stack.set("classList", classList);
		stack.set("eduList", eduList);
		stack.set("classInfoList", classInfoList);
		return "success";

	}

	public String delete() {
		ArrayList list = new ArrayList();
		for (int i = 0; i < classId.length; i++) {
			list.add(Integer.parseInt(classId[i]));
		}
		classInfoService.deleteClassInfo(list);

		return index();
	}

	public String select() {
		name = ("".equals(name) ? null : name);
		type = ("".equals(type) ? null : type);
		school = ("".equals(school) ? null : school);

		List<class_info> classList = classInfoService.selectClassInfo(name, type, school);
		List<specialty_report> reportList = specialtyReportService.getAll();
		List<edu_school> eduList = eduSchoolService.getEduSchoolAll();
		List<class_info> classInfoList = classInfoService.getClassInfoAll(null);

		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("reportList", reportList);
		stack.set("classList", classList);
		stack.set("eduList", eduList);
		stack.set("classInfoList", classInfoList);
		return "success";
	}

	public String updateArchivesClass() throws Exception {
		classInfoService.updateArchivesClass(classNo);
		return index();
	}

	public String add() throws Exception {
		class_info info = new class_info();
		info.setName(name);
		info.setSchool(school);
		info.setType(type);

		classInfoService.addClassInfo(info);
		clear();
		return index();
	}

	public void selectClassInfoAjax() throws Exception {
		name = ("".equals(name) ? null : name);
		type = ("".equals(type) ? null : type);
		school = ("".equals(school) ? null : school);
		StringBuffer buffer = new StringBuffer();
		String sc = (String) ActionContext.getContext().getSession().get("school");
		if (isRecord.equals("false")) {
			List<class_info> list = null;
			if (sc == null) {
				list = this.classInfoService.selectClassInfo(name, type, school);

			}
			else {
				list = stuEnrollService.selectEnrollClass(sc, type);
			}
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getName());
				if (i < list.size() - 1) {
					buffer.append("#");
				}
			}
		}
		else {
			List<String> list = stuRecordService.selectRecordClass(school, type);
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i));
				if (i < list.size() - 1) {
					buffer.append("#");
				}
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		writer.write(buffer.toString());
		writer.close();
		clear();
	}

	public void selectClassMaxAjax() throws Exception {
		type = ("".equals(type) ? null : type);
		class_info info = classInfoService.selectClassMax(type);
		int num;
		if(info!=null){
			String name = info.getName(); // 班级编号
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i <= 9; i++) {
				int temp = name.indexOf("" + i);
				if (temp != -1) {
					list.add(temp);
				}
			}
			Object[] obj = list.toArray();
			Arrays.sort(obj);
			num = Integer.parseInt(name.substring(Integer.parseInt(obj[0] + "")));
			num++;
			GregorianCalendar calendar=new GregorianCalendar();
			int i=calendar.get(GregorianCalendar.YEAR)-2000;
			i=i*100;
			if(i>num){
				num=++i;
			}
		}
		else{
			GregorianCalendar calendar=new GregorianCalendar();
			int i=calendar.get(GregorianCalendar.YEAR)-2000;
			i=i*100;
			num=++i;
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		writer.write(num + "");
		writer.close();
		clear();
	}

	public void getTypeAndSchoolAjax() throws Exception {
		class_info info = this.classInfoService.selectClassInfo(name, null, null).get(0);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		writer.write(info.getSchool() + "#" + info.getType());
		writer.close();
		clear();
	}

	public void selectClassBySchoolAjax() throws Exception {
		List<class_info> list = this.classInfoService.selectClassInfo(name, type, school);
		String json = gson.toJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Writer w = response.getWriter();
		w.write(json);
		w.close();
	}

	public void clear() {
		this.name = null;
		this.type = null;
		this.school = null;
		this.classId = null;
		this.isRecord = null;
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

	public String[] getClassId() {
		return classId;
	}

	public void setClassId(String[] classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EduSchoolService getEduSchoolService() {
		return eduSchoolService;
	}

	public void setEduSchoolService(EduSchoolService eduSchoolService) {
		this.eduSchoolService = eduSchoolService;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public StuRecordService getStuRecordService() {
		return stuRecordService;
	}

	public void setStuRecordService(StuRecordService stuRecordService) {
		this.stuRecordService = stuRecordService;
	}

	public String getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
	}

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

}
