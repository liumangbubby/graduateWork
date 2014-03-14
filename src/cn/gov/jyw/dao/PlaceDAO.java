package cn.gov.jyw.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.place;

public class PlaceDAO extends SqlMapClientDaoSupport {
	public List<place> getPlaceAll() {
		return this.getSqlMapClientTemplate().queryForList("SelectPlaceAll");
	}

	public void insertPlace(String place) {
		this.getSqlMapClientTemplate().insert("InsertPlace", place);
	}

	public void deletePlace(int id) {
		this.getSqlMapClientTemplate().delete("DeletePlace", id);
	}
}
