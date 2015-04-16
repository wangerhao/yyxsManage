package com.yyxs.admin.service.log;

import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

import com.yyxs.admin.service.auth.AuthService;
import com.yyxs.admin.util.YYXSAdminUtil;

/**
 * 日志记录  存入和取出
 * @author power
 */
@IocBean(create="init")
public class LogService {
	
	/**
	 * 初始化时存入所有权限
	 * @throws Throwable
	 */
	public void init() throws Throwable{
		Mvcs.getServletContext().setAttribute("allFunctionList", YYXSAdminUtil.mapListToMap(AuthService.getInstance().allFindFunctionList(), "name", "description"));
	}
	
	/**
	 * 取所有权限
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getAllFunctionList(){
		return (Map<String, String>) Mvcs.getServletContext().getAttribute("allFunctionList");
	}
}
