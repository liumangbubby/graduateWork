package cn.gov.jyw.dao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.pojo.exam_choose;

public class ExamDao extends SqlMapClientDaoSupport{
	public List<exam> listExams(String ids){
		return this.getSqlMapClientTemplate().queryForList("listByIds", ids);
	}
	public List<Object[]> queryOneByOne(int exam_id){
		return this.getSqlMapClientTemplate().queryForList("getExam2", exam_id);
	}
	public List<exam_choose> listExamChoose(int choose_id){
		System.out.println(choose_id);
		return this.getSqlMapClientTemplate().queryForList("selectExamChoose", choose_id);
	}
}
