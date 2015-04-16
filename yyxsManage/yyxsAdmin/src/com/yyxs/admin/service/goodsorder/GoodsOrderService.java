package com.yyxs.admin.service.goodsorder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ht.db.DBOperate;
import com.yyxs.admin.constant.YYXSAdminConstant;

/**
 * 商品订单service
 * @author power
 */
public class GoodsOrderService {

	private DBOperate dbo = new DBOperate();
	
	private static GoodsOrderService goodsOrderService = new GoodsOrderService();
	
	private GoodsOrderService(){}
	
	public static GoodsOrderService getInstance(){
		return goodsOrderService;
	}
	
	/**
	 * 获取所有订单 
	 */
	public List<Map<Object, Object>> allGoodsOrderShow(int currentPage)throws Throwable{
		String sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id ORDER BY systime DESC LIMIT ?,?";
		List<Object> paramList = Arrays.asList((Object)((currentPage - 1) * YYXSAdminConstant.DEFAULT_MAX_RESULT), YYXSAdminConstant.DEFAULT_MAX_RESULT);
		return dbo.retrieveSQL(sql, paramList);
	}
	/**
	 * 根据查询条件,获取所有订单 
	 * @param searchWords 
	 * @param searchType 
	 * @param deal 
	 */
	public List<Map<Object, Object>> searchGoodsOrder(int currentPage, String searchWords, int searchType, int deal)throws Throwable{
		String sql = null;
		List<Object> paramList = null;
		if(searchType == 1){
			sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and g.good_name like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
			if(deal > 0){
				sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and g.good_name like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
				if(deal == 6){
					sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and date(go.systime)=curdate() and g.good_name like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
				}
			}
			paramList = Arrays.asList((Object)((currentPage - 1) * YYXSAdminConstant.DEFAULT_MAX_RESULT), YYXSAdminConstant.DEFAULT_MAX_RESULT);
		}else if(searchType == 2){
			sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.consignee like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
			if(deal > 0){
				sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and go.consignee like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
				if(deal == 6){
					sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and date(go.systime)=curdate() and go.consignee like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
				}
			}
			paramList = Arrays.asList((Object)((currentPage - 1) * YYXSAdminConstant.DEFAULT_MAX_RESULT), YYXSAdminConstant.DEFAULT_MAX_RESULT);
		}else if(searchType == 3){
			sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.mobile_phone_number like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
			if(deal > 0){
				sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and go.mobile_phone_number like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
				if(deal == 6){
					sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and and date(go.systime)=curdate() and go.mobile_phone_number like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
				}
			}
			paramList = Arrays.asList((Object)((currentPage - 1) * YYXSAdminConstant.DEFAULT_MAX_RESULT), YYXSAdminConstant.DEFAULT_MAX_RESULT);
		}else if(searchType == 4){
			sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.consignee like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
			if(deal > 0){
				sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and go.consignee like '%"+searchWords+"%' ORDER BY systime DESC LIMIT ?,?";
			}
			paramList = Arrays.asList((Object)((currentPage - 1) * YYXSAdminConstant.DEFAULT_MAX_RESULT), YYXSAdminConstant.DEFAULT_MAX_RESULT);
		}else{
			sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id ORDER BY systime DESC LIMIT ?,?";
			if(deal > 0){
				sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+"  ORDER BY systime DESC LIMIT ?,?";
				if(deal == 6){
					sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.order_status,go.systime FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and date(go.systime)=curdate() ORDER BY systime DESC LIMIT ?,?";
				}
			}
			paramList = Arrays.asList((Object)((currentPage - 1) * YYXSAdminConstant.DEFAULT_MAX_RESULT), YYXSAdminConstant.DEFAULT_MAX_RESULT);
		}
		return dbo.retrieveSQL(sql, paramList);
	}
	/**
	 * 根据查询条件,获取所有订单数量
	 * @param searchWords 
	 * @param searchType 
	 * @param deal 
	 */
	public int searchGoodsOrderCount(String searchWords, int searchType, int deal)throws Throwable{
		String sql = null;
		if(searchType == 1){
			sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and g.good_name like '%"+searchWords+"%'  ";
			if(deal > 0){
				sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and g.good_name like '%"+searchWords+"%'  ";
				if(deal == 6){
					sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and date(go.systime)=curdate() and g.good_name like '%"+searchWords+"%'  ";
				}
			}
		}else if(searchType == 2){
			sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.consignee like '%"+searchWords+"%'  ";
			if(deal > 0){
				sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and go.consignee like '%"+searchWords+"%'  ";
				if(deal == 6){
					sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and date(go.systime)=curdate() and go.consignee like '%"+searchWords+"%'  ";
				}
			}
		}else if(searchType == 3){
			sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.mobile_phone_number like '%"+searchWords+"%'  ";
			if(deal > 0){
				sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and go.mobile_phone_number like '%"+searchWords+"%'  ";
				if(deal == 6){
					sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and and date(go.systime)=curdate() and go.mobile_phone_number like '%"+searchWords+"%'  ";
				}
			}
		}else if(searchType == 4){
			sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.consignee like '%"+searchWords+"%'  ";
			if(deal > 0){
				sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+" and go.consignee like '%"+searchWords+"%'  ";
			}
		}else{
			sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id  ";
			if(deal > 0){
				sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and go.order_status="+deal+"   ";
				if(deal == 6){
					sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id and date(go.systime)=curdate()  ";
				}
			}
		}
		
		return dbo.getRecordCountSQL(sql);
	}

