package cn.gov.jyw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Object[]> queryOneExam(Map map){
		return this.getSqlMapClientTemplate().queryForList("getExam3", map);
	}
	public boolean checkAnswer(int exam_id, int answer) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("examid", exam_id);
		map.put("answer", answer);
		Integer right_answer = (Integer)this.getSqlMapClientTemplate().queryForObject("checkAnswer", map);
		if(right_answer == null)
			return false;
		return right_answer == 1 ? true : false;
	}
}
