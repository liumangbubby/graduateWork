package cn.gov.jyw.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.politics;

public class PoliticsDAO extends SqlMapClientDaoSupport {
	public List<politics> getPoliticsAll() {
		return this.getSqlMapClientTemplate().queryForList("SelectPoliticsAll");
	}
}
