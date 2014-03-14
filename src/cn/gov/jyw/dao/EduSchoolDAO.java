package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.edu_school;

public class EduSchoolDAO extends SqlMapClientDaoSupport {
	public List<edu_school> getEduSchoolAll() {
		return this.getSqlMapClientTemplate().queryForList("SelectEduSchoolAll");
	}
	public List<String> getRecordEduSchoolAll() {
		return this.getSqlMapClientTemplate().queryForList("SelectRecordEduSchoolAll");
	}

	/**
	 * 根据申报专业ID查询培训学校
	 * 
	 * @param type
	 * @return
	 */
	public List selectRefEdu(int typeId) {
		return this.getSqlMapClientTemplate().queryForList("SelectRefEdu", typeId);
	}

	/**
	 * 根据申报专业名称查询培训学校
	 * 
	 * @param type
	 * @return
	 */
	public List selectRefEduBySpec(String name) {
		return this.getSqlMapClientTemplate().queryForList("SelectRefEduBySpec", name);
	}
	
	public List selectRecordRefEduBySpec(String name) {
		return this.getSqlMapClientTemplate().queryForList("SelectRecordRefEduBySpec", name);
	}

	public void deleteEduSchool(List list) {
		this.getSqlMapClientTemplate().delete("DeleteEduSchool", list);
	}

	public void addEduSchool(edu_school edu) {
		this.getSqlMapClientTemplate().insert("InsertEduSchool", edu);
	}
	
	public List<String> selectSpecBySchool(String school){
		return this.getSqlMapClientTemplate().queryForList("SelectSpecBySchool",school);
	}
	public List<HashMap> selectEduAll(){
		return this.getSqlMapClientTemplate().queryForList("SelectEduAll");
	}
}
