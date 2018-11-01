package com.ruiying.gskweb.test;



import java.util.HashSet;

import org.junit.Test;

public class test4 {
	@Test
	public void test(){
		String param ="{\"UserName\":\"2545\",\"Password\":\"431818\",\"ClientType\":\"mobile\",\"DevId\":\"8792792349274323723947923\"}";
		String param1 ="{\"RetNo\":\"请重新登陆\"}";
		//String param ='{\"UserName\":\"2545\",\"Password\":\"431818\"}';
		/*String param ="{\"RetNo\":\"\",\"Strategys\":[{\"Description\":\"待补充\",\"Name\":\"GH02\",\"Property\":\"17\",\"StrategyId\":\"5\"},{\"Description\":\"待补充\",\"Name\":\"GH02\",\"Property\":\"17\",\"StrategyId\":\"5\"}]}";
		JSONObject  jsonarray = JSONObject.fromObject(param);
		System.out.println(jsonarray);
		System.err.println(jsonarray.get("RetNo").equals(""));
		System.out.println("-------"+jsonarray.get("RetNo") + "dsada");*/
		/*HashSet set = new HashSet();
		System.out.println(set.size());*/
		System.out.println("a1");
		System.out.println("a2");
		System.out.println(param1);
	}
}
