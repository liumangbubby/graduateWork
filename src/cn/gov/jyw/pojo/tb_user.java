package cn.gov.jyw.pojo;

import java.util.ArrayList;
import java.util.List;

public class tb_user {
	private int userId;
	private String userName;
	private String paseWord;
	private long enrollId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPaseWord() {
		return paseWord;
	}
	public void setPaseWord(String paseWord) {
		this.paseWord = paseWord;
	}
	public long getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(long enrollId) {
		this.enrollId = enrollId;
	}
}
