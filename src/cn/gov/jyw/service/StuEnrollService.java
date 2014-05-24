package cn.gov.jyw.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.aspectj.lang.annotation.AdviceName;

import cn.gov.jyw.bean.ExcelBean;
import cn.gov.jyw.dao.NationDAO;
import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.dao.UserDAO;
import cn.gov.jyw.pojo.class_info;
import cn.gov.jyw.pojo.nation;
import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.pojo.stu_enroll;

public class StuEnrollService {
	private NationDAO nationDAO;
	private StuEnrollDAO stuEnrollDAO;
	private UserDAO userDAO;
	private ExcelBean excelBean;

	public List<nation> getNationList() {
		return nationDAO.getAll();
	}

	public boolean addEnroll(stu_enroll enroll) throws Exception {
		return stuEnrollDAO.insertOne(enroll);

	}

	public stu_enroll selectStuEnrollByPid(String pid, String edu_school,String remark) {
		return stuEnrollDAO.selectStuEnrollByPid(pid, edu_school,remark);
	}

	public void updateStuEnrollClassNo(String classNo, String[] pid) throws Exception {
		stuEnrollDAO.updateStuEnrollClassNo(classNo, pid);
	}

	public String toPrint(String path, String pid, String floder) throws Exception {
		Connection conn = stuEnrollDAO.getDataSource().getConnection();
		File reportFile = new File(path);
		JasperReport jasperReport = null;
		jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		HashMap map = new HashMap();
		map.put("pid", pid);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
		String fileName = new Date().getTime() + ".pdf";
		String downPath = floder + "\\" + fileName;
		JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(downPath));
		conn.close();
		return fileName;
	}

	/**
	 * 申报记录总数
	 * 
	 * @return
	 */
	public long getEnrollCount(String school,String remark) {
		return stuEnrollDAO.selectCount(school, remark);
	}

	public List<Long> selectEnrollByClassNo(String class_no) {
		return this.stuEnrollDAO.selectEnrollByClassNo(class_no);
	}

	public void updateEnrollStuNo(String stu_no, long id) {
		this.stuEnrollDAO.updateEnrollStuNo(stu_no, id);
	}

	public List<specialty_report> selectEnrollSpec(String school) {
		return stuEnrollDAO.selectEnrollSpec(school);
	}

	public List<class_info> selectEnrollClass(String school, String type) {
		return stuEnrollDAO.selectEnrollClass(school, type);
	}

	/**
	 * 导出Excel表格
	 * 
	 * @param path
	 * @param classNo
	 * @return
	 * @throws Exception
	 */
	public String exportRecord(String path, String classNo,String eduSchool) throws Exception {
		List<stu_enroll> list = stuEnrollDAO.selectStuEnroll_7(classNo,eduSchool);
		if (list != null && list.size() > 0) {
			String out = excelBean.exportEnroll(path, list);
			return out;
		}
		else {
			return "none";
		}
	}

	// ////////////////////////////////////////////////////////////////

	public NationDAO getNationDAO() {
		return nationDAO;
	}

	public void setNationDAO(NationDAO nationDAO) {
		this.nationDAO = nationDAO;
	}

	public StuEnrollDAO getStuEnrollDAO() {
		return stuEnrollDAO;
	}

	public void setStuEnrollDAO(StuEnrollDAO stuEnrollDAO) {
		this.stuEnrollDAO = stuEnrollDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ExcelBean getExcelBean() {
		return excelBean;
	}

	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

}
