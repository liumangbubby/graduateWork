package cn.gov.jyw.action.enroll;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.gov.jyw.service.TbUserService;
import cn.gov.jyw.util.MessageBox;

public class UserLoginAction extends ActionSupport{
	private String userName;
	private String password;
	private List<String> autologin = new ArrayList<String>();
	private TbUserService userService;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getAutologin() {
		return autologin;
	}
	public void setAutologin(List<String> autologin) {
		this.autologin = autologin;
	}
	public TbUserService getUserService() {
		return userService;
	}
	public void setUserService(TbUserService userService) {
		this.userService = userService;
	}
	
	/////////////////////////////////////////////////
	public String userLogin() throws Exception{
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		MessageBox msg = new MessageBox();
		Gson gson = new Gson();
		if(userService.hasUser(userName, password)){
			msg.setStutas(MessageBox.SUCCESS);
		}else{
			msg.setStutas(MessageBox.ERROR);
			msg.setMsg("用户不存在，账户名或密码错误");
		}
		String json = gson.toJson(msg);
		PrintWriter writer = res.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		return null;
	}
}
