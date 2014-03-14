package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.schedule;

public class ScheduleDAO extends SqlMapClientDaoSupport {
	public void insertSchedule(schedule s) {

		this.getSqlMapClientTemplate().insert("InsertSchedule", s);
	}

	public List<schedule> selectSchedule_1(String school, String classNo, String filter) {
		HashMap map = new HashMap();
		map.put("school", school);
		map.put("classNo", classNo);
		map.put("filter", filter);
		return getSqlMapClientTemplate().queryForList("SelectSchedule_1", map);
	}

	public void deleteSchedule_1(List list) {
		this.getSqlMapClientTemplate().delete("DeleteSchedule_1", list);
	}

	public List<String> selectSchedule_2() {
		return this.getSqlMapClientTemplate().queryForList("SelectSchedule_2");
	}

	public List<String> selectSchedule_3(String school) {
		HashMap map = new HashMap();
		map.put("school", school);
		return this.getSqlMapClientTemplate().queryForList("SelectSchedule_3", map);
	}

	public schedule selectSchedule_4(String classNo) {
		return (schedule) this.getSqlMapClientTemplate().queryForObject("SelectSchedule_4", classNo);
	}

	public List<schedule> selectSchedule_5(String classNo) {
		return this.getSqlMapClientTemplate().queryForList("SelectSchedule_5", classNo);
	}

	public schedule selectSchedule_6(String classNo, int week) {
		HashMap map = new HashMap();
		map.put("classNo", classNo);
		map.put("week", week);
		return (schedule) this.getSqlMapClientTemplate().queryForObject("SelectSchedule_6", map);
	}

	public void updateSchedule_1(schedule schedule) {
		this.getSqlMapClientTemplate().update("UpdateSchedule_1", schedule);
	}

	public boolean selectSchedule_7(String classNo, int week) {
		HashMap map = new HashMap();
		map.put("week", week);
		map.put("classNo", classNo);
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("SelectSchedule_7", map);
		return count >= 1 ? true : false;
	}

	public void deleteSchedule_2(String classNo, int week) {
		HashMap map = new HashMap();
		map.put("week", week);
		map.put("classNo", classNo);
		this.getSqlMapClientTemplate().delete("DeleteSchedule_2", map);
	}

}
