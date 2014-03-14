package cn.gov.jyw.service;

import cn.gov.jyw.dao.SystemDAO;

public class SystemService {
	private SystemDAO systemDAO;
	
	public String selectIsOpenEnroll() {
		return systemDAO.selectIsOpenEnroll();
	}

	public String selectYearEnroll() {
		return systemDAO.selectYearEnroll();
	}

	public void updateYearEnroll(String value) {
		systemDAO.updateYearEnroll(value);
	}

	public void updateOpenEnroll(String value) {
		systemDAO.updateOpenEnroll(value);
	}

	public SystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(SystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}

}
