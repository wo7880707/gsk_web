package com.ruiying.gskweb.common;
import net.sf.json.JSONObject;
public class JsonUtils {
	
	//json字符串转成json对象
	public static JSONObject stringToJson (String str) {
		return JSONObject.fromObject(str);
	}
	//json对象转成json字符串
	public static String stringToJson (JSONObject json) {
		return json.toString();
	}
	//拼接 gskApiHost + gskApiPort
	public static String stringToGskApi (String str) {
		return (String)stringToJson(str).get("gskApiHost") + ":" + (String)stringToJson(str).get("gskApiPort") + "/";
	}
	//拼接 ip + port
	public static String stringToIpPort (String str) {
		return (String)stringToJson(str).get("ip") + ":" + (String)stringToJson(str).get("port") + "/";
	}
	//取到access_token
	public static String getAccessToken (String str) {
		JSONObject returnInfo = JsonUtils.stringToJson(str);
		JSONObject returnInfoRetInfo = JsonUtils.stringToJson(returnInfo.getString("retInfo"));
		return (String)returnInfoRetInfo.get("message");
	}
	
}
