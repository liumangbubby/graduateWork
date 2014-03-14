package cn.gov.jyw.action.backyard;

import java.util.ArrayList;

import cn.gov.jyw.pojo.ref;
import cn.gov.jyw.service.RefService;

public class RefAction {
	private RefService refService;
	private String school;
	private String[] spec;

	public RefService getRefService() {
		return refService;
	}

	public void setRefService(RefService refService) {
		this.refService = refService;
	}

	public String addRef() {
		ArrayList list = new ArrayList();
		for (int i = 0; i < spec.length; i++) {
			ref ref = new ref();
			ref.setSchool(school);
			ref.setSpec(spec[i]);
			list.add(ref);
		}
		refService.addRef(list,school);
		return "success";

	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setSpec(String[] spec) {
		this.spec = spec;
	}

	public String[] getSpec() {
		return spec;
	}

}
