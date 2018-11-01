package com.ruiying.gskweb.test;

import java.util.UUID;

import org.junit.Test;

public class test5 {
	@Test
	public void test(){
		//String token = CodecUtil.createUUID();
		for(int i=0;i<10;i++){
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(uuid);
			}
	}
}
