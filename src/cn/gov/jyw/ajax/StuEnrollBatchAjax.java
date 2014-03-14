package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import cn.gov.jyw.dao.ClassInfoDAO;
import cn.gov.jyw.dao.EduSchoolDAO;
import cn.gov.jyw.dao.RefDAO;
import cn.gov.jyw.pojo.edu_school;

public class StuEnrollBatchAjax extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");

		Integer typeId = Integer.parseInt(req.getParameter("typeId"));

		String t = req.getParameter("t");
		ServletContext context = req.getServletContext();
		BeanFactory beanFactory = (BeanFactory) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ClassInfoDAO classInfoDAO = (ClassInfoDAO) beanFactory.getBean("ClassInfoDAO");
		RefDAO refDAO = (RefDAO) beanFactory.getBean("RefDAO");
		EduSchoolDAO eduSchoolDAO=(EduSchoolDAO) beanFactory.getBean("EduSchoolDAO");
		PrintWriter writer = resp.getWriter();

		if ("edu".equals(t)) {
			List<edu_school> list = eduSchoolDAO.selectRefEdu(typeId);
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				edu_school one = list.get(i);
				buffer = buffer.append(one.getSchool() + "@" + one.getId());
				buffer.append("#");
			}
			if (list != null && list.size() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
				writer.write(buffer.toString());
			}
		}
		if ("class".equals(t)) {
			Integer eduId = Integer.parseInt(req.getParameter("eduId"));
			String archives = req.getParameter("archives");
			List<HashMap> list = classInfoDAO.selectRefClass(typeId, eduId, archives);
			StringBuffer buffer = new StringBuffer();
			for (HashMap map : list) {
				buffer = buffer.append(map.get("name") + "@" + map.get("id"));
				buffer.append("#");
			}
			if (list != null && list.size() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
				writer.write(buffer.toString());
			}

		}
		if("specName".equals(t)){
			//按照专业的名称查询学校列表
			String spec=req.getParameter("spec");
			List<edu_school> list = eduSchoolDAO.selectRefEduBySpec(spec);
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				edu_school one = list.get(i);
				buffer = buffer.append(one.getSchool());
				buffer.append("#");
			}
			if (list != null && list.size() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
				writer.write(buffer.toString());
			}
		}
		writer.flush();
		writer.close();

	}

}
