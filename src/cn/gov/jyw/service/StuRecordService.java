package cn.gov.jyw.service;

import java.util.ArrayList;
import java.util.List;

import cn.gov.jyw.bean.ExcelBean;
import cn.gov.jyw.dao.ClassInfoDAO;
import cn.gov.jyw.dao.NationDAO;
import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.dao.StuRecordDAO;
import cn.gov.jyw.pojo.nation;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.stu_record;

public class StuRecordService {
	private StuRecordDAO stuRecordDAO;
	private ExcelBean excelBean;
	private NationDAO nationDAO;
	private StuEnrollDAO stuEnrollDAO;
	private ClassInfoDAO classInfoDAO;

	public List<nation> getNationList() {
		return nationDAO.getAll();
	}

	public void addRecord(stu_record record) {
		this.stuRecordDAO.addRecord(record);
	}

	/**
	 * 导入数据记录
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void addRecords(String floderPath) throws Exception {

		List<stu_record> list = excelBean.importRecord(floderPath);
		for (stu_record record : list) {
			this.stuRecordDAO.addRecord(record);
		}

	}

	/**
	 * 查询所有记录信息
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<stu_record> getStuRecord(int start, int limit, int order) {
		List list = stuRecordDAO.selectStuRecord(start, limit, order);
		return list;
	}

	/**
	 * 查询记录信息
	 * 
	 * @param name
	 * @param pid
	 * @param specialty_submit
	 * @param edu_school
	 * @param class_no
	 * @param join_state
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<stu_record> searchStuRecord(String name, String pid,
			String specialty_submit, String edu_school, String class_no,
			String join_state, int start, int limit, int order) {
		List<stu_record> list = stuRecordDAO.searchStuRecords(name, pid,
				specialty_submit, edu_school, class_no, join_state, start,
				limit, order);
		return list;
	}

	/**
	 * 根据ID查找登记信息
	 * 
	 * @param id
	 * @return
	 */
	public stu_record searchRecordById(long id) {
		return stuRecordDAO.selectStuRecordsById(id);
	}

	/**
	 * 记录总数
	 * 
	 * @return
	 */
	public long getRecordCount() {
		return stuRecordDAO.selectCount();
	}

	/**
	 * 根据查询条件计算总数
	 * 
	 * @param name
	 * @param pid
	 * @param specialty_submit
	 * @param edu_school
	 * @param class_no
	 * @param join_state
	 * @return
	 */
	public long getRecordCountByCondition(String name, String pid,
			String specialty_submit, String edu_school, String class_no,
			String join_state) {
		return stuRecordDAO.selectCountByCondition(name, pid, specialty_submit,
				edu_school, class_no, join_state);
	}

	/**
	 * 删除信息
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteStuRecord(List list) throws Exception {
		stuRecordDAO.deleteStuRecord(list);
	}

	/**
	 * 批量修改信息，仅限于学校、班级、申报专业和参与状态
	 */
	public void updateStuRecordBacth(String joinState, String specialtySubmit,
			String classNo, String eduSchool, ArrayList<Long> id) {
		for (int i = 0; i < id.size(); i++) {
			long one = id.get(i);
			stuRecordDAO.updateStuRecordBacth(joinState, specialtySubmit,
					classNo, eduSchool, one);

		}

	}

	/**
	 * 更新申报数据
	 * 
	 * @param enroll
	 */
	public void updateStuRecord(stu_record record) {
		stuRecordDAO.updateStuRecord(record);

	}

	/**
	 * 报名数据导入到归档数据
	 */
	public void addEnrollToRecord() {
		stuRecordDAO.insertEnrollToRecord(); // 导入数据
		stuEnrollDAO.deleteStuEnrollAll(); // 删除报名表所有数据
		classInfoDAO.updateClassInfoArchives(true);// 班级状态更新成为已归档
		classInfoDAO.updateClassInfoNum_1(); // 更新班级人数
		/*
		// 自动生成小序号
		List<stu_record> list = stuRecordDAO.selectStuRecordNoStuNo();
		String temp = "";// 临时保存班级编号
		int num = 1;
		for (int i = 0; i < list.size(); i++) {
			stu_record one = list.get(i);
			String classNo = one.getClass_No();
			long id = one.getId();
			if (i == 0) {
				temp = classNo;// 保存班级编号
			}
			// 班级编号改变的时候num要重新变成1
			if (classNo.equals(temp) == false) {
				temp = classNo;
				num = 1;
			}
			// 设置学号
			stuRecordDAO.updateStuNo(id, num);
			num++;
		}
		*/
	}

	public String exportRecord(String path, int year, String edu_school)
			throws Exception {
		List<stu_record> list = stuRecordDAO.selectStuRecordByCondition(year,
				edu_school);
		if (list == null || list.size() == 0) {
			return "none";
		} else {
			String out = excelBean.exportRecord(path, list);
			return out;
		}
	}
	public long selectStuRecordCount_3(int year) {
		return stuRecordDAO.selectStuRecordCount_3(year);
	}
	public void deleteStuRecordByClass(String class_no){
		this.stuRecordDAO.deleteStuRecordByClass(class_no);
	}
	public List<String> selectRecordClass(String edu_school,String type){
		return stuRecordDAO.selectRecordClass(edu_school,type);
	}
	public List<String> selectRecordSpecAll(){
		return stuRecordDAO.selectRecordSpecAll();
	}
	public List<String> selectRecordSchoolAll(){
		return stuRecordDAO.selectRecordSchoolAll();
	}
	
	// //////////////////////////////////////////////////////////////

	public StuRecordDAO getStuRecordDAO() {
		return stuRecordDAO;
	}

	public void setStuRecordDAO(StuRecordDAO stuRecordDAO) {
		this.stuRecordDAO = stuRecordDAO;
	}

	public ExcelBean getExcelBean() {
		return excelBean;
	}

	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

	public NationDAO getNationDAO() {
		return nationDAO;
	}

	public void setNationDAO(NationDAO nationDAO) {
		this.nationDAO = nationDAO;
	}

	public StuEnrollDAO getStuEnrollDAO() {
		return stuEnrollDAO;
	}

	public void setStuEnrollDAO(StuEnrollDAO stuEnrollDAO) {
		this.stuEnrollDAO = stuEnrollDAO;
	}

	public ClassInfoDAO getClassInfoDAO() {
		return classInfoDAO;
	}

	public void setClassInfoDAO(ClassInfoDAO classInfoDAO) {
		this.classInfoDAO = classInfoDAO;
	}

}
