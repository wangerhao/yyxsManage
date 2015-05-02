package com.yyxs.admin.constant;

public class YYXSAdminConstant {
	
	/**后台系统普通管理员标识*/
	public static final int USER_TYPE_ADMIN_ORDINARY = 0;
	
	/**后台系统超级管理员标识*/
	public static final int USER_TYPE_ADMIN_SYSTEM = 1;
	
	/**存储在客户端cookie中用于标识唯一登录用户的键*/
	public static final String USER_LOGIN_SESSION_TAG = "loginUser";
	
	/**菜单类别：yyxs数据管理*/
	public static final int MENU_TYPE_WEBSITE_DATA_MANAGE = 1;
	
	/**菜单类别：yyxs数据审查*/
	public static final int MENU_TYPE_WEBSITE_DATA_EXAMINE = 2;
	
	/**菜单类别：yyxs数据设置*/
	public static final int MENU_TYPE_WEBSITE_DATA_SET = 3;
	
	/**菜单类别：后台数据管理*/
	public static final int MENU_TYPE_BACKSTAGE_DATA_MANAGE = 4;
	
	/**每页显示的行数*/
	public static final int DEFAULT_MAX_RESULT = 10;
	
	/**普通用户解冻状态标识，当前状态为：正常*/
	public static final int USER_STATUS_THAW = 0;
	
	/**普通用户冻结状态标识，当前状态为：已冻结*/
	public static final int USER_STATUS_FREEZEN = 1;
	
	/** 日期格式标示 */
	public static String DEFAULT_FORMAT = "yyyy.MM.dd HH:mm:ss";
	public static String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
	public static String FORMAT_yyyy_MM = "yyyy-MM";
	
	/** 失败订单状态标识 */
	public static int FAIL_ORDER_STATUS = 5;
	
	/** 待处理订单状态标识 */
	public static int TO_PROCESSED_ORDER_STATUS = 1;
	
	/** 待发货订单状态标识 */
	public static int TO_SEND_GOODS_ORDER_STATUS = 2;
	
	/** 已发货订单状态标识 */
	public static int HAS_SENT_GOODS_ORDER_STATUS = 3;
	
	/** 已完成订单状态标识 */
	public static int HAS_COMPLETED_ORDER_STATUS = 4;
	
	/** 卫裤L型号标示 */
	public static int WEIKU_L_MODEL = 1;
	
	/** 卫裤XL型号标示 */
	public static int WEIKU_XL_MODEL = 2;
	
	/** 卫裤XXL型号标示*/
	public static int WEIKU_XXL_MODEL = 3;
}
