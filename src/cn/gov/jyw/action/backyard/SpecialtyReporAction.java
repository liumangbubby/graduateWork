package cn.gov.jyw.action.backyard;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.gov.jyw.service.SpecialtyReportService;

/**
 * 申报专业Action
 * 
 * @author admin
 * 
 */
public class SpecialtyReporAction {
	private SpecialtyReportService specialtyReportService;
	private String[] specialtyReportId;
	private String name;

	public String index() {
		List list = specialtyReportService.getAll();
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("rpList", list);
		return "success";
	}

	public String delete() {
		if(specialtyReportId==null||specialtyReportId.length==0){
			return "error";
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < specialtyReportId.length; i++) {
			list.add(Integer.parseInt(specialtyReportId[i]));
		}
		specialtyReportService.deleteSpecialty(list);
		return index();
	}

	public String insert() {
		if(name==null||name.length()==0){
			return "error";
		}
		specialtyReportService.addSpecialty(name);
		name = null;
		return index();
	}

	// ///////////////////////////////////////////////////////////////////////////////

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(
			SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

	public String[] getSpecialtyReportId() {
		return specialtyReportId;
	}

	public void setSpecialtyReportId(String[] specialtyReportId) {
		this.specialtyReportId = specialtyReportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
