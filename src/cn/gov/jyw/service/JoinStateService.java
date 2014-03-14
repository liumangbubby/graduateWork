package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.JoinStateDAO;
import cn.gov.jyw.pojo.join_state;

public class JoinStateService {
	private JoinStateDAO joinStateDAO;

	public List<join_state> getAll() {
		return this.getJoinStateDAO().getAll();
	}

	public void deleteState(List list) {
		joinStateDAO.deleteState(list);
	}

	public void addState(String state) {
		joinStateDAO.insertState(state);
	}

	public JoinStateDAO getJoinStateDAO() {
		return joinStateDAO;
	}

	public void setJoinStateDAO(JoinStateDAO joinStateDAO) {
		this.joinStateDAO = joinStateDAO;
	}

}
