package com.ruiying.gskweb.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class test2 {
	public static void main(String[] args) {
		/*String param = "{\"UserName\":\"ml888\",\"Password\":\"431818\",\"ClientType\":\"mobile\",\"DevId\":\"8792792349274323723947923\"}";
		System.out.println(param);*/
		String param = "{\"UserName\":\"ml888\",\"Password\":\"431818\",\"ClientType\":\"mobile\",\"DevId\":\"8792792349274323723947923\"}";
		 /*{"UserName":,"Password":,"ClientType":"mobile","DevId":"8792792349274323723947923"}*/
	System.out.println("-----传入值------" + param);
	URL connect;  
   StringBuffer data = new StringBuffer();  
   
   try {  
	/*   SSLContext sc = SSLContext.getInstance("SSL");
       sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
               new java.security.SecureRandom());

       URL console = new URL(url);
       HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
       conn.setSSLSocketFactory(sc.getSocketFactory());
       conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
       conn.setDoOutput(true);
       conn.connect();
       DataOutputStream out = new DataOutputStream(conn.getOutputStream());
       out.write(content.getBytes(charset));
       // 刷新、关闭
       out.flush();
       out.close();
       InputStream is = conn.getInputStream();
       if (is != null) {
           ByteArrayOutputStream outStream = new ByteArrayOutputStream();
           byte[] buffer = new byte[1024];
           int len = 0;
           while ((len = is.read(buffer)) != -1) {
               outStream.write(buffer, 0, len);
           }
           is.close();
           return outStream.toByteArray();
       }
       return null;*/
   } catch (Exception e) {  
       // TODO Auto-generated catch block  
       e.printStackTrace();  
   }  
   System.out.println(data.toString());
	}
}
