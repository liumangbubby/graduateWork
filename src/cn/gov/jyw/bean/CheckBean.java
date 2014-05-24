package cn.gov.jyw.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.gov.jyw.pojo.politics;
import cn.gov.jyw.pojo.specialty;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.year;
import cn.gov.jyw.service.PlaceService;
import cn.gov.jyw.service.PoliticsService;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.SpecialtyService;
import cn.gov.jyw.service.SystemService;
import cn.gov.jyw.service.YearService;

public class CheckBean {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private YearService yearService;
	private SpecialtyService specialtyService;
	private PoliticsService politicsService;
	private SpecialtyReportService specialtyReportService;
	private SystemService systemService;
	private PlaceService placeService;
	private int yearEnroll;
	private List srept;

	/**
	 * 检查姓名，四位汉字，不能有其他的字符
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkName(String name) {
		if (name == null) {
			return false;
		}
		Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,4}$");
		Matcher m = p.matcher(name);
		boolean yesorno = m.matches();
		return yesorno;

	}

	/**
	 * 验证性别，汉字男或女
	 * 
	 * @param sex
	 * @return
	 */
	public boolean checkSex(String sex) {
		if (sex == null) {
			return false;
		}
		return ("男".equals(sex) || "女".equals(sex));
	}

	/**
	 * 验证民族
	 * 
	 * @param nation
	 * @return
	 */
	public boolean checkNation(String nation) {
		if (nation == null) {
			return false;
		}
		String st = "汉族,蒙古族,回族,满族,朝鲜族,藏族,维吾尔族,苗族,彝族,壮族,布依族,"
				+ "侗族,瑶族,白族,土家族,哈尼族,哈萨克族,傣族,黎族,傈僳族,佤族,畲族,高山族,拉祜族,"
				+ "水族,东乡族,纳西族,景颇族,柯尔克孜族,土族,达斡尔族,仫佬族,羌族,布朗族,撒拉族,毛南族,"
				+ "仡佬族,锡伯族,阿昌族,普米族,塔吉克族,怒族,乌孜别克族,俄罗斯族,鄂温克族,德昂族,保安族,"
				+ "裕固族,京族,塔塔尔族,独龙族,鄂伦春族,赫哲族,门巴族,珞巴族,基诺族";
		String[] nations = st.split(",");

		for (int i = 0; i < nations.length; i++) {
			if (nations[i].equals(nation)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查身份证
	 * 
	 * @param pid
	 * @return
	 */
	public boolean checkPid(String pid) {
		if (pid == null) {
			return false;
		}
		IDCard cc = new IDCard();
		String temp = cc.IDCardValidate(pid);
		if (temp.length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 验证手机电话号码，11位英文数字
	 * 
	 * @param tel
	 * @return
	 */
	public boolean checkTel(String tel) {
		if (tel == null) {
			return false;
		}
		Pattern p = Pattern.compile("^[1-9][0-9]{10}$");
		Matcher m = p.matcher(tel);
		boolean yesorno = m.matches();
		return yesorno;
	}

	/**
	 * 验证毕业学校，20以内的汉字，不能有其他字符
	 * 
	 * @param school
	 * @return
	 */
	public boolean checkGraduteG(String school) {
		if (school == null) {
			return false;
		}
		Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,30}$");
		Matcher m = p.matcher(school);
		boolean yesorno = m.matches();
		return yesorno;
	}

	/**
	 * 验证毕业年份
	 * 
	 * @param year
	 * @return
	 */
	public boolean checkGraduteY(String year) {

		int temp = Integer.parseInt(year);
		if (temp == yearEnroll || temp == yearEnroll - 1
				|| temp == yearEnroll - 2) {
			return true;
		}
		return false;
	}

	/**
	 * 验证毕业时间
	 * 
	 * @param date
	 * @return
	 */
	public boolean checkGraduteD(String date) {
		if (date == null) {
			return false;
		}
		try {
			dateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * 验证工作类型，必须是指定的
	 * 
	 * @param edu
	 * @return
	 */
	public boolean checkEducation(String edu) {
		if (edu == null) {
			return false;
		}
		return (
				"市场营销  ".trim().equals(edu)||
				"媒体广告  ".trim().equals(edu)||
				"软件网络  ".trim().equals(edu)||
				"后勤文秘  ".trim().equals(edu)||
				"企业高管  ".trim().equals(edu)||
				"生产质检  ".trim().equals(edu)||
				"机械工程  ".trim().equals(edu)||
				"财会审计  ".trim().equals(edu)||
				"金融投资  ".trim().equals(edu)||
				"房产物业  ".trim().equals(edu)||
				"交通物流  ".trim().equals(edu)||
				"劳动家政  ".trim().equals(edu)||
				"店员导购  ".trim().equals(edu)||
				"教育教练  ".trim().equals(edu)||
				"资讯情报  ".trim().equals(edu)||
				"学术科研  ".trim().equals(edu)||
				"法律产权  ".trim().equals(edu)||
				"艺术设计  ".trim().equals(edu)||
				"影视新闻  ".trim().equals(edu)||
				"餐饮娱乐  ".trim().equals(edu)||
				"化工资源  ".trim().equals(edu)||
				"医疗保健  ".trim().equals(edu)||
				"语言翻译  ".trim().equals(edu)||
				"公务员    ".trim().equals(edu)||
				"环保园林  ".trim().equals(edu)||
				"农林牧渔  ".trim().equals(edu)||
				"学生及其他".trim().equals(edu)
				);
	}

	/**
	 * 验证专业信息，必须是指定的专业
	 * 
	 * @param spe
	 * @return
	 */
	public boolean checkSpecialty(String spe) {
		List<specialty> list = specialtyService.getSpecialtyAll();
		for (specialty specialty : list) {
			if (specialty.getSpecialty().equals(spe)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证政治面貌，必须是制定的政治面貌
	 * 
	 * @param pol
	 * @return
	 */
	public boolean checkPolitics(String pol) {
		List<politics> list = politicsService.getPoliticsAll();

		for (politics one : list) {
			if (one.getPolitics().equals(pol.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证生日，格式yyyy-MM-dd
	 * 
	 * @param birthday
	 * @return
	 */
	public boolean checkBirthday(String birthday) {
		if (birthday == null) {
			return false;
		}
		try {
			dateFormat.parse(birthday);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 验证现在住址，30个字符之内
	 * 
	 * @param address
	 * @return
	 */
	public boolean checkAddress(String address) {
		Pattern p = Pattern.compile("[0-9A-Za-z\\u4e00-\\u9fa5-,，]{1,30}");
		Matcher m = p.matcher(address);
		boolean yesorno = m.matches();
		return yesorno;
	}

	/**
	 * 验证户籍所在地，30个字符之内
	 * 
	 * @param address
	 * @return
	 */
	public boolean checHousehold(String address) {
		Pattern p = Pattern.compile("[0-9A-Za-z\\u4e00-\\u9fa5-,，]{1,30}");
		Matcher m = p.matcher(address);
		boolean yesorno = m.matches();
		return yesorno;
	}

	/**
	 * 验证邮箱地址，20个字符以内
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {

		if (email == null || email.length() > 20) {
			return false;
		}
		Pattern p = Pattern
				.compile("^([\\!#\\$%&\'\\*\\+/\\=?\\^`\\{\\|\\}~a-zA-Z0-9_-]+[\\.]?)+[\\!#\\$%&\'\\*\\+/\\=?\\^`\\{\\|\\}~a-zA-Z0-9_-]+@{1}((([0-9A-Za-z_-]+)([\\.]{1}[0-9A-Za-z_-]+)*\\.{1}([A-Za-z]){1,6})|(([0-9]{1,3}[\\.]{1}){3}([0-9]{1,3}){1}))$");
		Matcher m = p.matcher(email);
		boolean yesorno = m.matches();
		return yesorno;
	}

	/**
	 * 验证家庭地址，30个字符以内
	 * 
	 * @param address
	 * @return
	 */
	public boolean checkHomeAddress(String address) {
		Pattern p = Pattern.compile("[0-9A-Za-z\\u4e00-\\u9fa5-,，]{1,30}");
		Matcher m = p.matcher(address);
		boolean yesorno = m.matches();
		return yesorno;
	}

	/**
	 * 验证家庭电话，英文或者数字，长度不限
	 * 
	 * @param tel
	 * @return
	 */
	public boolean checkHomeTel(String tel) {
		if (tel == null) {
			return false;
		}
		Pattern p = Pattern.compile("^[0-9a-zA-Z\\(\\)\\-\\—]{1,30}$");
		Matcher m = p.matcher(tel);
		boolean yesorno = m.matches();
		return yesorno;

	}

	/**
	 * 验证健康情况，四个汉字以内
	 * 
	 * @param healthy
	 * @return
	 */
	public boolean checkHealthy(String healthy) {
		// if (healthy == null) {
		// return false;
		// }
		// Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,4}$");
		// Matcher m = p.matcher(healthy);
		// boolean yesorno = m.matches();
		// return yesorno;
		if (healthy != null && healthy.length() != 0
				&& (healthy.equals("健康") || healthy.equals("残疾"))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证培训地点，沈阳、大连、鞍山和Null
	 * 
	 * @param place
	 * @return
	 */
	public boolean checkPlace(String place) {
		List<cn.gov.jyw.pojo.place> list = placeService.getPlaceAll();
		boolean bool = false;
		for (cn.gov.jyw.pojo.place p : list) {
			if (p.getPlace().equals(place)) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	/**
	 * 验证是否同意更换培训地点，是、否或者Null
	 * 
	 * @param c
	 * @return
	 */
	public boolean checkChangePlace(String c) {
		if (c == null || c.length() == 0 || "是".equals(c) || "否".equals(c)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证申报专业
	 * 
	 * @return
	 */
	public boolean checkReport(String rp) {
		List<specialty_report> list = srept;
		for (specialty_report report : list) {
			if (report.getName().equals(rp)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证求职意向，10个汉字以内
	 * 
	 * @param in
	 * @return
	 */
	public boolean checkIntention(String in) {
//		if (in == null) {
//			return false;
//		}
//		Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,10}$");
//		Matcher m = p.matcher(in);
//		boolean yesorno = m.matches();
//		return yesorno;
		return true;
	}
	


	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}

	public SpecialtyService getSpecialtyService() {
		return specialtyService;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public PoliticsService getPoliticsService() {
		return politicsService;
	}

	public void setPoliticsService(PoliticsService politicsService) {
		this.politicsService = politicsService;
	}

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(
			SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public int getYearEnroll() {
		return yearEnroll;
	}

	public void setYearEnroll(int yearEnroll) {
		this.yearEnroll = yearEnroll;
	}

	public List getSrept() {
		return srept;
	}

	public void setSrept(List srept) {
		this.srept = srept;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

}
