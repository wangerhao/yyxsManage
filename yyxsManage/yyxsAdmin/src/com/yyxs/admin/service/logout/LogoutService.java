package com.yyxs.admin.service.logout;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.Mvcs;

import com.meinvli.util.CookieMapUtil;
import com.yyxs.admin.constant.YYXSAdminConstant;

/**
 * 登出
 * @author power
 *
 */
public class LogoutService {
	
	private static final LogoutService logoutService = new LogoutService();
	
	private LogoutService(){}
	
	public static LogoutService getInstance(){
		return logoutService;
	}
	
	/**
	 * 用户登出
	 * @param request
	 * @throws Throwable 
	 */
	public void logout(HttpServletRequest request) throws Throwable{
		CookieMapUtil cookie = new CookieMapUtil(request, Mvcs.getResp());
		String sessionId = cookie.get(YYXSAdminConstant.USER_LOGIN_SESSION_TAG);
		Mvcs.getServletContext().removeAttribute(sessionId);
		request.getSession().invalidate();
	}
}
