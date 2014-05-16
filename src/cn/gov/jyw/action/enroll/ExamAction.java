package cn.gov.jyw.action.enroll;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.service.ExamService;

public class ExamAction extends ActionSupport{
	private int id;
	private ExamService examService;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ExamService getExamService() {
		return examService;
	}
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	public String examQueryOne(){
		List<Object[]> list = examService.queryOneByOne(id);
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("examlist", list);
		System.out.println("------------------over----------");
		return "queryOne";
	}
}
