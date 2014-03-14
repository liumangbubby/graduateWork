package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.PoliticsDAO;
import cn.gov.jyw.pojo.politics;

public class PoliticsService {
	private PoliticsDAO politicsDAO;

	public List<politics> getPoliticsAll() {
		return politicsDAO.getPoliticsAll();
	}

	// ///////////////////////////////////////////////////////////////////////

	public PoliticsDAO getPoliticsDAO() {
		return politicsDAO;
	}

	public void setPoliticsDAO(PoliticsDAO politicsDAO) {
		this.politicsDAO = politicsDAO;
	}

}
