package cn.gov.jyw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		if (uri.equals("/stuenroll/jsp/backyard/login.jsp")
				|| uri.equals("/stuenroll/jsp/backyard/LoginAction!login.action")) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			String school = (String) session.getAttribute("school");
			if (school == null || school.length() == 0) {
				chain.doFilter(request, response);
			} else {
				// System.out.println(uri);
				String expUri = "/stuenroll/"
						+ (String) session.getAttribute("url");
				if (uri.indexOf("/stuenroll/jsp/backyard/ManageAction!") != -1
						|| uri.equals("/stuenroll/jsp/backyard/ClassInfoAction!selectClassInfoAjax.action")
						|| uri.equals("/stuenroll/jsp/backyard/ClassInfoAction!getTypeAndSchoolAjax.action")
						|| uri.equals("/stuenroll/jsp/backyard/StuRecordAction!index.action")
						|| uri.equals("/stuenroll/jsp/backyard/LoginAction!out.action")
						|| uri.equals("/stuenroll/jsp/backyard/StuEnrollAction!exportEnroll.action")
						|| uri.equals("/stuenroll/jsp/backyard/enroll_export.jsp")
						|| uri.equals("/stuenroll/jsp/backyard/ScheduleAction!index.action")
						|| expUri.endsWith(uri)
						|| uri.equals("/stuenroll/jsp/backyard/ScheduleAction!search.action")
						|| uri.equals("/stuenroll/jsp/backyard/ScheduleAction!schedule.action")
						|| uri.equals("/stuenroll/jsp/backyard/ScheduleAction!scheduleOne.action")
						|| uri.equals("/stuenroll/jsp/backyard/ScheduleAction!save.action")
						|| uri.equals("/stuenroll/jsp/backyard/ScheduleAction!export.action")
						|| uri.equals("/stuenroll/jsp/backyard/StopAction!stop.action")
						|| uri.equals("/stuenroll/jsp/backyard/StopAction!delete.action")
						|| uri.equals("/stuenroll/ajax/PidAjax")
						|| uri.equals("/stuenroll/jsp/backyard/StopAction!addStop.action")
						|| uri.equals("/stuenroll/jsp/backyard/StopAction!chartStop.action")
						|| uri.equals("/stuenroll/jsp/backyard/StopAction!exportExcel.action")

				) {
					chain.doFilter(request, response);
				} else {
					HttpServletResponse resp = (HttpServletResponse) response;
					resp.sendError(404);
				}
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
