package com.ruiying.gskweb.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;



public class VisitRestApiV3 {
	static Logger logger = Logger.getLogger("gskWeb");
	public static String visit(String urlWeb,String param) {
		//读取服务器返回的参数（json字符串）
		StringBuffer buffer = null;  
	    try {  
	    URL url = new URL(urlWeb); 
	    if("https".equalsIgnoreCase(url.getProtocol())){
	    	//需要信任的证书
	    	//SslUtils.trustAllHttpsCertificates();
	    	//忽略证书信任
	    	 SslUtils.ignoreSsl();	    	   
	    	}
	    //与远程URL对象连接
	    HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();  
	    //设置输入,输出,缓存,请求方式的内容
	    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    conn.setDoOutput(true); 
	    conn.setDoInput(true); 
	    conn.setUseCaches(false);  
	    conn.setRequestMethod("POST");    
	    conn.connect();  
	    //往服务器端写内容  
	    if(null != param){  
	        OutputStream os = conn.getOutputStream();  
	        os.write(param.getBytes("utf-8"));  
	        os.close();  
	    }     
	    //读取服务器端返回的内容  
	    InputStream is = conn.getInputStream();  
	    InputStreamReader isr = new InputStreamReader(is,"utf-8");  
	    BufferedReader br = new BufferedReader(isr);  
	    buffer = new StringBuffer();  
	    String line = null;  
	    while((line = br.readLine()) != null){  
	        buffer.append(line);  
	    }  
	    br.close();
	    isr.close();
	    is.close();
	    }catch(Exception e){  
	    	
	    	/*logger.error("访问api异常：" + new Date().toString() + "/" + urlWeb + "/" + param  );*/
	    	e.printStackTrace();
	    }  
		return buffer.toString();
	}
}
