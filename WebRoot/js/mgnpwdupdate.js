(function($){
	
	if(typeof $.mnlmgnpwdupdate == "undefined"){
		$.mnlmgnpwdupdate = {};
	}
	
	if(typeof $.mnlmgnpwdupdate.mgnpwdupdate == "undefined"){
		$.mnlmgnpwdupdate.mgnpwdupdate = {};
	}
	
	/** 旧密码错误信息验证 */
	$.mnlmgnpwdupdate.mgnpwdupdate.oldMgnPwdErrorFun = function(){
		if($.trim($("#oldMgnPwd").val()).length <= 0){
			$("#MgnPwdUpdateErrorInfo").show();
			$("#MgnPwdUpdateErrorInfo").html("旧密码不能为空。。。");
			return false;
		}
		$("#MgnPwdUpdateErrorInfo").hide();
		return true;
	}
	
	/** 新密码错误信息验证 */
	$.mnlmgnpwdupdate.mgnpwdupdate.newMgnPwdErrorFun = function(){
		if($.trim($("#newMgnPwd").val()).length <= 0){
			$("#MgnPwdUpdateErrorInfo").show();
			$("#MgnPwdUpdateErrorInfo").html("更改的新密码不能为空。。。");
			return false;
		}
		$("#MgnPwdUpdateErrorInfo").hide();
		return true;
	}
	
	/** 确认密码错误信息验证 */
	$.mnlmgnpwdupdate.mgnpwdupdate.affirmNewMgnPwd = function(){
		if($.trim($("#affirmNewMgnPwd").val()).length <= 0){
			$("#MgnPwdUpdateErrorInfo").show();
			$("#MgnPwdUpdateErrorInfo").html("确认密码不能为空。。。");
			return false;
		}
		if($.trim($("#affirmNewMgnPwd").val()) != $.trim($("#newMgnPwd").val())){
			$("#MgnPwdUpdateErrorInfo").show();
			$("#MgnPwdUpdateErrorInfo").html("两次密码不一致。。。");
			return false;
		}
		$("#MgnPwdUpdateErrorInfo").hide();
		return true;
	}
	
})(jQuery);
$(function(){
	
	/** 当旧密码失去焦点时调用旧密码错误信息验证 */
	$("#oldMgnPwd").blur(function(){
		$.mnlmgnpwdupdate.mgnpwdupdate.oldMgnPwdErrorFun();
	});
	
	/** 当旧密码失去焦点时调用旧密码错误信息验证 */
	$("#newMgnPwd").blur(function(){
		$.mnlmgnpwdupdate.mgnpwdupdate.newMgnPwdErrorFun();
	});
	
	/** 当旧密码失去焦点时调用旧密码错误信息验证 */
	$("#affirmNewMgnPwd").blur(function(){
		$.mnlmgnpwdupdate.mgnpwdupdate.affirmNewMgnPwd();
	});
	
	/** 管理员密码更改提交 */
	$("#managerPasswordUpdateSubmit").click(function(){
		if($.mnlmgnpwdupdate.mgnpwdupdate.oldMgnPwdErrorFun() && $.mnlmgnpwdupdate.mgnpwdupdate.newMgnPwdErrorFun() && $.mnlmgnpwdupdate.mgnpwdupdate.affirmNewMgnPwd()){
			$.ajax({
				url:"/mgnpwdupdate/managerPasswordUpdate.html",
				type:"post",
				data:$("#managerPasswordUpdateFrom").serialize(),
				success:function(response){
					if(response == "mgnPwdNonexistence"){	//管理员密码不存在
						$("#MgnPwdUpdateErrorInfo").show();
						$("#MgnPwdUpdateErrorInfo").html("管理员密码输入错误。");
						return false;
					}
					if(response == "mgnPwdUpdateError"){	//管理员密码更改失败
						$("#MgnPwdUpdateErrorInfo").show();
						$("#MgnPwdUpdateErrorInfo").html("管理员密码更改失败，请联系管理员。");
						return false;
					}
					if(response == "mgnPwdUpdateSuccess"){	//管理员密码更改成功
						alert("管理员密码更改成功。。。");
						location.href = "/menu.html";
					}
				}
			});
		}
	});
	
});