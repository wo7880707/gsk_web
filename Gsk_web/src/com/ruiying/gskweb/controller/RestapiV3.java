package com.ruiying.gskweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiying.gskweb.common.HttpUtils;
import com.ruiying.gskweb.common.JsonUtils;
import com.ruiying.gskweb.common.NowDateString;
import com.ruiying.gskweb.common.VisitRestApiV3;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/restapi_v3")
public class RestapiV3 {
	static Logger logger = Logger.getLogger("gskWeb");
	//配置文件得到的值
	@Value("${url}")
	private String urlAll ;
	/**
	 * restapi:1、登录
	 * @param param
	 * @param request
	 * @return
	 */ 
	// 杠上开账户登录
	@RequestMapping("user_login.action")
	public @ResponseBody String userLogin(String param, String paramFront,HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---杠上开账户登录传入值param------" + param);
		//logger.info(NowDateString.nowDate() + "---杠上开账户登录传入值param1------" + paramFront);
		String data = HttpUtils.visitHttp(JsonUtils.stringToGskApi(paramFront) + "api/services/gsk/user/login", param);
		logger.info(NowDateString.nowDate() + "-----杠上开账户登录返回值------" + data);
		//判断返回值retNo:0  retInfo.errNo:0
		JSONObject returnInfo = JsonUtils.stringToJson(data);
		JSONObject returnInfoRetInfo = JsonUtils.stringToJson(returnInfo.getString("retInfo"));
		// 前端返回值 0： 成功
		if (returnInfo.get("retNo").equals("0") && returnInfoRetInfo.get("errNo").equals("0")) {
			// 保存登录时候返回的参数
			request.getSession().setAttribute("userLoginV3", data);
			// 保存登录的front信息
			request.getSession().setAttribute("userLoginFrontInfoV3", paramFront);
		}
		return data;
	}
	/**
	 * restapi:1、登录  密码加密备用
	 * @param param
	 * @param request
	 * @return
	 */ 
	// 杠上开账户登录
	/*@RequestMapping("user_login.action")
	public @ResponseBody int userLogin1(String UserName, String Password,String ClientType,String DevID,HttpServletRequest request) {
		//给密码编码
		DesCryptoUtils des = new DesCryptoUtils();
		//编码
		byte[] password = des.encode(Password);
		//设置访问API参数
		String param = "";
		//组合json字符串
		JSONObject json_all = new JSONObject();
		json_all.put("UserName", UserName);
		json_all.put("Password", password);
		json_all.put("ClientType", ClientType);
		json_all.put("DevID", DevID);
		param = json_all.toString();
		logger.info(NowDateString.nowDate() + "---杠上开账户登录传入值------" + param);
		//String url = "https://www.dlruiying.com:8443/api/services/gsk/user/login";
		String url = urlAll +"api/services/gsk/user/login";
		String data = VisitRestApiV3.visit(url, param);
		logger.info(NowDateString.nowDate() + "-----杠上开账户登录返回值------" + data);
		String temp = data.split("\"")[3];
		logger.info("这个值" + temp);
		// 前端返回值 0： 成功
		int back = 0;
		if (temp.equals("0")) {
			// 创建session 返回前端0
			request.getSession().setAttribute("userLogin", data);
		}
		else {
			back = -1;
		}
		return back;
	}*/
	/**
	 * restapi:1、登录
	 * @param param
	 * @param request
	 * @return
	 */ 
	// 1.是否有杠上开账户登录 2.取出login session 的api返回值
	@RequestMapping("/user_login_return.action")
	public @ResponseBody String userLoginReturn(HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "进入user_login_return");
		String back = (String) request.getSession().getAttribute("userLoginV3");
		//logger.info(NowDateString.nowDate() + "-----是否有杠上开账户登录返回值------" + back);
		return back;
	}
	/**
	 * restapi:1、登录
	 * @param param
	 * @param request
	 * @return
	 */ 
	// 注销所有登录的session(包括gsk账号 和 期货账号)
	@RequestMapping("/destroy_login_return.action")
	public @ResponseBody int destroyLoginReturn(HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "退出所有登录");
		//删除所有session
		request.getSession().invalidate();
		return 0;
	}
	/**
	 * restapi:2、查询基本信息
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_base_info_return.action")
	public @ResponseBody String qryBaseInfoReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---查询基本信息传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
				     "api/services/gsk/user/qry_base_info?access_token=" + 
				     JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3"));
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----查询基本信息返回值------" + data);
		return data;
	}
	/**
	 * restapi:3、查询服务器支持的策略信息
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_strategys_return.action")
	public @ResponseBody String qryStrategysReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---服务器支持的策略传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) +
					 "api/services/gsk/public/qry_strategys?access_token=" +
					 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3"));
		//logger.info("url" + url);
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----服务器支持的策略返回值------" + data);
		return data;
	}
	/**
	 * restapi:4、查询全部合约行情快照信息
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_quotesnapshot_return.action")
	public @ResponseBody String qryQuotesnapshotReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---全部合约行情快照传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
				 	 "api/services/gsk/public/qry_quotesnapshot?access_token=" + 
				 	 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3")); 
		//转成json
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----全部合约行情快照返回值------" + data);
		return data;
	}
	/**
	 * restapi:5、查询某一合约行情
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_quote_return.action")
	public @ResponseBody String qryQuoteReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---某一合约行情传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
			 	 	 "api/services/gsk/public/qry_quote?access_token=" + 
			 	 	 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3")); 
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----某一合约行情返回值------" + data);
		return data;
	}
	/**
	 * restapi:6、查询指标信号
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_index_return.action")
	public @ResponseBody String qryIndexReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---查询指标信号传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
			 	 	 "api/services/gsk/public/qry_index?access_token=" + 
			 	 	 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3"));
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----查询指标信号返回值------" + data);
		return data;
	}
	/**
	 * restapi:7、查询策略单（已经委托的策略单
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_strategy_orders_return.action")
	public @ResponseBody String qryStrategyOrdersReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---查询策略单传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
					 "api/services/gsk/user/qry_strategy_orders?access_token=" + 
					 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3"));
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----查询策略单返回值------" + data);
		return data;
	}
	/**
	 * restapi:8、报入策略单委托（新建策略单）
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/insert_strategy_orders_return.action")
	public @ResponseBody String insertStrategyOrders_return(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---报入策略单委托传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
				 	 "api/services/gsk/user/insert_strategy_orders?access_token=" + 
				 	 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3")); 
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----报入策略单委托返回值------" + data);
		return data;
	}
	/**
	 * restapi:9、策略单操作（已经委托的策略单）
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/strategy_order_opt_return.action")
	public @ResponseBody String strategyOrderOptReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---策略单操作传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
					 "api/services/gsk/user/strategy_order_opt?access_token=" + 
					 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3"));
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----策略单操作返回值------" + data);
		return data;
	}
	/**
	 * restapi:10、查询合约列表（该服务器支持的所有合约，一般来说是主力和次主力）
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_contracts_return.action")
	public @ResponseBody String qryContractsReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---查询合约列表传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) +
				 	 "api/services/gsk/public/qry_contracts?access_token=" +
				 	 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3")); 
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----查询合约列表返回值------" + data);
		return data;
	}
	/**
	 * restapi:11、期货交易账号登录
	 * @param param         
	 * @param request            2018.4.4  根据api返回值 需要在前端显示api返回的错误信息  
	 *                           20 21 继续每秒查询一次  有 ”ok“ 成功 剩下全是跳出循环 
	 * @return
	 */ 
	//{"RetNo":"0","RetInfo":{"ErrNo":"26","Message":"非法交易时段，不允许登录交易服务器"}}
	@RequestMapping("/future_login.action")
	public @ResponseBody String futureLogin(String param, HttpServletRequest request) {
		logger.info(NowDateString.nowDate() + "---期货账号登录传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
					 "api/services/gsk/user/trade_acc_login?access_token=" + 
					 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3"));
		//循环1s 查询一次  Thread.currentThread().sleep(1000);
		//back   0 成功     1  失败
		int back = 1;
		String data = null;
		//计数  如果一直这么循环登录10次 还是没登录进去 就返回 3 返回前端  
		int num = 0;
		while(true){
			num ++;
			try {
			data = VisitRestApiV3.visit(url, param);
			logger.info(NowDateString.nowDate() + "期货账号登录返回值数据是:::" + data );
			//有”ok“ ：成功  param 存储 跳出循环
			if(data.indexOf("OK") != -1){
				//前端返回值为0 同时
				back = 0;
				// 删除session
				//request.getSession().removeAttribute("futureLogin");
				// 创建session 返回前端0
				request.getSession().setAttribute("futureLoginV3", param);
				break;
			//没有 ”20“或者”21“ 跳出循环 前端显示
			}else if(!(data.indexOf("20") != -1 || data.indexOf("21") != -1 )){
				back = 2;
				//跳出循环
				break;
			}else if(num >= 15){
			//查询大约10次还在一直查询  就跳出 自己定义一个json字符串
				data = "{\"RetNo\":\"连接超时请重新登录\"}";
				//跳出循环 
				break;
			}
			//休眠1s 接着循环查询
			Thread.currentThread().sleep(1000);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
	/**
	 * restapi:11、期货交易账号登录
	 * @param param
	 * @param request
	 * @return
	 */ 
	// 1.是否有期货账户登录 2.取出login session 的api返回值
	@RequestMapping("/future_login_return.action")
	public @ResponseBody String futureLoginReturn(HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "进入future_login_return");
		String back = (String) request.getSession().getAttribute("futureLoginV3");
		//logger.info(NowDateString.nowDate() + "-----是否有期货账户登录返回值------" + back);
		return back;
	}
	/**
	 * // restapi:11、期货交易账号注销
	 * @param param
	 * @param request
	 * @return
	 */ 
	// 注销期货登录的session
	@RequestMapping("/destroy_future_login_return.action")
	public @ResponseBody int destroyFutureLoginReturn(HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "destroy_future_login_return");
		// 单单的把这个期货账号的session删除
		request.getSession().removeAttribute("futureLoginV3");
		return 0;
	}
	/**
	 * restapi:12、查询合约信息
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_instrument_return.action")
	public @ResponseBody String qryInstrumentReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---查询合约信息传入值------" + param);
		String url = JsonUtils.stringToIpPort((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
				 	 "api/services/gsk/public/qry_instrument?access_token=" + 
				 	 JsonUtils.getAccessToken((String)request.getSession().getAttribute("userLoginV3")); 
		String data = VisitRestApiV3.visit(url, param);
		//logger.info(NowDateString.nowDate() + "-----查询合约信息返回值------" + data);
		return data;
	}
	/**
	 * restapi:13、查询期货公司和交易服务器信息
	 * @param param
	 * @param request
	 * @return
	 */ 
	@RequestMapping("/qry_futures_company_return.action")
	public @ResponseBody String qryFuturesCompanyReturn(String param, HttpServletRequest request) {
		//logger.info(NowDateString.nowDate() + "---期货公司和交易服务器信息传入值------" + param);
		String url = JsonUtils.stringToGskApi((String)request.getSession().getAttribute("userLoginFrontInfoV3")) + 
					 "api/services/gsk/public/qry_futures_company";
		String data = HttpUtils.visitHttp(url, param);
		//logger.info(NowDateString.nowDate() + "-----期货公司和交易服务器信息返回值------" + data);
		return data;
	}
}
