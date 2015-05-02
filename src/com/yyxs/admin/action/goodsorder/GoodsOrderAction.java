package com.yyxs.admin.action.goodsorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.meinvli.util.MeinvliUtil;
import com.yyxs.admin.filter.AddLogFilter;
import com.yyxs.admin.filter.AuthFilter;
import com.yyxs.admin.filter.SessionFilter;
import com.yyxs.admin.service.goodsorder.GoodsOrderService;
import com.yyxs.admin.util.YYXSAdminUtil;

/**
 * 商品订单action
 * @author power
 */
@At("/goodsorder")
@Filters({ @By(type = SessionFilter.class), @By(type = AuthFilter.class)})
public class GoodsOrderAction {

	/**
	 * 所有商品订单展示
	 */
	@At("/")
	@Ok("jsp:/jsp/goodsorder/allGoodsOrderShow.jsp")
	@Filters({ @By(type = SessionFilter.class), @By(type = AuthFilter.class),@By(type = AddLogFilter.class)})
	public void allGoodsOrderShow(@Param("currentPage") int currentPage){
		try {
			int total = 0;
			total = GoodsOrderService.getInstance().goodsOrderTotalNum();
			List<Map<Object, Object>> goodsOrderList = GoodsOrderService.getInstance().allGoodsOrderShow(currentPage == 0 ? 1 : currentPage);
			MeinvliUtil.convertDatetimeTo_yyyyMMddhhMMss(goodsOrderList, "systime");
			Mvcs.getReq().setAttribute("goodsOrderList", goodsOrderList);
			YYXSAdminUtil.savePageInfo(currentPage, "allOrder",total);
		} catch (Throwable e) {
			YYXSAdminUtil.log(e.getMessage(), e);
		}
	}
	
