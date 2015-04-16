<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|权限</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
	</head>
	<body>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">

			<!-- 导航条 -->
			<div class="page-header">
				<ol class="breadcrumb">
					<li><a href="/menu.html">后台管理菜单</a></li>
					<li class="active">错误信息页面</li>
				</ol>
			</div>

			<div class="row">
				<div class="col-md-4">
					<div class="alert alert-danger">无权限操作此功能，请联系超级管理员开通!</div>
				</div>
				<div class="col-md-4">
					<div class="alert alert-info"><a href="/menu.html">点击返回管理菜单</a></div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
	</body>
</html>