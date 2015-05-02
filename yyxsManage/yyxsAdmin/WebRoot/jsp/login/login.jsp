<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|登录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="/css/signin.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
	</head>
	<body>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">
			<div class="page-header">
				<h4>
					管理员登录
				</h4>
			</div>
			<form id="loginForm" class="form-signin">
        		<input type="text" class="form-control" id="userName" name="userName" placeholder="用户名" autofocus>
        		<input type="password" class="form-control" id="password" name="password" placeholder="密码">
        		<div class="alert alert-danger" id="errorDiv" style="display: none"></div>
        		<button class="btn btn-lg btn-primary btn-block" id="btn_login" type="button">登录</button>
      		</form>
    	</div>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/login.js"></script>
		<a href="http://www.ickd.cn/" target="_blank" >快递查询</a>
	</body>
</html>