(function($) {
	if(typeof $.mnladmin=="undefined"){
		$.mnladmin={};
	}
	if(typeof $.mnladmin.adminmgn=="undefined"){
		$.mnladmin.adminmgn={};
	}

	/**
	 * 显示名 （新建管理员账号）
	 */
	$.mnladmin.adminmgn.reg_nickNameFun = function() {
		if ($.trim($("#nickName").val()).length <= 0) {
			$("#nickNameError").html("<br/>错误信息提示：显示名不能为空...");
			$("#nickNameError").css("color", "red");
			return false;
		}
		$("#nickNameError").html("<br/>信息提示：√");
		$("#nickNameError").css("color", "green");
		return true;
	}

	/**
	 * 登录名 （新建管理员账号）
	 */
	$.mnladmin.adminmgn.reg_userNameFun = function() {
		if ($.trim($("#userName").val()).length <= 0) {
			$("#userNameError").html("错误信息提示：登录名不能为空...");
			$("#userNameError").css("color", "red");
			return false;
		}
		$("#userNameError").html("信息提示：√");
		$("#userNameError").css("color", "green");
		return true;
	}

	/**
	 * 密码 （新建管理员账号）
	 */
	$.mnladmin.adminmgn.reg_passWordFun = function() {
		if ($.trim($("#passWord").val()).length <= 0) {
			$("#passWordError").html("错误信息提示：密码不能为空...");
			$("#passWordError").css("color", "red");
			return false;
		}
		$("#passWordError").html("信息提示：√");
		$("#passWordError").css("color", "green");
		return true;
	}

	/**
	 * 重复密码 （新建管理员账号）
	 */
	$.mnladmin.adminmgn.reg_checkPassWordFun = function() {
		if ($.trim($("#checkPassWord").val()).length <= 0) {
			$("#checkPassWordError").html("错误信息提示：重复密码不能为空...");
			$("#checkPassWordError").css("color", "red");
			return false;
		}
		if ($.trim($("#checkPassWord").val())!= $("#passWord").val()) {
			$("#checkPassWordError").html("错误信息提示：两次密码不一致...");
			$("#checkPassWordError").css("color", "red");
			return false;
		}
		$("#checkPassWordError").html("信息提示：√");
		$("#checkPassWordError").css("color", "green");
		return true;
	}
	
	/**
	 * 权限名称 （添加新权限功能）
	 */
	$.mnladmin.adminmgn.permissionNameFun=function(){
		if($.trim($("#permissionName").val()).length<=0){
			$("#addPermissionsError").show();
			$("#addPermissionsError").html("错误提示：权限名称不能为空....");
			return false;
		}
		$("#addPermissionsError").hide();
		return true;
	}
	
	/**
	 * 权限action （添加新权限功能）
	 */
	$.mnladmin.adminmgn.permissionAction=function(){
		if($.trim($("#permissionAction").val()).length<=0){
			$("#addPermissionsError").show();
			$("#addPermissionsError").html("错误提示：权限action不能为空....");
			return false;
		}
		$("#addPermissionsError").hide();
		return true;
	}
	
	/**
	 * 权限url （添加新权限功能）
	 */
	$.mnladmin.adminmgn.permissionUrl=function(){
		if($.trim($("#permissionUrl").val()).length<=0){
			$("#addPermissionsError").show();
			$("#addPermissionsError").html("错误提示：权限url不能为空....");
			return false;
		}
		$("#addPermissionsError").hide();
		return true;
	}
	
	/**
	 * 全选和全不选 （新建管理员账号功能）
	 */
	$.mnladmin.adminmgn.functionAllChooseFun = function(){
		if($("#functionAllChoose:checked").size() == 0){
			$("input[name='ckFunction']").prop("checked","");
			$("input[name='dbckFunction']").prop("checked","");
		}else{
			$("input[name='ckFunction']").prop("checked","checked");
			$("input[name='dbckFunction']").prop("checked","checked");
		}
	}
	
	/**
	 * 所有权限全部选中时，全选按钮也选中。当有一个权限没被选中，那么全选按钮也不选中 （新建管理员账号功能）
	 */
	$.mnladmin.adminmgn.ckFunctionAll = function(){
		if($("input[name='dbckFunction']").size() > 0){
			if(($("input[name='dbckFunction']").size() + $("input[name='ckFunction']").size()) == ($("input[name='ckFunction']:checked").size() + $("input[name='dbckFunction']:checked").size())){
				$("#functionAllChoose").prop("checked","checked");
			}else{
				$("#functionAllChoose").prop("checked","");
			}
		}else{
			if($("input[name='ckFunction']").size() == $("input[name='ckFunction']:checked").size()){
				$("#functionAllChoose").prop("checked","checked");
			}else{
				$("#functionAllChoose").prop("checked","");
			}
		}
	}
})(jQuery);
$(function() {
	
	/**
	 * 网页显示时，显示此方法
	 */
	$.mnladmin.adminmgn.ckFunctionAll();
	
	/**
	 * 显示名失去焦点 （新建管理员账号）
	 */
	$("#nickName").blur(function() {
		$.mnladmin.adminmgn.reg_nickNameFun();	   //调用显示名错误信息方法
	});
	
	/**
	 * 登陆名失去焦点 （新建管理员账号）
	 */
	$("#userName").blur(function() {
		$.mnladmin.adminmgn.reg_userNameFun();     //调用登陆名错误信息方法
	});
	
	/**
	 * 密码失去焦点 （新建管理员账号）
	 */
	$("#passWord").blur(function() {
		$.mnladmin.adminmgn.reg_passWordFun();     //调用密码错误信息方法
	});
	
	/**
	 * 重复密码失去焦点 （新建管理员账号）
	 */
	$("#checkPassWord").blur(function() {
		$.mnladmin.adminmgn.reg_checkPassWordFun(); //调用重复密码错误信息方法
	});
	
	/**
	 *  新建管理员信息提交 （新建管理员账号）
	 */
	$("#regCommit").click(function() {
		
		/**
		 * 权限选择判断
		 */
		if ($("input:checkbox[name='ckFunction']:checked").length <= 0 && $("#adminType").val() == 0 ) {
			alert("请选择权限.....");
			return false;
		}
		
		/**
		 * 当 显示名 、 登录名 、密码 、重复密码 没有错误时，使用ajax提交他们的信息
		 */
		if ($.mnladmin.adminmgn.reg_nickNameFun() == true && $.mnladmin.adminmgn.reg_userNameFun() == true && $.mnladmin.adminmgn.reg_passWordFun() == true && $.mnladmin.adminmgn.reg_checkPassWordFun() == true) {
			$.ajax({
					url : "/adminmgn/addAdminUser.html",
					type : "post",
					data : $("#userForm").serialize(),
					success : function(response) {
							if (response == "regSuccess") {  				//新建管理员成功
								window.location.href = "/adminmgn.html";
								return;
							}
							if (response == "usernameRepeat") {				//登录名已存在
								$("#userNameError").html("错误信息提示：登录名已存在...");
								$("#userNameError").css("color", "red");
								return;
							}
							if (response =="regFail"){						//新建管理员失败
								alert("抱歉注册失败，请联系管理员...");
							}
						}
					});
		}
	});
	
	/**
	 * 操作记录删除按钮 （查看操作记录功能）
	 */
	$("#dayButton").click(function() {
		
				/**
				 * 天数为空 ，错误信息判断
				 */
				var regex = /^^[0-9]\d*$/;
				if ($.trim($("#day").val()).length <= 0 || !regex.test($.trim($("#day").val()))) {
					alert("请输入你要删除几天前的数据，直接写数字即可,注意必须是整数....");
					return;
				}
				
				/**
				 * 天数为0 ，删除今天之前的数据
				 */
				if($.trim($("#day").val()) == 0){
					if (confirm("你确定要删除今天之前的数据吗？？？？")) {
						$("#dayForm").submit();		//日志删除表单删除
						return;
					}
				}
				
				/**
				 * 提交要删除的数据
				 */
				if($.trim($("#day").val()) != 0){
					if (confirm("你确定要删除" + $("#day").val() + "天之前的数据吗？？？？")) {
						$("#dayForm").submit();		//日志删除表单删除
						return;
					}
				}
	});
	
	/**
	 * 操作记录搜索提交按钮 （查看操作记录功能）
	 */
	$("#logFindSubmit").click(function(){
		$("#pageNum").val("1");
		$("#fmSearch").submit();
	});
	
	/**
	 * 权限类型选择按钮：1.APP数据管理 （添加新权限功能）
	 */
	$("#userManagement").click(function() {
		$("#permissionsChoose").val("yyxs数据管理");
		$("#permissionsType").val($("#menu_type_website_data_manage").val());
	});
	
	/**
	 * 权限类型选择按钮：2.APP数据审核  （添加新权限功能）
	 */
	$("#dataReview").click(function() {
		$("#permissionsChoose").val("yyxs数据审核");
		$("#permissionsType").val($("#menu_type_website_data_examine").val());
	});
	
	/**
	 * 权限类型选择按钮：3.APP数据设置  （添加新权限功能）
	 */
	$("#dataSet").click(function() {
		$("#permissionsChoose").val("yyxs数据设置");
		$("#permissionsType").val($("#menu_type_website_data_set").val());
	});
	
	/**
	 * 权限类型选择按钮：4.后台数据管理  （添加新权限功能）
	 */
	$("#backgroundManagement").click(function() {
		$("#permissionsChoose").val("后台数据管理");
		$("#permissionsType").val($("#menu_type_backstage_data_manage").val());
	});
	
	/**
	 * 新增权限提交   （添加新权限功能）
	 */
	$("#addPermissionsCommit").click(function(){
		/**
		 * 当 权限名称 、 action 、url 没有错误时，使用ajax提交他们的信息
		 */
		if ($.mnladmin.adminmgn.permissionNameFun() == true && $.mnladmin.adminmgn.permissionAction() == true && $.mnladmin.adminmgn.permissionUrl() == true) {
			$.ajax( {
				url : "/adminmgn/addPermissions.html",
				type : "post",
				data : $("#addPermissionsFrom").serialize(),
				success : function(response) {
					if (response == "addPermissionsFail") {
						$("#addPermissionsError").show();
						$("#addPermissionsError").html("错误提示：新增权限失败，请联系超级管理员...");
						return false;
					}
					if (response == "addPermissionsSuccess") {
						location.href = "/adminmgn/allFunctionList.html";
					}
				}
			});
		}
	});
	
	/**
	 * 权限名称失去焦点 （添加新权限功能）
	 */
	$("#permissionName").blur(function(){
		$.mnladmin.adminmgn.permissionNameFun();    //调用权限名称错误信息方法
	});
	
	/**
	 * 权限名称失去焦点 （添加新权限功能）
	 */
	$("#permissionAction").blur(function(){
		$.mnladmin.adminmgn.permissionAction();    //调用权限action错误信息方法
	});
	
	/**
	 * 权限名称失去焦点 （添加新权限功能）
	 */
	$("#permissionUrl").blur(function(){
		$.mnladmin.adminmgn.permissionUrl();      //调用权限url错误信息方法
	});
	
	/**
	 * 管理员类型选择  （新建管理员账号功能）
	 */
	$("#commonAdmin").click(function(){
		$("#adminChoose").val("普通管理员");
		$("#adminType").val($("#user_type_admin_ordinary").val());
		$("#allFunction").show();
	});
	
	/**
	 * 管理员类型选择  （新建管理员账号功能）
	 */
	$("#superAdmin").click(function(){
		$("#adminChoose").val("超级管理员");
		$("#adminType").val($("#user_type_admin_system").val());
		$("#allFunction").hide();
	});
	
	/**
	 * 权限删除 （管理操作权限功能）
	 */
	$("input[name='deletePermissions']").click(function(){
		var _this = $(this);
		if(confirm("你确定要删除《"+$(this).attr("title")+"》权限吗？？？？")){
		  $.ajax({
			 url:"/adminmgn/deletePermissions.html",
			 type:"post",
			 data:{"permissionsId":$(this).attr("lang")},
			 success:function(response){
			   if(response=="deletePermissionsSuccess"){
				   $(_this).parents("tr").remove();
			   }
			 }
		 });
	   }
	});
	
	/**
	 * 冻结与解冻 （管理员列表功能）
	 */
	$("input[name='thawAndFrozen']").click(function(){
		var _this = $(this);
		var url = "/adminmgn/thawAdminmgnUser.html";
		if(_this.attr("dir") == $("#user_status_thaw").val()){
			url = "/adminmgn/freezeAdminmgnUser.html";
		}
		 $.ajax({
			 url:url,
			 type:"post",
			 data:{"userId":_this.attr("lang")},
			 success:function(response){
			 	if(response == "thawSuccess"){	//解冻成功
			 		_this.prop("dir",$("#user_status_thaw").val());
			 		_this.prop("class","btn btn-info");
			 		_this.val("冻结");
			 		return;
			 	}
			 	if(response == "frozenSuccess"){	//冻结成功
			 		_this.prop("dir",$("#user_status_frozen").val());
			 		_this.prop("class","btn btn-danger");
			 		_this.val("解冻");
			 		return;
			 	}
			 }
		 });
	});
	
	/**
	 * 管理员权限编辑
	 */
	$("#compilePermissionsBtn").click(function(){
		if(($("input[name='ckFunction']:checked").size() + $("input[name='dbckFunction']:checked").size()) <= 0){
			alert("请选择管理员权限！！");
			return;
		}
		$("#compilePermissionsFrom").submit();
	});
});