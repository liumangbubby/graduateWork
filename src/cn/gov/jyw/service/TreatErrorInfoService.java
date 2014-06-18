package cn.gov.jyw.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.gov.jyw.dao.ErrorTemDAO;
import cn.gov.jyw.dao.ExamErrorDAO;
import cn.gov.jyw.dao.TbUserDAO;
import cn.gov.jyw.pojo.error_exam_tem;
import cn.gov.jyw.pojo.exam_error;
import cn.gov.jyw.pojo.tb_user;

public class TreatErrorInfoService {
	private ExamErrorDAO errorDao;
	private ErrorTemDAO errorTemDao;
	private TbUserDAO userDao;
	public void addTreatErrInfo(){
		List<tb_user> users = userDao.getUserAll();
		for(tb_user user : users){
			List<error_exam_tem> errorTems = errorTemDao.queryTemErrorByUser(user.getUserId());
			List<exam_error> errors = new ArrayList<exam_error>();
			Set<error_exam_tem> set = new HashSet<error_exam_tem>(errorTems);
			for(error_exam_tem errorTem : set){
				errors.add(new exam_error(errorTem.getUser_id(),errorTem.getExam_id(),Collections.frequency(errorTems, errorTem)));
			}
			errorDao.addExamError(errors);
			errorTemDao.deleteAllInfo();
		}
		System.out.println("------------TreatErrInfo------------");
	}
	//getter setter
	public ExamErrorDAO getErrorDao() {
		return errorDao;
	}
	public void setErrorDao(ExamErrorDAO errorDao) {
		this.errorDao = errorDao;
	}
	public ErrorTemDAO getErrorTemDao() {
		return errorTemDao;
	}
	public void setErrorTemDao(ErrorTemDAO errorTemDao) {
		this.errorTemDao = errorTemDao;
	}
	public TbUserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(TbUserDAO userDao) {
		this.userDao = userDao;
	}
}
