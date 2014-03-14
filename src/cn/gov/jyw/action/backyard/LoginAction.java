
package cn.gov.jyw.action.backyard;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.bean.PageBean;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.join_state;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.EnrollManageService;
import cn.gov.jyw.service.JoinStateService;
import cn.gov.jyw.service.LoginService;
import cn.gov.jyw.service.RemarkService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.UserService;

public class LoginAction {
	private String username;
	private String password;
	private LoginService loginService;
	private PageBean pageBean;
	private EnrollManageService enrollManageService;
	private SpecialtyReportService specialtyReportService;
	private JoinStateService joinStateService;
	private EduSchoolService eduSchoolService;
	private ClassInfoService classInfoService;
	private UserService userService;
	private StuEnrollService stuEnrollService;
	private RemarkService remarkService;

	public String login() {
		boolean bool = loginService.login(username, password);
		if (bool) {
			String sch = userService.selectUserSchool(username);	//查询用户所在的机构
			//查询remark
			String num=remarkService.selectNum(sch);
			long count = enrollManageService.getEnrollCount(sch,num);
			pageBean.init(count, 1, 15);
			List<stu_enroll> list = enrollManageService.getEnrollInfo(pageBean.getStart(), pageBean.getLimit(), 1, sch,num);
			List<specialty_report> spList = null;
			List<class_info> classList = null;
			//String school = (String) ActionContext.getContext().getSession().get("school");
			if (sch == null) {
				spList = specialtyReportService.getAll(); // 所有申报专业列表
				classList = classInfoService.getClassInfoAll("false"); // 班级列表信息
			}
			else {
				// 某个学校的专业列表
				spList = stuEnrollService.selectEnrollSpec(sch);
				classList = stuEnrollService.selectEnrollClass(sch, null);

			}
			List<join_state> joList = joinStateService.getAll();

			List<edu_school> eduList = eduSchoolService.getEduSchoolAll(); // 培训学校列表
			ValueStack stack = ActionContext.getContext().getValueStack();

			stack.set("enrollList", list);
			stack.set("pageBean", pageBean);
			stack.set("spList", spList);
			stack.set("joList", joList);
			stack.set("classList", classList);
			stack.set("eduList", eduList);
			// 登陆成功之后往Session中保存数据
			Map session = ActionContext.getContext().getSession();
			session.put("username", username); // 记录用户名
			session.put("loginTime", new Date().getTime());// 记录登陆时间
			session.put("school", sch); // 用户所在的机构
		}

		return bool == true ? "success" : "fail";
	}

	public String out() {
		ServletActionContext.getRequest().getSession().invalidate(); // 销毁Session对象
		return "out";
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

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public EnrollManageService getEnrollManageService() {
		return enrollManageService;
	}

	public void setEnrollManageService(EnrollManageService enrollManageService) {
		this.enrollManageService = enrollManageService;
	}

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

	public JoinStateService getJoinStateService() {
		return joinStateService;
	}

	public void setJoinStateService(JoinStateService joinStateService) {
		this.joinStateService = joinStateService;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

	public RemarkService getRemarkService() {
		return remarkService;
	}

	public void setRemarkService(RemarkService remarkService) {
		this.remarkService = remarkService;
	}

}
