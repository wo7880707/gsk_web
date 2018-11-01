package com.ruiying.gskweb.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.ruiying.gskweb.common.SslUtils;
import com.ruiying.gskweb.common.VisitRestApi;



public class test3_connection_url {
	/*private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }*/
		static class MyX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}

	}
	public static void main(String[] args) {
		//String param = "{\"StrategyMd5\":\"45edadca6c8d780126713b10a807d740\"}";
		//String param = "{\"UserName\":\"ml888\",\"Password\":\"431818\",\"ClientType\":\"mobile\",\"DevId\":\"8792792349274323723947923\"}";
		//String param = "{\"UserId\":\"14\",\"TradeAcc\":\"ml888\",\"TradePwd\":\"431818\",\"BrokerID\":\"8016\",\"LastIndex\":\"10\"}";
		String param = "{\"strategyMd5\":\"\"}";
		//适应三期的测试 json类型
		//System.out.println(VisitRestApi.visit("https://192.168.0.202:9443/api/services/gsk/public/qry_strategys", param));
		StringBuffer buffer=null;  
	    try{  
	    //创建SSLContext  
	    SSLContext sslContext=SSLContext.getInstance("SSL");  
	    //外部要有一个MyX509TrustManager类 实现X509TrustManager
	    TrustManager[] tm={new MyX509TrustManager()};  
	    //初始化  
	    sslContext.init(null, tm, new java.security.SecureRandom());;  
	    //获取SSLSocketFactory对象  
	    SSLSocketFactory ssf=sslContext.getSocketFactory();  
	    //URL url=new URL("https://www.dlruiying.com:8443/api/services/gsk/user/qry_base_info"); 
	   // URL url=new URL("https://124.93.26.73:28443/api/services/gsk/user/qry_base_info");
	    //URL url=new URL("https://124.93.26.73:28443/api/services/gsk/public/qry_strategys");
	    URL url=new URL("https://192.168.0.202:9443/api/services/gsk/public/qry_strategys");
	    //URL url=new URL("https://www.dlruiying.com:8443/api/services/gsk/public/qry_quotesnapshot");
	    //忽略ssl问题  关键
	    if("https".equalsIgnoreCase(url.getProtocol())){
	    	   SslUtils.ignoreSsl();
	    	}
	    HttpsURLConnection conn=(HttpsURLConnection)url.openConnection(); 
	    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    conn.setDoOutput(true);  
	    conn.setDoInput(true);  
	    conn.setUseCaches(false);  
	    conn.setRequestMethod("POST");  
	    //设置输入,输出,缓存,请求方式的内容
	    
	    //设置当前实例使用的SSLSoctetFactory  
	    conn.setSSLSocketFactory(ssf);  
	    conn.connect();  
	    //往服务器端写内容  
	    if(null!=param){  
	        OutputStream os=conn.getOutputStream();  
	        os.write(param.getBytes("utf-8"));  
	        os.close();  
	    }  
	      
	    //读取服务器端返回的内容  
	    InputStream is=conn.getInputStream();  
	    InputStreamReader isr=new InputStreamReader(is,"utf-8");  
	    BufferedReader br=new BufferedReader(isr);  
	    buffer=new StringBuffer();  
	    String line=null;  
	    while((line=br.readLine())!=null){  
	        buffer.append(line);  
	    }  
	    }catch(Exception e){  
	        e.printStackTrace();  
	    }  
	    System.out.println(buffer.toString());
	}
}
