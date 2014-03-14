package cn.gov.jyw.service.web;

import java.util.List;

import javax.jws.WebService;

import cn.gov.jyw.pojo.specialty_report;

@WebService
public interface SpecialtyReport {
	public List<specialty_report> getAll();
}
