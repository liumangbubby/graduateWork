package cn.gov.jyw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gov.jyw.pojo.exam;
import cn.gov.jyw.service.ExamService;

public class ExportPaper {
	public static final int A1 = 1;
	public static final int A2 = 2;
	public static final int A3 = 3;
	public static final int B1 = 4;
	public static final int B2 = 5;
	public static final int C1 = 6;
	public static final int C2 = 7;
	public static final int C3 = 8;
	public static final int C4 = 9;
	public static final int C5 = 10;
	//////////////////////////////////
	private ExamService examServ;
	
	public List<Map> exportByType(int typeId){
		List<Map> examlist = new ArrayList<Map>();
		NoreptRandomNum random = NoreptRandomNum.norptNumFactory(String.valueOf((Math.random()*100)));
		for(int i = 0 ; i < 100 ; i++){
			Map map = new HashMap();
			map.put("id", random.getNoreptNum());
			map.put("examclass", ExportPaper.C1);
			List<Object[]> e = examServ.queryOneExam(map);
			Map mapb = new HashMap();
			mapb.put("bean", e);
			examlist.add(mapb);
		}
		return examlist;
	}
	
	////////////////////////////////////////
	public ExamService getExamServ() {
		return examServ;
	}
	public void setExamServ(ExamService examServ) {
		this.examServ = examServ;
	}
}
