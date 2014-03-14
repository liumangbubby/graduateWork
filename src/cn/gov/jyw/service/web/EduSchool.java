package cn.gov.jyw.service.web;

import java.util.List;

import javax.jws.WebService;

import cn.gov.jyw.pojo.edu_school;
@WebService
public interface EduSchool {
	public List<edu_school> getEduSchoolAll();
}
