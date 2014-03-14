package cn.gov.jyw.service.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.jws.WebService;

import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.stu_enroll;

@WebService
public interface StuEnroll {
	public stu_enroll getStuEnroll(String pid);

	public boolean beginClass(class_info info, String pid);
}
