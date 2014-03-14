package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.UserDAO;
import cn.gov.jyw.pojo.user;

public class UserService {
	private UserDAO userDAO;

	public void addUser(user user) {
		this.userDAO.addUser(user);
	}

	public void deleteUser(List list) {
		this.userDAO.deleteUser(list);
	}

	public void addHasAdmin() {
		boolean bool = this.userDAO.hasAdmin();
		if (bool == false) {
			user user = new user();
			user.setUsername("admin");
			user.setPassword("adminbackyard");
			addUser(user);
		}
	}

	public List<user> getUserAll() {
		return this.userDAO.getUserAll();
	}
	public String selectUserSchool(String username){
		return userDAO.selectUserSchool(username);
	}

	// //////////////////////////////////////////////////////////
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
