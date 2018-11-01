package com.ruiying.gskweb.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.CyclicBarrier;

public class Test6 {

		public static void main(String[] args) throws Exception {
			// 同样先初始化Properties类  
	        // 初始化之后在内存就出现一个保存key-value对的properties对象  
	        Properties properties = new Properties();  
	        // 之后设置三个属性，可以与HashMap的put方法做类比，同样是在操作key-value对  
	        // 故意设置一个中文的value来说明*.ini，*.properties与*.xml之间对中文的支持  
	      /*  properties.setProperty("username", "aaa");  
	        properties.setProperty("chinese", "中文");  
	        properties.setProperty("password", "123");  
	        // 此时，内存中的properties对象便有三个key-value对  
	        // 利用store方法与storeToXML方法把内存中的properties对象记录的属性保存到磁盘中的配置文件  
	        // 方法仅支持文件输出流对象，单单一个文件对象File是行不通的  
	        // 第二个参数是此配置文件的注释  
	        properties.store(new FileOutputStream("api.ini"), "中文");  
	        properties.store(new FileOutputStream("api.properties"), "comment");  
	        properties.storeToXML(new FileOutputStream("api.xml"), "注释");  
	        // 清空properties对象  
	        properties = new Properties();  
	        // 再把c:\a.ini里面存放的属性读取进来  
	        properties.load(new FileInputStream("api.ini"));  
	        // 修改password属性的值变成456  
	        properties.put("password", "456");  
	        // 打印此时内存中的properties对象  
	        System.out.println(properties);  
	        // 如下方法，可以取得内存中的properties对象中的username的属性值  
	        System.out.println(properties.getProperty("username"));  
	        // 再清空properties对象，读取c:\a.xml配置文件所存储的属性，注意读*.xml方法是不同  
*/	        properties = new Properties();  
	        properties.loadFromXML(new FileInputStream("api.xml"));  
	        // 打印内存中的属性  
	        System.out.println(properties);  
	        System.out.println(properties.getProperty("url"));
	    }

}
