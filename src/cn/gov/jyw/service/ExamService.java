package cn.gov.jyw.service;

import java.util.List;

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
	public ExamDao getExamDao() {
		return examDao;
	}
	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}
}
