package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.TbUserDAO;
import cn.gov.jyw.pojo.tb_user;

public class TbUserService {
	private TbUserDAO userDAO;

	public void addUser(tb_user user) {
		this.userDAO.addUser(user);
	}

	public void deleteUser(List list) {
		this.userDAO.deleteUser(list);
	}


	public List<tb_user> getUserAll() {
		return this.userDAO.getUserAll();
	}
	
	public boolean hasUser(String username,String password){
		return userDAO.hasUser(username, password);
	}

	// //////////////////////////////////////////////////////////
	public TbUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(TbUserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
