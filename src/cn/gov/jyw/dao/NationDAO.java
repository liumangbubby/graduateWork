package cn.gov.jyw.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.nation;

public class NationDAO extends SqlMapClientDaoSupport {
	public List<nation> getAll() {
		List<nation> list = this.getSqlMapClientTemplate().queryForList("selectNationAll");
		return list;
	}
}
