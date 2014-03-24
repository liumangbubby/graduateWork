package cn.gov.jyw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 该类为验证用户是否登陆的过滤器类
 * 
 * @author 杨迪
 * 
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if ("/stuenroll/jsp/backyard/login_1.jsp".equals(request.getRequestURI())
				|| "/stuenroll/jsp/backyard/LoginAction!login.action".equals(request.getRequestURI())) {
			// 登陆界面，需要放行
			chain.doFilter(req, res);
			return;
		}
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null || username.length() == 0) {
			// 没有登陆
			String url = request.getRequestURL().toString();
			String temp[] = url.split("/stuenroll"); // 取出网站的域名和端口
			response.sendRedirect(temp[0] + "/stuenroll/jsp/backyard/login_1.jsp");

		} else {
			// 登陆成功
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
