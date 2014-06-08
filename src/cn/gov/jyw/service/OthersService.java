package cn.gov.jyw.service;

import cn.gov.jyw.dao.OthersDAO;


public class OthersService {
	private OthersDAO othersDAO;

	public int queryTotalExam(){
		int total = othersDAO.queryExamTotal();
		return total > 0 ? total : 0;
	}

	//getter setter
	public OthersDAO getOthersDAO() {
		return othersDAO;
	}
	
	public void setOthersDAO(OthersDAO othersDAO) {
		this.othersDAO = othersDAO;
	}
}
