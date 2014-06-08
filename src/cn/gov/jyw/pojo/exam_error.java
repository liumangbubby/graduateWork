package cn.gov.jyw.pojo;

public class exam_error {
	private int error_id;
	private int user_id;
	private int exam_id;
	private int error_time;
	private int review_time;
	//constructors
	public exam_error(int user_id, int exam_id, int error_time, int review_time) {
		super();
		this.user_id = user_id;
		this.exam_id = exam_id;
		this.error_time = error_time;
		this.review_time = review_time;
	}
	public exam_error(int user_id, int exam_id) {
		super();
		this.user_id = user_id;
		this.exam_id = exam_id;
	}
	public exam_error() {
		super();
	}
	//getter setter
	public int getError_id() {
		return error_id;
	}
	public void setError_id(int error_id) {
		this.error_id = error_id;
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
	public int getError_time() {
		return error_time;
	}
	public void setError_time(int error_time) {
		this.error_time = error_time;
	}
	public int getReview_time() {
		return review_time;
	}
	public void setReview_time(int review_time) {
		this.review_time = review_time;
	}
	//toString
	@Override
	public String toString() {
		return "exam_error [error_id=" + error_id + ", user_id=" + user_id
				+ ", exam_id=" + exam_id + ", error_time=" + error_time
				+ ", review_time=" + review_time + "]";
	}
}
