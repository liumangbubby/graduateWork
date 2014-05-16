package cn.gov.jyw.pojo;

public class exam_choose {
	private int choose_id;
	private int exam_id;
	private String choose_body;
	private int right_answer_flag;
	private String choose_img;
	public int getChoose_id() {
		return choose_id;
	}
	public void setChoose_id(int choose_id) {
		this.choose_id = choose_id;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public String getChoose_body() {
		return choose_body;
	}
	public void setChoose_body(String choose_body) {
		this.choose_body = choose_body;
	}
	public int getRight_answer_flag() {
		return right_answer_flag;
	}
	public void setRight_answer_flag(int right_answer_flag) {
		this.right_answer_flag = right_answer_flag;
	}
	public String getChoose_img() {
		return choose_img;
	}
	public void setChoose_img(String choose_img) {
		this.choose_img = choose_img;
	}
	@Override
	public String toString() {
		return "exam_choose [choose_id=" + choose_id + ", exam_id=" + exam_id
				+ ", choose_body=" + choose_body + ", right_answer_flag="
				+ right_answer_flag + ", choose_img=" + choose_img + "]";
	}
}
