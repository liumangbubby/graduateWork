package cn.gov.jyw.listener;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gov.jyw.bean.CheckBean;
import cn.gov.jyw.bean.Task;
import cn.gov.jyw.service.SpecialtyReportService;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.SystemService;
import cn.gov.jyw.service.UserService;

public class SystemListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		BeanFactory beanFactory = (BeanFactory) context
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		SystemService systemService = (SystemService) beanFactory
				.getBean("SystemService");
		String isOpenEnroll = systemService.selectIsOpenEnroll();
		String yearEnroll = systemService.selectYearEnroll();
		context.setAttribute("isOpenEnroll", isOpenEnroll);
		context.setAttribute("yearEnroll", yearEnroll);
		CheckBean checkBean = (CheckBean) beanFactory.getBean("CheckBean");
		checkBean.setYearEnroll(Integer.parseInt(yearEnroll));
		SpecialtyReportService specialtyReportService = (SpecialtyReportService) beanFactory
				.getBean("SpecialtyReportService");
		List list = specialtyReportService.getAll();
		checkBean.setSrept(list);

		UserService userService = (UserService) beanFactory
				.getBean("UserService");
		userService.addHasAdmin();

		String path = context.getRealPath("/");

		context.setAttribute("jasperPath", path
				+ "WEB-INF\\classes\\cn\\gov\\jyw\\ireport\\report2.jasper"); // jasper文件地址
		context.setAttribute("root", path); // 网站根路径
		context.setAttribute("pdfFloder", path + "\\pdf"); // PDF存放地址

//		System.out.println(path + "pdf");
//
//		Task task = (Task) beanFactory.getBean("Task");
//		task.setFloder(path + "pdf");
//		// 一天的毫秒数
//		//long daySpan = 24 * 60 * 60 * 1000;
//		long daySpan = 10* 1000;
//		// 规定的每天时间02:30:30运行
//		final SimpleDateFormat sdf = new SimpleDateFormat(
//				"yyyy-MM-dd '22:50:00'");
//		// 首次运行时间
//		Date startTime;
//		try {
//			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
//			// 如果今天的已经过了 首次运行时间就改为明天
//			if (System.currentTimeMillis() > startTime.getTime()){
//				startTime = new Date(startTime.getTime() + daySpan);
//			}
//			Timer t = new Timer();
//			t.scheduleAtFixedRate(task, startTime, daySpan);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
