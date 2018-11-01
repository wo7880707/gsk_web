package com.ruiying.gskweb.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class test_code {
	/*public static void main(String[] args) throws Exception {
		 //请求的webservice的url
		          URL url = new URL("http://www.gskwin.com:10102/user/forgetPassword2?telephone=15041116120&verificationCode=1234");
		          //创建http链接
		          HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		      
		          //设置请求的方法类型
		          httpURLConnection.setRequestMethod("POST");
		          
		         //设置请求的内容类型
		         httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		         
		         //设置发送数据
		         httpURLConnection.setDoOutput(true);
		         //设置接受数据
		         httpURLConnection.setDoInput(true);
		         
		         //发送数据,使用输出流
		         OutputStream outputStream = httpURLConnection.getOutputStream();
		         //发送的soap协议的数据
		         //String requestXmlString = requestXml("北京");
		     
		         String content = "user_id="+ URLEncoder.encode("13846", "gbk");
		         
		         //发送数据
		         outputStream.write(content.getBytes());
		     
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
		         String response = byteArrayOutputStream.toString();
		         
		         System.out.println(response);
	}*/
	public static String a(String telephone,String code) throws Exception{
				// 短信发送规则
				//String content = " 【杠上开】您好，您的验证码为:" + erificationCode;
				// 组合短信发送内容
				StringBuffer sb = new StringBuffer("http://www.gskwin.com:10102/user/forgetPassword2/?");
				sb.append("telephone=" + telephone);
				sb.append("&verificationCode=" + code );
				System.out.println("url---------------" + sb.toString());
				//sb.append("&mobile=" + phoneNumber + "&encode=utf8");
				//sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
				// 创建url对象 并发送短信
				URL url = new URL(sb.toString());
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				// 返回发送结果‘100’ 发送成功
				String result = in.readLine();
				System.out.println("返回值---------------" + result);
				String back = "a";
				if(result.indexOf("验证成功") != -1){
					back = code;
				}
				return back;
	}
	public static void main(String[] args) throws Exception {
	/*	StringBuffer sb = new StringBuffer("http://www.gskwin.com:10102//user/forgetPasswordSendVerificationCode?mobile=15041116120");
		System.out.println("url---------------" + sb.toString());
		//sb.append("&mobile=" + phoneNumber + "&encode=utf8");
		//sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
		// 创建url对象 并发送短信
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		// 返回发送结果‘100’ 发送成功
		String result = in.readLine();
		System.out.println("返回值---------------" + result);*/

		
		
		for (int i = 8950; i <= 9999; i++) {
			String b = "";
			try {
				b = a("15041116120",i + "");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("循环出问题");
			}
			if(!(b.equals("a"))){
				System.out.println("验证码为：" +  b);
				break;
			}
			
		}
	}
}
