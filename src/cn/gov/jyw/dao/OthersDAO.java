package cn.gov.jyw.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class OthersDAO extends SqlMapClientDaoSupport {
	public int queryExamTotal(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("SelectExamTotal");
	}
}
