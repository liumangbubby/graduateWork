package cn.gov.jyw.action.backyard;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.user;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.UserService;

public class UserAction {
	private UserService userService;
	private EduSchoolService eduSchoolService;
	private String[] userId;
	private String username;
	private String password;
	private String school;

	public String index() {
		List<user> userList = this.userService.getUserAll();
		List<edu_school> schoolList = this.eduSchoolService.getEduSchoolAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("userList", userList);
		stack.set("schoolList", schoolList);
		return "success";
	}

	public String delete() {
		if (userId == null || userId.length == 0) {
			return "error";
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < userId.length; i++) {
			list.add(Integer.parseInt(userId[i]));
		}
		userService.deleteUser(list);
		clear();
		return index();
	}

	public String insert() {
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return "error";
		}
		user user = new user();
		user.setUsername(username);
		user.setPassword(password);
		user.setSchool(school);
		userService.addUser(user);
		clear();
		return index();
	}

	private void clear() {
		this.password = null;
		this.username = null;
		this.userId = null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String[] getUserId() {
		return userId;
	}

	public void setUserId(String[] userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
