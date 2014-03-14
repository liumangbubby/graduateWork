package cn.gov.jyw.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SystemDAO extends SqlMapClientDaoSupport {

	public String selectIsOpenEnroll() {
		return (String) this.getSqlMapClientTemplate().queryForObject("SelectIsOpenEnroll");
	}

	public String selectYearEnroll() {
		return (String) this.getSqlMapClientTemplate().queryForObject("SelectYearEnroll");
	}

	public void updateYearEnroll(String value) {
		this.getSqlMapClientTemplate().update("UpdateYearEnroll", value);
	}

	public void updateOpenEnroll(String value) {
		this.getSqlMapClientTemplate().update("UpdateOpenEnroll", value);
	}
}
