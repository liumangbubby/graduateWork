package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.YearDAO;
import cn.gov.jyw.pojo.year;

public class YearService {
	private YearDAO yearDAO;

	public List<year> getYearAll() {
		return yearDAO.getYearAll();
	}

	public void deleteYear(List list) {
		yearDAO.deleteYear(list);
	}

	public void addYear(year year) {
		yearDAO.addYear(year);
	}

	public void updateActiveYear(int id) {
		yearDAO.unActiveYear();
		yearDAO.activeYear(id);
	}

	public void updateUnActiveYear() {
		yearDAO.unActiveYear();
	}

	// //////////////////////////////////////////////////////////

	public YearDAO getYearDAO() {
		return yearDAO;
	}

	public void setYearDAO(YearDAO yearDAO) {
		this.yearDAO = yearDAO;
	}

}
