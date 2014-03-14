package cn.gov.jyw.action.backyard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import cn.gov.jyw.bean.CheckBean;
import cn.gov.jyw.pojo.edu_school;
import cn.gov.jyw.pojo.year;
import cn.gov.jyw.service.EduSchoolService;
import cn.gov.jyw.service.EnrollManageService;
import cn.gov.jyw.service.RemarkService;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.StuRecordService;
import cn.gov.jyw.service.SystemService;
import cn.gov.jyw.service.YearService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class SystemAction {
	private SystemService systemService;
	private YearService yearService;
	private EnrollManageService enrollManageService;
	private StuRecordService stuRecordService;
	private StuEnrollService stuEnrollService;
	private String isOpenEnroll;
	private String yearEnroll;
	private CheckBean checkBean;
	private EduSchoolService eduSchoolService;
	private RemarkService remarkService;

	public String index() {
		String school=(String) ActionContext.getContext().getSession().get("school");
		String isOpenEnroll = systemService.selectIsOpenEnroll();
		String yearEnroll = systemService.selectYearEnroll();
		List<year> yearList = yearService.getYearAll();
		String num=remarkService.selectNum(school);
		long enrollCount = enrollManageService.getEnrollCount(school,num);
		List<edu_school> eduList = eduSchoolService.getEduSchoolAll();
		long num_1=stuEnrollService.getEnrollCount(school,num);	//申报表人数
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(new Date());
		long num_2=stuRecordService.selectStuRecordCount_3(calendar.get(GregorianCalendar.YEAR));	//本年度已经归档的人数
		ValueStack stack = ActionContext.getContext().getValueStack();

		stack.set("isOpenEnroll", isOpenEnroll);
		stack.set("yearEnroll", yearEnroll);
		stack.set("yearList", yearList);
		stack.set("enrollCount", enrollCount);
		stack.set("eduList", eduList);
		stack.set("num_1", num_1);
		stack.set("num_2", num_2);
		
		return "success";
	}

	public String isOpenEnroll() {
		systemService.updateOpenEnroll(isOpenEnroll);
		Map app = ActionContext.getContext().getApplication();
		app.put("isOpenEnroll", isOpenEnroll);
		return index();
	}

	public String isYearEnroll() {
		systemService.updateYearEnroll(yearEnroll);
		Map app = ActionContext.getContext().getApplication();
		app.put("yearEnroll", yearEnroll);
		checkBean.setYearEnroll(Integer.parseInt(yearEnroll));
		return index();
	}

	/**
	 * 删除全部申报记录
	 * 
	 * @return
	 */
	public String deleteStuEnrollAll() {
		enrollManageService.deleteStuEnrollAll();

		return index();
	}

	/**
	 * 归档数据
	 * 
	 * @return
	 */
	public String insertEnrollToRecord() {
		stuRecordService.addEnrollToRecord();
		return index();
	}

	// //////////////////////////////////////////////////////////////////////
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public String getIsOpenEnroll() {
		return isOpenEnroll;
	}

	public void setIsOpenEnroll(String isOpenEnroll) {
		this.isOpenEnroll = isOpenEnroll;
	}

	public String getYearEnroll() {
		return yearEnroll;
	}

	public void setYearEnroll(String yearEnroll) {
		this.yearEnroll = yearEnroll;
	}

	public EnrollManageService getEnrollManageService() {
		return enrollManageService;
	}

	public void setEnrollManageService(EnrollManageService enrollManageService) {
		this.enrollManageService = enrollManageService;
	}

	public StuRecordService getStuRecordService() {
		return stuRecordService;
	}

	public void setStuRecordService(StuRecordService stuRecordService) {
		this.stuRecordService = stuRecordService;
	}

	public CheckBean getCheckBean() {
		return checkBean;
	}

	public void setCheckBean(CheckBean checkBean) {
		this.checkBean = checkBean;
	}

	public EduSchoolService getEduSchoolService() {
		return eduSchoolService;
	}

	public void setEduSchoolService(EduSchoolService eduSchoolService) {
		this.eduSchoolService = eduSchoolService;
	}

	public StuEnrollService getStuEnrollService() {
		return stuEnrollService;
	}

	public void setStuEnrollService(StuEnrollService stuEnrollService) {
		this.stuEnrollService = stuEnrollService;
	}

	public RemarkService getRemarkService() {
		return remarkService;
	}

	public void setRemarkService(RemarkService remarkService) {
		this.remarkService = remarkService;
	}
	

}
