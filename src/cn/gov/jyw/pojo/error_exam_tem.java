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
	//equals hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exam_id;
		result = prime * result + user_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		error_exam_tem other = (error_exam_tem) obj;
		if (exam_id != other.exam_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
}
