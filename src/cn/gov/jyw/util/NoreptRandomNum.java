package cn.gov.jyw.util;

import java.util.HashMap;
import java.util.Map;

public class NoreptRandomNum {
	public static final int NORPT = -1;
	private int flag = 0;
	private static final int maxIndex = 973;
	private static int[] nums = new int[maxIndex];
	private static Map<String,NoreptRandomNum> map = new HashMap<String,NoreptRandomNum>();
	
	static{
		for(int i = 1 ; i < maxIndex + 1 ; i++){
			nums[i-1] = i;
		}
	}
	private NoreptRandomNum(){
		
	}
	public static NoreptRandomNum norptNumFactory(String userid){
		if(map.containsKey(userid))
			return map.get(userid);
		else{
			NoreptRandomNum ranNum = new NoreptRandomNum();
			map.put(userid, ranNum);
			return ranNum;
		}
	}
	public int getNoreptNum(){
		int nextIndex = (int) (Math.random()*(maxIndex - flag)+flag); 
		int temp = nums[nextIndex];
		nums[nextIndex] = nums[flag];
		nums[flag] = temp;
		flag++;
		if(flag == maxIndex)
//			throw new RuntimeException("flag用完了");
			return -1;
		return nums[flag - 1];
	}
}
