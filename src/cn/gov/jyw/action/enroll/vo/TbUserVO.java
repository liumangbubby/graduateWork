package cn.gov.jyw.action.enroll.vo;

import com.opensymphony.xwork2.ModelDriven;

public class TbUserVO{
	private String username;
	private String password;
	private String pid;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}
