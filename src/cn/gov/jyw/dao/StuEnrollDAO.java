package cn.gov.jyw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;

public class StuEnrollDAO extends SqlMapClientDaoSupport {
	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<stu_enroll> getAll() {
		List<stu_enroll> list = null;
		return list = getSqlMapClientTemplate().queryForList("selectAll");
	}
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	public stu_enroll getOne(long id){
		Map map = new HashMap();
		map.put("id", id);
		return (stu_enroll) this.getSqlMapClientTemplate().queryForObject("SelectStuEnroll_4", map);
	}

	/**
	 * 插入一条记录
	 * 
	 * @param enroll
	 */
	public boolean insertOne(stu_enroll enroll) {
		try {
			getSqlMapClientTemplate().insert("InsertStuEnroll", enroll);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 是否存在PID
	 * 
	 * @param pid
	 * @return
	 */
	public boolean havePid(String pid) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("SelectStuEnroll_1", pid);
		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 查询报名信息
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<stu_enroll> selectStuInfo(int start, int limit, int order, String school,String remark) {
		HashMap map = new HashMap();
		map.put("school", school);
		map.put("start", start);
		map.put("limit", limit);
		map.put("order", order);
		map.put("remark", remark);
		List<stu_enroll> list = getSqlMapClientTemplate().queryForList("SelectStuEnroll_2", map);
		return list;
	}

	/**
	 * 查询所有报名记录数
	 * 
	 * @return
	 */
	public long selectCount(String school,String remark) {
		HashMap map = new HashMap();
		map.put("school", school);
		map.put("remark",remark);
		long count = (Long) getSqlMapClientTemplate().queryForObject("SelectStuEnrollCount_1", map);
		return count;
	}

	/**
	 * 计算符合查询条件的记录总数
	 * 
	 * @return
	 */
	public long selectCountByCondition(String name, String pid, String specialty_submit, String edu_school, String class_no, String join_state,String remark) {
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("pid", pid);
		map.put("specialty_submit", specialty_submit);
		map.put("edu_school", edu_school);
		map.put("class_no", class_no);
		map.put("join_state", join_state);
		map.put("remark", remark);
		long count = (Long) getSqlMapClientTemplate().queryForObject("SelectStuEnrollCount_2", map);
		return count;
	}

	/**
	 * 删除报名信息
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteStuInfo(List list) throws Exception {
		try {
			getSqlMapClientTemplate().delete("DeleteStuEnroll", list);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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
	public List<stu_enroll> searchStuEnrolls(String name, String pid, String specialty_submit, String edu_school, String class_no, String join_state,
			int start, int limit, int order,String remark) {
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
		map.put("remark", remark);
		List<stu_enroll> list = this.getSqlMapClientTemplate().queryForList("SelectStuEnroll_3", map);
		return list;
	}

	/**
	 * 根据ID查找用户信息
	 * 
	 * @param id
	 * @return
	 */
	public stu_enroll selectStuEnrollsById(long id,String school) {
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("school", school);
		return (stu_enroll) getSqlMapClientTemplate().queryForObject("SelectStuEnroll_4", map);
	}

	/**
	 * 修改申报信息
	 * 
	 * @param enroll
	 */
	public void updateStuEnroll(stu_enroll enroll) {
		if(enroll.getEdu_school().length()==0){
			enroll.setEdu_school(null);
		}
		this.getSqlMapClientTemplate().update("UpdateStuEnroll", enroll);
	}

	/**
	 * 删除全部报名信息
	 */
	public void deleteStuEnrollAll() {
		this.getSqlMapClientTemplate().delete("deleteStuEnrollAll");
	}

	/**
	 * 批量修改信息，仅限于学校、班级、申报专业和参与状态
	 */
	public void updateStuEnrollBacth(String joinState, String specialtySubmit, String classNo, String eduSchool, long id) {
		HashMap map = new HashMap();
		map.put("join_state", joinState);
		map.put("specialty_submit", specialtySubmit);
		map.put("class_no", classNo);
		map.put("edu_school", eduSchool);
		map.put("id", id);
		this.getSqlMapClientTemplate().update("UpdateStuEnrollBacth", map);
	}

	/**
	 * 根据身份证查找报名信息
	 * 
	 * @param pid
	 * @return
	 */
	public stu_enroll selectStuEnrollByPid(String pid,String edu_school,String remark) {
		HashMap map=new HashMap();
		map.put("pid", pid);
		map.put("edu_school", edu_school);
		map.put("remark", remark);
		return (stu_enroll) this.getSqlMapClientTemplate().queryForObject("SelectStuEnrollByPid", map);
	}

	public void updateStuEnrollClassNo(String classNo, String[] pid) throws Exception {

		HashMap map = new HashMap();

		map.put("class_no", classNo);
		map.put("pid", pid);

		this.getSqlMapClientTemplate().update("UpdateStuEnrollClassNo", map);
	}

	/**
	 * 删除该班级对应的报名记录
	 * 
	 * @param class_no
	 */
	public void deleteStuEnrollByClass(String class_no) {
		this.getSqlMapClientTemplate().delete("DeleteStuEnrollByClass", class_no);
	}

	public List<Long> selectEnrollByClassNo(String class_no) {
		return this.getSqlMapClientTemplate().queryForList("SelectEnrollByClassNo", class_no);
	}

	public void updateEnrollStuNo(String stu_no, long id) {
		HashMap map = new HashMap();
		map.put("stu_no", stu_no);
		map.put("id", id);
		this.getSqlMapClientTemplate().update("UpdateEnrollStuNo", map);
	}

	public HashMap selectStuEnroll_5(String class_no) {
		return (HashMap) this.getSqlMapClientTemplate().queryForObject("SelectStuEnroll_5", class_no);
	}

	public List<stu_enroll> selectStuEnroll_6(String class_no) {
		return this.getSqlMapClientTemplate().queryForList("SelectStuEnroll_6", class_no);

	}

	public List<specialty_report> selectEnrollSpec(String school) {
		return this.getSqlMapClientTemplate().queryForList("SelectEnrollSpec", school);
	}

	public List<class_info> selectEnrollClass(String school, String type) {
		HashMap map = new HashMap();
		map.put("school", school);
		map.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("SelectEnrollClass", map);
	}
	public List<stu_enroll> selectStuEnroll_7(String classNo,String eduSchool){
		HashMap map=new HashMap();
		map.put("edu_school", eduSchool);
		map.put("class_no", classNo);
		return this.getSqlMapClientTemplate().queryForList("SelectStuEnroll_7", map);
		
	}

}
