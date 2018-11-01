//第一层控制 登录GSK访问控制
$(function() {				
		var Id;
		//判断是否有杠上开账号登录        查询session   login
		$('body').oneTime('0.05s',function(){


			$.ajax({
					type: "POST",
					url: "../restapi/user_login_return.action"
				}).done(function(temp) {			
					if (temp == "") {
					    /*swal("客户，您好！", "需要您先登录哟 :)", "error");*/
						swal({
							  title: "客户，您好!",
							  text: "需要您先登录杠上开账号哟:)",
							  type: "error",
							  showCancelButton: false,
							  confirmButtonColor: "#DD6B55",
							  confirmButtonText: "确定",
							  closeOnConfirm: false
							},function(){
								location.href = "login.html"
							}
						);
					} else {
						//res为用户信息
						var res = JSON.parse(temp);
						//给Id赋值  留给查询基本信息参数用
						Id = res.UserInfo.Id;
						//读取右上角客户姓名
						$("#username").html(res.UserInfo.Name);
						//读取用户信息资料
						$("#return_Name").html(res.UserInfo.Name);
						$("#return_UserType").html(res.UserInfo.UserType);
						$("#return_Telephone").html(res.UserInfo.Telephone);
						$("#return_EmailAddress").html(res.UserInfo.EmailAddress);
						//网表格中动态添加权限login_detail_Indices
						//时间转换格式
						for (var i = 0; i < res.UserInfo.Indices.length; i++) {
							//三目运算 判断是否大于现在的时间
							var temp =  new Date(res.UserInfo.Indices[i].ExpireDate).getTime() >=new Date().getTime()?"正常":"过期";
							$("#login_detail_Indices").append('<tr><td >' + res.UserInfo.Indices[i].IndexDisplayName + '</td><td>' + res.UserInfo.Indices[i].ExpireDate + '</td><td> ' + temp + '</td></tr>')
						}	
					} 
					}).fail(function(error){	
						swal("否有杠上开账号登录调用错误");					
				})		
			//判断是否有期货账号登录
			$.ajax({
				type: "POST",
				url: "../restapi/future_login_return.action"
			}).done(function(temp) {
				if (temp == "") {
					$("#future_login").html("期货账号登录");
				} else {
					$("#future_login").html("期货账号注销");
				}
			}).fail(function(error){	
				swal("是否有期货账号登录调用错误");	
			})	
		});
	})