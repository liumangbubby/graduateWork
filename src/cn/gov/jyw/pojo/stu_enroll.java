package cn.gov.jyw.pojo;

import java.util.Date;

public class stu_enroll {

	private long id;
	private String name;
	private String sex;
	private String nation;
	private String pid;
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
	private Date create_time;
	private String stu_no;
	private String join_state;
	private String edu_school;
	private String skilled;
	private String place;
	private boolean change_place;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

	public boolean isChange_place() {
		return change_place;
	}

	public void setChange_place(boolean change_place) {
		this.change_place = change_place;
	}

	public String getGradute_d() {
		return gradute_d;
	}

	public void setGradute_d(String gradute_d) {
		this.gradute_d = gradute_d;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public stu_enroll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public stu_enroll(long id, String name, String sex, String nation, String pid, String gradute_g, String gradute_y, String gradute_d,
			String education, String specialty, String healthy, String politics, String birthday, String address, String household,
			String tel, String email, String home_address, String home_tel, String specialty_submit, String intention, String remark,
			String class_no, String stu_no, String join_state, String edu_school, String skilled, String place, boolean change_place) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.nation = nation;
		this.pid = pid;
		this.gradute_g = gradute_g;
		this.gradute_y = gradute_y;
		this.gradute_d = gradute_d;
		this.education = education;
		this.specialty = specialty;
		this.healthy = healthy;
		this.politics = politics;
		this.birthday = birthday;
		this.address = address;
		this.household = household;
		this.tel = tel;
		this.email = email;
		this.home_address = home_address;
		this.home_tel = home_tel;
		this.specialty_submit = specialty_submit;
		this.intention = intention;
		this.remark = remark;
		this.class_no = class_no;
		this.stu_no = stu_no;
		this.join_state = join_state;
		this.edu_school = edu_school;
		this.skilled = skilled;
		this.place = place;
		this.change_place = change_place;
	}



}
