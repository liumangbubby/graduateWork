package cn.gov.jyw.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegMatch {
	// 表达式对象
	Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,*}$");

	public boolean checkName(String name) {
		// 创建 Matcher 对象
		Matcher m = p.matcher("your string");
		// 是否完全匹配
		boolean yesorno = m.matches();
		return yesorno;
	}
}
