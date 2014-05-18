package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.group.RpcMessage.NoRpcChannelReply;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import cn.gov.jyw.dao.ExamDao;
import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.pojo.exam_choose;
import cn.gov.jyw.util.MessageBox;
import cn.gov.jyw.util.NoreptRandomNum;

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
		ServletContext context = req.getServletContext();
		BeanFactory beanFactory = (BeanFactory) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ExamDao examDao = (ExamDao) beanFactory.getBean("ExamDao");
		PrintWriter writer = resp.getWriter();
		MessageBox msg = new MessageBox();
		String json = null;
		Gson gson = new Gson();
		
		int id = Integer.parseInt(req.getParameter("ids"));
		String userid = req.getParameter("userid");
		if(id == NoreptRandomNum.NORPT){
			if(userid != null && userid.trim().length() > 0){
				NoreptRandomNum ranNum = NoreptRandomNum.norptNumFactory(userid);
				id = ranNum.getNoreptNum();
				List<Object[]> list = examDao.queryOneByOne(id);
				msg.setStutas(MessageBox.SUCCESS);
				msg.setData(list);
				json = gson.toJson(msg);
			}else{
				msg.setStutas(MessageBox.ERROR);
				msg.setMsg("userid为空");
				json = gson.toJson(msg);
			}
		}
		else{
			List<Object[]> list = examDao.queryOneByOne(id);
	//		List<exam_choose> e = examDao.listExamChoose(id);
			msg.setStutas(MessageBox.SUCCESS);
			msg.setData(list);
			json = gson.toJson(list);
		}
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
