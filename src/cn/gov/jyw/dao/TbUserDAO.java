package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.tb_user;

public class TbUserDAO extends SqlMapClientDaoSupport{
	public int hasUser(String username, String password) {
		HashMap map = new HashMap();
		map.put("userName", username);
		map.put("paseWord", password);
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("SelectTbUser", map);
		return count > 0 ? count : 0;
	}
	
	public void addUser(tb_user user) {
		this.getSqlMapClientTemplate().insert("InsertTbUser", user);
	}

	public void deleteUser(List list) {
		// TODO Auto-generated method stub
		
	}

	public List<tb_user> getUserAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("SelectAllUser");
	}
	
	public tb_user getUser(int userId){
		return (tb_user) this.getSqlMapClientTemplate().queryForObject("SelectUser",userId);
	}
	
	public tb_user getPID(String userid){
		Map map = new HashMap();
		map.put("userId", userid);
		return (tb_user) this.getSqlMapClientTemplate().queryForObject("SelectUser", map);
	}

	public void updateUser(Map map) {
		this.getSqlMapClientTemplate().update("UpdateUser", map);
	}
}
