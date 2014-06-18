package cn.gov.jyw.service;

import java.util.List;
import java.util.Map;

import cn.gov.jyw.dao.ExamDao;
import cn.gov.jyw.pojo.exam;

public class ExamService {
	private ExamDao examDao;
	public List<exam> listByIds(String ids){
		return examDao.listExams(ids);
	}
	public List<Object[]> queryOneByOne(int exam_id){
		return examDao.queryOneByOne(exam_id);
	}
	public List<Object[]> queryOneExam(Map map){
		return examDao.queryOneExam(map);
	}
	public boolean checkAnswer(int exam_id,int answer){
		return examDao.checkAnswer(exam_id,answer);
	}
	////////////////////////////////////
	public ExamDao getExamDao() {
		return examDao;
	}
	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}
}
