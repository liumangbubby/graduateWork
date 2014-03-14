package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.user;

public class UserDAO extends SqlMapClientDaoSupport {
	public void addUser(user user) {
		this.getSqlMapClientTemplate().insert("InsertUser", user);
	}

	public void deleteUser(List list) {
		this.getSqlMapClientTemplate().delete("DeleteUser", list);
	}

	public boolean hasAdmin() {
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject("HasAdmin");
		return i > 0 ? true : false;
	}

	public boolean hasUser(String username, String password) {
		HashMap map = new HashMap();
		map.put("username", username);
		map.put("password", password);
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("SelectUser_1", map);
		return count > 0 ? true : false;
	}
	public String selectUserSchool(String username){
		return (String) this.getSqlMapClientTemplate().queryForObject("SelectUserSchool",username);
	}

	public List<user> getUserAll() {
		return this.getSqlMapClientTemplate().queryForList("SelectUserAll");
	}
}
