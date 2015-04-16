package com.yyxs.admin.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.meinvli.util.CookieMapUtil;
import com.yyxs.admin.constant.YYXSAdminConstant;



/**
 * 登录状态
 * @author power
 *
 */
@IocBean
public class SessionFilter implements ActionFilter {
	
	private static final Logger log = Logger.getLogger(SessionFilter.class);
	
	public View match(ActionContext actionContext) {
		try {
			HttpServletRequest request = actionContext.getRequest();
			CookieMapUtil cookie = new CookieMapUtil(request, actionContext.getResponse());
			String sessionId = cookie.get(YYXSAdminConstant.USER_LOGIN_SESSION_TAG);
			if(sessionId == null){
				return new ServerRedirectView("/loginShow.html");
			}
			Object user = Mvcs.getServletContext().getAttribute(sessionId);
			if(user == null){
				return new ServerRedirectView("/loginShow.html");
			}
			request.setAttribute("user", user);
		} catch (Throwable e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
}
