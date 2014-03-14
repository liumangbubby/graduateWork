package cn.gov.jyw.pojo;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class schedule {

	private int id;
	private String school;
	private String class_no;
	private Date upload_time;
	private String upload_user;
	private int week;
	private String day_1;
	private String day_2;
	private String day_3;
	private String day_4;
	private String day_5;
	private int lack;
	private String teacher;
	private String number;
	private String remark;
	private String place;
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getUpload_user() {
		return upload_user;
	}

	public void setUpload_user(String upload_user) {
		this.upload_user = upload_user;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getDay_1() {
		return day_1;
	}

	public void setDay_1(String day_1) {
		this.day_1 = day_1;
	}

	public String getDay_2() {
		return day_2;
	}

	public void setDay_2(String day_2) {
		this.day_2 = day_2;
	}

	public String getDay_3() {
		return day_3;
	}

	public void setDay_3(String day_3) {
		this.day_3 = day_3;
	}

	public String getDay_4() {
		return day_4;
	}

	public void setDay_4(String day_4) {
		this.day_4 = day_4;
	}

	public String getDay_5() {
		return day_5;
	}

	public void setDay_5(String day_5) {
		this.day_5 = day_5;
	}

	public int getLack() {
		return lack;
	}

	public void setLack(int lack) {
		this.lack = lack;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
