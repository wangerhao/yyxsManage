<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.yyxs.admin.constant.YYXSAdminConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html lang="en">
	<head>
		<title>鴛鴦戲水后台管理系统|商品订单</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/css/smartpaginator.css" type="text/css"></link>
		<!--[if lt IE 9]>
      		<script src="/js/html5shiv.js"></script>
      		<script src="/js/respond.min.js"></script>
    	<![endif]-->
	</head>
	<body>
		<c:set var="fail_order_status" value="<%=YYXSAdminConstant.FAIL_ORDER_STATUS %>"></c:set>
		<c:set var="to_processed_order_status" value="<%=YYXSAdminConstant.TO_PROCESSED_ORDER_STATUS %>"></c:set>
		<c:set var="to_send_goods_order_status" value="<%=YYXSAdminConstant.TO_SEND_GOODS_ORDER_STATUS %>"></c:set>
		<c:set var="has_sent_goods_order_status" value="<%=YYXSAdminConstant.HAS_SENT_GOODS_ORDER_STATUS %>"></c:set>
		<c:set var="has_completed_order_status" value="<%=YYXSAdminConstant.HAS_COMPLETED_ORDER_STATUS %>"></c:set>
		<c:set var="default_max_result" value="<%=YYXSAdminConstant.DEFAULT_MAX_RESULT %>"></c:set>
		<div id="pageTop"></div>
		<c:import url="/jsp/common/header.jsp"></c:import>
		<div class="container">
			<div class="page-header">
				<ol class="breadcrumb">
					<li>
						<a href="/menu.html">后台管理菜单</a>
					</li>
					<li class="active">
						商品订单
					</li>
				</ol>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-3">
									<ul class="pager">
										<li class="previous">
											<a href="/menu.html">&larr;返回</a>
										</li>
										<li class="previous">
											<a href="/goodsorder/addNewOrderShow.html">添加新订单</a>
										</li>
									</ul>
								</div>
									<div class="col-md-3">
										<div class="input-group" style="margin-top: 10px">
											<div class="input-group-btn">
												<button id="option" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
													${searchType == null || searchType == 0 ? "所有 <span class=\"caret\"></span>" : ""}${searchType == 1 ? "商品名称 <span class=\"caret\"></span>" : ""}${searchType == 2 ? "收货人 <span class=\"caret\"></span>" : ""}${searchType == 3 ? "电话 <span class=\"caret\"></span>" : ""}
												</button>
												<ul class="dropdown-menu" role="menu">
													<li>
														<a id="option1" href="javascript:void(0)">商品名</a>
													</li>
													<li>
														<a id="option2" href="javascript:void(0)">收货人</a>
													</li>
													<li>
														<a id="option3" href="javascript:void(0)">电话</a>
													</li>
													<li>
														<a id="option4" href="javascript:void(0)">所有</a>
													</li>
												<!-- 	<li>
														<a id="option4" href="javascript:void(0)">物流订单</a>
													</li>  -->
												</ul>
											</div>
											<form id="searchForm" action="/goodsorder/searchOrder.html" method="post">
												<input id="searchType" name="searchType" type="hidden" value="${searchType}"/>
												<input id="deal" name="deal" type="hidden" value="${deal}"/>
												<input id="searchWords" name="searchWords" type="text" class="form-control" value="${searchWords}" placeholder="Search for..." readonly="readonly">
											</form>
											<span class="input-group-btn">
												<button id="searchBut" class="btn btn-default" type="button">
													Go!
												</button> </span>
										</div>
									</div>
								
								<div class="col-md-6">
									<ul class="pager">
										<li class="previous">
											<a id="deal6" href="javascript:void(0)" style="background-color:${deal == 6 ? '#00BFFF' : ''}">看今天</a>
										</li>
										<li class="previous">
											<a id="deal1" href="javascript:void(0)" style="background-color:${deal == 1 ? '#00BFFF' : ''}">待处理</a>
										</li>
										<li class="previous">
											<a id="deal2" href="javascript:void(0)" style="background-color:${deal == 2 ? '#00BFFF' : ''}">待发货</a>
										</li>
										<li class="previous">
											<a id="deal3" href="javascript:void(0)" style="background-color:${deal == 3 ? '#00BFFF' : ''}">已发货</a>
										</li>
										<li class="previous">
											<a id="deal4" href="javascript:void(0)" style="background-color:${deal == 4 ? '#00BFFF' : ''}">已完成</a>
										</li>
										<li class="previous">
											<a id="deal5" href="javascript:void(0)" style="background-color:${deal == 5 ? '#00BFFF' : ''}">订单失败</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table">
								<tr>
									<th>商品名称</th>
									<th>商品个数</th>
									<th>商品价钱(元)</th>
									<th>收货人</th>
									<th>收货人电话</th>
									<th>订单状态</th>
									<th>订单创建时间</th>
									<th>操作</th>
								</tr>
								<c:if test="${fn:length(goodsOrderList) > 0}">
								<c:forEach var="goodsorder" items="${goodsOrderList}">
									<tr>
										<td style="width: 13%; table-layout: fixed; word-break: break-all; overflow: hidden;">
											<a href="javascript:void(0)" name="goodsNameBtn" goodsid="${goodsorder.goid}">${goodsorder.good_name}</a>
										</td>
										<td style="width: 10%; table-layout: fixed; word-break: break-all; overflow: hidden;">
											${goodsorder.goods_number}
										</td>
										<td style="width: 10%">
											${goodsorder.money}
										</td>
										<td style="width: 10%; table-layout: fixed; word-break: break-all; overflow: hidden;">
											${goodsorder.consignee}
										</td>
										<td style="width: 15%; table-layout: fixed; word-break: break-all; overflow: hidden;">
											${goodsorder.mobile_phone_number}
										</td>
										<td style="width: 8%">
											<c:if test="${goodsorder.order_status == fail_order_status}"><span class="label label-default">订单失败</span> </c:if>
											<c:if
												test="${goodsorder.order_status == to_processed_order_status}"><span class="label label-danger">待处理</span></c:if>
											<c:if
												test="${goodsorder.order_status == to_send_goods_order_status}"><span class="label label-warning">待发货</span></c:if>
											<c:if
												test="${goodsorder.order_status == has_sent_goods_order_status}"><span class="label label-info">已发货</span></c:if>
											<c:if
												test="${goodsorder.order_status == has_completed_order_status}"><span class="label label-success">已完成</span></c:if>
										</td>
										<td style="width: 20%; table-layout: fixed; word-break: break-all; overflow: hidden;">
											${goodsorder.systime}
										</td>
										<td>
											<a href="/goodsorder/goodsOrderInfoUpdateShow.html?orderId=${goodsorder.goid}&currentPage=${currentPage}&dataSaveType=${dataSaveType}&deal=${deal}" class="btn btn-primary">修改</a>
										<!-- 	<a id="deleteBut" href="javascript:void(0)" class="btn btn-danger">删除</a>  -->
										</td>
									</tr>
								</c:forEach>
								</c:if>
								
								<tr style="width: 100%">
									<th colspan="8">
										<br />
										<div id="pageDiv"></div>
									</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="picModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
		<input type="hidden" id="timeDelay" />
		<input type="hidden" value="${dataSaveType}" id="dataSaveType" />
		<input type="hidden" id="pageMaxResult" value="${default_max_result}" />
		<input type="hidden" id="totalNum" value="${totalNum}" />
		<form action="/goodsorder.html" method="post" id="fmSearch">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}" />
			<input id="searchType2" name="searchType" value="${searchType}" type="hidden"/>
			<input id="deal2" name="deal" value="${deal}" type="hidden"/>
			<input id="searchWords2" name="searchWords" value="${searchWords}" type="hidden">
		</form>
		<div id="scorllxDiv" style="display: none;">
			<a href="javascript:void(0);" style="font-size: 70px; text-decoration: none; outline: none;" id="scrollxBtn" lang="#pageBottom" class="glyphicon glyphicon-arrow-down"></a>
		</div>
		<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/js/jquery.form.js"></script>
		<script type="text/javascript" src="/js/smartpaginator.js"></script>
		<script type="text/javascript" src="/js/page.js"></script>
		<script type="text/javascript" src="/js/scrollx.js"></script>
		<script type="text/javascript" src="/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/js/goodsorder.js"></script>
		<div id="pageBottom"></div>
	</body>
</html>
