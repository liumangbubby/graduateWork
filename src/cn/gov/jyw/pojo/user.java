package cn.gov.jyw.pojo;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class user {

	private int id;
	private String username;
	private String password;
	private String school;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}


}
