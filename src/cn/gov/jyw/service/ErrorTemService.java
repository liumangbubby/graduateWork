package cn.gov.jyw.service;

import java.util.List;

import cn.gov.jyw.dao.ErrorTemDAO;
import cn.gov.jyw.pojo.error_exam_tem;

public class ErrorTemService {
	private ErrorTemDAO errorTemDao;
	public void addErrorTem(List<error_exam_tem> errors){
		errorTemDao.addErrorTem(errors);
	}
	//getter setter
	public ErrorTemDAO getErrorTemDao() {
		return errorTemDao;
	}
	public void setErrorTemDao(ErrorTemDAO errorTemDao) {
		this.errorTemDao = errorTemDao;
	}
}
