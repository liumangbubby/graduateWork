package cn.gov.jyw.dao;

import java.util.HashMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RemarkDAO extends SqlMapClientDaoSupport {
	public String selectNum(String school){
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"SelectRemark_1", school);
	}
	
	public void updateRemark_1(String num,String school){
		HashMap map=new HashMap();
		map.put("num", num);
		map.put("school", school);
		this.getSqlMapClientTemplate().update("UpdateRemark_1", map);
	}
	
	public void addRemark(String num,String school){
		HashMap map=new HashMap();
		map.put("num", num);
		map.put("school", school);
		this.getSqlMapClientTemplate().insert("InsertRemark", map);
	}
	public void deleteRemark(int id){
		this.getSqlMapClientTemplate().delete("DeleteRemark", id);
	}
}
