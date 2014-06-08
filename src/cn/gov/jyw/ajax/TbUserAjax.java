package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import cn.gov.jyw.bean.CheckBean;
import cn.gov.jyw.dao.StuEnrollDAO;
import cn.gov.jyw.dao.TbUserDAO;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.tb_user;
import cn.gov.jyw.util.MessageBox;

/**
 * Servlet implementation class TbUserAjax
 */
@WebServlet("/ajax/TbUserAjax")
public class TbUserAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletConfig().getServletContext());
		StuEnrollDAO stuEnrollDAO = (StuEnrollDAO) ctx.getBean("StuEnrollDAO");
		TbUserDAO tbuserDAO = (TbUserDAO) ctx.getBean("tbuserDao");
		String userid = request.getParameter("userid");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		Writer writer = response.getWriter();
		Gson gson = new Gson();
		MessageBox msg = new MessageBox();
		tb_user userinfo = tbuserDAO.getPID(userid); 
		if(userinfo != null && userinfo.getEnrollId() > 0){
			stu_enroll stu = stuEnrollDAO.selectStuEnrollsById(userinfo.getEnrollId(), null);
			msg.setStutas(MessageBox.SUCCESS);
			msg.setData(stu);
		}else{
			msg.setStutas(MessageBox.ERROR);
			msg.setMsg("没有该用户的报名信息");
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
