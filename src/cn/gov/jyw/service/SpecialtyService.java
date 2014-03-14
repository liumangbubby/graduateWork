package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.SpecialtyDAO;
import cn.gov.jyw.pojo.specialty;

public class SpecialtyService {
	private SpecialtyDAO specialtyDAO;

	public List<specialty> getSpecialtyAll() {
		return specialtyDAO.getSpecialtyAll();
	}

	// ///////////////////////////////////////////////////////////

	public SpecialtyDAO getSpecialtyDAO() {
		return specialtyDAO;
	}

	public void setSpecialtyDAO(SpecialtyDAO specialtyDAO) {
		this.specialtyDAO = specialtyDAO;
	}

}
