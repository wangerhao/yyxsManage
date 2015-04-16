<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.yyxs.admin.constant.YYXSAdminConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|管理员列表|查看操作记录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/css/admin.css" type="text/css"></link>
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
		
	</head>
	<body>
		<!-- 1.APP数据管理 -->
		<c:set var="menu_type_website_data_manage" value="<%=YYXSAdminConstant.MENU_TYPE_WEBSITE_DATA_MANAGE%>"></c:set>
		<!-- 2.APP数据审核 -->
		<c:set var="menu_type_website_data_examine" value="<%=YYXSAdminConstant.MENU_TYPE_WEBSITE_DATA_EXAMINE%>"></c:set>
		<!-- 3.APP数据设置 -->
		<c:set var="menu_type_website_data_set" value="<%=YYXSAdminConstant.MENU_TYPE_WEBSITE_DATA_SET%>"></c:set>
		<!-- 4.后台数据管理 -->
		<c:set var="menu_type_backstage_data_manage" value="<%=YYXSAdminConstant.MENU_TYPE_BACKSTAGE_DATA_MANAGE%>"></c:set>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">
			<div class="page-header">
				<ol class="breadcrumb">
					<li><a href="/menu.html">后台管理菜单</a></li>
					<li><a href="/adminmgn.html">管理员列表</a></li>
					<li><a href="/adminmgn/allFunctionList.html">管理操作权限</a></li>
					<li class="active">添加新权限</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-5">
					<center>
						<div class="panel panel-primary" id="addPermissionsPanel">
							<div class="panel-heading">
								<h3 class="panel-title">权限新增</h3>
							</div>
							<div class="panel-body">
								<form role="from" id="addPermissionsFrom">
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
										<div class="alert alert-danger" id="addPermissionsError"></div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;权限名称:</span>
												<input type="text" id="permissionName" name="permissionName"
													class="form-control"
													placeholder="请输入权限的名称..." />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;action:</span>
												<input type="text" id="permissionAction" name="permissionAction" class="form-control" placeholder="请输入权限的action..." />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url:</span>
												<input type="text" id="permissionUrl" name="permissionUrl" class="form-control" placeholder="请输入权限的url..." />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
												<div class="input-group-btn">
													<button type="button" id="permissionsTypeButton" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
														权限类型
														<span class="caret"></span>
													</button>
													<ul class="dropdown-menu" id="permissionsTypeButton">
														<li><a href="javascript:void(0)" id="userManagement">1.yyxs数据管理</a></li>
														<li><a href="javascript:void(0)" id="dataReview">2.yyxs数据审核</a></li>
														<li><a href="javascript:void(0)" id="dataSet">3.yyxs数据设置</a></li>
														<li><a href="javascript:void(0)" id="backgroundManagement">4.后台数据管理</a></li>
													</ul>
												</div>
												<input type="text" class="form-control" readonly="readonly" id="permissionsChoose" value="yyxs数据管理" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<br />
											<input type="button" class="btn btn-info" value="新增权限" style="width: 360px" id="addPermissionsCommit" />
										</div>
									</div>
									<input type="hidden" id="permissionsType" name="permissionsType" value="${menu_type_website_data_manage}" />
								</form>
							</div>
						</div>
					</center>
				</div>
			</div>
		</div>
		<input type="hidden" id="menu_type_website_data_manage" value="${menu_type_website_data_manage}"/>
		<input type="hidden" id="menu_type_website_data_examine" value="${menu_type_website_data_examine}"/>
		<input type="hidden" id="menu_type_website_data_set" value="${menu_type_website_data_set}"/>
		<input type="hidden" id="menu_type_backstage_data_manage" value="${menu_type_backstage_data_manage}"/>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/admin.js"></script>
		<script type="text/javascript" src="/js/dropdown.js"></script>
	</body>
</html>