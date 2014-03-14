package cn.gov.jyw.service.web;

import javax.jws.WebService;

@WebService
public interface Login {
	public boolean login(String username, String password);
}
