package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.stop;

public class StopDAO extends SqlMapClientDaoSupport {
	public List<stop> selectStop_1(String school, String classNo, String name, String pid,Integer year) {
		HashMap map = new HashMap();
		map.put("school", school);
		map.put("class_no", classNo);
		map.put("name", name);
		map.put("pid", pid);
		map.put("year", year);
		List<stop> list = this.getSqlMapClientTemplate().queryForList("SelectStop_1", map);
		return list;
	}

	public List<String> selectStop_2() {
		return this.getSqlMapClientTemplate().queryForList("SelectStop_2");
	}

	public List<String> selectStop_3(String school) {
		HashMap map = new HashMap();
		map.put("school", school);
		return this.getSqlMapClientTemplate().queryForList("SelectStop_3", map);
	}

	public void deleteStop_1(String[] stopId) {
		this.getSqlMapClientTemplate().delete("DeleteStop_1", stopId);
	}

	public void addStop(stop s) {
		HashMap map = new HashMap();
		map.put("class_no", s.getClass_no());
		map.put("school", s.getSchool());
		map.put("name", s.getName());
		map.put("pid", s.getPid());
		map.put("stu_no", s.getClass_no());
		map.put("tel", s.getTel());
		map.put("join_date", s.getJoin_date());
		map.put("stop_date", s.getStop_date());
		map.put("days", s.getDays());
		map.put("reason", s.getReason());
		map.put("remark", s.getRemark());
		map.put("number", s.getNumber());
		map.put("name", s.getName());
		this.getSqlMapClientTemplate().insert("InsertStop", map);
	}

	public HashMap selectStop_4(String classNo) {
		return (HashMap) this.getSqlMapClientTemplate().queryForObject("SelectStop_4", classNo);
	}
	
	public long selectStop_5(int year,String school){
		HashMap map=new HashMap();
		map.put("year", year);
		map.put("school", school);
		return (Long) this.getSqlMapClientTemplate().queryForObject("SelectStop_5",map);
	}
	public long selectStop_6(int year,String school){
		HashMap map=new HashMap();
		map.put("year", year);
		map.put("school", school);
		return (Long) this.getSqlMapClientTemplate().queryForObject("SelectStop_6",map);
	}
	public long selectStop_7(int year){
		return (Long) this.getSqlMapClientTemplate().queryForObject("SelectStop_7",year);
	}
	public long selectStop_8(int year){
		return (Long) this.getSqlMapClientTemplate().queryForObject("SelectStop_8",year);
	}
	public long selectStop_9(int year){
		return (Long) this.getSqlMapClientTemplate().queryForObject("SelectStop_9",year);
	}
}
