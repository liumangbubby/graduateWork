package cn.gov.jyw.pojo;

public class error_exam_tem {
	private int tem_id;
	private int user_id;
	private int exam_id;
	//constructors
	public error_exam_tem() {
		super();
	}
	public error_exam_tem(int user_id, int exam_id) {
		super();
		this.user_id = user_id;
		this.exam_id = exam_id;
	}
	//getter setter
	public int getTem_id() {
		return tem_id;
	}
	public void setTem_id(int tem_id) {
		this.tem_id = tem_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	//toString
	@Override
	public String toString() {
		return "error_exam_tem [tem_id=" + tem_id + ", user_id=" + user_id
				+ ", exam_id=" + exam_id + "]";
	}
}
