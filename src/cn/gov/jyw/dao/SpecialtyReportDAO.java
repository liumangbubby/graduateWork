package cn.gov.jyw.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.specialty_report;

public class SpecialtyReportDAO extends SqlMapClientDaoSupport {

	public List<specialty_report> getAll() {
		List<specialty_report> list = this.getSqlMapClientTemplate()
				.queryForList("SelectSpecialtyReport");
		return list;
	}

	public void deleteSpeciaty(List list) {
		this.getSqlMapClientTemplate().delete("DeleteSpecialtyReport", list);
	}

	public void insertSpecialty(String name) {
		this.getSqlMapClientTemplate().insert("InsertSpecialtyReport", name);
	}
}
