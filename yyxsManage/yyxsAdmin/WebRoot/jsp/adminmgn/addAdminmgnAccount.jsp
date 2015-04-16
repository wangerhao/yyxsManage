<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.yyxs.admin.constant.YYXSAdminConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|管理员列表|新建管理员账号</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="/css/admin.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
	</head>
	<body>
		<!-- 普遍管理员 -->
		<c:set var="user_type_admin_ordinary" value="<%=YYXSAdminConstant.USER_TYPE_ADMIN_ORDINARY%>"></c:set>
		<!-- 超级管理员 -->
		<c:set var="user_type_admin_system" value="<%=YYXSAdminConstant.USER_TYPE_ADMIN_SYSTEM%>"></c:set>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">
			<div class="page-header">
				<ol class="breadcrumb">
					<li><a href="/menu.html">后台管理菜单</a></li>
					<li class="active"><a href="/adminmgn.html">管理员列表</a></li>
					<li class="active">新建管理员账号</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel panel-default">
						<form role="form" id="userForm">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-6">
										<br />
										<div class="input-group">
											<span class="input-group-addon"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;显示名:</span>
											<input type="text" id="nickName" name="nickName" class="form-control" placeholder="请输入注册的显示名..." />
										</div>
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-4" style="color: green;" id="nickNameError"><br />信息提示：√</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-6">
										<div class="input-group">
											<span class="input-group-addon"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;登陆名:</span>
											<input type="text" id="userName" name="userName" class="form-control" placeholder="请输入注册的登录名..." />
										</div>
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-4" style="color: green;" id="userNameError">
										信息提示：√
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-6">
										<div class="input-group">
											<span class="input-group-addon">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;密码:</span>
											<input type="password" id="passWord" name="passWord" class="form-control" placeholder="请输入注册的密码..." />
										</div>
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-4" style="color: green;" id="passWordError">
										信息提示：√
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-6">
										<div class="input-group">
											<span class="input-group-addon">&nbsp; &nbsp; &nbsp; 重复密码:</span>
											<input type="password" id="checkPassWord" name="checkPassWord" class="form-control" placeholder="请输入重复密码..." />
										</div>
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-4" style="color: green;" id="checkPassWordError">信息提示：√</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-6">
										<div class="input-group">
											<div class="input-group-btn">
												<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
													管理员类型
													<span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li><a href="javascript:void(0)" id="commonAdmin">0.普通管理员</a></li>
													<li><a href="javascript:void(0)" id="superAdmin">1.超级管理员</a></li>
												</ul>
											</div>
											<input type="text" class="form-control" readonly="readonly" id="adminChoose" placeholder="普通管理员" value="普通管理员" />
										</div>
									</div>
									<div class="col-md-1"></div>
									<div class="col-md-4" style="color: green;" id="adminError">信息提示：√</div>
								</div>
							</div>
							<div class="panel-footer" id="allFunction">
								<div class="row">
									<div class="col-md-6">
										<span class="badge">
											<div id="functionAllChooseDiv"><input type="checkbox" id="functionAllChoose" onchange="$.mnladmin.adminmgn.functionAllChooseFun()"/> 可使用的权限：</div>
										</span>
									</div>
								</div>
								<div class="row">
									<c:forEach var="fList" items="${functionList}" varStatus="sta">
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> 
												     <input type="checkbox" name="ckFunction" id="ckFunction" value="<c:out value='${fList.id}'/>" onchange="$.mnladmin.adminmgn.ckFunctionAll()" /> 
												</span>
												<input type="text" class="form-control" style="font-size: 13px" value='${fList.description}' readonly="readonly" />
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<hr style="border: 1px #ccc solid;" />
							<div class="row">
								<div class="col-lg-12" style="text-align: center;">
									<input type="button" class="btn btn-info" value="确定注册" style="width: 40%" id="regCommit" />
									<input type="button" class="btn btn-info" value="取消注册" onclick="javascript:location.href='/adminmgn.html'" style="width: 40%" />
								</div>
							</div>
							<br />
							<input type="hidden" id="adminType" name="adminType" value="${user_type_admin_ordinary}"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="user_type_admin_ordinary" value="${user_type_admin_ordinary}"/>
		<input type="hidden" id="user_type_admin_system" value="${user_type_admin_system}"/>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/admin.js"></script>
		<script type="text/javascript" src="/js/dropdown.js"></script>
	</body>
</html>