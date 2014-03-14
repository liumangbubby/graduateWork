package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.gov.jyw.bean.CheckBean;
import cn.gov.jyw.dao.StuEnrollDAO;

public class PidAjax extends HttpServlet {
	private CheckBean bean = new CheckBean();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletConfig().getServletContext());
		StuEnrollDAO stuEnrollDAO = (StuEnrollDAO) ctx.getBean("StuEnrollDAO");
		String pid = req.getParameter("pid");
		boolean ck = bean.checkPid(pid);
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();

		if (ck) {
			// 查询身份证是否在数据库中存在

			boolean rs = stuEnrollDAO.havePid(pid);
			if (rs == true) {

				writer.write("havePid");
			}
			else {

				writer.write(ck + "");
			}
		}
		else {
			writer.write(ck + "");

		}

		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		boolean ck = bean.checkPid(pid);
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();
		writer.write(ck + "");
		writer.close();
	}

}
