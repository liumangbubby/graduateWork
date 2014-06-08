package cn.gov.jyw.action.enroll;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.gov.jyw.pojo.error_exam_tem;
import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.pojo.exam_error;
import cn.gov.jyw.pojo.tb_user;
import cn.gov.jyw.pojo.user_details;
import cn.gov.jyw.service.ErrorTemService;
import cn.gov.jyw.service.ExamService;
import cn.gov.jyw.service.UserDetailsService;
import cn.gov.jyw.util.MessageBox;

public class UserDetailsAction extends ActionSupport{
	private int user_id;
	private int exam_no;
	private int type_no;
	private String errorlist;
	private UserDetailsService detailsServ;
	private ErrorTemService errorServ;
	//插入
	public String insertDetails() throws Exception{
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		if(errorlist != null && errorlist.length() > 0){
			String[] ss = errorlist.split("#");
			Set<String> errors = new HashSet<String>(Arrays.asList(ss));
			List<error_exam_tem> list = new ArrayList<error_exam_tem>();
			for(String s : errors){
				error_exam_tem error = new error_exam_tem(user_id,Integer.parseInt(s));
				list.add(error);
			}
			errorServ.addErrorTem(list);
		}
		detailsServ.addUserDetails(new user_details(user_id,new Timestamp(System.currentTimeMillis()),exam_no,type_no));
		return "null";
	}
	//查询用户题目进度
	public String queryUserQuesNo() throws Exception{
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		MessageBox msg = new MessageBox();
		Gson gson = new Gson();
		int ques_no = detailsServ.queryQuesNo(user_id);
		if(ques_no > 0){
			msg.setStutas(MessageBox.SUCCESS);
			msg.setData(ques_no);
		}else{
			msg.setStutas(MessageBox.ERROR);
			msg.setMsg("未知错误！");
		}
		PrintWriter writer = res.getWriter();
		writer.write(gson.toJson(msg));
		writer.close();
		return "null";
	}
	//getter setter
	public int getExam_no() {
		return exam_no;
	}
	public void setExam_no(int exam_no) {
		this.exam_no = exam_no;
	}
	public int getType_no() {
		return type_no;
	}
	public void setType_no(int type_no) {
		this.type_no = type_no;
	}
	public UserDetailsService getDetailsServ() {
		return detailsServ;
	}
	public void setDetailsServ(UserDetailsService detailsServ) {
		this.detailsServ = detailsServ;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getErrorlist() {
		return errorlist;
	}
	public void setErrorlist(String errorlist) {
		this.errorlist = errorlist;
	}
	public ErrorTemService getErrorServ() {
		return errorServ;
	}
	public void setErrorServ(ErrorTemService errorServ) {
		this.errorServ = errorServ;
	}
}
