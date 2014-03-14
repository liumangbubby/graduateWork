package cn.gov.jyw.service.web;

import javax.jws.WebService;

import cn.gov.jyw.service.LoginService;
import cn.gov.jyw.service.UserService;

@WebService(endpointInterface = "cn.gov.jyw.service.web.Login", serviceName = "LoginImpl")
public class LoginImpl implements Login {
	private LoginService loginService;

	@Override
	public boolean login(String username, String password) {
		boolean bool=loginService.login(username, password);
		return bool;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}



}
