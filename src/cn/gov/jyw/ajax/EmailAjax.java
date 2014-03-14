package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gov.jyw.bean.CheckBean;

public class EmailAjax extends HttpServlet {
	private CheckBean bean = new CheckBean();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		boolean ck = bean.checkEmail(email);
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();
		writer.write(ck + "");
		writer.flush();
		writer.close();
	}
}
