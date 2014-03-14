package cn.gov.jyw.bean;

import java.io.File;
import java.util.TimerTask;

import cn.gov.jyw.service.StuEnrollService;

public class Task extends TimerTask {
	private String floder;

	@Override
	public void run() {
		File file=new File(floder);
		File[] files=file.listFiles();
		System.out.println(files.length);
		for (int i = 0; i < files.length; i++) {
			files[i].delete();
		}
	}

	public String getFloder() {
		return floder;
	}

	public void setFloder(String floder) {
		this.floder = floder;
	}

}
