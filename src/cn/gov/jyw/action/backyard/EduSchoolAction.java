package cn.gov.jyw.action.backyard;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.ref;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.RefService;
import cn.gov.jyw.service.RemarkService;
import cn.gov.jyw.service.SpecialtyReportService;

public class EduSchoolAction {
	private EduSchoolService eduSchoolService;
	private SpecialtyReportService specialtyReportService;;
	private RefService refService;
	private RemarkService remarkService;
	private String[] eduId;
	private String school;
	private String spec[];
	private String del;
	private String name;
	private String isRecord;
	private String num;
	

	public String index() {
		List<HashMap> eduList = eduSchoolService.selectEduAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("eduList", eduList);
		return "success";
	}
	

	public String toRef() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		String eduId = ServletActionContext.getRequest().getParameter("eduId");
		if (eduId == null || eduId.length() == 0) {
			eduId = stack.findString("eduId");
		}
		List<specialty_report> list = specialtyReportService.getAll();
		List<ref> refList = refService.selectBySchool(eduId);

		stack.set("list", list);
		StringBuffer buffer = new StringBuffer();
		for (ref ref : refList) {
			buffer.append("#");
			buffer.append(ref.getSpec());
		}
		if (buffer.length() > 0) {
			buffer.substring(1);
		}

		stack.set("bf", buffer.toString());
		return "ref";
	}

	public String addRef() {
		ArrayList list = new ArrayList();
		if ("false".equals(del)) {
			for (int i = 0; i < spec.length; i++) {
				ref ref = new ref();
				ref.setSchool(school);
				ref.setSpec(spec[i]);
				list.add(ref);
			}
			refService.addRef(list, school);
			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("eduId", school);
		}
		else {
			refService.deleteRefBySchool(school); // 如果提交的专业是空，那么就删除该学校的所有专业
		}
		return index();

	}

	public String delete() {
		ArrayList list = new ArrayList();
		for (int i = 0; i < eduId.length; i++) {
			list.add(eduId[i]);
			remarkService.deleteRemark(Integer.parseInt(eduId[i]));
		}
		eduSchoolService.deleteEduSchool(list);
		return index();
	}

	public String insert() {
		edu_school edu = new edu_school();
		edu.setSchool(school);
		eduSchoolService.addEduSchool(edu);
		remarkService.addRemark(num, school);	//插入学校编码
		return index();
	}
	public String addNum() {
		remarkService.addRemark(num, school);
		return index();
	}
	public String updateNum() {
		remarkService.updateRemark_1(num, school);
		return index();
	}
	

	/**
	 * 根据专业查询学校
	 * 
	 * @throws Exception
	 */
	public void selectRefEduBySpecAjax() throws Exception {
		StringBuffer buffer = new StringBuffer();
		if (isRecord.equals("false")) {
			List<edu_school> list = null;
			if (name == null || name.length() == 0) {
				list = eduSchoolService.getEduSchoolAll();
			}
			else {
				list = eduSchoolService.selectRefEduBySpec(name);
			}
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getSchool());
				if (i < list.size() - 1) {
					buffer.append("#");
				}
			}
		}
		else {
			List<String> list = null;
			if (name == null || name.length() == 0) {
				list = eduSchoolService.getRecordEduSchoolAll();
			}
			else {
				// 某个归档学校
				list = eduSchoolService.selectRecordRefEduBySpec(name);
			}
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i));
				if (i < list.size() - 1) {
					buffer.append("#");
				}
			}
			
		}

		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();
		writer.write(buffer.toString());
		writer.close();
	}

	public void selectSpecBySchoolAjax() throws Exception {
		StringBuffer buffer=new StringBuffer();
		if(isRecord.equals("false")){
			List<String> list = this.eduSchoolService.selectSpecBySchool(school);
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i));
				if (i < list.size() - 1) {
					buffer.append("#");
				}
			}
		}
		else{
			//搜索归档
		}
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();
		writer.write(buffer.toString());
		writer.close();
	}

	public EduSchoolService getEduSchoolService() {
		return eduSchoolService;
	}

	public void setEduSchoolService(EduSchoolService eduSchoolService) {
		this.eduSchoolService = eduSchoolService;
	}

	public String[] getEduId() {
		return eduId;
	}

	public void setEduId(String[] eduId) {
		this.eduId = eduId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

	public RefService getRefService() {
		return refService;
	}

	public void setRefService(RefService refService) {
		this.refService = refService;
	}

	public String[] getSpec() {
		return spec;
	}

	public void setSpec(String[] spec) {
		this.spec = spec;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
	}


	public RemarkService getRemarkService() {
		return remarkService;
	}


	public void setRemarkService(RemarkService remarkService) {
		this.remarkService = remarkService;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}

}
