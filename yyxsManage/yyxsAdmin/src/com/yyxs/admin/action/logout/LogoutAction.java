package com.yyxs.admin.action.logout;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.yyxs.admin.action.BaseAction;
import com.yyxs.admin.filter.SessionFilter;
import com.yyxs.admin.service.addlog.AddLogService;
import com.yyxs.admin.service.logout.LogoutService;

/**
 * 登出
 * @author wangxin
 *
 */
@Filters({@By(type=SessionFilter.class)})
public class LogoutAction extends BaseAction{
	
	private static final Logger log = Logger.getLogger(LogoutService.class);
	
	/**
	 * 用户登出
	 * @param request
	 * @throws Throwable 
	 */
	@At
	@Ok(">>:/loginShow.html")
	public void logout(HttpServletRequest request){
		try {
			LogoutService.getInstance().logout(request);
			AddLogService.getInstance().addLog("[" + loginUser(request).getNickName() + "]的管理员已经登出配配后台管理系统。");
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}
}
