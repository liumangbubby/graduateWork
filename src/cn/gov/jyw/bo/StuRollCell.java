package cn.gov.jyw.bo;

import cn.gov.jyw.pojo.stu_enroll;

public class StuRollCell {
	private int row;
	private stu_enroll enroll;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public stu_enroll getEnroll() {
		return enroll;
	}

	public void setEnroll(stu_enroll enroll) {
		this.enroll = enroll;
	}

	public StuRollCell(int row, stu_enroll enroll) {
		super();
		this.row = row;
		this.enroll = enroll;
	}

}
