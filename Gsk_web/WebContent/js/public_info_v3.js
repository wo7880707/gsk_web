//登录页 ： 1.读取公共信息  2.查看服务器是否开启  
$(function(){
	//先不用了，放每个方法效率更高
	//提供一个全局变量 查看服务器是否开启的标志  
	//var openorclose = "1";
//3、查询服务器支持的策略信息
		//先查询本地是否有md5值
		var param_strategys;
		if(localStorage.getItem("strategys_return")!= null){
 				param_strategys = {"strategyMd5":JSON.parse(localStorage.getItem("strategys_return")).strategyMd5};
 			}else {
 				//MD5值为空  强制更新
 				param_strategys = {"strategyMd5":""};
 			} 
		$.ajax({
			type: "POST",
			url: "../restapi_v3/qry_strategys_return.action",
			data:{"param":JSON.stringify(param_strategys)}
		}).done(function(temp) {			
			var res = JSON.parse(temp);
			if(res.retNo == "-1"){
				//跳到welcome
				//api这个没有res.RetInfo json串
				//location.href = "../welcome.html?" + res.retInfo.message ; 
				//location.href = "../welcome.html" ;
			}
			//如果查到值的MD5和保存在本地的相同 “不用更新” 不同 “强制更新存储Cookie”
			if(res.strategyMd5 != "" && res.strategyMd5 != null){
				//把json数据存到localStorage中
				localStorage.setItem("strategys_return",temp);
			}	
		}).fail(function(error){	
			swal("查询服务器支持的策略信息调用错误");
		})	

//10、查询合约列表（该服务器支持的所有合约，一般来说是主力和次主力）
		var param_contracts = {};
		$.ajax({
			type: "POST",
			url: "../restapi_v3/qry_contracts_return.action",
			data:{"param":JSON.stringify(param_contracts)}
		}).done(function(temp) {
			var res = JSON.parse(temp);
			if(res.retNo == "-1"){
				//跳到welcome
				//location.href = "../welcome.html?" + res.retInfo.message ; 
			}
			//把json数据存到cookies中
			localStorage.setItem("contracts_return",temp);	
		}).fail(function(error){	
			swal("查询合约列表调用错误");
		})	
		
//12、查询合约信息FutureCompanyMd5   存储了MD5 且没变动下 返回的还是原来的MD5值
		//先查询本地是否有md5值
		var param_instrument;
		if(localStorage.getItem("instrument_return")!= null){
 				param_instrument = {"instrumentMd5":JSON.parse(localStorage.getItem("instrument_return")).instrumentMd5};
 			}else {
 				//MD5值为空  强制更新
 				param_instrument = {"instrumentMd5":""};
 			} 
		$.ajax({
			type: "POST",
			url: "../restapi_v3/qry_instrument_return.action",
			data:{"param":JSON.stringify(param_instrument)}
		}).done(function(temp) {
			var res = JSON.parse(temp);
			if(res.retNo == "-1"){
				//跳到welcome
				//location.href = "../welcome.html?" + res.retInfo.message ; 
			}
			//如果查到值的MD5和保存在本地的相同 “不用更新” 不同 “强制更新存储Cookie”
			if(res.instrumentMd5 != "" && res.instrumentMd5 != null){
				//把json数据存到localStorage中
			localStorage.setItem("instrument_return",temp);
			}	
		}).fail(function(error){
			swal("查询合约信息调用错误");
		})	
//13、查询期货公司和交易服务器信息                 有MD5不变的话  返回的数据字段中 没有FutureCompanyMd5 
		//先查询本地是否有md5值
/*		var param_futures_company;
		if(localStorage.getItem("futures_company_return") != null){
 				param_futures_company = {"FutureCompanyMd5":JSON.parse(localStorage.getItem("futures_company_return")).FutureCompanyMd5}
 			}else {
 				//MD5值为空  强制更新
 				param_futures_company = {"FutureCompanyMd5":""};
 			} 
		$.ajax({
			type: "POST",
			url: "../restapi_v3/qry_futures_company_return.action",
			data:{"param":JSON.stringify(param_futures_company)}
		}).done(function(temp) {
			var res = JSON.parse(temp);
			if(res.RetNo == "-1"){
				//跳到wemcome
				location.href = "../welcome.html?" + res.retInfo.message ; 
			}
			//如果查到值的MD5和保存在本地的相同 “不用更新” 不同 “强制更新存储Cookie”
			if(res.FutureCompanyMd5 != "" && res.FutureCompanyMd5 != null){
				//把json数据存到localStorage中
				localStorage.setItem("futures_company_return",temp);
			}	
		}).fail(function(error){	
			swal("查询期货公司和交易服务器信息调用错误");
		})	*/
})
	
		