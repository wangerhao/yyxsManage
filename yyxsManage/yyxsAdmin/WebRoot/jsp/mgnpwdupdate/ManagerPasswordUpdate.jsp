<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
  	<title>鴛鴦戲水后台管理系统|管理员密码更改</title>
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
		<div class="page-header">
			<ol class="breadcrumb">
				<li><a href="/menu.html">后台管理菜单</a></li>
				<li class="active">管理员密码更改</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<br/>
				<div class="panel panel-primary">
					<div class="panel-heading">管理员密码更改</div>
					<div class="panel-body">
						<form id="managerPasswordUpdateFrom">
							<input type="hidden" id="mgnuserid" name="mgnuserid" value="${mgnuser.id}"/>
							<div class="row">
								<div class="col-md-4"><span class="text-danger">更改人：${mgnuser.userName}</span></div>
							</div>
							<br/>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<div class="alert alert-danger" id="MgnPwdUpdateErrorInfo" style="display: none;"></div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<div class="input-group">
									  <span class="input-group-addon">　　旧密码</span>
									  <input type="password" class="form-control" placeholder="请输入旧密码...." id="oldMgnPwd" name="oldMgnPwd">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<div class="input-group">
									  <span class="input-group-addon">　　新密码</span>
									  <input type="password" class="form-control" placeholder="请输入新密码..." id="newMgnPwd" name="newMgnPwd">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<div class="input-group">
									  <span class="input-group-addon">确认新密码</span>
									  <input type="password" class="form-control" placeholder="请重新输入新密码..." id="affirmNewMgnPwd">
									</div>
								</div>
							</div>
							<hr/>
							<input type="button" value="完成密码更改" style="width: 100%" class="btn btn-primary" id="managerPasswordUpdateSubmit"/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
	<script type="text/javascript" src="/js/mgnpwdupdate.js"></script>
  </body>
</html>
