package com.yyxs.admin.action.menu;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.yyxs.admin.action.BaseAction;
import com.yyxs.admin.filter.SessionFilter;
import com.yyxs.admin.service.menu.MenuService;

/**
 * 管理主菜单
 * @author power
 *
 */
@Filters({@By(type=SessionFilter.class)})
public class MenuAction extends BaseAction {
	
	private static final Logger log = Logger.getLogger(MenuAction.class);
	
	/**
	 * 获取当前登录管理员的可操作功能列表
	 * @param request
	 */
	@At
	@Ok("jsp:/jsp/menu/menu.jsp")
	public void menu(HttpServletRequest request){
		try {
			request.setAttribute("menuList", MenuService.getInstance().MenuList(loginUser(request)));
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}
}
