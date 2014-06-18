package cn.gov.jyw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public tb_user getUser(int userid){
		return this.userDAO.getUser(userid);
	}
	
	public int hasUser(String username,String password){
		return userDAO.hasUser(username, password);
	}
	
	public void updateExamScore(int userid,int score){
		Map map = new HashMap();
		map.put("userid", userid);
		map.put("score", score);
		userDAO.updateUser(map);
	}

	// //////////////////////////////////////////////////////////
	public TbUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(TbUserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
