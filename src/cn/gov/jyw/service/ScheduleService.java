package cn.gov.jyw.service;

import java.util.HashMap;
import java.util.List;

import cn.gov.jyw.dao.ScheduleDAO;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.schedule;

public class ScheduleService {
	private ScheduleDAO scheduleDAO;

	public ScheduleDAO getScheduleDAO() {
		return scheduleDAO;
	}

	public List<schedule> selectSchedule_1(String school, String classNo, String filter) {
		return this.scheduleDAO.selectSchedule_1(school, classNo, filter);
	}

	public void setScheduleDAO(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;
	}

	public List<String> selectSchedule_2() {
		return this.scheduleDAO.selectSchedule_2();
	}

	public List<String> selectSchedule_3(String school) {
		return this.scheduleDAO.selectSchedule_3(school);
	}

	public schedule selectSchedule_4(String classNo) {
		return this.scheduleDAO.selectSchedule_4(classNo);
	}

	public List<schedule> selectSchedule_5(String classNo) {
		return this.scheduleDAO.selectSchedule_5(classNo);
	}

	public schedule selectSchedule_6(String classNo, int week) {
		return this.scheduleDAO.selectSchedule_6(classNo, week);
	}

	public void updateSchedule_1(schedule schedule) {
		this.scheduleDAO.updateSchedule_1(schedule);
	}

	public void addEmpty(String school, String classNo, int week) {
		// 插入一条课程表记录
		schedule schedule = new schedule();
		schedule.setClass_no(classNo);
		schedule.setSchool(school);
		schedule.setLack(-1);
		schedule.setDay_1("['','','','','','','','','']");
		schedule.setDay_2("['','','','','','','','','']");
		schedule.setDay_3("['','','','','','','','','']");
		schedule.setDay_4("['','','','','','','','','']");
		schedule.setDay_5("['','','','','','','','','']");
		schedule.setNumber("['','','','','','','','']");
		schedule.setPlace("['','','','','','','','']");
		schedule.setRemark("['','','','','','','','']");
		schedule.setTeacher("['','','','','','','','']");
		schedule.setSubject("['','','','','','','','']");
		schedule.setWeek(week);

		this.scheduleDAO.insertSchedule(schedule);
	}

	public void addOne(schedule s) {
		this.scheduleDAO.insertSchedule(s);
	}

	public boolean selectSchedule_7(String classNo, int week) {
		return this.scheduleDAO.selectSchedule_7(classNo, week);
	}

	public void deleteSchedule_2(String classNo, int week) {
		this.scheduleDAO.deleteSchedule_2(classNo, week);
	}
}
