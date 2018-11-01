package com.ruiying.gskweb.test;

import org.junit.Test;


import gsk_des.DesCryptoUtils;
import gsk_des.GSKDes;

public class TestDes {
	@Test
	public void test() throws Exception{
		GSKDes g = new GSKDes();
		DesCryptoUtils des = new DesCryptoUtils();
		String param = "abc";
		//System.out.println(password);
		//编码
		byte[] a = des.encode(param);
		//解码
		byte[] b = des.decode(a);
		System.out.println("编码：：：" + new String(a));
		System.out.println("解码：：：" + new String(b));
	}
}
