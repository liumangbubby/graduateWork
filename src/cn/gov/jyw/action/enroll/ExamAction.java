package cn.gov.jyw.action.enroll;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.bean.PageBean;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.pojo.join_state;
import cn.gov.jyw.pojo.nation;
import cn.gov.jyw.pojo.politics;
import cn.gov.jyw.pojo.specialty;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.service.ExamService;
import cn.gov.jyw.service.SpecialtyReportService;

public class ExamAction extends ActionSupport{
	private int id;
	private ExamService examService;
	private PageBean pageBean;
	private SpecialtyReportService specialtyReportService;
	private String toPage;
	private String stype;
	private String context;
	private String specialtySubmit;
	private String eduSchool; 
	private int order;
	private String type;
	private String ids;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getSpecialtySubmit() {
		return specialtySubmit;
	}
	public void setSpecialtySubmit(String specialtySubmit) {
		this.specialtySubmit = specialtySubmit;
	}
	public String getToPage() {
		return toPage;
	}
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getEduSchool() {
		return eduSchool;
	}
	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}
	public void setSpecialtyReportService(
			SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}
	public ExamService getExamService() {
		return examService;
	}
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	////////////////////////////////////////////////////////////////
	public String examQueryOne(){
		List<Object[]> list = examService.queryOneByOne(id);
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("examlist", list);
		System.out.println("------------------over----------");
		return "queryOne";
	}
	public String examQueryAll(){
		Long count = examService.getExamCount();
		pageBean.init(count, 1, 15);
		List<Object[]> enrollList = examService.queryAll(pageBean.getStart(), pageBean.getLimit(), 1);
		List<specialty_report> spList = specialtyReportService.getAll(); // 所有申报专业列表;
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("enrollList", enrollList);
		stack.set("pageBean", pageBean);
		stack.set("spList", spList);
		return "queryAll";
	}
	/**
	 * 查询试题信息
	 * 
	 * @return
	 */
	public String searchExams() {
		// 去除空字符串，因为ibatis动态SQL根据not null判断
		context = "".equals(context) ? null : context;
		eduSchool = "".equals(eduSchool) ? null : eduSchool;
		specialtySubmit = "".equals(specialtySubmit) ? null : specialtySubmit;
		List<Object[]> list = null;
		Long count = examService.getExamCount();
		
		if ("search".equals(stype)) {
			// 输入条件查询，忽略页数，重新计算分页

			pageBean.init(count, 1, 15);
		}
		if ("to".equals(stype)) {
			// 输入跳转页数，不用重新计算分页

			pageBean.init(count, Integer.parseInt(toPage), 15);
		}
		if (pageBean.getStart() == pageBean.getLimit() && pageBean.getStart() == 0) {
			list = examService.queryAll(0, 15, order);
		}
		else {
			list = examService.queryAll(pageBean.getStart(), pageBean.getLimit(), order);
		}
		List<specialty_report> spList = specialtyReportService.getAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("spList", spList);
		stack.set("pageBean", pageBean);
		stack.set("enrollList", list); // 报名数据

		return "success";
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception 
	 */
	public String deleteExam() throws Exception{
		if ("delete".equals(type) && ids != null && ids.length() != 0) {
			String[] id = ids.split("#");
			ArrayList list = new ArrayList();
			for (int i = 0; i < id.length; i++) {
				list.add(id[i]);
			}
			try {
				examService.deleteExam(list);
				examQueryAll();
				return "success";
			}
			catch (Exception e) {
				e.printStackTrace();
				throw e; // 删除失败上抛异常
			}
		}
		else {
			throw new Exception(); // 如果type值不是delete删除数据
		}
	}
	/**
	 * 修改
	 * @return
	 */
	public String modify(){
		if ("modify".equals(type) && ids != null && ids.length() != 0) {
			List<Object[]> list = examService.queryOneByOne(Integer.parseInt(ids.split("#")[0]));
			List<specialty_report> spList = specialtyReportService.getAll(); // 所有申报专业列表;
			
			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("spList", spList);
			stack.set("examlist", list);
			return "modify";
		}
		else {
			return "error";
		}
	}
	/**
	 * 更新
	 * @return
	 */
	public String updateExam(){
		examQueryAll();
		return "success";
	}
}
