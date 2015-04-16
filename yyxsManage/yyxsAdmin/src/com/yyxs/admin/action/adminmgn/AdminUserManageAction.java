package com.yyxs.admin.action.adminmgn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.yyxs.admin.action.BaseAction;
import com.yyxs.admin.filter.AddLogFilter;
import com.yyxs.admin.filter.AuthFilter;
import com.yyxs.admin.filter.SessionFilter;
import com.yyxs.admin.service.adminmgn.AdminUserManagerService;
import com.yyxs.admin.service.auth.AuthService;

/**
 * 
 * 后台用户管理模块
 * @author power
 * 
 */
@At("/adminmgn")
@Filters({ @By(type = SessionFilter.class), @By(type = AuthFilter.class)})
public class AdminUserManageAction extends BaseAction {
	
	private static final Logger log = Logger.getLogger(AdminUserManageAction.class);

	/**
	 * 所有管理员显示 （管理员展示功能）
	 * @param request
	 */
	@At("/")
	@Ok("jsp:/jsp/adminmgn/adminUserList.jsp")
	@Filters({ @By(type = SessionFilter.class), @By(type = AuthFilter.class),@By(type = AddLogFilter.class)})
	public void adminUserList(){
		try {
			Mvcs.getReq().setAttribute("userList", AdminUserManagerService.getInstance().findAllUser());
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 所有权限查询  （新建管理员账号功能）
	 * @throws Throwable
	 */
	@At
	@Ok("jsp:/jsp/adminmgn/addAdminmgnAccount.jsp")
	public void showAdminManagePage(){
		try {
			Mvcs.getReq().setAttribute("functionList", AuthService.getInstance().findFunctionList());
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询操作记录  （查看操作记录功能）
	 * @param pageNum
	 * @param nickname
	 * @throws Throwable 
	 */
	@At
	@Ok("jsp:/jsp/adminmgn/list_log.jsp")
	public void showAdminLog(@Param("pageNum") int pageNum,@Param("nickname") String nickname){
		try {
			AdminUserManagerService.getInstance().showAdminLogList(pageNum == 0 ? 1 : pageNum,nickname);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询所有权限（管理操作权限功能）
	 * @param request
	 * @throws Throwable 
	 */
	@At
	@Ok("jsp:/jsp/adminmgn/functionList.jsp")
	public void allFunctionList(HttpServletRequest request){
		try {
			request.setAttribute("functionList",AuthService.getInstance().allFindFunctionList());
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 新建管理员账号
	 * @param nickName
	 * @param userName
	 * @param passWord
	 * @param ckFunction
	 */
	@At
	@Ok("raw")
	public String addAdminUser(@Param("nickName") String nickName,@Param("userName") String userName,@Param("passWord") String passWord,@Param("ckFunction") List<String> ckFunction,@Param("adminType") int adminType) {
		try {
			return AdminUserManagerService.getInstance().reg(nickName, userName, passWord, ckFunction, adminType);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		return "regFail"; // 注册失败
	}

	/**
	 * 管理员用户冻结 （管理员展示功能）
	 * @param userId
	 */
	@At
	@Ok("raw")
	public String freezeAdminmgnUser(@Param("userId") int userId){
		try {
			return AdminUserManagerService.getInstance().freeze(userId);	//冻结成功
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			return "frozenError";		//冻结失败，前台没有验证，如果日后有时间再改
		}	
	}

	/**
	 * 管理员用户解冻 （管理员展示功能）
	 * @param userId
	 * @throws Throwable
	 */
	@At
	@Ok("raw")
	public String thawAdminmgnUser(@Param("userId") int userId){
		try {
			return AdminUserManagerService.getInstance().thaw(userId);	//解冻成功
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			return "thawError";		//解冻失败，前台没有验证，如果日后有时间再改
		}		
	}
	
	/**
	 * 查询编辑用户的权限 （管理员展示功能）
	 * @param userid
	 * @param username
	 * @param request
	 * @throws Throwable 
	 */
	@At
	@Ok("jsp:/jsp/adminmgn/compilePermissions.jsp")
	public void queryPermissions(@Param("userid") int userid,@Param("username") String username,HttpServletRequest request){
		try {
			request.setAttribute("permissionsList", AdminUserManagerService.getInstance().compileQuery(userid));
			request.setAttribute("username", username);
			request.setAttribute("userid", userid);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 编辑用户的权限 （管理员展示功能）
	 * @param userid			-->管理员编号
	 * @param ckFunction		-->新添加的权限	
	 * @throws dbckFunction		-->管理员剩余的权限编号集合
	 */
	@At
	public View compilePermissions(@Param("userid") int userid , @Param("ckFunction") List<Object> ckFunction , @Param("dbckFunction") List<Object> dbckFunction){
		try {
			AdminUserManagerService.getInstance().compilePermissions(userid, ckFunction , dbckFunction);
			return new ServerRedirectView("/adminmgn.html");
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			return null;		//日后有时间再改
		}
		
	}
	
	/**
	 * 操作记录删除 （查看操作记录功能）
	 * @param day
	 * @throws Throwable 
	 */
	@At
	@Ok(">>:/adminmgn/showAdminLog.html")
	public void deleteAdminLog(@Param("day") int day){
		try {
			AdminUserManagerService.getInstance().deleteAdminLog(day);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 删除权限（管理操作权限功能）
	 * @param permissionsId
	 * @throws Throwable 
	 * @throws NumberFormatException 
	 * @return View
	 */
	@At
	@Ok("raw")
	public String deletePermissions(@Param("permissionsId") int permissionsId){
		try {
			return AdminUserManagerService.getInstance().deletePermissions(permissionsId);	//删除权限成功
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			return "deletePermissionsError";		//删除权限错误，前台没有验证，日后有时间再改
		} 
	}
	
	/**
	 * 新增权限 （管理操作权限里的添加新权限功能）
	 * @param permissionName
	 * @param permissionAction
	 * @param permissionUrl
	 * @param permissionsType
	 * @return String
	 */
	@At
	@Ok("raw")
	public String addPermissions(@Param("permissionName") String permissionName,@Param("permissionAction") String permissionAction,@Param("permissionUrl") String permissionUrl,@Param("permissionsType") int permissionsType){
		try {
			AdminUserManagerService.getInstance().addPermissions(permissionName, permissionAction, permissionUrl, permissionsType);
			return "addPermissionsSuccess";  //新增权限成功
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			return "addPermissionsFail";	//新增权限失败
		}
		
	}
	
	/**
	 * 打开新增权限jsp页面 （管理操作权限里的添加新权限功能）
	 */
	@At
	@Ok("jsp:/jsp/adminmgn/addPermissions.jsp")
	public void showPermissionsJsp(){}
	
	/**
	 * 打开错误信息jsp页面
	 */
	@At
	@Ok("jsp:/error.jsp")
	public void error(){}
	
}
