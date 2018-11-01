//登录页 ： 1.读取公共信息  2.查看服务器是否开启  
$(function(){
	//先不用了，放每个方法效率更高
	//提供一个全局变量 查看服务器是否开启的标志  
	//var openorclose = "1";
//3、查询服务器支持的策略信息
		//先查询本地是否有md5值
		var param_strategys;
		if(localStorage.getItem("strategys_return")!= null){
 				param_strategys = {"StrategyMd5":JSON.parse(localStorage.getItem("strategys_return")).StrategyMd5};
 			}else {
 				//MD5值为空  强制更新
 				param_strategys = {"StrategyMd5":""};
 			} 
		$.ajax({
			type: "POST",
			url: "../restapi/qry_strategys_return.action",
			data:{"param":JSON.stringify(param_strategys)}
		}).done(function(temp) {			
			var res = JSON.parse(temp);
			if(res.RetNo == "-1"){
				//跳到wemcome
				//api这个没有res.RetInfo json串
				//location.href = "../welcome.html?" + res.RetInfo.Message ; 
				location.href = "../welcome.html" ;
			}
			//如果查到值的MD5和保存在本地的相同 “不用更新” 不同 “强制更新存储Cookie”
			if(res.StrategyMd5 != "" && res.StrategyMd5 != null){
				//把json数据存到localStorage中
				localStorage.setItem("strategys_return",temp);
			}	
			
		}).fail(function(error){	
			//swal("查询服务器支持的策略信息调用错误");
			swal("查询服务器支持的策略信息调用错误");
		})	

//10、查询合约列表（该服务器支持的所有合约，一般来说是主力和次主力）
		var param_contracts = {};
		$.ajax({
			type: "POST",
			url: "../restapi/qry_contracts_return.action",
			data:{"param":JSON.stringify(param_contracts)}
		}).done(function(temp) {
			var res = JSON.parse(temp);
			if(res.RetNo == "-1"){
				//跳到wemcome
				location.href = "../welcome.html?" + res.RetInfo.Message ; 
			}
			//把json数据存到cookies中
			localStorage.setItem("contracts_return",temp);
			//$.JSONCookie("contracts_return",res,{path:'/',expires:30})	
			
		}).fail(function(error){	
			//swal("查询合约列表调用错误");
			swal("查询合约列表调用错误");
		})	
		
//12、查询合约信息FutureCompanyMd5   存储了MD5 且没变动下 返回的还是原来的MD5值
		//先查询本地是否有md5值
		/*var param_instrument;
		if(localStorage.getItem("instrument_return")!= null){
 				param_instrument = {"InstrumentMd5":JSON.parse(localStorage.getItem("instrument_return")).InstrumentMd5};
 			}else {
 				//MD5值为空  强制更新
 				param_instrument = {"InstrumentMd5":""};
 			} */
		//20181031把MD5这个先注销了 因为10月31日这版需要强制更新下合约
		//组成合约名称和合约交易所对照表，之前客户浏览器中保存了MD5，可能会不更新  然后找不到对应的名称而显示错误
		var param_instrument = {"InstrumentMd5":""};
		$.ajax({
			type: "POST",
			url: "../restapi/qry_instrument_return.action",
			data:{"param":JSON.stringify(param_instrument)}
		}).done(function(temp) {
			var res = JSON.parse(temp);
			if(res.RetNo == "-1"){
				//跳到wemcome
				location.href = "../welcome.html?" + res.RetInfo.Message ; 
			}
			//如果查到值的MD5和保存在本地的相同 “不用更新” 不同 “强制更新存储Cookie”
			if(res.InstrumentMd5 != "" && res.InstrumentMd5 != null){
					//把json数据存到localStorage中
					localStorage.setItem("instrument_return",temp);
					//------提取出来的显示转换信息也保存	------	
					//交易所数组
					var exchangeInfo = res.ExchangeInfo;
					//交易所标号对应英文编码对象
					var futuresExchange = {};
					for (var i = 0; i < exchangeInfo.length; i++) {
						var exchangeInfoIndex = exchangeInfo[i].EXC.split(",");
						futuresExchange[exchangeInfoIndex[0]] = exchangeInfoIndex[3] ;
					}
					//合约数组
					var tradeTimeRange = res.TradeTimeRange;
					//合约代码和中文对照表
					var contracts_contrast = {};
					//合约代码和交易所英文编码对照表
					var contracts_exchange = {};
					//组成id数字和英文编码名称对象
					//循环合约数组赋值到需要的对象中
					for (var i = 0; i < tradeTimeRange.length; i++) {
						var tradeTimeRangeIndex = tradeTimeRange[i].Range.split(",");
						contracts_contrast[tradeTimeRangeIndex[0]] = tradeTimeRangeIndex[1];
						contracts_exchange[tradeTimeRangeIndex[0]] = futuresExchange[tradeTimeRangeIndex[tradeTimeRangeIndex.length - 2]];
					}
					localStorage.setItem("contracts_contrast",JSON.stringify(contracts_contrast));
					localStorage.setItem("contracts_exchange",JSON.stringify(contracts_exchange));
			}	
			
		}).fail(function(error){
			//swal("查询合约信息调用错误");
			swal("查询合约信息调用错误");
		})	
//13、查询期货公司和交易服务器信息                 有MD5不变的话  返回的数据字段中 没有FutureCompanyMd5 
		//先查询本地是否有md5值
		var param_futures_company;
		if(localStorage.getItem("futures_company_return") != null){
 				param_futures_company = {"FutureCompanyMd5":JSON.parse(localStorage.getItem("futures_company_return")).FutureCompanyMd5}
 			}else {
 				//MD5值为空  强制更新
 				param_futures_company = {"FutureCompanyMd5":""};
 			} 
		$.ajax({
			type: "POST",
			url: "../restapi/qry_futures_company_return.action",
			data:{"param":JSON.stringify(param_futures_company)}
		}).done(function(temp) {
			var res = JSON.parse(temp);
			if(res.RetNo == "-1"){
				//跳到wemcome
				location.href = "../welcome.html?" + res.RetInfo.Message ; 
			}
			//如果查到值的MD5和保存在本地的相同 “不用更新” 不同 “强制更新存储Cookie”
			if(res.FutureCompanyMd5 != "" && res.FutureCompanyMd5 != null){
				//把json数据存到localStorage中
				localStorage.setItem("futures_company_return",temp);
			}	
		}).fail(function(error){	
			//swal("查询期货公司和交易服务器信息调用错误");
			swal("查询期货公司和交易服务器信息调用错误");
		})	
})
	
		