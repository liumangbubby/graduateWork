package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gov.jyw.bean.IDCard;

public class BirthdayAjax extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String pid = req.getParameter("pid");
		String birthday = IDCard.getBirthday(pid);
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();
		writer.write(birthday);
		writer.flush();
		writer.close();
	}
}
