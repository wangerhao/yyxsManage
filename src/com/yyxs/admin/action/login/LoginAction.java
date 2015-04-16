package com.yyxs.admin.action.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.yyxs.admin.action.BaseAction;
import com.yyxs.admin.service.login.LoginService;

/**
 * 管理员登录
 * @author power
 *
 */
public class LoginAction extends BaseAction {
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	/**
	 * 登录页面显示
	 */
	@At
	@Ok("jsp:/jsp/login/login.jsp")
	public void loginShow(){}
	
	/**
	 * 登录
	 * @param userName 管理员账号
	 * @param password 密码
	 * @param request
	 * @return
	 */
	@At
	@Ok("raw")
	public String login(@Param("userName") String userName, @Param("password") String password, HttpServletRequest request){
		try {
			return LoginService.getInstance().login(userName, password, request);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		return "loginFail";	//登录失败
	}
}
