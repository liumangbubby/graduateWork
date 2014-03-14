package cn.gov.jyw.service;

import java.util.ArrayList;
import java.util.List;

import cn.gov.jyw.dao.RefDAO;
import cn.gov.jyw.pojo.ref;

public class RefService {
	private RefDAO refDAO;

	// 插入学校专业数据
	public void addRef(ArrayList<ref> list,String school) {
		refDAO.deleteRefBySchool(school);
		for (ref ref : list) {
			refDAO.insert(ref);
		}
	}

	public List<ref> selectBySchool(String school) {
		
		return refDAO.selectBySchool(school);
	}
	/**
	 * 删除该学校下面所有的专业
	 * @param school
	 */
	public void deleteRefBySchool(String school){
		refDAO.deleteRefBySchool(school);
	}

	public RefDAO getRefDAO() {
		return refDAO;
	}

	public void setRefDAO(RefDAO refDAO) {
		this.refDAO = refDAO;
	}

}
