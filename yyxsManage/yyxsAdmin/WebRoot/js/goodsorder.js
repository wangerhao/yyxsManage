(function($){
	
	if(typeof $.goods == "undefined"){
		$.goods = {};
	}
	
	if(typeof $.goods.order == "undefined"){
		$.goods.order = {};
	}
	 
	/** 商品个数验证 */
	$.goods.order.goodsNumberVail = function(){
		if($.trim($("#goodsNumber").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写商品个数!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		var regex = /^[0-9]*$/;
		if(regex.test($("#goodsNumber").val()) == false){
			$("#orderInfoUpdateErrorDiv").html("商品个数输入错误!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 商品价钱验证 */
	$.goods.order.moneyVail = function(){
		if($.trim($("#money").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写商品价钱!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		var regex = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
		if(regex.test($("#money").val()) == false){
			$("#orderInfoUpdateErrorDiv").html("商品价钱输入错误!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 收货人姓名验证 */
	$.goods.order.consigneeVail = function(){
		if($.trim($("#consignee").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写收货人姓名!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 收货人电话验证 */
	$.goods.order.mobilePhoneNumberVail = function(){
		if($.trim($("#mobilePhoneNumber").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写收货人姓名!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 收货地址验证 */
	$.goods.order.detailedAddressVail = function(){
		if($.trim($("#detailedAddress").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写收货人姓名!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 订单状态备注验证 */
	$.goods.order.orderStatusNoteVail = function(){
		if($.trim($("#orderStatusNote").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写订单失败备注!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 物流名称验证 */
	$.goods.order.wuliuNameVail = function(){
		if($.trim($("#wuliuName").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写物流名称!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}

	/** 物流单号验证 */
	$.goods.order.wuliuNumberVail = function(){
		if($.trim($("#wuliuNumber").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写物流单号!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
	
	/** 物流价格验证 */
	$.goods.order.wuliuPriceVail = function(){
		if($.trim($("#wuliuPrice").val()).length <= 0){
			$("#orderInfoUpdateErrorDiv").html("请填写物流价钱!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		var regex = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
		if(regex.test($("#wuliuPrice").val()) == false){
			$("#orderInfoUpdateErrorDiv").html("物流价钱输入错误!");
			$("#orderInfoUpdateErrorDiv").show();
			return false;
		}
		$("#orderInfoUpdateErrorDiv").hide();
		return true;
	}
})(jQuery);
$(function(){
	
	/** 物流信息查看按钮 */
	$("a[name='goodsNameBtn']").click(function(){
		var _this = $(this);
		$("#picModal").modal({
				backdrop: "static",
				keyboard: false,
				remote: "/jsp/small/modalSmall.jsp"
		});
		for(var i = 0; i < 1000; i++){
			$("#timeDelay").val(i);
		}
		$.ajax({
  			url:"/goodsorder/wuLiuInfo.html",
  			type:"post",
  			dataType:"json",
  			data:{"goodsid":_this.attr("goodsid")},
  			success:function(res){
  				$(".modal-title").html(res.good_name);
  				if(res.order_type != "" && res.order_type > 0){
  					$("#goodType").parents("tr").show();
  					if(res.order_type == $("#weikuLModel").val()){
  						$("#goodType").html("L号(黑色)");
  					}
  					if(res.order_type == $("#weikuXLModel").val()){
  						$("#goodType").html("XL号(黑色)");
  					}
  					if(res.order_type == $("#weikuXXLModel").val()){
  						$("#goodType").html("XXL号(黑色)");
  					}
  				}
  				$("#address").html(res.detailed_address);
  				$("#wuLiuName").html(res.wuliu_name);
  				$("#wuLiuNumber").html(res.wuliu_number);
  				$("#wuLiuPrice").html(res.wuliu_price);
  				$("#statusNote").html(res.order_status_note);
  				
  			}
  		});
		$('#picModal').on('hidden.bs.modal', function (e) {
			$(this).removeData("bs.modal");
		});
	});
	
	/** 待处理按钮 */
	$("#toProcessedBtn").click(function(){
		$("#orderStatusVal").val($(this).children().html());
		$("#orderStatus").val($("#toProcessedOrderStatus").val());
		$("#orderStatusDiv").show();
		$("#wuliuDiv").hide();
	});
	
	/** 待发货按钮 */
	$("#toSendGoodsBtn").click(function(){
		$("#orderStatusVal").val($(this).children().html());
		$("#orderStatus").val($("#toSendGoodsOrderStatus").val());
		$("#orderStatusDiv").hide();
		$("#wuliuDiv").hide();
	});
	
	/** 已发货按钮 */
	$("#hasBeenShippedBtn").click(function(){
		$("#orderStatusVal").val($(this).children().html());
		$("#orderStatus").val($("#hasSentGoodsOrderStatus").val());
		$("#orderStatusDiv").hide();
		$("#wuliuDiv").show();
	});
	/****************************修改订单型号***************************/
	/** L号按钮 */
	$("#orderTypeL").click(function(){
		$("#orderTypeVal").val($(this).children().html());
		$("#orderType").val(1);
	});
	
	/** XL号按钮 */
	$("#orderTypeXL").click(function(){
		$("#orderTypeVal").val($(this).children().html());
		$("#orderType").val(2);
	});
	
	/** XXL号按钮 */
	$("#orderTypeXXL").click(function(){
		$("#orderTypeVal").val($(this).children().html());
		$("#orderType").val(3);
	});
	
	/** 订单失败按钮 */
	$("#orderFailedBtn").click(function(){
		$("#orderStatusVal").val($(this).children().html());
		$("#orderStatus").val($("#failOrderStatus").val());
		$("#orderStatusDiv").show();
		$("#wuliuDiv").hide();
	});
	
	/** 已完成按钮 */
	$("#hasBeenCompletedBtn").click(function(){
		$("#orderStatusVal").val($(this).children().html());
		$("#orderStatus").val($("#hasCompletedOrderStatus").val());
		$("#orderStatusDiv").hide();
		$("#wuliuDiv").hide();
	});
	
	/** 订单修改按钮 */
	$("#orderUpdateBtn").click(function(){
		if($.goods.order.goodsNumberVail() && $.goods.order.moneyVail() && $.goods.order.consigneeVail() && $.goods.order.mobilePhoneNumberVail() &&　$.goods.order.detailedAddressVail()){
			if($("#orderStatus").val() == $("#failOrderStatus").val()){
				if(!$.goods.order.orderStatusNoteVail()) return false;
			}
			if($("#orderStatus").val() == $("#hasSentGoodsOrderStatus").val()){
				if(!$.goods.order.wuliuNameVail() || !$.goods.order.wuliuNumberVail() || !$.goods.order.wuliuPriceVail()) return false;
			}
			$.ajax({
				url:"/goodsorder/orderInfoUpdate.html",
				type:"post",
				data:$("#orderUpdateForm").serialize(),
				success:function(res){
					if(res == "success"){
						location.href = "/goodsorder.html?currentPage=" + $("#currentPage").val();
					}
					$("#orderInfoUpdateErrorDiv").html("商品订单修改失败，请联系超级管理员!");
				}
			});
		}
	});
	
	$("#option1").click(function(){
		$("#option").html("商品名 <span class=\"caret\"></span>");
		$("#searchType").val(1);
		$("#searchWords").prop("readonly", "");
	});
	$("#option2").click(function(){
		$("#option").html("收货人 <span class=\"caret\"></span>");
		$("#searchType").val(2);
		$("#searchWords").prop("readonly", "");
	});
	$("#option3").click(function(){
		$("#option").html("电话 <span class=\"caret\"></span>");
		$("#searchType").val(3);
		$("#searchWords").prop("readonly", "");
	});
	$("#option4").click(function(){
		$("#option").html("所有 <span class=\"caret\"></span>");
		$("#searchType").val(0);
		$("#searchWords").prop("readonly", "readonly");
	});
	
	/**
	 * 查询按钮
	 */
	$("#searchBut").click(function(){
		if($("#searchType").val() == 0) {
			location.href = "/goodsorder.html";
		}
		if($("#searchType").val() == 1 || $("#searchType").val() == 2 || $("#searchType").val() == 3){
			$("#searchForm").submit();
		} 
	});
	$("#deal1").click(function(){
		$("#deal").val(1);
		$("#searchForm").submit();
	});
	$("#deal2").click(function(){
		$("#deal").val(2);
		$("#searchForm").submit();
	});
	$("#deal3").click(function(){
		$("#deal").val(3);
		$("#searchForm").submit();
	});
	$("#deal4").click(function(){
		$("#deal").val(4);
		$("#searchForm").submit();
	});
	$("#deal5").click(function(){
		$("#deal").val(5);
		$("#searchForm").submit();
	});
	$("#deal6").click(function(){
		$("#deal").val(6);
		$("#searchForm").submit();
	});
});