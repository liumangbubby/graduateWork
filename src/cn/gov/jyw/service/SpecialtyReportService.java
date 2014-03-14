package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.ClassInfoDAO;
import cn.gov.jyw.dao.RefDAO;
import cn.gov.jyw.dao.SpecialtyReportDAO;
import cn.gov.jyw.pojo.specialty_report;

public class SpecialtyReportService {
	private SpecialtyReportDAO specialtyReportDAO;
	private ClassInfoDAO classInfoDAO;
	private RefDAO refDAO;

	public List<specialty_report> getAll() {
		return specialtyReportDAO.getAll();
	}

	public void deleteSpecialty(List list) {
		refDAO.deleteRefBySpec(list); // 删除学校关联的专业
		classInfoDAO.deleteClassByType(list);	//删除有该专业的班级
		specialtyReportDAO.deleteSpeciaty(list);
	}

	public void addSpecialty(String name) {
		specialtyReportDAO.insertSpecialty(name);
	}

	public SpecialtyReportDAO getSpecialtyReportDAO() {
		return specialtyReportDAO;
	}

	public void setSpecialtyReportDAO(SpecialtyReportDAO specialtyReportDAO) {
		this.specialtyReportDAO = specialtyReportDAO;
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
