package com.ruiying.gskweb.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	public static String visitHttp(String urlWeb,String param) {
		//读取服务器返回的参数（json字符串）
		String buffer = null;  
	    try {  
        URL url = new URL(urlWeb);
        //创建http链接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //设置请求的方法类型
        httpURLConnection.setRequestMethod("POST");
       //设置请求的内容类型
       //httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");     
       httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
       //设置发送数据
       httpURLConnection.setDoOutput(true);
       //设置接受数据
       httpURLConnection.setDoInput(true);       
       //发送数据,使用输出流
       OutputStream outputStream = httpURLConnection.getOutputStream();
       //发送的soap协议的数据
       //String requestXmlString = requestXml("北京");   
       //String content = "user_id="+ URLEncoder.encode("13846", "gbk");       
       //发送数据
       if(null != param){
    	   outputStream.write(param.getBytes());   
       }
       //接收数据
       InputStream inputStream = httpURLConnection.getInputStream();  
       //定义字节数组
       byte[] b = new byte[1024];      
       //定义一个输出流存储接收到的数据
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();      
       //开始接收数据
       int len = 0;
       while (true) {
           len = inputStream.read(b);
           if (len == -1) {
               //数据读完
               break;
           }
           byteArrayOutputStream.write(b, 0, len);
       }
       //从输出流中获取读取到数据(服务端返回的)
       buffer = byteArrayOutputStream.toString();
	    }catch(Exception e){  
	    	/*logger.error("访问api异常：" + new Date().toString() + "/" + urlWeb + "/" + param  );*/
	    	e.printStackTrace();
	    }  
       return buffer;
	}
}
