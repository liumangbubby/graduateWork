package cn.gov.jyw.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.join_state;

public class JoinStateDAO extends SqlMapClientDaoSupport {

	public List<join_state> getAll() {
		return this.getSqlMapClientTemplate().queryForList("SelectStateAll");
	}

	public void deleteState(List list) {
		this.getSqlMapClientTemplate().delete("DeleteJoinState", list);
	}

	public void insertState(String state) {
		this.getSqlMapClientTemplate().insert("InsertJoinState", state);
	}
}
