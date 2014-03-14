package cn.gov.jyw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenEnrollFilter implements Filter {
	private ServletContext context;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String isOpenEnroll = (String) context.getAttribute("isOpenEnroll");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		if ("true".equals(isOpenEnroll) || "/stuenroll/jsp/enroll/notEnroll.jsp".equals(request.getRequestURI())) {
			// 开放报名
			chain.doFilter(req, res);
		}
		else {
			response.sendRedirect("/stuenroll/jsp/enroll/notEnroll.jsp");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
	}

}
