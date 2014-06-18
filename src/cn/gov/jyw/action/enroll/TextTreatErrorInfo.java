package cn.gov.jyw.action.enroll;

import cn.gov.jyw.service.TreatErrorInfoService;

import com.opensymphony.xwork2.ActionSupport;

public class TextTreatErrorInfo extends ActionSupport{
	private TreatErrorInfoService treatErrorServ;
	public String TreatError() throws Exception {
		// TODO Auto-generated method stub
		treatErrorServ.addTreatErrInfo();
		return null;
	}
	//getter setter
	public TreatErrorInfoService getTreatErrorServ() {
		return treatErrorServ;
	}
	public void setTreatErrorServ(TreatErrorInfoService treatErrorServ) {
		this.treatErrorServ = treatErrorServ;
	}
}
