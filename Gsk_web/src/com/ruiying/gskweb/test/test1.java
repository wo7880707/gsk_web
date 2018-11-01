package com.ruiying.gskweb.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class test1 {
	public static void main(String[] args) {
		/*String param = "{\"UserName\":\"ml888\",\"Password\":\"431818\",\"ClientType\":\"mobile\",\"DevId\":\"8792792349274323723947923\"}";
		System.out.println(param);*/
		String param = "{\"UserName\":\"ml888\",\"Password\":\"431818\",\"ClientType\":\"mobile\",\"DevId\":\"8792792349274323723947923\"}";
		 /*{"UserName":,"Password":,"ClientType":"mobile","DevId":"8792792349274323723947923"}*/
	System.out.println("-----传入值------" + param);
	URL connect;  
   StringBuffer data = new StringBuffer();  
   
   try {  
       connect = new URL("https://www.dlruiying.com:8443/api/services/gsk/user/login");  
       HttpURLConnection connection = (HttpURLConnection)connect.openConnection();  
       connection.setRequestMethod("POST");  
       connection.setDoOutput(true);  
        
       OutputStreamWriter paramout = new OutputStreamWriter(  
               connection.getOutputStream(),"UTF-8");  
       paramout.write(param);  
       paramout.flush();  
 
       BufferedReader reader = new BufferedReader(new InputStreamReader(  
               connection.getInputStream(), "gb2312"));  
       String line ;
       while ((line = reader.readLine()) != null) {          
           data.append(line);            
       }  
       System.out.println("-----返回值------" + line);
       paramout.close();  
       reader.close();  
   } catch (Exception e) {  
       // TODO Auto-generated catch block  
       e.printStackTrace();  
   }  
   System.out.println(data.toString());
	}
}
