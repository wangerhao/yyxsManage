package com.yyxs.admin.action;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.IocBean;

import com.yyxs.admin.bean.User;


/**
 * action都需要继承该类，提供一些公用方法并且可以继承@IocBean
 * @author power
 *
 */
@IocBean
public class BaseAction {
	
	/**
	 * 当前登录用户
	 * @return
	 */
	protected User loginUser(HttpServletRequest request){
		return (User)request.getAttribute("user");
	}
}
