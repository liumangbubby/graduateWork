package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.class_info;

public class ClassInfoDAO extends SqlMapClientDaoSupport {
	/**
	 * 所有班级信息
	 * 
	 * @return
	 */
	public List<class_info> getClassInfoAll(String archives) {
		HashMap map = new HashMap();
		map.put("archives", archives);
		return this.getSqlMapClientTemplate().queryForList("SelectClassInfoAll", map);
	}

	/**
	 * 添加班级信息
	 * 
	 * @param info
	 */
	public void addClassInfo(class_info info) throws Exception {
		this.getSqlMapClientTemplate().insert("InsertClassInfo", info);
	}

	/**
	 * 按照学校删除班级
	 * 
	 * @param list
	 */
	public void deleteClass(List list) {
		this.getSqlMapClientTemplate().delete("DeleteClass", list);
	}

	/**
	 * 删除班级信息
	 * 
	 * @param id
	 */
	public void deleteClassInfo(List list) {
		this.getSqlMapClientTemplate().delete("DeleteClassInfo", list);
	}

	/**
	 * 按照专业删除班级
	 * 
	 * @param list
	 */
	public void deleteClassByType(List list) {
		this.getSqlMapClientTemplate().delete("DeleteClassByType", list);
	}

	/**
	 * 查询班级信息
	 * 
	 * @param name
	 * @param type
	 * @param school
	 * @return
	 */
	public List<class_info> selectClassInfo(String name, String type, String school) {
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("type", type);
		map.put("school", school);
		return this.getSqlMapClientTemplate().queryForList("SelectClassInfo", map);
	}

	/**
	 * 根据申报专业和学校查询班级
	 * 
	 * @param type
	 * @return
	 */
	public List selectRefClass(int typeId, int eduId, String archives) {
		HashMap map = new HashMap();
		map.put("typeId", typeId);
		map.put("eduId", eduId);
		map.put("archives", archives);
		List list = getSqlMapClientTemplate().queryForList("SelectRefClass", map);
		return list;
	}

	/**
	 * 更新班级的状态
	 * 
	 * @param bool
	 */
	public void updateClassInfoArchives(boolean bool) {
		this.getSqlMapClientTemplate().update("UpdateClassInfoArchives", bool);
	}

	/**
	 * 更新班级人数（已经归档）
	 */
	public void updateClassInfoNum_1() {
		this.getSqlMapClientTemplate().update("UpdateClassInfoNum_1");
	}

	/**
	 * 更新班级人数(单独按照班级归档)
	 */
	public void updateClassInfoNum_2(String name, int num) {
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("name", name);
		this.getSqlMapClientTemplate().update("UpdateClassInfoNum_2", map);
	}

	/**
	 * 班级是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasClassInfo(String name) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("HasClassInfo", name);
		return count > 0 ? true : false;
	}

	public void updateClassInfoArchivesByClass(String name, boolean archives) {
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("archives", archives);
		this.getSqlMapClientTemplate().update("UpdateClassInfoArchivesByClass", map);

	}

	public class_info selectClassById(int id) {
		return (class_info) this.getSqlMapClientTemplate().queryForObject("SelectClassById", id);
	}

	public class_info selectClassMax(String type) {
		return (class_info) this.getSqlMapClientTemplate().queryForObject("SelectClassMax", type);
	}
}
