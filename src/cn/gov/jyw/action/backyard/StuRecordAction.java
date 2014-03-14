package cn.gov.jyw.action.backyard;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.bean.PageBean;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.join_state;
import cn.gov.jyw.pojo.nation;
import cn.gov.jyw.pojo.politics;
import cn.gov.jyw.pojo.specialty;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.stu_record;
import cn.gov.jyw.pojo.year;
import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.JoinStateService;
import cn.gov.jyw.service.PlaceService;
import cn.gov.jyw.service.PoliticsService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.SpecialtyService;
import cn.gov.jyw.service.StuRecordService;
import cn.gov.jyw.service.YearService;

public class StuRecordAction implements ServletContextAware {
	private ServletContext context;
	private StuRecordService stuRecordService;
	private SpecialtyReportService specialtyReportService;
	private JoinStateService joinStateService;
	private EduSchoolService eduSchoolService;
	private ClassInfoService classInfoService;
	private YearService yearService;
	private SpecialtyService specialtyService;
	private PoliticsService politicsService;
	private PlaceService placeService;
	private String floderPath;
	private PageBean pageBean;
	// ////////////////////////////////////////////////////////////
	private long id;
	private String ids;
	private String type;
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
	private int y;

	public String importRecord() {
		try {
			stuRecordService.addRecords(floderPath);
			return "success";
		}
		catch (Exception e) {
			return "fail";
		}
	}

	public String index() {
		clear();
		long count = stuRecordService.getRecordCount();
		pageBean.init(count, 1, 15);
		List<stu_record> list = stuRecordService.getStuRecord(pageBean.getStart(), pageBean.getLimit(), 1); // 信息分页数据
		List<String> spList = stuRecordService.selectRecordSpecAll();// 申报专业列表

		List<join_state> joList = joinStateService.getAll(); // 参与状态列表
		List<class_info> classList = classInfoService.getClassInfoAll("true"); // 班级列表信息
		List<String> eduList = stuRecordService.selectRecordSchoolAll(); // 培训学校列表

		ValueStack stack = ActionContext.getContext().getValueStack();

		stack.set("recordList", list);
		stack.set("pageBean", pageBean);
		stack.set("spList", spList);
		stack.set("joList", joList);
		stack.set("classList", classList);
		stack.set("eduList", eduList);
		return "index";
	}

	/**
	 * 查询学生信息
	 * 
	 * @return
	 */
	public String searchRecords() {
		// 去除空字符串，因为ibatis动态SQL根据not null判断
		name = "".equals(name) ? null : name;
		pid = "".equals(pid) ? null : pid;
		specialtySubmit = "".equals(specialtySubmit) ? null : specialtySubmit;
		eduSchool = "".equals(eduSchool) ? null : eduSchool;
		classNo = "".equals(classNo) ? null : classNo;
		joinState = "".equals(joinState) ? null : joinState;
		List<stu_record> list = null;

		long count = stuRecordService.getRecordCountByCondition(name, pid, specialtySubmit, eduSchool, classNo, joinState);
		if ("search".equals(stype)) {
			// 输入条件查询，忽略页数，重新计算分页

			pageBean.init(count, 1, 15);
		}
		if ("to".equals(stype)) {
			// 输入跳转页数，不用重新计算分页

			pageBean.init(count, Integer.parseInt(toPage), 15);
		}
		if (pageBean.getStart() == pageBean.getLimit() && pageBean.getStart() == 0) {
			list = stuRecordService.searchStuRecord(name, pid, specialtySubmit, eduSchool, classNo, joinState, 0, 15, order);
		}
		else {
			list = stuRecordService.searchStuRecord(name, pid, specialtySubmit, eduSchool, classNo, joinState, pageBean.getStart(), pageBean.getLimit(), order);
		}

		// List<specialty_report> spList = specialtyReportService.getAll(); //
		// 申报查询专业
		List<String> spList = stuRecordService.selectRecordSpecAll();
		List<join_state> joList = joinStateService.getAll(); // 参与状态
		List<class_info> classList = classInfoService.getClassInfoAll("true"); // 班级列表信息
		List<String> eduList =stuRecordService.selectRecordSchoolAll(); // 培训学校列表
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("joList", joList);
		stack.set("spList", spList);
		stack.set("pageBean", pageBean);
		stack.set("classList", classList);
		stack.set("eduList", eduList);
		stack.set("recordList", list); // 报名数据

		return "index";
	}

