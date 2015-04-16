package com.yyxs.admin.action.mgnpasswordupdate;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.yyxs.admin.action.BaseAction;
import com.yyxs.admin.filter.SessionFilter;
import com.yyxs.admin.service.mgnpasswordupdate.ManagerPasswordUpdateService;

/**
 * 管理员密码更改
 * @author power
 */
@At("/mgnpwdupdate")
@Filters({ @By(type = SessionFilter.class)})
public class ManagerPasswordUpdateAction extends BaseAction {

	private static final Logger log = Logger.getLogger(ManagerPasswordUpdateAction.class);
	
	/**
	 * 管理员密码更改jsp数据展示
	 * @param request
	 */
	@At("/")
	@Ok("jsp:/jsp/mgnpwdupdate/ManagerPasswordUpdate.jsp")
	public void managerPasswordUpdateJspDataShow(HttpServletRequest request){
		request.setAttribute("mgnuser", loginUser(request));
	}
	
	/**
	 * 管理员密码更改
	 * @param mgnuserid		-->管理员用户编号
	 * @param oldMgnPwd		-->旧管理员密码
	 * @param newMgnPwd		-->新管理员密码
	 */
	@At
	@Ok("raw")
	public String managerPasswordUpdate(@Param("mgnuserid") int mgnuserid , @Param("oldMgnPwd") String oldMgnPwd , @Param("newMgnPwd") String newMgnPwd){
		try {
			return ManagerPasswordUpdateService.getInstance().managerPasswordUpdate(mgnuserid , oldMgnPwd , newMgnPwd);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			return "mgnPwdUpdateError";		//管理员密码更改失败
		}
	}
	
}
