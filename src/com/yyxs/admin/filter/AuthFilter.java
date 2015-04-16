package com.yyxs.admin.filter;

import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;

import com.meinvli.util.CookieMapUtil;
import com.yyxs.admin.bean.User;
import com.yyxs.admin.constant.YYXSAdminConstant;


/**
 * 权限验证
 * @author wangxin
 *
 */
@IocBean
public class AuthFilter implements ActionFilter {
	
	private static final Logger log = Logger.getLogger(AuthFilter.class);
		
	public View match(ActionContext context) {
		try {
			CookieMapUtil cookie = new CookieMapUtil(context.getRequest(), context.getResponse());
			String sessionId = cookie.get(YYXSAdminConstant.USER_LOGIN_SESSION_TAG);
			User user = (User)Mvcs.getServletContext().getAttribute(sessionId);
			String functionName = context.getPath().split("/")[1]; //从请求路径中截取功能名称
			if(!user.getAuthList().contains(functionName)){
				return new JspView("/jsp/auth/authError.jsp");
			}
			context.getRequest().setAttribute("functionName", functionName);
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
