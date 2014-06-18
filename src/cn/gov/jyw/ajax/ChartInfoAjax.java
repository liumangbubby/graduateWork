package cn.gov.jyw.ajax;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gov.jyw.util.MessageBox;

import com.google.gson.Gson;

/**
 * Servlet implementation class ChartInfoAjax
 * 预留为获得chart数据
 */
@WebServlet("/ajax/ChartInfoAjax")
public class ChartInfoAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		int userid = Integer.parseInt(request.getParameter("userid"));
		Writer writer = response.getWriter();
		Gson gson = new Gson();
		MessageBox msg = new MessageBox();
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
