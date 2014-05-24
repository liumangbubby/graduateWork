package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.tb_user;

public class TbUserDAO extends SqlMapClientDaoSupport{
	public boolean hasUser(String username, String password) {
		HashMap map = new HashMap();
		map.put("userName", username);
		map.put("paseWord", password);
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("SelectTbUser", map);
		return count > 0 ? true : false;
	}
	
	public void addUser(tb_user user) {
		this.getSqlMapClientTemplate().insert("InsertTbUser", user);
	}

	public void deleteUser(List list) {
		// TODO Auto-generated method stub
		
	}

	public List<tb_user> getUserAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