	public String deleteStuRecord() throws Exception {
		if ("delete".equals(type) && ids != null && ids.length() != 0) {
			String[] id = ids.split("#");
			ArrayList list = new ArrayList();
			for (int i = 0; i < id.length; i++) {
				list.add(id[i]);
			}
			try {
				stuRecordService.deleteStuRecord(list);
				this.clear(); // 清除index.jsp的搜索条件
				long count = stuRecordService.getRecordCount();
				pageBean.init(count, 1, 15);
				List<stu_record> enrollList = stuRecordService.getStuRecord(pageBean.getStart(), pageBean.getLimit(), 1);
				// List<specialty_report> spList =
				// specialtyReportService.getAll();
				List<String> spList = stuRecordService.selectRecordSpecAll();
				List<join_state> joList = joinStateService.getAll();
				List<class_info> classList = classInfoService.getClassInfoAll("true"); // 班级列表信息
				List<String> eduList = stuRecordService.selectRecordSpecAll(); // 培训学校列表
				ValueStack stack = ActionContext.getContext().getValueStack();
				stack.set("recordList", enrollList);
				stack.set("pageBean", pageBean);
				stack.set("spList", spList);
				stack.set("joList", joList);
				stack.set("classList", classList);
				stack.set("eduList", eduList);
				return "index";
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

	/**
	 * 跳转到数据更新画面
	 * 
	 * @return
	 */
	public String modify() {
		if ("modify".equals(type) && ids != null && ids.length() != 0) {
			stu_record record = this.stuRecordService.searchRecordById(Long.parseLong(ids.split("#")[0])); // 申报信息
			List<nation> nationList = stuRecordService.getNationList(); // 民族列表
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(record.getCreate_time()); // 根据报名时间取出来注册的年份
			int year = calendar.get(calendar.YEAR);
			// 当前年份以前的三年
			ArrayList yearList = new ArrayList();

			yearList.add(year - 2);
			yearList.add(year - 1);
			yearList.add(year);
			List<specialty> specialtyList = specialtyService.getSpecialtyAll();// 大学专业
			List<specialty_report> reportList = specialtyReportService.getAll(); // 申报专业
			List<politics> politicsList = politicsService.getPoliticsAll(); // 政治面貌
			List<String> eduList = stuRecordService.selectRecordSchoolAll(); // 培训学校
			List<join_state> joinList = joinStateService.getAll(); // 参与状态
			List<class_info> infoList = classInfoService.getClassInfoAll("true"); // 班级信息
			List placeList = placeService.getPlaceAll(); // 培训地点

			ValueStack stack = ActionContext.getContext().getValueStack();
			stack.set("enroll", record);
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

	public String updateStuRecord() {
		stu_record record = new stu_record(id, name, sex, nation, pid, gradute_g, gradute_y, gradute_d, education, specialty, healthy, politics, birthday,
				address, household, tel, email, home_address, home_tel, specialty_submit, intention, remark, class_no, stu_no, join_state, edu_school, skilled,
				change_place, Boolean.parseBoolean(change_place));
		this.stuRecordService.updateStuRecord(record);
		this.clear();
		return this.index();
	}

	/**
	 * 批量修改信息，仅限于学校、班级、申报专业和参与状态
	 */
	public String updateStuRecordBacth() {
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
			this.stuRecordService.updateStuRecordBacth(joinState, specialtySubmit, classNo, eduSchool, list);
		}
		this.clear();
		return index();
	}

	public String exportRecord() throws Exception {
		String targetDirectory = context.getRealPath("/");
		if ("".equals(eduSchool)) {
			eduSchool = null;
		}
		String path = stuRecordService.exportRecord(targetDirectory, y, eduSchool);
		// System.out.println(path);
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("url", path);

		return "exp_success";
	}

	// /////////////////////////////////////////////////////////////////////////////
	public StuRecordService getStuRecordService() {
		return stuRecordService;
	}

	public void setStuRecordService(StuRecordService stuRecordService) {
		this.stuRecordService = stuRecordService;
	}

	public String getFloderPath() {
		return floderPath;
	}

	public void setFloderPath(String floderPath) {
		this.floderPath = floderPath;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}

	public SpecialtyService getSpecialtyService() {
		return specialtyService;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public PoliticsService getPoliticsService() {
		return politicsService;
	}

	public void setPoliticsService(PoliticsService politicsService) {
		this.politicsService = politicsService;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;

	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

}
