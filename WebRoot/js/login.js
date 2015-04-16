(function($){
	
	if(typeof $.mnladmin=="undefined"){
		$.mnladmin={};
	}
	if(typeof $.mnladmin.login=="undefined"){
		$.mnladmin.login={};
	}
	
	/**
	 * 用户登录
	 */
	$.mnladmin.login.userLogin = function(){
		var userName = $.trim($("#userName").val());
		if(userName.length==0){
			$("#errorDiv").html("请输入用户名").show();
			return;
		}
		var password = $.trim($("#password").val());
		if(password.length==0){
			$("#errorDiv").html("请输入密码").show();
			return;
		}
		$.ajax({
			url:"/login.html",
			type:"post",
			data:$("#loginForm").serialize(),
			success:function(response){
				if(response=="loginSuccess"){
					window.location.href = "/menu.html";
					return;
				}
				if(response=="accountError"){
					$("#errorDiv").html("无效的用户名");
				}else if(response=="passwordError"){
					$("#errorDiv").html("密码错误");
				}else if(response=="accountFreeze"){
					$("#errorDiv").html("账户被冻结");
				}else{
					$("#errorDiv").html("登录失败，请联系超级管理员");
				}
				$("#errorDiv").show();
			}
		});
	};
})(jQuery);
$(function(){
	$("#btn_login").click(function(){
		$.mnladmin.login.userLogin();
	});
	
	// 回车键事件  
	$(document).keypress(function(e) {  
       if(e.which == 13) {  
    	  $.mnladmin.login.userLogin();
       }  
   }); 
	
	$("#userName").on("focus",function(){
		$("#errorDiv").hide();
	});
	$("#password").on("focus",function(){
		$("#errorDiv").hide();
	});
});