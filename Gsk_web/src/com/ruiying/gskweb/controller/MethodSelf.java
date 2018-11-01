package com.ruiying.gskweb.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiying.gskweb.common.VisitRestApi;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping("/self")
public class MethodSelf {
	/**
     * 实现自有方法的api
     * @throws Exception
     */

	// szq:1.获取手机序列号
	@RequestMapping("/get_mobile_serialno.action")
	public @ResponseBody String getMobileSerialno(String param, HttpServletRequest request) {
		String serial = "123abc";       
        try {           
            /*Class<?> c = Class.forName("android.os.SystemProperties");      
            Method get = c.getMethod("get", String.class);     
            serial = (String) get.invoke(c, "ro.serialno");    
            System.out.println(serial);  */
            }   
        catch (Exception ignored) {    
              
        }   
        return serial;  
	}
	//szq：2.查看当前在线人数
	@RequestMapping("/online_nums.action")
	public @ResponseBody int onlineNums(HttpServletRequest request) {
		int num = 10 ;       
        try {   
        	//HashSet set = (HashSet)application.getAttribute("sessions");
        	num = ((HashSet)(request.getSession().getServletContext().getAttribute("sessions"))).size();
            }   
        catch (Exception ignored) {    
              
        }   
        return num;  
	}	
	//szq：3.策略信息
		@RequestMapping("/strategy_list.action")
		public @ResponseBody String strategyList(String param,HttpServletRequest request) {
			String serial = null;       
	        try {           
	        	serial = (String)request.getSession().getServletContext().getAttribute("qry_strategys");
	            }   
	        catch (Exception ignored) {         
	        }   
	        return serial;  
		}
		//szq：4.合约信息
		@RequestMapping("/contract_list.action")
		public @ResponseBody String contractList(String param,HttpServletRequest request) {
			String serial = null;       
	        try {           
	        	serial = (String)request.getSession().getServletContext().getAttribute("qry_instrument");
	            }   
	        catch (Exception ignored) {    
	              
	        }   
	        return serial;  
		}
		//szq：4.合约信息
		@RequestMapping("/getMAC.action")
		public @ResponseBody String getMAC(HttpServletRequest request) {
			String macAddress = "";
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
			      byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
			          .getHardwareAddress();
			      // 下面代码是把mac地址拼装成String
			      StringBuilder sb = new StringBuilder();
			      for (int i = 0; i < mac.length; i++) {
			        if (i != 0) {
			          sb.append("-");
			        }
			        // mac[i] & 0xFF 是为了把byte转化为正整数
			        String s = Integer.toHexString(mac[i] & 0xFF);
			        sb.append(s.length() == 1 ? 0 + s : s);
			      }
			      // 把字符串所有小写字母改为大写成为正规的mac地址并返回
			      macAddress = sb.toString().trim().toUpperCase();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		    return macAddress;
		}
		
}
