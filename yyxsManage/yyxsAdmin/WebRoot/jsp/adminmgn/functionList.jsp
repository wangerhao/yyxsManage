<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|管理员列表|管理操作权限</title>
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
					<li><a href="/adminmgn.html">管理员列表</a></li>
					<li class="active">管理操作权限</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading">
							<ul class="pager">
								<li class="previous"><a href="javascript:void(0)" onclick="javascript:location.href='/adminmgn.html'">&larr;返回</a></li>
								<li class="previous"><a href="/adminmgn/showPermissionsJsp.html">添加新权限</a></li>
							</ul>
						</div>
						<div class="panel-body">
							<table class="table" id="table">
								<tr>
									<th>id编号</th>
									<th>操作功能名称</th>
									<th>对应的Action</th>
									<th>创建时间</th>
									<th>权限删除</th>
								</tr>
								<c:forEach items="${functionList}" var="function" varStatus="s">
									<tr>
										<td>${function.id}</td>
										<td>${function.description}</td>
										<td>${function.name}</td>
										<td>${function.createtime}</td>
										<td><input type="button" class="btn btn-info" name="deletePermissions" lang="${function.id}" dir="${s.count}" title="${function.description}" value="删除"/></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/admin.js"></script>
	</body>
</html>