	/**
	 * 物流信息
	 */
	@At
	@Ok("raw")
	public String wuLiuInfo(@Param("goodsid") int orderid){
		try {
			List<Map<Object, Object>> wuLiuInfoList = GoodsOrderService.getInstance().wuLiuInfo(orderid);
			if(wuLiuInfoList.size() <= 0){
				wuLiuInfoList = GoodsOrderService.getInstance().getOrderInfo(orderid);
			}
			if(wuLiuInfoList.size() <= 0){
				return StringUtils.EMPTY;
			}
			Map<Object, Object> wuLiuInfoMap = wuLiuInfoList.get(0);
			return new JSONObject(wuLiuInfoMap).toString();
		} catch (Throwable e) {
			YYXSAdminUtil.log(e.getMessage(), e);
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 添加新订单展示
	 */
	@At
	@Ok("jsp:/jsp/goodsorder/addNewOrder.jsp")
	@Filters({ @By(type = SessionFilter.class), @By(type = AuthFilter.class),@By(type = AddLogFilter.class)})
	public void addNewOrderShow(){}
	
	/**
	 * 商品信息修改展示
	 * @param orderId		-->订单id
	 * @param currentPage	-->当前页
	 * @param deal		
	 */
	@At
	@Ok("jsp:/jsp/goodsorder/goodsOrderInfoUpdate.jsp")
	@Filters({ @By(type = SessionFilter.class), @By(type = AuthFilter.class),@By(type = AddLogFilter.class)})
	public void goodsOrderInfoUpdateShow(@Param("orderId") int orderId, @Param("currentPage") int currentPage, @Param("dataSaveType") String dataSaveType, @Param("deal") int deal){
		try {
			List<Map<Object, Object>> dataList = GoodsOrderService.getInstance().goodsAndGoodsOrderAndWuLiuData(orderId);
			if(dataList.size() <= 0){
				dataList = GoodsOrderService.getInstance().goodsAndGoodsOrdeData(orderId);
			}
			Mvcs.getReq().setAttribute("dataMap", dataList.get(0));
			Mvcs.getReq().setAttribute("currentPage", currentPage);
			Mvcs.getReq().setAttribute("dataSaveType", dataSaveType);
			Mvcs.getReq().setAttribute("deal", deal);
		} catch (Throwable e) {
			YYXSAdminUtil.log(e.getMessage(), e);
		}
	}
	
	/**
	 * 订单信息修改
	 * @param goodsNumber			-->商品个数
	 * @param money					-->商品价钱
	 * @param consignee				-->收货人
	 * @param mobilePhoneNumber		-->收货人电话
	 * @param detailedAddress		-->收货地址
	 * @param orderStatus			-->订单状态
	 * @param orderStatusNote		-->订单状态备注
	 * @param wuliuName				-->物流名称
	 * @param wuliuNumber			-->物流单号
	 * @param wuliuPrice			-->物流价格
	 */
	@At
	@Ok("raw")
	public String orderInfoUpdate(@Param("orderId") int orderId,@Param("goodsNumber") int goodsNumber, @Param("money") double money, @Param("orderType") int orderType, @Param("consignee") String consignee, @Param("mobilePhoneNumber") String mobilePhoneNumber, @Param("detailedAddress") String detailedAddress, @Param("orderStatus") int orderStatus, @Param("orderStatusNote") String orderStatusNote, @Param("wuliuName") String wuliuName, @Param("wuliuNumber") String wuliuNumber, @Param("wuliuPrice") double wuliuPrice){
		try {
			List<String> sqlList = new ArrayList<String>();
			sqlList.add("update yyxs_goods_order set goods_number = " + goodsNumber + ",money = " + money + ",order_type = " + orderType + ",consignee = '" + consignee + "',mobile_phone_number = " + mobilePhoneNumber + ",detailed_address = '" + detailedAddress + "',order_status = " + orderStatus + ",order_status_note = '" + orderStatusNote + "' where id = " + orderId);
			int count = GoodsOrderService.getInstance().orderWuLiuCount(orderId);
			if(count <= 0){
				sqlList.add("insert into yyxs_wuliu(wuliu_name,wuliu_number,wuliu_price,order_id) values('" + wuliuName + "','" + wuliuNumber + "'," + wuliuPrice + "," + orderId + ")");
			} else {
				sqlList.add("update yyxs_wuliu set wuliu_name = '" + wuliuName + "',wuliu_number = '" + wuliuNumber + "',wuliu_price = " + wuliuPrice + " where order_id = " + orderId);
			}
			GoodsOrderService.getInstance().orderInfoUpdate(sqlList);
			
			return "success";
		} catch (Throwable e) {
			YYXSAdminUtil.log(e.getMessage(), e);
			return "fail";
		}
	}
	
	/**
	 * 内容搜索
	 * @param searchType		-->搜索类型(1:商品名称、2：收货人、3：电话)
	 * @param searchWords		-->搜索的内容
	 * @param currentPage		-->订单首页面的当前页
	 * @param deal
	 */
	@At
	@Ok("jsp:/jsp/goodsorder/allGoodsOrderShow.jsp")
	public void searchOrder(@Param("searchType") int searchType,@Param("searchWords") String searchWords,@Param("currentPage") int currentPage,@Param("deal") int deal){
		try {
			int total = 0;
			currentPage = currentPage == 0 ? 1 : currentPage;
			total = GoodsOrderService.getInstance().searchGoodsOrderCount(searchWords,searchType,deal);
			List<Map<Object, Object>> goodsOrderList = GoodsOrderService.getInstance().searchGoodsOrder(currentPage,searchWords,searchType,deal);
			MeinvliUtil.convertDatetimeTo_yyyyMMddhhMMss(goodsOrderList, "systime");
			Mvcs.getReq().setAttribute("goodsOrderList", goodsOrderList);
			Mvcs.getReq().setAttribute("searchWords", searchWords);
			Mvcs.getReq().setAttribute("searchType", searchType);
			Mvcs.getReq().setAttribute("deal", deal);
			YYXSAdminUtil.savePageInfo(currentPage, "search", total);
		} catch (Throwable e) {
			YYXSAdminUtil.log(e.getMessage(), e);
		}
	}
	
}
