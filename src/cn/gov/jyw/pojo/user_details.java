package cn.gov.jyw.pojo;

import java.sql.Timestamp;

public class user_details {
	private int id;
	private int user_id;
	private Timestamp savedate;
	private int ques_no;
	private int type;
	//constructor
	public user_details() {
		super();
	}
	public user_details(int user_id, Timestamp savedate, int ques_no, int type) {
		super();
		this.user_id = user_id;
		this.savedate = savedate;
		this.ques_no = ques_no;
		this.type = type;
	}
	//getter setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Timestamp getSavedate() {
		return savedate;
	}
	public void setSavedate(Timestamp savedate) {
		this.savedate = savedate;
	}
	public int getQues_no() {
		return ques_no;
	}
	public void setQues_no(int ques_no) {
		this.ques_no = ques_no;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	//toString
	@Override
	public String toString() {
		return "user_details [id=" + id + ", user_id=" + user_id
				+ ", savedate=" + savedate + ", ques_no=" + ques_no + ", type="
				+ type + "]";
	}
}
