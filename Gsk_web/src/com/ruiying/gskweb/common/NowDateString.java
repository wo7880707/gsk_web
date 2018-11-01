package com.ruiying.gskweb.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NowDateString {

	public static String nowDate() { 
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		// new Date()为获取当前系统时间
		return df.format(new Date());
	}


}
