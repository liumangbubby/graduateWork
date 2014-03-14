package cn.gov.jyw.service;

import java.util.ArrayList;
import java.util.List;

import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.pojo.stu_enroll;

public class EnrollManageService {
	private StuEnrollDAO stuEnrollDAO;

	/**
	 * 查询所有申报信息
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<stu_enroll> getEnrollInfo(int start, int limit, int order,String school,String remark) {
		List<stu_enroll> list = stuEnrollDAO.selectStuInfo(start, limit, order,school,remark);
		return list;
	}

	/**
	 * 删除申报信息
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteEnrollInfo(List list) throws Exception {
		stuEnrollDAO.deleteStuInfo(list);
	}

	/**
	 * 查询申报信息
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
	public List<stu_enroll> searchEnrolls(String name, String pid, String specialty_submit, String edu_school, String class_no, String join_state, int start,
			int limit, int order,String remark) {
		List<stu_enroll> list = stuEnrollDAO.searchStuEnrolls(name, pid, specialty_submit, edu_school, class_no, join_state, start, limit, order,remark);
		return list;
	}

	/**
	 * 根据ID查找登记信息
	 * 
	 * @param id
	 * @return
	 */
	public stu_enroll searchEnrollsById(long id,String school) {
		return stuEnrollDAO.selectStuEnrollsById(id,school);
	}

	/**
	 * 更新申报数据
	 * 
	 * @param enroll
	 */
	public void updateEnrolls(stu_enroll enroll) {
		stuEnrollDAO.updateStuEnroll(enroll);

	}

	/**
	 * 批量修改信息，仅限于学校、班级、申报专业和参与状态
	 */
	public void updateStuEnrollBacth(String joinState, String specialtySubmit, String classNo, String eduSchool, ArrayList<Long> id) {
		for (int i = 0; i < id.size(); i++) {
			long one = id.get(i);
			stuEnrollDAO.updateStuEnrollBacth(joinState, specialtySubmit, classNo, eduSchool, one);

		}

	}

	public void deleteStuEnrollAll() {
		this.stuEnrollDAO.deleteStuEnrollAll();
	}

	/**
	 * 申报记录总数
	 * 
	 * @return
	 */
	public long getEnrollCount(String school,String remark) {
		return stuEnrollDAO.selectCount(school,remark);
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
	public long getEnrollCountByCondition(String name, String pid, String specialty_submit, String edu_school, String class_no, String join_state,String remark) {
		return stuEnrollDAO.selectCountByCondition(name, pid, specialty_submit, edu_school, class_no, join_state,remark);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////
	public StuEnrollDAO getStuEnrollDAO() {
		return stuEnrollDAO;
	}

	public void setStuEnrollDAO(StuEnrollDAO stuEnrollDAO) {
		this.stuEnrollDAO = stuEnrollDAO;
	}

}
