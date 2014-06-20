package cn.gov.jyw.pojo;

import java.util.LinkedList;
import java.util.List;

public class exam {
	private int exam_id;
	private String exam_no;
	private String exam_type;
	private String exam_mappingno;
	private String exam_body;
	private String body_img;
	private int exam_class;
	private String answer_info;
	private List<exam_choose> chooses = new LinkedList<exam_choose>();
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public String getExam_no() {
		return exam_no;
	}
	public void setExam_no(String exam_no) {
		this.exam_no = exam_no;
	}
	public String getExam_type() {
		return exam_type;
	}
	public void setExam_type(String exam_type) {
		this.exam_type = exam_type;
	}
	public String getExam_mappingno() {
		return exam_mappingno;
	}
	public void setExam_mappingno(String exam_mappingno) {
		this.exam_mappingno = exam_mappingno;
	}
	public String getExam_body() {
		return exam_body;
	}
	public void setExam_body(String exam_body) {
		this.exam_body = exam_body;
	}
	public String getBody_img() {
		return body_img;
	}
	public void setBody_img(String body_img) {
		this.body_img = body_img;
	}
	public List<exam_choose> getChooses() {
		return chooses;
	}
	public void setChooses(List<exam_choose> chooses) {
		this.chooses = chooses;
	}
	public String getAnswer_info() {
		return answer_info;
	}
	public void setAnswer_info(String answer_info) {
		this.answer_info = answer_info;
	}
	public int getExam_class() {
		return exam_class;
	}
	public void setExam_class(int exam_class) {
		this.exam_class = exam_class;
	}
	@Override
	public String toString() {
		return "exam [exam_id=" + exam_id + ", exam_no=" + exam_no
				+ ", exam_type=" + exam_type + ", exam_mappingno="
				+ exam_mappingno + ", exam_body=" + exam_body + ", body_img="
				+ body_img + ", answer_info=" + answer_info + ", chooses="
				+ chooses + "]";
	}
}
