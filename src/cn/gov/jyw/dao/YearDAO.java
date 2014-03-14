package cn.gov.jyw.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.year;

public class YearDAO extends SqlMapClientDaoSupport {

	public List<year> getYearAll() {
		return getSqlMapClientTemplate().queryForList("SelectYearAll");
	}

	public void deleteYear(List list) {
		getSqlMapClientTemplate().delete("DeleteYear", list);
	}

	public void addYear(year year) {
		getSqlMapClientTemplate().insert("InsertYear", year);
	}

	public void activeYear(int id) {
		getSqlMapClientTemplate().update("ActiveYear", id);
	}

	public void unActiveYear() {
		getSqlMapClientTemplate().update("UnActiveYear");
	}

}
