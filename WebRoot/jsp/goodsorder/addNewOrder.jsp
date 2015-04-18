<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.yyxs.admin.constant.YYXSAdminConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|商品订单|添加新订单</title>
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
					<li><a href="/goodsorder.html">商品订单</a></li>
					<li class="active">添加新订单</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">添加新订单</div>
						<div class="panel-body">
							<form id="orderUpdateForm">
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="alert alert-danger" id="orderInfoUpdateErrorDiv" style="display: none"></div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <span class="input-group-addon" id="basic-addon1">商品个数&nbsp;&nbsp;&nbsp;</span>
										  <input type="text" class="form-control" placeholder="请输入商品个数!" aria-describedby="basic-addon1" name="goodsNumber" id="goodsNumber"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <span class="input-group-addon" id="basic-addon1">商品价钱&nbsp;&nbsp;&nbsp;</span>
										  <input type="text" class="form-control" placeholder="请输入商品价钱!" aria-describedby="basic-addon1" id="money" name="money"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <span class="input-group-addon" id="basic-addon1">收货人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
										  <input type="text" class="form-control" placeholder="请输入收货人!" aria-describedby="basic-addon1" id="consignee" name="consignee"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <span class="input-group-addon" id="basic-addon1">收货人电话</span>
										  <input type="text" class="form-control" placeholder="请输入收货人电话!" aria-describedby="basic-addon1" id="mobilePhoneNumber" name="mobilePhoneNumber"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <span class="input-group-addon" id="basic-addon1">收货地址&nbsp;&nbsp;&nbsp;</span>
										  <textarea class="form-control" placeholder="请输入收货地址!" aria-describedby="basic-addon1" id="detailedAddress" name="detailedAddress"></textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <div class="input-group-btn">
									        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">订单状态&nbsp;<span class="caret"></span></button>
									        <ul class="dropdown-menu" role="menu">
									          <li><a href="javascript:void(0)" id="toProcessedBtn">1、<span>待处理</span></a></li>
									          <li><a href="javascript:void(0)" id="toSendGoodsBtn">2、<span>待发货</span></a></li>
									          <li><a href="javascript:void(0)" id="hasBeenShippedBtn">3、<span>已发货</span></a></li>
									          <li><a href="javascript:void(0)" id="orderFailedBtn">4、<span>订单失败</span></a></li>
									          <li><a href="javascript:void(0)" id="hasBeenCompletedBtn">5、<span>已完成</span></a></li>
									        </ul>
									      </div>
										  <input type="text" class="form-control" placeholder="请选择订单状态!" aria-describedby="basic-addon1" readonly="readonly" id="orderStatusVal"/>
										  <input type="hidden" id="orderStatus" name="orderStatus" value="${dataMap.order_status}"/>
										</div>
									</div>
								</div>
								<div class="row" id="orderStatusDiv" style="display: none;">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="input-group">
										  <span class="input-group-addon" id="basic-addon1">状态备注&nbsp;&nbsp;&nbsp;</span>
										  <textarea class="form-control" placeholder="请输入订单状态备注!" aria-describedby="basic-addon1" id="orderStatusNote" name="orderStatusNote">${dataMap.order_status_note}</textarea>
										</div>
									</div>
								</div>
								<div id="wuliuDiv" style="display: none;">
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
											  <span class="input-group-addon" id="basic-addon1">物流公司&nbsp;&nbsp;&nbsp;</span>
											  <input type="text" class="form-control" placeholder="请输入物流公司!" aria-describedby="basic-addon1" value="${dataMap.wuliu_name}" id="wuliuName" name="wuliuName"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
											  <span class="input-group-addon" id="basic-addon1">物流单号&nbsp;&nbsp;&nbsp;</span>
											  <input type="text" class="form-control" placeholder="请输入物流单号!" aria-describedby="basic-addon1" value="${dataMap.wuliu_number}" id="wuliuNumber" name="wuliuNumber"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<div class="input-group">
											  <span class="input-group-addon" id="basic-addon1">物流价格&nbsp;&nbsp;&nbsp;</span>
											  <input type="text" class="form-control" placeholder="请输入物流价格!" aria-describedby="basic-addon1" value="${dataMap.wuliu_price}" id="wuliuPrice" name="wuliuPrice"/>
											</div>
										</div>
									</div>
								</div>
							</form>
							<br/>
							<hr/>
							<br/>
							<div class="row">
								<div class="col-md-1"></div>
								<div class="col-md-10">
									<input type="button" class="btn btn-primary" id="orderUpdateBtn" style="width: 100%" value="完成"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	   </div>
	   	<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/js/goodsorder.js"></script>
  </body>
</html>
