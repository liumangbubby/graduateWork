package cn.gov.jyw.service;

import cn.gov.jyw.dao.UserDAO;

public class LoginService {
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean login(String username, String password) {
		boolean bool = userDAO.hasUser(username, password);
		return bool;
	}

}
