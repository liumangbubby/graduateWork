package cn.gov.jyw.service.web;

import java.util.List;

import javax.jws.WebService;

import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.service.EduSchoolService;

@WebService(endpointInterface = "cn.gov.jyw.service.web.EduSchool", serviceName = "EduSchoolImpl")
public class EduSchoolImpl implements EduSchool {
	private EduSchoolService eduSchoolService;

	@Override
	public List<edu_school> getEduSchoolAll() {
		
		return eduSchoolService.getEduSchoolAll();
		
	}

	public EduSchoolService getEduSchoolService() {
		return eduSchoolService;
	}

	public void setEduSchoolService(EduSchoolService eduSchoolService) {
		this.eduSchoolService = eduSchoolService;
	}

}
