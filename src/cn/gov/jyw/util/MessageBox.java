package cn.gov.jyw.util;

public class MessageBox {
	public static final int SUCCESS = 100;
	public static final int ERROR = 500;
	private int stutas;
	private String msg;
	private Object data;
	public int getStutas() {
		return stutas;
	}
	public void setStutas(int stutas) {
		this.stutas = stutas;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public MessageBox() {
		super();
	}
	public MessageBox(int stutas, String msg) {
		super();
		this.stutas = stutas;
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "MessageBox [stutas=" + stutas + ", msg=" + msg + ", data="
				+ data + "]";
	}
}
