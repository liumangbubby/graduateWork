package cn.gov.jyw.service.web;

import javax.jws.WebService;

import cn.gov.jyw.service.ClassInfoService;

@WebService(endpointInterface = "cn.gov.jyw.service.web.ClassInfo", serviceName = "ClassInfoImpl")
public class ClassInfoImpl implements ClassInfo {
	private ClassInfoService classInfoService;

	@Override
	public boolean hasClassInfo(String name) {
		return classInfoService.hasClassInfo(name);
	}

	public ClassInfoService getClassInfoService() {
		return classInfoService;
	}

	public void setClassInfoService(ClassInfoService classInfoService) {
		this.classInfoService = classInfoService;
	}

}
