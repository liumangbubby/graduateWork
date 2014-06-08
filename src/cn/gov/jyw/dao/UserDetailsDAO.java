package cn.gov.jyw.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.user_details;


public class UserDetailsDAO extends SqlMapClientDaoSupport{
	public void addDetails(user_details details) {
		this.getSqlMapClientTemplate().insert("InsertUserDetails", details);
		System.out.println("----------insert details----------");
	}

	public int queryUserQuesNo(int user_id) {
		System.out.println("----------query details----------");
		int ques_no = (Integer) this.getSqlMapClientTemplate().queryForObject("QueryUserQuesNo", user_id);
		return ques_no > 0 ? ques_no : 0;
	}
}
