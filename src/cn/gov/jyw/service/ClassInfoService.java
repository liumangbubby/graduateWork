package cn.gov.jyw.service;

import java.util.HashMap;
import java.util.List;

import cn.gov.jyw.dao.ClassInfoDAO;
import cn.gov.jyw.dao.ScheduleDAO;
import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.dao.StuRecordDAO;
import cn.gov.jyw.pojo.schedule;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.stu_enroll;

public class ClassInfoService {
	private ClassInfoDAO classInfoDAO;
	private StuEnrollDAO stuEnrollDAO;
	private StuRecordDAO stuRecordDAO;
	private ScheduleDAO scheduleDAO;

	/**
	 * 获得所有班级信息
	 * 
	 * @return
	 */
	public List<class_info> getClassInfoAll(String archives) {
		return this.classInfoDAO.getClassInfoAll(archives);
	}

	/**
	 * 插入班级信息
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void addClassInfo(class_info info) throws Exception {
		this.classInfoDAO.addClassInfo(info);
		// 插入一条课程表记录
		schedule schedule = new schedule();
		schedule.setClass_no(info.getName());
		schedule.setSchool(info.getSchool());
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

		this.scheduleDAO.insertSchedule(schedule);
	}

	/**
	 * 删除班级信息
	 * 
	 * @param id
	 */
	public void deleteClassInfo(List list) {
		this.scheduleDAO.deleteSchedule_1(list);
		this.classInfoDAO.deleteClassInfo(list);

	}

	/**
	 * 查询班级信息
	 * 
	 * @param name
	 * @param type
	 * @param school
	 * @return
	 */
	public List<class_info> selectClassInfo(String name, String type, String school) {
		return this.classInfoDAO.selectClassInfo(name, type, school);
	}

	/**
	 * 班级是否已经存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasClassInfo(String name) {
		return this.classInfoDAO.hasClassInfo(name);
	}

	/**
	 * 归档班级
	 */
	public void updateArchivesClass(String class_no) {
		// 检查报名表里面学号为null或者空字符串的记录，学号根据所在班级最大学号设置
		HashMap map = stuEnrollDAO.selectStuEnroll_5(class_no);
		long count = (Long) map.get("count");
		Object temp = map.get("max");
		long max = (temp == null || (temp + "").length() == 0) ? 0 : Long.parseLong(temp + "");
		if (max != count) {
			// 学号有缺失的现象，补全学号
			List<stu_enroll> list = stuEnrollDAO.selectStuEnroll_6(class_no);
			for (int i = 0; i < list.size(); i++) {
				stu_enroll enroll = list.get(i);
				enroll.setStu_no(max + i + 1 + "");
				stuEnrollDAO.updateEnrollStuNo(enroll.getStu_no(), enroll.getId());
			}
		}
		this.stuRecordDAO.insertEnrollToRecordByClass(class_no); // 把报名记录拷贝到归档表
		this.stuEnrollDAO.deleteStuEnrollByClass(class_no); // 删除报名表的相关学生报名信息
		this.classInfoDAO.updateClassInfoArchivesByClass(class_no, true); // 更新班级表的班级激活状态
		List<Long> list = this.stuRecordDAO.selectStuRecord_4(class_no); // 查询归档表中改班级的所有学生id
		classInfoDAO.updateClassInfoNum_2(class_no, list.size()); // 设置班级学生总数
	}

	public class_info selectClassById(int id) {
		return classInfoDAO.selectClassById(id);
	}

	public class_info selectClassMax(String type) {
		return this.classInfoDAO.selectClassMax(type);
	}

	// /////////////////////////////////////////////////////////////////

	public ClassInfoDAO getClassInfoDAO() {
		return classInfoDAO;
	}

	public void setClassInfoDAO(ClassInfoDAO classInfoDAO) {
		this.classInfoDAO = classInfoDAO;
	}

	public StuEnrollDAO getStuEnrollDAO() {
		return stuEnrollDAO;
	}

	public void setStuEnrollDAO(StuEnrollDAO stuEnrollDAO) {
		this.stuEnrollDAO = stuEnrollDAO;
	}

	public StuRecordDAO getStuRecordDAO() {
		return stuRecordDAO;
	}

	public void setStuRecordDAO(StuRecordDAO stuRecordDAO) {
		this.stuRecordDAO = stuRecordDAO;
	}

	public ScheduleDAO getScheduleDAO() {
		return scheduleDAO;
	}

	public void setScheduleDAO(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;
	}

}
