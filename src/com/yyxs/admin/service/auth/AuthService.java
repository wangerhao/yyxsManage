package com.yyxs.admin.service.auth;

import java.util.List;
import java.util.Map;

import com.ht.db.DBOperate;
import com.meinvli.util.MeinvliUtil;
import com.yyxs.admin.constant.YYXSAdminConstant;
import com.yyxs.admin.util.YYXSAdminUtil;

/**
 * 功能权限
 * @author power
 *
 */
public class AuthService {
	
	private static final DBOperate dbo = new DBOperate();
	
	private static final AuthService authService = new AuthService();
	
	private AuthService(){}
	
	public static AuthService getInstance(){
		return authService;
	}
	
	/**
	 * 获取该管理员的权限列表
	 * @param userId 管理员Id
	 * @param userType 管理员类型
	 * @return
	 * @throws Throwable
	 */
	public List<Map<Object,Object>> getUserFunction(int userId, int userType) throws Throwable{
		if(userType==YYXSAdminConstant.USER_TYPE_ADMIN_SYSTEM){
			return getFunctionList();
		}
		String sql= "SELECT af.id,af.name,af.url,af.description,af.type,af.seq FROM admin_function af,admin_user_function auf WHERE af.id = auf.fid AND auf.userid = " + userId + " ORDER BY af.seq";
		return dbo.retrieveSQL(sql);
	}
	
	/**
	 * 获取功能权限列表
	 * @return
	 * @throws Throwable
	 */
	public List<Map<Object,Object>> getFunctionList() throws Throwable{
		String sql = "SELECT id,name,url,description,type,seq,createtime FROM admin_function ORDER BY seq";
		return dbo.retrieveSQL(sql);
	}
	
	/**
	 * 获取该管理员权限名称列表
	 * @param userId 管理员Id
	 * @param userType	管理员类型
	 */
	public List<Object> getUserFunctionName(int userId, int userType) throws Throwable{
		List<Map<Object,Object>> list = getUserFunction(userId, userType);
		return YYXSAdminUtil.mapListToList(list, "name");
	}
	
	/**
	 * 获取所有权限,除了 后台用户管理
	 * @throws Throwable 
	 */
	public List<Map<Object,Object>> findFunctionList() throws Throwable {
		return dbo.retrieveSQL("SELECT id,name,description,createtime FROM admin_function WHERE name != 'adminmgn'");
	}
	
	/**
	 * 获取所有权限
	 * @throws Throwable 
	 */
	public List<Map<Object,Object>> allFindFunctionList() throws Throwable {
		List<Map<Object, Object>> functionList = getFunctionList();
		MeinvliUtil.convertDatetimeTo_yyyyMMddhhMMss(functionList, "createtime"); 	//时间类型转换
		return functionList;
	}
}
