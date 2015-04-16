<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|管理员列表|编辑管理员权限</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		<!-- css样式专区 -->
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="/css/admin.css" rel="stylesheet" type="text/css" />
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
					<li class="active">编辑管理员权限</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel panel-default">
						<form role="form" method="post" id="compilePermissionsFrom" action="/adminmgn/compilePermissions.html">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-4">
										<ul class="pager">
  											<li class="previous" style="position: absolute;left: 5px;"><a href="/adminmgn.html">&larr; 返回</a></li>
										</ul>
										<input type="hidden" name="userid" value="${userid}" />
									</div>
									<div class="col-md-6">
										<br />
										<p class="text-danger"><b>管理员账号名：${username}</b></p>
									</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-md-2">
										<span class="badge">
											<div id="functionAllChooseDiv"><input type="checkbox" id="functionAllChoose" onchange="$.mnladmin.adminmgn.functionAllChooseFun()"/> 可使用的权限：</div>
										</span>
									</div>
									<div class="col-md-3"></div>
									<div class="col-md-3"></div>
									<div class="col-md-4"></div>
								</div>
								<div class="row">
									<c:forEach var="fList" items="${permissionsList}" varStatus="sta">
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> 
												    <input type="checkbox" name="${fList.checked== 'true'?'dbckFunction':'ckFunction'}" id="${fList.checked== 'true'?'dbckFunction':'ckFunction'}" value="${fList.id}" ${fList.checked== 'true'?'checked':''} onchange="$.mnladmin.adminmgn.ckFunctionAll()" />
												</span>
												<input type="text" class="form-control" style="font-size: 13px" value='${fList.description}' readonly="readonly" />
											</div>
										</div>
									</c:forEach>
								</div>
								<hr style="border: 1px #ccc solid;" />
							</div>
							<div class="row">
								<div class="col-lg-12" style="text-align: center;">
									<input type="button" class="btn btn-info" value="编辑权限" style="width: 40%" id="compilePermissionsBtn"/>
									<input type="button" class="btn btn-info" value="取消编辑" onclick="javascript:location.href='/adminmgn.html'" style="width: 40%" />
								</div>
							</div>
							<br />
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/admin.js"></script>
	</body>
</html>