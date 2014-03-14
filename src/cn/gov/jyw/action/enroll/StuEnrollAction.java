package cn.gov.jyw.action.enroll;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.gov.jyw.bean.CheckBean;
import cn.gov.jyw.dao.EduSchoolDAO;
import cn.gov.jyw.pojo.nation;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.service.PlaceService;
import cn.gov.jyw.service.RemarkService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.StuEnrollService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class StuEnrollAction {
	private StuEnrollService stuEnrollService;
	private SpecialtyReportService specialtyReportService;
	private RemarkService remarkService;
	private PlaceService placeService;
	private EduSchoolDAO eduSchoolDAO;
	private CheckBean checkBean;
	

	private String name;
	private String sex;
	private String nation;
	private String pid;
	private String graduteG;
	private String graduteY;
	private String datepicker; // 毕业时间
	private String education;
	private String specialty; // 专业
	private String politics;// 政治面貌
	private String birthday;
	private String address;
	private String household;
	private String tel;
	private String email;
	private String homeAddress;
	private String homeTel;
	private String skilled;
	private String healthy;
	private String specialtySubmit;
	private String place;
	private String changePlace;
	private String intention;
	private String remark;
	private String type;
	private String eduSchool;
	private String classNo;

	public String toEnroll() {
		List<nation> list = stuEnrollService.getNationList();
		List<specialty_report> spList = specialtyReportService.getAll(); // 申报专业列表
		List placeList = placeService.getPlaceAll();
		List eduList = eduSchoolDAO.getEduSchoolAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("nationList", list);
		stack.set("spList", spList);
		stack.set("placeList", placeList);
		stack.set("eduList", eduList);
		return "success";
	}

	public String doEnroll() throws Exception {
		stu_enroll enroll = new stu_enroll();
		enroll.setName(name);
		enroll.setSex(sex);
		enroll.setNation(nation);
		enroll.setPid(pid);
		enroll.setGradute_g(graduteG);
		enroll.setGradute_y(graduteY);
		enroll.setGradute_d(datepicker);
		enroll.setEducation(education);
		enroll.setSpecialty(specialty);
		enroll.setPolitics(politics);
		enroll.setBirthday(birthday);
		enroll.setAddress(address);
		enroll.setHousehold(household);
		enroll.setTel(tel);
		enroll.setEmail(email);
		enroll.setHome_address(homeAddress);
		enroll.setHome_tel(homeTel);
		enroll.setSkilled(skilled);
		enroll.setHealthy(healthy);
		enroll.setSpecialty_submit(specialtySubmit);
		enroll.setPlace(place);
		enroll.setChange_place(true);
		enroll.setIntention("");
		enroll.setRemark(remark);
		enroll.setEdu_school(eduSchool); // 保存学校
		// 后台验证
		if (checkBean.checkName(name) && checkBean.checkSex(sex) && checkBean.checkNation(nation) && checkBean.checkPid(pid) && checkBean.checkTel(tel)
				&& checkBean.checkGraduteG(graduteG) && checkBean.checkGraduteY(graduteY) && checkBean.checkEducation(education)
				&& checkBean.checkSpecialty(specialty) && checkBean.checkPolitics(politics) && checkBean.checkAddress(address)
				&& checkBean.checHousehold(household) && checkBean.checkEmail(email) && checkBean.checkHomeAddress(homeAddress)
				&& checkBean.checkHomeTel(homeTel) && checkBean.checkHealthy(healthy)) {
			boolean rs = stuEnrollService.addEnroll(enroll);
			if (rs == true) {
				ValueStack stack = ActionContext.getContext().getValueStack();
				stack.set("enroll", enroll);
				return "enroll_success";
			}
			else {
				throw new Exception("学生报名失败");
			}
		}
		else {
			throw new Exception("后台验证没有通过");
		}

	}

	public String exportEnroll() throws Exception {
		ServletContext context = ServletActionContext.getServletContext();
		Map session=ActionContext.getContext().getSession();
		String targetDirectory = context.getRealPath("/");
		String school = (String) session.get("school");
		String path = stuEnrollService.exportRecord(targetDirectory, classNo, school);
		ValueStack stack = ActionContext.getContext().getValueStack();
		if (path.equals("none")) {
			stack.set("url", path);
		}
		else {
			stack.set("url", path);
			session.put("url", path);
			//System.out.println(path);
		}
		return "exp_success";
	}

	public String toPrint() {
		String num=remarkService.selectNum(eduSchool);
		stu_enroll enroll = stuEnrollService.selectStuEnrollByPid(pid, eduSchool,num);
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("enroll", enroll);
		stack.set("type", type);
		return "print";
	}

	public String toPDF() throws Exception {
		Map map = ActionContext.getContext().getApplication();
		String jasperPath = (String) map.get("jasperPath");
		String floder = (String) map.get("pdfFloder");
		String fileName = stuEnrollService.toPrint(jasperPath, pid, floder);
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("fileName", fileName);
		return "pdf";
	}

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getGraduteG() {
		return graduteG;
	}

	public void setGraduteG(String graduteG) {
		this.graduteG = graduteG;
	}

	public String getGraduteY() {
		return graduteY;
	}

	public void setGraduteY(String graduteY) {
		this.graduteY = graduteY;
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

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
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

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getSkilled() {
		return skilled;
	}

	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}

	public String getHealthy() {
		return healthy;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getSpecialtySubmit() {
		return specialtySubmit;
	}

	public void setSpecialtySubmit(String specialtySubmit) {
		this.specialtySubmit = specialtySubmit;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getChangePlace() {
		return changePlace;
	}

	public void setChangePlace(String changePlace) {
		this.changePlace = changePlace;
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

	public CheckBean getCheckBean() {
		return checkBean;
	}

	public void setCheckBean(CheckBean checkBean) {
		this.checkBean = checkBean;
	}

	public String getDatepicker() {
		return datepicker;
	}

	public void setDatepicker(String datepicker) {
		this.datepicker = datepicker;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

	public EduSchoolDAO getEduSchoolDAO() {
		return eduSchoolDAO;
	}

	public void setEduSchoolDAO(EduSchoolDAO eduSchoolDAO) {
		this.eduSchoolDAO = eduSchoolDAO;
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

	public RemarkService getRemarkService() {
		return remarkService;
	}

	public void setRemarkService(RemarkService remarkService) {
		this.remarkService = remarkService;
	}

}
