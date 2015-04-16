(function($){
	
	if(typeof $.mnlpaging == "undefined"){
		$.mnlpaging = {};
	}
	
	if(typeof $.mnlpaging.paging == "undefined"){
		$.mnlpaging.paging = {};
	}
	
	/** 分页action跳转 */
	$.mnlpaging.paging.pagingActionRedirect = function(){
		if($("#dataSaveType").val() == "allOrder"){
			$("#fmSearch").attr("action","/goodsorder.html");
		}
		if($("#dataSaveType").val() == "search" ){
//			$("searchForm").attr("action","/goodsorder/searchOrder.html");
//			$("#searchForm").submit();
			
			$("#fmSearch").attr("action","/goodsorder/searchOrder.html");
			$("#searchType2").val($("#searchType").val());
			$("#deal2").val($("#deal").val());
			$("#searchWords2").val($("#searchWords").val());
		}
	}
})(jQuery);
$(function() {
	
	/**
	 * 分页插件 （查看操作记录功能）
	 */
	if ($("#pageDiv").attr("id") == "pageDiv") {
		$("#pageDiv").smartpaginator( {
			"totalrecords" : $("#totalNum").val(),
			"recordsperpage" : $("#pageMaxResult").val(),
			"initval" : $("#currentPage").val(),
			onchange : function(currentPage) {
				$("#currentPage").val(currentPage);
				$.mnlpaging.paging.pagingActionRedirect();
				$("#fmSearch").submit(); 	//分页查询表单提交
			}
		});
	}
});