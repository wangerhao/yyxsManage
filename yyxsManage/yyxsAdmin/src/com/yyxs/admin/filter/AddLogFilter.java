package com.yyxs.admin.filter;

import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;

import com.yyxs.admin.service.addlog.AddLogService;
import com.yyxs.admin.service.loginit.LogInitService;


/**
 * 权限功能日志记录拦截器
 * @author xiaolong
 * 
 */
@IocBean
public class AddLogFilter implements ActionFilter {

	private static final Logger log = Logger.getLogger(AddLogFilter.class);

	public View match(ActionContext actionContext) {
		try {
			String functionName=(String) actionContext.getRequest().getAttribute("functionName");
			String functionDesc = Mvcs.getIoc().get(LogInitService.class).getAllFunctionList().get(functionName);
			AddLogService.getInstance().addLog("已进入 《" + functionDesc + "》操作...");
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
