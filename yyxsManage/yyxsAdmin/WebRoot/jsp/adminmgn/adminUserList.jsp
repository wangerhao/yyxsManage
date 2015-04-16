<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.yyxs.admin.constant.YYXSAdminConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|管理员列表</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
	</head>
	<body>
		<!-- 后台登录用户解冻状态标识 -->
		<c:set var="user_status_thaw" value="<%=YYXSAdminConstant.USER_STATUS_THAW%>"></c:set>
		<!-- 后台登录用户冻结状态标识 -->
		<c:set var="user_status_frozen" value="<%=YYXSAdminConstant.USER_STATUS_FREEZEN%>"></c:set>
		<!-- 普遍管理员 -->
		<c:set var="user_type_admin_ordinary" value="<%=YYXSAdminConstant.USER_TYPE_ADMIN_ORDINARY%>"></c:set>
		<!-- 超级管理员 -->
		<c:set var="user_type_admin_system" value="<%=YYXSAdminConstant.USER_TYPE_ADMIN_SYSTEM%>"></c:set>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">
			<div class="page-header">
				<ol class="breadcrumb">
					<li><a href="/menu.html">后台管理菜单</a></li>
					<li class="active">管理员列表</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="panel panel-default">
						<div class="panel-header">
							<input type="button" value="新建管理员账号" class="btn btn-info" onclick="javascript:location.href='/adminmgn/showAdminManagePage.html'" />
							<input type="button" value="查看操作记录" class="btn btn-info" onclick="javascript:location.href='/adminmgn/showAdminLog.html'" />
							<input type="button" value="管理操作权限" class="btn btn-info" onclick="javascript:location.href='/adminmgn/allFunctionList.html'" />
						</div>
						<div class="panel-body">
							<table class="table">
								<tr>
									<th>管理员名</th>
									<th>管理员账号</th>
									<th>管理员类型</th>
									<th>操作</th>
								</tr>
								<c:forEach var="uList" items="${userList}" varStatus="sta">
									<tr>
										<td>${uList.nickname}</td>
										<td>${uList.username}</td>
										<td>${uList.type==1?"超级管理员":"普遍管理员"}</td>
										<td>
											<c:if test="${uList.type == user_status_thaw}">
												<input type="button" value="编辑" class="btn btn-info" onclick='javascript:location.href="/adminmgn/queryPermissions.html?userid=${uList.id}&username=${uList.username}"' />
												<input type="button" value="${uList.status == user_status_thaw?'冻结':'解冻'}" class="${uList.status == user_status_thaw?'btn btn-info':'btn btn-danger'}" name = "thawAndFrozen" lang="${uList.id}" dir="${uList.status}" />
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="user_status_thaw" value="${user_status_thaw}"/>
		<input type="hidden" id="user_status_frozen" value="${user_status_frozen}"/>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/admin.js"></script>
	</body>
</html>