	/**
	 * 商品订单总个数
	 * @throws Throwable 
	 */
	public int goodsOrderTotalNum() throws Throwable {
		String sql = "SELECT count(*) FROM yyxs_goods_order go,yyxs_goods g WHERE g.id = go.good_id";
		return dbo.getRecordCountSQL(sql);
	}

	/**
	 * 物流信息
	 * @param orderid		-->商品id
	 * @throws Throwable
	 */
	public List<Map<Object, Object>> wuLiuInfo(int orderid) throws Throwable {
		String sql = "SELECT w.id,go.detailed_address,go.order_status,go.order_status_note,w.wuliu_name,w.wuliu_number,w.wuliu_price,g.good_name FROM yyxs_goods_order go,yyxs_wuliu w,yyxs_goods g WHERE go.good_id=g.id and go.id = w.order_id and w.order_id = ?";
		List<Object> paramList = Arrays.asList((Object)orderid);
		return dbo.retrieveSQL(sql, paramList);
	}
	
	/**
	 * 获取订单信息
	 * @param orderid		-->订单id
	 * @throws Throwable
	 */
	public List<Map<Object, Object>> getOrderInfo(int orderid) throws Throwable{
		String sql = "SELECT go.detailed_address,g.good_name FROM yyxs_goods_order go,yyxs_goods g WHERE go.good_id=g.id and go.id=?";
		List<Object> paramList = Arrays.asList((Object)orderid);
		return dbo.retrieveSQL(sql, paramList);
	}

	/**
	 * 商品和商品订单和物流数据
	 * @param orderId	-->订单id
	 * @throws Throwable 
	 */
	public List<Map<Object, Object>> goodsAndGoodsOrderAndWuLiuData(int orderId) throws Throwable {
		String sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.goods_number,go.consignee,go.mobile_phone_number,go.detailed_address,go.order_status,go.order_status_note,w.id as wid,w.wuliu_name,w.wuliu_number,w.wuliu_price FROM yyxs_goods g,yyxs_goods_order go,yyxs_wuliu w WHERE go.id = ? and go.id = w.order_id and go.good_id = g.id";
		List<Object> paramList = Arrays.asList((Object)orderId);
		return dbo.retrieveSQL(sql, paramList);
	}

	/**
	 * 商品和商品订单数据
	 * @param orderId	-->订单id
	 * @throws Throwable 
	 */
	public List<Map<Object, Object>> goodsAndGoodsOrdeData(int orderId) throws Throwable {
		String sql = "SELECT g.id as gid,g.good_name,go.money,go.id as goid,go.consignee,go.detailed_address,go.goods_number,go.mobile_phone_number,go.order_status,go.order_status_note FROM yyxs_goods g,yyxs_goods_order go WHERE g.id = go.good_id and go.id = ?";
		List<Object> paramList = Arrays.asList((Object)orderId);
		return dbo.retrieveSQL(sql, paramList);
	}

	/**
	 * 订单信息修改
	 * @param sqlList
	 * @throws Throwable 
	 */
	public void orderInfoUpdate(List<String> sqlList) throws Throwable {
		dbo.batchExecute(sqlList, true);
	}

	/**
	 * 订单物流个数
	 * @param orderId	-->订单个数
	 * @throws Throwable 
	 */
	public int orderWuLiuCount(int orderId) throws Throwable {
		String sql = "SELECT count(*) FROM yyxs_wuliu WHERE order_id = ?";
		List<Object> paramList = Arrays.asList((Object)orderId);
		return dbo.getRecordCountSQL(sql, paramList);
	}
}
