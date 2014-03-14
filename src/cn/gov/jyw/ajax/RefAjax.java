package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.gov.jyw.dao.RefDAO;
import cn.gov.jyw.pojo.ref;

public class RefAjax extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		Writer writer=resp.getWriter();
		
		String school = req.getParameter("school");
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletConfig().getServletContext());
		RefDAO refDAO=(RefDAO) ctx.getBean("RefDAO");
		List<ref> list=refDAO.selectBySchool(school);
		StringBuffer buffer = new StringBuffer();
		for (ref one : list) {
			buffer.append("#");
			buffer.append(one.getSpec());
		}
		if (list != null && list.size() > 0) {
			buffer.deleteCharAt(0);
			writer.write(buffer.toString());
		}
		writer.flush();
		writer.close();
	}
}
