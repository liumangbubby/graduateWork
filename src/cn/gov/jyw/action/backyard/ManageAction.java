package cn.gov.jyw.action.backyard;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.bean.PageBean;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.join_state;
import cn.gov.jyw.pojo.nation;
import cn.gov.jyw.pojo.politics;
import cn.gov.jyw.pojo.specialty;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.year;
import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.EnrollManageService;
import cn.gov.jyw.service.JoinStateService;
import cn.gov.jyw.service.PlaceService;
import cn.gov.jyw.service.PoliticsService;
import cn.gov.jyw.service.RemarkService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.SpecialtyService;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.YearService;
import cn.gov.jyw.pojo.edu_school;

public class ManageAction {
	private EnrollManageService enrollManageService;
	private SpecialtyReportService specialtyReportService;
	private JoinStateService joinStateService;
	private StuEnrollService stuEnrollService;
	private YearService yearService;
	private PoliticsService politicsService;
	private SpecialtyService specialtyService;
	private EduSchoolService eduSchoolService;
	private ClassInfoService classInfoService;
	private PlaceService placeService;
	private RemarkService remarkService;
	// //////////////////////////////////////////////////////
	private long id;
	private String ids;
	private String type;
	private PageBean pageBean;
	private String name;
	private String pid;
	private String specialtySubmit;
	private String eduSchool;
	private String classNo;
	private String joinState;
	private String toPage;
	private String stype;

	private String sex;
	private String nation;
	private String gradute_g;
	private String gradute_y;
	private String gradute_d;
	private String education;
	private String specialty;
	private String healthy;
	private String politics;
	private String birthday;
	private String address;
	private String household;
	private String tel;
	private String email;
	private String home_address;
	private String home_tel;
	private String specialty_submit;
	private String intention;
	private String remark;
	private String class_no;
	private String stu_no;
	private String join_state;
	private String edu_school;
	private String skilled;
	private String place;
	private String change_place;
	private int order;
	private String classArray;

