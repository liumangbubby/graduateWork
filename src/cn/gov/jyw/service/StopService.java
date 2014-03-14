package cn.gov.jyw.service;

import java.util.HashMap;
import java.util.List;

import cn.gov.jyw.dao.StopDAO;
import cn.gov.jyw.pojo.stop;

public class StopService {
	private StopDAO stopDAO;

	public List<stop> selectStop_1(String school, String classNo, String name, String pid,Integer year) {
		return this.stopDAO.selectStop_1(school, classNo, name, pid,year);
	}

	public List<String> selectStop_2() {
		return this.stopDAO.selectStop_2();
	}

	public List<String> selectStop_3(String school) {
		return this.stopDAO.selectStop_3(school);
	}

	public StopDAO getStopDAO() {
		return stopDAO;
	}

	public void setStopDAO(StopDAO stopDAO) {
		this.stopDAO = stopDAO;
	}

	public void deleteStop_1(String[] stopId) {
		this.stopDAO.deleteStop_1(stopId);
	}

	public void addStop(stop s) {
		this.stopDAO.addStop(s);
	}

	public HashMap selectStop_4(String classNo) {
		return stopDAO.selectStop_4(classNo);
	}

	public long selectStop_5(int year, String school) {
		return stopDAO.selectStop_5(year, school);
	}

	public long selectStop_6(int year, String school) {
		return stopDAO.selectStop_6(year, school);
	}
	public long selectStop_7(int year){
		return stopDAO.selectStop_7(year);
	}
	public long selectStop_8(int year){
		return stopDAO.selectStop_8(year);
	}
	public long selectStop_9(int year){
		return stopDAO.selectStop_9(year);
	}
}
