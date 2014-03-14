package cn.gov.jyw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.ref;

public class RefDAO extends SqlMapClientDaoSupport {
	public void insert(ref ref) {
		this.getSqlMapClientTemplate().insert("InsertRef", ref);
	}

	public List<ref> selectBySchool(String school) {
		return this.getSqlMapClientTemplate().queryForList("SelectRefBySchool",
				school);
	}
	public void deleteRefBySchool(String school){
		 this.getSqlMapClientTemplate().delete("DeleteRefBySchool", school);
	}
	public void deleteRef(List list){
		this.getSqlMapClientTemplate().delete("DeleteRef",list);
	}
	public void deleteRefBySpec(List list){
		this.getSqlMapClientTemplate().delete("DeleteRefBySpec",list);
	}
}
