package com.yyxs.admin.service.adminmgn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.mvc.Mvcs;

import com.ht.db.DBOperate;
import com.meinvli.util.BCryptUtil;
import com.meinvli.util.CookieMapUtil;
import com.meinvli.util.DBQueryUtil;
import com.meinvli.util.DateUtil;
import com.meinvli.util.MeinvliUtil;
import com.yyxs.admin.bean.User;
import com.yyxs.admin.constant.YYXSAdminConstant;
import com.yyxs.admin.service.addlog.AddLogService;
import com.yyxs.admin.service.auth.AuthService;
import com.yyxs.admin.service.loginit.LogInitService;
import com.yyxs.admin.util.YYXSAdminUtil;

/**
 * 后台用户管理 service
 * @author xiaolong
 */
public class AdminUserManagerService {
	
	private static final DBOperate dbo = new DBOperate();
	
	private static final AdminUserManagerService adminUserManagerService = new AdminUserManagerService();
	
	private AdminUserManagerService(){}
	
	public static AdminUserManagerService getInstance(){
		return adminUserManagerService;
	}
		
	/**
	 * 获取所有后台用户
	 * @throws Throwable 
	 */
	public List<Map<Object, Object>> findAllUser() throws Throwable {
		return dbo.retrieveSQL("SELECT id,nickname,username,type,status FROM admin_user ORDER BY createtime");
	}
	
