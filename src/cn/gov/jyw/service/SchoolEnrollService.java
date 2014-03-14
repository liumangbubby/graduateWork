package cn.gov.jyw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import cn.gov.jyw.bean.ExcelBean;
import cn.gov.jyw.bo.StuRollCell;
import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.pojo.stu_enroll;

public class SchoolEnrollService {
	private StuEnrollDAO stuEnrollDAO;
	private ExcelBean excelBean;

	public boolean addEnroll(String path) {
		boolean isError = false;
		ArrayList<StuRollCell> list = null;

		try {
			HashMap map = excelBean.readFile(path);
			list = (ArrayList<StuRollCell>) map.get("list");// 读取Excel文件中的报名信息
			int code = (Integer) map.get("code");
			HashSet<Integer> set=(HashSet<Integer>) map.get("errorRows");
			if (code == 200) {
				isError = true;
			}
			if (list != null && list.size() > 0) {

				for (StuRollCell cell : list) {
					boolean bool = stuEnrollDAO.havePid(cell.getEnroll().getPid()); // 判断是不是存在重复身份PID
					if (bool != true) {
						boolean rs = stuEnrollDAO.insertOne(cell.getEnroll()); // rs保存是否插入成功
						if (rs == false) {
							set.add(cell.getRow()); // 记录行号
						}
					}
					else {
						set.add(cell.getRow()); // 记录行号
					}

				}

				if (set.size() > 0) {
					excelBean.saveError(path, new ArrayList(set)); // 标记背景色
					isError = true;
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			isError = true;
		}
		return isError;
	}

	public StuEnrollDAO getStuEnrollDAO() {
		return stuEnrollDAO;
	}

	public void setStuEnrollDAO(StuEnrollDAO stuEnrollDAO) {
		this.stuEnrollDAO = stuEnrollDAO;
	}

	public ExcelBean getExcelBean() {
		return excelBean;
	}

	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

}
