package cn.gov.jyw.service.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.jws.WebService;

import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.service.ClassInfoService;
import cn.gov.jyw.service.StuEnrollService;

@WebService(endpointInterface = "cn.gov.jyw.service.web.StuEnroll", serviceName = "StuEnrollImpl")
public class StuEnrollImpl implements StuEnroll {
	private StuEnrollService stuEnrollService;
	private ClassInfoService classInfoService;

	@Override
	public stu_enroll getStuEnroll(String pid) {
		stu_enroll enroll = this.stuEnrollService.selectStuEnrollByPid(pid,null,null);
		return enroll;
	}

	@Override
	public boolean beginClass(class_info info, String pid) {
		try {
			ArrayList list = new ArrayList();
			pid = pid.substring(1);
			pid = pid.substring(0, pid.length() - 1);

			classInfoService.addClassInfo(info); // 添加班级信息
			stuEnrollService.updateStuEnrollClassNo(info.getName(),
					pid.split(","));
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

	public ClassInfoService getClassInfoService() {
		return classInfoService;
	}

	public void setClassInfoService(ClassInfoService classInfoService) {
		this.classInfoService = classInfoService;
	}

}
