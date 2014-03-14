package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

public class CaptAjax extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		resp.setCharacterEncoding("utf-8");
		Writer writer = resp.getWriter();

		req.setCharacterEncoding("UTF-8"); // Do this so we can capture
											// non-Latin chars
		String answer = req.getParameter("answer");
		if (answer!=null&&captcha.isCorrect(answer)) {
			writer.write("true");
		}
		else {
			writer.write("false");
		}
		writer.flush();
		writer.close();
	}
}
