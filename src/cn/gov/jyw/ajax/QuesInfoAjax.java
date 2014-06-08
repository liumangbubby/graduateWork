package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import cn.gov.jyw.dao.ExamDao;
import cn.gov.jyw.service.OthersService;
import cn.gov.jyw.util.MessageBox;

import com.google.gson.Gson;

@WebServlet("/ajax/QuesInfoAjax")
public class QuesInfoAjax extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		ServletContext context = req.getServletContext();
		BeanFactory beanFactory = (BeanFactory) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		OthersService otherServ = (OthersService) beanFactory.getBean("OthersService");
		PrintWriter writer = resp.getWriter();
		MessageBox msg = new MessageBox();
		String json = null;
		Gson gson = new Gson();
		int total = otherServ.queryTotalExam();
		if(total > 0){
			msg.setStutas(MessageBox.SUCCESS);
			msg.setData(total);
		}else{
			msg.setStutas(MessageBox.ERROR);
			msg.setMsg("未知错误，查询试题总数是发生！");
		}
		writer.write(gson.toJson(msg));
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
