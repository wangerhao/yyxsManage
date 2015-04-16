<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.yyxs.admin.constant.YYXSAdminConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|管理员列表|查看操作记录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		<!-- css样式专区 -->
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/css/smartpaginator.css" type="text/css"></link>
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
	</head>
	<body>
		<div id="pageTop"></div>
		<c:set var="defaultMaxResult" value="<%=YYXSAdminConstant.DEFAULT_MAX_RESULT%>"></c:set>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">
			<div class="page-header">
				<ol class="breadcrumb">
					<li><a href="/menu.html">后台管理菜单</a></li>
					<li><a href="/adminmgn.html">管理员列表</a></li>
					<li class="active">查看操作记录</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-lg-3">
									<form method="post" name="fmSearch" id="fmSearch" action="/adminmgn/showAdminLog.html">
										<div class="input-group">
											<span class="input-group-addon">姓名：</span>
											<input type="text" class="form-control" value="${nickname}" name="nickname" id="nickname" />
											<span class="input-group-btn"> 
											     <input type="button" class="btn btn-default" value="搜索" id="logFindSubmit" /> 
											</span>
										</div>
										<input type="hidden" value="${pageNum}" id="pageNum"  name="pageNum"/>
									</form>
								</div>
								<div class="col-lg-5">
									<form role="form" id="dayForm" action="/adminmgn/deleteAdminLog.html" method="post">
										<div class="input-group">
											<span class="input-group-addon">输入天数：</span>
											<input type="text" class="form-control" name="day" id="day" placeholder="0 是删除今天的数据...." />
											<span class="input-group-btn"> <input type="button" id="dayButton" class="btn btn-default" value="删除" /> </span>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table">
								<tr>
									<th>时间</th>
									<th>操作人</th>
									<th>进入状态</th>
									<th>操作</th>
								</tr>
								<c:forEach var="log" items="${logList}" varStatus="s">
									<tr>
										<td><c:out value="${log.time}" /></td>
										<td><c:out value="${log.nickname}" /></td>
										<td><c:out value="${log.fname}" /></td>
										<td><c:out value="${log.content}" /></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="4"><center><div id="UnderPageDiv"></div></center>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="scorllxDiv" style="display: none;"><a href="javascript:void(0);" style="font-size: 70px;text-decoration: none;outline: none;" id="scrollxBtn" lang="#pageBottom" class="glyphicon glyphicon-arrow-down"></a></div>
		</div>
		<input type="hidden" value="${totalNum}" id="totalNum" />
		<input type="hidden" value="${defaultMaxResult}" id="pageMaxResult" />
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
		<script type="text/javascript" src="/js/smartpaginator.js"></script>
		<script type="text/javascript" src="/js/admin.js"></script>
		<script type="text/javascript" src="/js/page.js"></script>
		<script type="text/javascript" src="/js/scrollx.js"></script>
	<div id="pageBottom"></div>
	</body>
</html>