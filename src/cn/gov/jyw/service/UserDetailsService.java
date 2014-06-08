package cn.gov.jyw.service;

import cn.gov.jyw.dao.UserDetailsDAO;
import cn.gov.jyw.pojo.user_details;

public class UserDetailsService {
	private UserDetailsDAO detailDao;
	
	public void addUserDetails(user_details details){
		detailDao.addDetails(details);
	}
	
	public int queryQuesNo(int user_id) {
		return detailDao.queryUserQuesNo(user_id);
	}
	//getter setter
	public UserDetailsDAO getDetailDao() {
		return detailDao;
	}

	public void setDetailDao(UserDetailsDAO detailDao) {
		this.detailDao = detailDao;
	}
}
