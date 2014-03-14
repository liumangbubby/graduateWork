package cn.gov.jyw.service;

import cn.gov.jyw.dao.RemarkDAO;

public class RemarkService {
	private RemarkDAO remarkDAO;

	public RemarkDAO getRemarkDAO() {
		return remarkDAO;
	}

	public void setRemarkDAO(RemarkDAO remarkDAO) {
		this.remarkDAO = remarkDAO;
	}

	public String selectNum(String school) {
		return this.remarkDAO.selectNum(school);
	}
	
	public void updateRemark_1(String num,String school){
		this.remarkDAO.updateRemark_1(num, school);
	}
	public void addRemark(String num,String school){
		this.remarkDAO.addRemark(num, school);
	}
	public void deleteRemark(int id){
		this.remarkDAO.deleteRemark(id);
	}
}
