package cn.gov.jyw.service.web;

import java.util.List;

import javax.jws.WebService;

import cn.gov.jyw.pojo.specialty_report;
import cn.gov.jyw.service.SpecialtyReportService;

@WebService(endpointInterface = "cn.gov.jyw.service.web.SpecialtyReport", serviceName = "SpecialtyReportImpl")
public class SpecialtyReportImpl implements SpecialtyReport {
	private SpecialtyReportService specialtyReportService;

	@Override
	public List<specialty_report> getAll() {
		return specialtyReportService.getAll();
	}

	public SpecialtyReportService getSpecialtyReportService() {
		return specialtyReportService;
	}

	public void setSpecialtyReportService(
			SpecialtyReportService specialtyReportService) {
		this.specialtyReportService = specialtyReportService;
	}

}
