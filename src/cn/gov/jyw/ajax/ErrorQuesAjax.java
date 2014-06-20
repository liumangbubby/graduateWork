package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.gov.jyw.dao.ExamErrorDAO;
import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.pojo.exam_error;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.util.MessageBox;

import com.google.gson.Gson;

/**
 * Servlet implementation class TbUserAjax
 */
@WebServlet("/ajax/ExamErrorAjax")
public class ErrorQuesAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletConfig().getServletContext());
		StuEnrollDAO stuEnrollDAO = (StuEnrollDAO) ctx.getBean("StuEnrollDAO");
		ExamErrorDAO errorDao = (ExamErrorDAO) ctx.getBean("errorDao");
		String userid = request.getParameter("userid");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		Writer writer = response.getWriter();
		Gson gson = new Gson();
		MessageBox msg = new MessageBox();
		List<exam_error> list = errorDao.queryExamErrorByUser(Integer.parseInt(userid)); 
		if(list != null && list.size() > 0){
			msg.setStutas(MessageBox.SUCCESS);
			msg.setData(list);
		}else{
			msg.setStutas(MessageBox.ERROR);
			msg.setMsg("没有该用户的错题信息");
		}
		String json = gson.toJson(msg);
		writer.write(json);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
