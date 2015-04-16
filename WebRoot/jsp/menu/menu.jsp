<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|后台管理菜单</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
				<h4>
					后台管理菜单
				</h4>
			</div>
			<div class="row">
				<c:forEach var="menu" items="${menuList}">
					<div class="col-md-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									${menu.name}
								</h3>
							</div>
							<div class="panel-body">
								<ul class="list-group">
									<c:forEach var="menuItem" items="${menu.menuItemList}">
										<li class="list-group-item">
											<a href="${menuItem.url}">${menuItem.description}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/todayuserregistrations.js"></script>
	</body>
</html>