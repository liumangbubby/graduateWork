package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.stu_record;

public class StuRecordDAO extends SqlMapClientDaoSupport {

	public void addRecord(stu_record record) {
		this.getSqlMapClientTemplate().insert("InsertStuRecord", record);
	}

	/**
	 * 查询记录信息
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<stu_record> selectStuRecord(int start, int limit, int order) {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("limit", limit);
		map.put("order", order);
		List<stu_record> list = getSqlMapClientTemplate().queryForList("SelectStuRecord_1", map);
		return list;
	}

	/**
	 * 查询所有记录数
	 * 
	 * @return
	 */
	public long selectCount() {
		long count = (Long) getSqlMapClientTemplate().queryForObject("SelectStuRecordCount_1");
		return count;
	}

	/**
	 * 计算符合查询条件的记录总数
	 * 
	 * @return
	 */
	public long selectCountByCondition(String name, String pid, String specialty_submit, String edu_school, String class_no, String join_state) {
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("pid", pid);
		map.put("specialty_submit", specialty_submit);
		map.put("edu_school", edu_school);
		map.put("class_no", class_no);
		map.put("join_state", join_state);
		long count = (Long) getSqlMapClientTemplate().queryForObject("SelectStuRecordCount_2", map);
		return count;
	}

	/**
	 * 查找用户信息
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
	public List<stu_record> searchStuRecords(String name, String pid, String specialty_submit, String edu_school, String class_no, String join_state,
			int start, int limit, int order) {
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("pid", pid);
		map.put("specialty_submit", specialty_submit);
		map.put("edu_school", edu_school);
		map.put("class_no", class_no);
		map.put("join_state", join_state);
		map.put("start", start);
		map.put("limit", limit);
		map.put("order", order);
		List list = this.getSqlMapClientTemplate().queryForList("SelectStuRecord_2", map);
		return list;
	}

	/**
	 * 根据ID查找用户信息
	 * 
	 * @param id
	 * @return
	 */
	public stu_record selectStuRecordsById(long id) {
		return (stu_record) getSqlMapClientTemplate().queryForObject("SelectStuRecord_3", id);
	}

	/**
	 * 删除报名信息
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteStuRecord(List list) throws Exception {
		try {
			getSqlMapClientTemplate().delete("DeleteStuRecord", list);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 修改记录信息
	 * 
	 * @param enroll
	 */
	public void updateStuRecord(stu_record record) {
		this.getSqlMapClientTemplate().update("UpdateStuRecord", record);
	}

	/**
	 * 批量修改信息，仅限于学校、班级、申报专业和参与状态
	 */
	public void updateStuRecordBacth(String joinState, String specialtySubmit, String classNo, String eduSchool, long id) {
		HashMap map = new HashMap();
		map.put("join_state", joinState);
		map.put("specialty_submit", specialtySubmit);
		map.put("class_no", classNo);
		map.put("edu_school", eduSchool);
		map.put("id", id);
		this.getSqlMapClientTemplate().update("UpdateStuRecordBacth", map);
	}

	public void insertEnrollToRecord() {
		this.getSqlMapClientTemplate().insert("InsertEnrollToRecord");
	}

	/**
	 * 查询没有学号的归档信息
	 * 
	 * @return
	 */
	public List<stu_record> selectStuRecordNoStuNo() {
		return this.getSqlMapClientTemplate().queryForList("SelectStuRecordNoStuNo");
	}

	/**
	 * 更新学号
	 * 
	 * @param id
	 * @param stu_no
	 */
	public void updateStuNo(long id, int stu_no) {
		HashMap map = new HashMap();
		map.put("stu_no", stu_no);
		map.put("id", id);
		this.getSqlMapClientTemplate().update("UpdateStuNo", map);
	}

	public List<stu_record> selectStuRecordByCondition(int year, String edu_school) {
		HashMap map = new HashMap();
		map.put("year", year);
		map.put("edu_school", edu_school);
		return this.getSqlMapClientTemplate().queryForList("SelectStuRecordByCondition", map);
	}

	public void insertEnrollToRecordByClass(String class_no) {
		this.getSqlMapClientTemplate().insert("InsertEnrollToRecordByClass", class_no);
	}

	/**
	 * 根据班级查询学员的ID
	 * 
	 * @param class_no
	 * @return
	 */
	public List<Long> selectStuRecord_4(String class_no) {
		return this.getSqlMapClientTemplate().queryForList("SelectStuRecord_4", class_no);
	}

	/**
	 * 根据年份查询已经归档的人数
	 * 
	 * @param year
	 * @return
	 */
	public long selectStuRecordCount_3(int year) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("SelectStuRecordCount_3", year);
	}

	/**
	 * 根据班级删除数据
	 * 
	 * @param class_no
	 */
	public void deleteStuRecordByClass(String class_no) {
		this.getSqlMapClientTemplate().delete("DeleteStuRecordByClass", class_no);
	}

	public List<String> selectRecordClass(String edu_school, String type) {
		HashMap map = new HashMap();
		map.put("edu_school", edu_school);
		map.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("SelectRecordClass", map);
	}
	public List<String> selectRecordSpecAll(){
		return this.getSqlMapClientTemplate().queryForList("SelectRecordSpecAll");
	}
	public List<String> selectRecordSchoolAll(){
		return this.getSqlMapClientTemplate().queryForList("SelectRecordSchoolAll");
	}

}
