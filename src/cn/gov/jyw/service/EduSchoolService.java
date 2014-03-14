package cn.gov.jyw.service;

import java.util.HashMap;
import java.util.List;

import cn.gov.jyw.dao.ClassInfoDAO;
import cn.gov.jyw.dao.EduSchoolDAO;
import cn.gov.jyw.dao.RefDAO;
import cn.gov.jyw.pojo.edu_school;

public class EduSchoolService {
	private EduSchoolDAO eduSchoolDAO;
	private RefDAO refDAO;
	private ClassInfoDAO classInfoDAO;

	public List<edu_school> getEduSchoolAll() {
		return eduSchoolDAO.getEduSchoolAll();
	}
	public List<String> getRecordEduSchoolAll() {
		return eduSchoolDAO.getRecordEduSchoolAll();
	}

	public void deleteEduSchool(List list) {

		this.refDAO.deleteRef(list); // 删除学校对应的专业
		this.classInfoDAO.deleteClass(list); // 删除学校对应的班级
		this.eduSchoolDAO.deleteEduSchool(list);

	}

	public void addEduSchool(edu_school edu) {
		this.eduSchoolDAO.addEduSchool(edu);
	}

	public List selectRefEduBySpec(String name) {
		return this.eduSchoolDAO.selectRefEduBySpec(name);
	}
	public List selectRecordRefEduBySpec(String name) {
		return this.eduSchoolDAO.selectRecordRefEduBySpec(name);
	}
	public List<String> selectSpecBySchool(String school){
		return this.eduSchoolDAO.selectSpecBySchool(school);
	}
	public List<HashMap> selectEduAll(){
		return this.eduSchoolDAO.selectEduAll();
	}

	// //////////////////////////////////////////////////////////////////////

	public EduSchoolDAO getEduSchoolDAO() {
		return eduSchoolDAO;
	}

	public void setEduSchoolDAO(EduSchoolDAO eduSchoolDAO) {
		this.eduSchoolDAO = eduSchoolDAO;
	}

	public RefDAO getRefDAO() {
		return refDAO;
	}

	public void setRefDAO(RefDAO refDAO) {
		this.refDAO = refDAO;
	}

	public ClassInfoDAO getClassInfoDAO() {
		return classInfoDAO;
	}

	public void setClassInfoDAO(ClassInfoDAO classInfoDAO) {
		this.classInfoDAO = classInfoDAO;
	}

}
