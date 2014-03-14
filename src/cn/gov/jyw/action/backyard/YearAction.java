package cn.gov.jyw.action.backyard;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.pojo.year;
import cn.gov.jyw.service.YearService;

public class YearAction {
	private YearService yearService;
	private String[] yearId;
	private String year;
	private String id;

	public String index() {
		List<year> yearList = yearService.getYearAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("yearList", yearList);
		return "success";
	}

	public String delete() {
		ArrayList list = new ArrayList();
		for (int i = 0; i < yearId.length; i++) {
			list.add(yearId[i]);
		}
		this.yearService.deleteYear(list);
		return index();
	}

	public String insert() {
		year y = new year();
		y.setActive(false);
		y.setYear(year);
		this.yearService.addYear(y);
		return index();
	}

	public String updateActiveYear() {
		this.yearService.updateActiveYear(Integer.parseInt(id));
		return index();
	}

	public String updateUnActiveYear() {
		this.yearService.updateUnActiveYear();
		return index();
	}

	// ////////////////////////////////////////////////////////////////
	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}

	public String[] getYearId() {
		return yearId;
	}

	public void setYearId(String[] yearId) {
		this.yearId = yearId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