	public String deleteEnrollInfo() throws Exception {

		String school = (String) ActionContext.getContext().getSession().get("school");

		if (school != null && school.length() > 0) {
			throw new Exception();// 判断用户是否是网站人员，不是就不能删除记录
		}
		if ("delete".equals(type) && ids != null && ids.length() != 0) {
			String[] id = ids.split("#");
			ArrayList list = new ArrayList();
			for (int i = 0; i < id.length; i++) {
				list.add(id[i]);
			}
			try {
				enrollManageService.deleteEnrollInfo(list);
				index();
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

	public String index() {
		clear();
		String school = (String) ActionContext.getContext().getSession().get("school");
		String num=remarkService.selectNum(school);
		long count = enrollManageService.getEnrollCount(school,num);
		pageBean.init(count, 1, 15);
		List<stu_enroll> list = enrollManageService.getEnrollInfo(pageBean.getStart(), pageBean.getLimit(), 1, school,num); // 申报信息分页数据
		List<specialty_report> spList = null;
		List<class_info> classList = null;
		if (school == null) {
			spList = specialtyReportService.getAll(); // 所有申报专业列表
			classList = classInfoService.getClassInfoAll("false"); // 班级列表信息
		}
		else {
			// 某个学校的专业列表
			spList = stuEnrollService.selectEnrollSpec(school);
			classList = stuEnrollService.selectEnrollClass(school, null);

		}

		List<join_state> joList = joinStateService.getAll(); // 参与状态列表

		List<edu_school> eduList = eduSchoolService.getEduSchoolAll(); // 培训学校列表

		ValueStack stack = ActionContext.getContext().getValueStack();

		stack.set("enrollList", list);
		stack.set("pageBean", pageBean);
		stack.set("spList", spList);
		stack.set("joList", joList);
		stack.set("classList", classList);
		stack.set("eduList", eduList);

		return "success";
	}

	/**
	 * 查询学生信息
	 * 
	 * @return
	 */
	public String searchEnrolls() {
		// 去除空字符串，因为ibatis动态SQL根据not null判断
		name = "".equals(name) ? null : name;
		pid = "".equals(pid) ? null : pid;
		specialtySubmit = "".equals(specialtySubmit) ? null : specialtySubmit;
		eduSchool = "".equals(eduSchool) ? null : eduSchool;
		classNo = "".equals(classNo) ? null : classNo;
		joinState = "".equals(joinState) ? null : joinState;
		List<stu_enroll> list = null;
		String num=remarkService.selectNum(eduSchool);
		long count = enrollManageService.getEnrollCountByCondition(name, pid, specialtySubmit, eduSchool, classNo, joinState,num);
		if ("search".equals(stype)) {
			// 输入条件查询，忽略页数，重新计算分页

			pageBean.init(count, 1, 15);
		}
		if ("to".equals(stype)) {
			// 输入跳转页数，不用重新计算分页

			pageBean.init(count, Integer.parseInt(toPage), 15);
		}
		if (pageBean.getStart() == pageBean.getLimit() && pageBean.getStart() == 0) {
			list = enrollManageService.searchEnrolls(name, pid, specialtySubmit, eduSchool, classNo, joinState, 0, 15, order,num);
		}
		else {
			list = enrollManageService
					.searchEnrolls(name, pid, specialtySubmit, eduSchool, classNo, joinState, pageBean.getStart(), pageBean.getLimit(), order,num);
		}
		String school = (String) ActionContext.getContext().getSession().get("school");
		List<specialty_report> spList = null;
		List<class_info> classList = null;
		if (school == null) {
			spList = specialtyReportService.getAll(); // 所有申报专业列表
			classList = classInfoService.getClassInfoAll("false"); // 班级列表信息
		}
		else {
			// 某个学校的专业列表
			spList = stuEnrollService.selectEnrollSpec(school);
			classList = stuEnrollService.selectEnrollClass(school, null);

		}
		List<join_state> joList = joinStateService.getAll(); // 参与状态
		List<edu_school> eduList = eduSchoolService.getEduSchoolAll(); // 培训学校列表
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("joList", joList);
		stack.set("spList", spList);
		stack.set("pageBean", pageBean);
		stack.set("classList", classList);
		stack.set("eduList", eduList);
		stack.set("enrollList", list); // 报名数据

		return "success";
	}

	/**
	 * 跳转到数据更新画面
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String modify() throws Exception {
		String school = (String) ActionContext.getContext().getSession().get("school");

		if (school != null && school.length() > 0) {
			throw new Exception();// 判断用户是否是网站人员，不是就不能删除记录
		}
		if ("modify".equals(type) && ids != null && ids.length() != 0) {
			stu_enroll enroll = this.enrollManageService.searchEnrollsById(Long.parseLong(ids.split("#")[0]), eduSchool); // 申报信息
			List<nation> nationList = stuEnrollService.getNationList(); // 民族列表
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(enroll.getCreate_time()); // 根据报名时间取出来注册的年份
			int year = calendar.get(calendar.YEAR);
			// 当前年份以前的三年
			ArrayList yearList = new ArrayList();

			yearList.add(year - 2);
			yearList.add(year - 1);
			yearList.add(year);
			List<specialty> specialtyList = specialtyService.getSpecialtyAll();// 大学专业
			List<specialty_report> reportList = specialtyReportService.getAll(); // 申报专业
			List<politics> politicsList = politicsService.getPoliticsAll(); // 政治面貌
			List<edu_school> eduList = eduSchoolService.getEduSchoolAll(); // 培训学校
			List<join_state> joinList = joinStateService.getAll(); // 参与状态
			List<class_info> infoList = classInfoService.getClassInfoAll("false"); // 班级信息
			List placeList = placeService.getPlaceAll(); // 培训地点

			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("enroll", enroll);
			stack.set("nationList", nationList);
			stack.set("yearList", yearList);
			stack.set("specialtyList", specialtyList);
			stack.set("reportList", reportList);
			stack.set("politicsList", politicsList);
			stack.set("eduList", eduList);
			stack.set("joinList", joinList);
			stack.set("infoList", infoList);
			stack.set("placeList", placeList);
			return "modify";
		}
		else {
			return "error";
		}

	}

	public String updateStuEnroll() throws Exception {
		String school = (String) ActionContext.getContext().getSession().get("school");

		if (school != null && school.length() > 0) {
			throw new Exception();// 判断用户是否是网站人员，不是就不能删除记录
		}
		stu_enroll enroll = new stu_enroll(id, name, sex, nation, pid, gradute_g, gradute_y, gradute_d, education, specialty, healthy, politics, birthday,
				address, household, tel, email, home_address, home_tel, specialty_submit, "", remark, class_no, stu_no, join_state, edu_school, skilled, place,
				Boolean.parseBoolean(change_place));
		this.enrollManageService.updateEnrolls(enroll);
		this.clear();
		return this.index();
	}

	/**
	 * 批量修改信息，仅限于学校、班级、申报专业和参与状态
	 * @throws Exception 
	 */
	public String updateStuEnrollBacth() throws Exception {
		String school = (String) ActionContext.getContext().getSession().get("school");

		if (school != null && school.length() > 0) {
			throw new Exception();// 判断用户是否是网站人员，不是就不能删除记录
		}

		if (ids != null && ids.length() != 0) {
			String[] id = ids.split("#");
			ArrayList list = new ArrayList();
			for (int i = 0; i < id.length; i++) {
				list.add(Long.parseLong(id[i]));
			}
			// 将不需要的变量设置成null，便于ibatis合成动态SQL
			joinState = ("".equals(joinState) || "-1".equals(joinState)) ? null : joinState;
			specialtySubmit = ("".equals(specialtySubmit) || "-1".equals(specialtySubmit)) ? null : specialtySubmit;
			classNo = ("".equals(classNo) || "-1".equals(classNo)) ? null : classNo;
			eduSchool = ("".equals(eduSchool) || "-1".equals(eduSchool)) ? null : eduSchool;

			HashSet<String> set = new HashSet<String>();
			String[] temp = classArray.split("#");
			for (String one : temp) {
				set.add(one);
			}
			class_info info = classInfoService.selectClassById(Integer.parseInt(classNo));
			set.add(info.getName());

			this.enrollManageService.updateStuEnrollBacth(joinState, specialtySubmit, classNo, eduSchool, list);
			// 生成小序号
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String one = (String) iterator.next(); // 一个班级
				List<Long> list_1 = stuEnrollService.selectEnrollByClassNo(one);
				for (int i = 0; i < list_1.size(); i++) {
					stuEnrollService.updateEnrollStuNo((i + 1) + "", list_1.get(i));
				}

			}

		}
		this.clear();
		return index();
	}

	/*
	 * 删除某条记录之后，搜索条件清空
	 */
	private void clear() {
		name = null;
		pid = null;
		specialtySubmit = null;
		eduSchool = null;
		classNo = null;
		joinState = null;
		toPage = null;
		order = 1;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	public EnrollManageService getEnrollManageService() {
		return enrollManageService;
	}

	public void setEnrollManageService(EnrollManageService enrollManageService) {
		this.enrollManageService = enrollManageService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
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

	public String getSpecialtySubmit() {
		return specialtySubmit;
	}

	public void setSpecialtySubmit(String specialtySubmit) {
		this.specialtySubmit = specialtySubmit;
	}

	public String getEduSchool() {
		return eduSchool;
	}

	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getJoinState() {
		return joinState;
	}

	public void setJoinState(String joinState) {
		this.joinState = joinState;
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

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}

	public PoliticsService getPoliticsService() {
		return politicsService;
	}

	public void setPoliticsService(PoliticsService politicsService) {
		this.politicsService = politicsService;
	}

	public SpecialtyService getSpecialtyService() {
		return specialtyService;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getGradute_g() {
		return gradute_g;
	}

	public void setGradute_g(String gradute_g) {
		this.gradute_g = gradute_g;
	}

	public String getGradute_y() {
		return gradute_y;
	}

	public void setGradute_y(String gradute_y) {
		this.gradute_y = gradute_y;
	}

	public String getGradute_d() {
		return gradute_d;
	}

	public void setGradute_d(String gradute_d) {
		this.gradute_d = gradute_d;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getHealthy() {
		return healthy;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHome_address() {
		return home_address;
	}

	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}

	public String getHome_tel() {
		return home_tel;
	}

	public void setHome_tel(String home_tel) {
		this.home_tel = home_tel;
	}

	public String getSpecialty_submit() {
		return specialty_submit;
	}

	public void setSpecialty_submit(String specialty_submit) {
		this.specialty_submit = specialty_submit;
	}

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public String getStu_no() {
		return stu_no;
	}

	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}

	public String getJoin_state() {
		return join_state;
	}

	public void setJoin_state(String join_state) {
		this.join_state = join_state;
	}

	public String getEdu_school() {
		return edu_school;
	}

	public void setEdu_school(String edu_school) {
		this.edu_school = edu_school;
	}

	public String getSkilled() {
		return skilled;
	}

	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getChange_place() {
		return change_place;
	}

	public void setChange_place(String change_place) {
		this.change_place = change_place;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

	public String getClassArray() {
		return classArray;
	}

	public void setClassArray(String classArray) {
		this.classArray = classArray;
	}

	public RemarkService getRemarkService() {
		return remarkService;
	}

	public void setRemarkService(RemarkService remarkService) {
		this.remarkService = remarkService;
	}

}
