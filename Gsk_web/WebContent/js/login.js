$(function(){
	
//写入期货公司和名称连接
/*$(document).on('click','#future_login',function(){*/
/*	$('#bb1').load("header_1.html");
	$('#bb2').load("header_2.html");
	var all_companys = JSON.parse(localStorage.getItem("futures_company_return")).ServerInfo;
	for (var j = 0; j < all_companys.length; j++) {
		$("#future_login_list").append('<li><a href = "login_trade_acc.html?Index='+all_companys[j].Index + '">' + all_companys[j].Title + '</a></li>"')
	}*/	
//第二层控制 登录期货公司
//托管点击事件
$(document).on('click','#hosting',function(){
	//原方法 判断字符不好
/*	if($("#future_login").html() == "期货账号登录") {
		//判断期货账户是否登录
			sweetAlert({
		  title: "客户,您好！",
		  text: "需要您先登录期货账号哟:)",
		  type: "warning",
		  
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  showCancelButton: true,
		  closeOnConfirm: false
		}, function(){
			location.href = "login_trade_acc.html";
		});
		$("#order_list").hide();
	}else{
		location.href = "add_strategy_orders.html";
	}*/
	//改进下
	$.ajax({
			type: "POST",
			url: "../restapi/future_login_return.action"
		}).done(function(temp) {
			if (temp == "") {
				sweetAlert({
					  title: "客户,您好！",
					  text: "需要您先登录期货账号哟:)",
					  type: "warning",
					  
					  confirmButtonColor: "#DD6B55",
					  confirmButtonText: "确定",
					  cancelButtonText: "取消",
					  showCancelButton: true,
					  closeOnConfirm: false
					}, function(){
						location.href = "login_trade_acc.html";
					});
					$("#order_list").hide();
			} else {
				location.href = "add_strategy_orders.html";
			}
		}).fail(function(error){	
			swal("是否有期货账号登录调用错误");	
		})	
})
//委托单状态状态点击事件
$(document).on('click','#statement',function(){
	//原方法 判断字符不好
/*	if($("#future_login").html() == "期货账号登录") {
		//判断期货账户是否登录
  			sweetAlert({
			  title: "客户,您好！",
			  text: "需要您先登录期货账号哟:)",
			  type: "warning",
			  
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  showCancelButton: true,
			  closeOnConfirm: false
			}, function(){
				location.href = "login_trade_acc.html";
			});

	}else{
		location.href="show_strategy_orders.html";
	}*/
		//改进下
	$.ajax({
			type: "POST",
			url: "../restapi/future_login_return.action"
		}).done(function(temp) {
			if (temp == "") {
				sweetAlert({
					  title: "客户,您好！",
					  text: "需要您先登录期货账号哟:)",
					  type: "warning",
					  
					  confirmButtonColor: "#DD6B55",
					  confirmButtonText: "确定",
					  cancelButtonText: "取消",
					  showCancelButton: true,
					  closeOnConfirm: false
					}, function(){
						location.href = "login_trade_acc.html";
					});
					$("#order_list").hide();
			} else {
				location.href = "show_strategy_orders.html";
			}
		}).fail(function(error){	
			swal("是否有期货账号登录调用错误");	
		})	
})
//期货账号登录点击事件
$(document).on('click','#future_login',function(){ 
	if($("#future_login").html() != "期货账号登录") {
		sweetAlert({
			  title: "客户,您好！",
			  text: "尊敬的客户，确定要退出交易账号登录!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: false
			}, function(){
				$.ajax({
					type: "POST",
					url: "../restapi/destroy_future_login_return.action"
				}).done(function(res) {
					//var res = JSON.parse(temp);
					if(res == "0") {
						//重新加载页面  或者跳转去主页面
						//window.reload();
						//手机页面就跳转
						var u = navigator.userAgent;
						var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
						var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
						var isiPad =  u.indexOf('iPad') > -1;
						/*alert('是否是Android：'+isAndroid);
						alert('是否是iOS：'+isiOS);*/
						if(isAndroid == true || isiOS == true || isiPad == true){
							//跳到手机平板界面
							location.href = "main_phone.html";
						}else {
							//跳到PC界面
							location.href = "main.html";
						}
					}
				})
			});
	}else{
		location.href="login_trade_acc.html";
	}	
	})

//退出账户out
$(document).on('click','#out',function(){
			sweetAlert({
				  title: "客户,您好！",
				  text: "尊敬的客户，确定要退出账号登录!",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  cancelButtonColor: "#DD6B55",
				  confirmButtonText: "确定",
				  cancelButtonText: "取消",
				  closeOnConfirm: false
		}, function(){
					$.ajax({
							type: "POST",
							url: "../restapi/destroy_login_return.action"
						}).done(function(res) {
							//var res = JSON.parse(temp);
							if(res == "0") {
								location.href = "login.html"
							}
						}).fail(function(error) {
							//swal("退出账户调用错误");
							alert("退出账户调用错误");
						})
					});
			/*var r = confirm("尊敬的客户，确定要退出杠上开系统!");
			if(r == true) {
				$.ajax({
					type: "POST",
					url: "../restapi/destroy_login_return.action"
				}).done(function(res) {
					//var res = JSON.parse(temp);
					if(res == "0") {
						location.href = "login.html"
					}
				}).fail(function(error) {
					//swal("退出账户调用错误");
					alert("退出账户调用错误");
				})
		}	*/
})
//基本信息显示
$(document).on('click','#accountdetail',function(){ 
    $('#aa').modal("show") ;
})
//基本信息显示  
$(document).on('click','#username_button',function(){ 
    $('#aa').modal("show") ;
})
})