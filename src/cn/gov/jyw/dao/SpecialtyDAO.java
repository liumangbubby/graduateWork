package cn.gov.jyw.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.specialty;

public class SpecialtyDAO extends SqlMapClientDaoSupport {
	public List<specialty> getSpecialtyAll() {
		return getSqlMapClientTemplate().queryForList("SelectSpecialtyAll");
	}
}