	/**
	 * 查询所有操作记录
	 * @param pageNum
	 * @param nickname
	 * @throws Throwable
	 */
	public void showAdminLogList(int pageNum,String nickname) throws Throwable {
		String sql="SELECT nickname,fname,content,time FROM admin_log ";
		List<Object> adminLogParamList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(nickname)) {
			sql="SELECT nickname,fname,content,time FROM admin_log WHERE nickname = ? ";
			adminLogParamList.add(nickname);
		}
		int logTotalNum = dbo.retrieveSQL(sql, adminLogParamList).size();
		sql += "ORDER BY time DESC LIMIT ?,?";
		adminLogParamList.add((pageNum-1) * YYXSAdminConstant.DEFAULT_MAX_RESULT);
		adminLogParamList.add(YYXSAdminConstant.DEFAULT_MAX_RESULT);
		List<Map<Object,Object>> logList = dbo.retrieveSQL(sql,adminLogParamList);
		MeinvliUtil.convertDatetimeTo_yyyyMMddhhMMss(logList, "time");
		Mvcs.getReq().setAttribute("logList", logList);	//保存操作记录信息
		Mvcs.getReq().setAttribute("nickname",nickname);		//操作人
		YYXSAdminUtil.savePageInfo(pageNum, "allLog", logTotalNum);
	}
	
	/**
	 * 删除操作记录日志
	 * @param day
	 * @throws Throwable 
	 */
	public void deleteAdminLog(int day) throws Throwable {
		String sql = "DELETE FROM admin_log WHERE time < DATE_SUB(NOW(),INTERVAL ? DAY)";
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(day);
		dbo.deleteData(sql, valueList);
		AddLogService.getInstance().addLog(day==0 ? "已经删除了今天之前的数据...." : "已删除了" + day + "天之前的数据....");	//日志记录
	}
	
	/**
	 * 查询编辑用户的权限
	 * @param userid
	 * @throws Throwable
	 * return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>> compileQuery(int userid) throws Throwable {
		List<Map<Object,Object>> adminFunctionList = AuthService.getInstance().findFunctionList();
		List<Object> adminUserFunctionList = YYXSAdminUtil.mapListToList(AuthService.getInstance().getUserFunction(userid,YYXSAdminConstant.USER_TYPE_ADMIN_ORDINARY), "id");
		for (Map<Object, Object> adminFunction : adminFunctionList) {
			if (adminUserFunctionList.contains(adminFunction.get("id"))) {
				adminFunction.put("checked","true");
			}
		}
		return adminFunctionList;
	}

	/**
	 * 编辑用户的权限
	 * @param userid			-->管理员编号
	 * @param ckFunction		-->新添加的权限	
	 * @throws dbckFunction		-->管理员剩余的权限编号集合
	 */
	public void compilePermissions(int userid, List<Object> ckFunction , List<Object> dbckFunction) throws Throwable {
		
		/** 管理员权限删除 */
		managerFunctionDelete(userid, dbckFunction);
		
		/** 管理员权限添加 */
		if(ckFunction != null && !ckFunction.isEmpty()){
			managerFunctionAdd(userid, ckFunction);
		}
	}
	
	/**
	 * 新增权限
	 * @param permissionName
	 * @param permissionAction
	 * @param permissionUrl
	 * @param permissionsType
	 * @throws Throwable 
	 */
	public void addPermissions(String permissionName, String permissionAction, String permissionUrl, int permissionsType) throws Throwable {
		List<Object> adminFunctionParam = new ArrayList<Object>();
		adminFunctionParam.add(permissionAction);
		adminFunctionParam.add(permissionUrl);
		adminFunctionParam.add(permissionName);
		adminFunctionParam.add(permissionsType);
		adminFunctionParam.add(DateUtil.getSimpleDateFormat(new Date()));
		adminFunctionParam.add(DateUtil.getSimpleDateFormat(new Date()));
		dbo.insertData("INSERT INTO admin_function(name,url,description,type,createtime,systime) VALUES(?,?,?,?,?,?)",adminFunctionParam);
		AddLogService.getInstance().addLog("已新增《"+permissionName+"》权限成功...");	//日志记录
	}

	/**
	 * 权限删除
	 * @param permissionsId
	 * @throws Throwable 
	 */
	public String deletePermissions(int permissionsId) throws Throwable {
		String sql = "SELECT description FROM admin_function WHERE id = ?";
		List<Object> adminFunctionParam = new ArrayList<Object>();
		adminFunctionParam.add(permissionsId);
		String functionName = DBQueryUtil.getStringValue(sql, adminFunctionParam, "description");
		sql = "DELETE FROM admin_function WHERE id = ?";
		dbo.deleteData(sql,adminFunctionParam);
		AddLogService.getInstance().addLog("已删除《"+functionName+"》权限.....");	//日志记录
		return "deletePermissionsSuccess";	//删除权限成功
	}
	
	/**
	 * 管理员冻结
	 * 
	 * @param userId
	 * @throws Throwable
	 */
	public String freeze(int userId) throws Throwable {
		String sql = "UPDATE admin_user SET status = ?,systime = ? WHERE id = ?";
		List<Object> freezeParamList = new ArrayList<Object>();
		freezeParamList.add(YYXSAdminConstant.USER_STATUS_FREEZEN);
		freezeParamList.add(DateUtil.getSimpleDateFormat(new Date()));
		freezeParamList.add(userId);
		dbo.updateData(sql , freezeParamList);
		String nickname = findNickname(userId);
		AddLogService.getInstance().addLog("已经冻结《" + nickname + "》管理员账号...");  //日志记录 
		return "frozenSuccess";	//解冻成功
	}

	/**
	 * 管理员解冻
	 * 
	 * @param userId
	 * @throws Throwable
	 */
	public String thaw(int userId) throws Throwable {
		String sql = "UPDATE admin_user SET status = ?,systime = ? WHERE id = ?";
		List<Object> thawParamList = new ArrayList<Object>();
		thawParamList.add(YYXSAdminConstant.USER_STATUS_THAW);
		thawParamList.add(DateUtil.getSimpleDateFormat(new Date()));
		thawParamList.add(userId);
		dbo.updateData(sql , thawParamList);
		String nickname = findNickname(userId);
		AddLogService.getInstance().addLog("已经解冻《" + nickname + "》管理员账号...");  //日志记录 
		return "thawSuccess";	//冻结成功
	}

	/**
	 * 管理员注册
	 * 
	 * @param nickName
	 * @param passWord
	 * @param userName
	 * @param ckFunction
	 * @throws Throwable
	 */
	public String reg(String nickName, String userName, String passWord,List<String> ckFunction, int adminType) throws Throwable {
		String sql = "SELECT COUNT(*) FROM admin_user WHERE username = ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userName);
		int userCount = dbo.getRecordCountSQL(sql, paramList);
		if (userCount > 0) {
			return "usernameRepeat"; // 登录名重复
		}
		/** 管理员注册 */
		int userId = managerReg(nickName, userName, passWord, adminType);
		
		/** 添加管理员权限 */
		addManagerFunction(adminType, ckFunction, userId);
		AddLogService.getInstance().addLog("新建《"+nickName+"》管理员成功...");	//日志记录
		return "regSuccess"; // 注册成功
	}
	
	/**
	 * 添加管理员
	 * @param nickName		-->管理员昵称
	 * @param userName		-->管理员账号
	 * @param passWord		-->管理员密码
	 * @param adminType		-->账号状态
	 */
	private final int managerReg(String nickName , String userName , String passWord , int adminType) throws Throwable{
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(nickName);
		valueList.add(userName);
		valueList.add(BCryptUtil.hashpw(passWord));
		valueList.add(adminType);
		valueList.add(DateUtil.getSimpleDateFormat(new Date()));
		valueList.add(YYXSAdminConstant.USER_STATUS_THAW);
		valueList.add(DateUtil.getSimpleDateFormat(new Date()));
		int userId = dbo.insertData("INSERT INTO admin_user(nickname,username,password,type,createtime,status,systime) VALUES(?,?,?,?,?,?,?)",valueList);
		return userId;
	}
	
	/**
	 * 用户表里的nickname查询
	 * @param userId
	 * @return String
	 * @throws Throwable
	 */
	public String findNickname(int userId) throws Throwable{
		List<Object> userParamList = new ArrayList<Object>();
		userParamList.add(userId);
		return DBQueryUtil.getStringValue("SELECT nickname FROM admin_user WHERE id = ?", userParamList, "nickname");
	}
	
	/**
	 * 管理员权限删除
	 * @param userid			-->管理员编号
	 * @param dbckFunction		-->管理员剩余的权限
	 */
	private final String managerFunctionDelete(int userid , List<Object> dbckFunction) throws Throwable{
		/*
		 * 将所有权限编号转换为int类型 ，最后把要删除的权限编号给取出来。可能你会想object类型难道不是通用的类型吗？为什么还要转换呢？
		 * 因为jsp页面传过来的数据表面上是object类型，其实是string类型的。为什么还要转换呢？ 因为adminUserAllFunctionList集合里的数据是int类型的
		 * 如果两个集合的数据类型不相同，是不能removeAll的，所以要把dbckFunction集合转换成int类型
		 */
		List<Object> conversiondbckFunction = intTypeConversion(dbckFunction);
		List<Object> adminUserAllFunctionList = YYXSAdminUtil.mapListToList(AuthService.getInstance().getUserFunction(userid , YYXSAdminConstant.USER_TYPE_ADMIN_ORDINARY), "id");
		adminUserAllFunctionList.removeAll(conversiondbckFunction);
		CookieMapUtil cookie = new CookieMapUtil(Mvcs.getReq(), Mvcs.getResp());
		String sessionId = cookie.get(YYXSAdminConstant.USER_LOGIN_SESSION_TAG);
		User user = (User)Mvcs.getServletContext().getAttribute(sessionId);
		String functionName = (String) Mvcs.getReq().getAttribute("functionName");
		Map<String, String> functionMap = Mvcs.getIoc().get(LogInitService.class).getAllFunctionList();
		String managerNickName = AdminUserManagerService.getInstance().findNickname(userid);
		List<String> sqlList = new ArrayList<String>();
		for (int i = 0; i < adminUserAllFunctionList.size(); i++) {
			sqlList.add("DELETE FROM admin_user_function WHERE fid = " + adminUserAllFunctionList.get(i));
			sqlList.add("INSERT INTO admin_log(userid,nickname,fname,content,time,systime) VALUES(" + user.getId() + ",'" + user.getNickName() + "','" + functionMap.get(functionName) + "','[" + user.getNickName() + "]超级管理员把[" + managerNickName + "]管理员权限id为[" + adminUserAllFunctionList.get(i) + "]的权限给删除了','" + DateUtil.getSimpleDateFormat(new Date()) + "','" + DateUtil.getSimpleDateFormat(new Date()) + "')");
		}
		dbo.batchExecute(sqlList , true);
		return "managerFunctionDeleteSuccess";		//管理员权限删除成功
	}
	
	/**
	 * 管理员权限批量添加
	 * @param userid		-->管理员编号
	 * @param ckFunction	-->添加的管理员权限
	 */
	private final String managerFunctionAdd(int userid , List<Object> ckFunction) throws Throwable{
		CookieMapUtil cookie = new CookieMapUtil(Mvcs.getReq(), Mvcs.getResp());
		String sessionId = cookie.get(YYXSAdminConstant.USER_LOGIN_SESSION_TAG);
		User user = (User)Mvcs.getServletContext().getAttribute(sessionId);
		String functionName = (String) Mvcs.getReq().getAttribute("functionName");
		Map<String, String> functionMap = Mvcs.getIoc().get(LogInitService.class).getAllFunctionList();
		String managerNickName = AdminUserManagerService.getInstance().findNickname(userid);
		List<String> sqlList = new ArrayList<String>();
		for (int i = 0; i < ckFunction.size(); i++) {
			sqlList.add("INSERT INTO admin_user_function(userid,fid,systime) VALUES(" + userid + "," + ckFunction.get(i) + ",'" + DateUtil.getSimpleDateFormat(new Date()) +"')");
			sqlList.add("INSERT INTO admin_log(userid,nickname,fname,content,time,systime) VALUES(" + user.getId() + ",'" + user.getNickName() + "','" + functionMap.get(functionName) + "','[" + user.getNickName() + "]超级管理员为[" + managerNickName + "]管理员添加了id为[" + ckFunction.get(i) + "]的权限','" + DateUtil.getSimpleDateFormat(new Date()) + "','" + DateUtil.getSimpleDateFormat(new Date()) + "')");
		}
		dbo.batchExecute(sqlList , true);
		return "managerFunctionAddSuccess";		//管理员权限新增成功
	}
	
	/**
	 * int类型转换
	 * @param ckFunction	-->权限集合
	 */
	private final List<Object> intTypeConversion(List<Object> ckFunction){
		List<Object> functionList = new ArrayList<Object>();
		for(Object ckObj : ckFunction){
			functionList.add(Integer.valueOf(ckObj.toString()));
		}
		return functionList;
	}
	
	/**
	 * 添加管理员权限	
	 * @param adminType		-->账号状态
	 * @param ckFunction	-->管理员权限
	 * @param userId		-->管理员编号
	 */
	private final String addManagerFunction(int adminType , List<String> ckFunction , int userId) throws Throwable{
		List<String> sqlList = new ArrayList<String>();
		if (adminType == YYXSAdminConstant.USER_TYPE_ADMIN_ORDINARY) {
			for (String fid : ckFunction) {
				sqlList.add("INSERT INTO admin_user_function(userid,fid,systime) VALUES(" + userId + "," + Integer.parseInt(fid) + ",'" + DateUtil.getSimpleDateFormat(new Date()) + "')");
			}
		}else if(adminType == YYXSAdminConstant.USER_TYPE_ADMIN_SYSTEM){
			List<Map<Object, Object>> functionList=dbo.retrieveSQL("SELECT id FROM admin_function");
			for (Map<Object, Object> functionMap : functionList) {
				sqlList.add("INSERT INTO admin_user_function(userid,fid,systime) VALUES(" + userId + "," + Integer.parseInt(functionMap.get("id").toString()) + ",'" + DateUtil.getSimpleDateFormat(new Date()) + "')");
			}
		}
		dbo.batchExecute(sqlList, true);
		return "addManagerFunctionSuccess";
	}
}
