package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import cn.gov.jyw.dao.ExamDao;
import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.pojo.exam_choose;

import com.google.gson.Gson;

public class ExamAjax extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		
		int id = Integer.parseInt(req.getParameter("ids"));
		
		ServletContext context = req.getServletContext();
		BeanFactory beanFactory = (BeanFactory) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ExamDao examDao = (ExamDao) beanFactory.getBean("ExamDao");
		List<Object[]> list = examDao.queryOneByOne(id);
//		List<exam_choose> e = examDao.listExamChoose(id); 
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter writer = resp.getWriter();
		System.out.println(json);
		writer.write(json);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
