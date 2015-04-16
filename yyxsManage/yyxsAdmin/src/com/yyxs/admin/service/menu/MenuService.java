package com.yyxs.admin.service.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yyxs.admin.bean.MenuType;
import com.yyxs.admin.bean.User;
import com.yyxs.admin.constant.YYXSAdminConstant;
import com.yyxs.admin.service.auth.AuthService;

/**
 * 管理菜单service
 * @author power
 *
 */
public class MenuService {
	
	private static final MenuService menuService = new MenuService();
	
	private MenuService(){}
	
	public static MenuService getInstance(){
		return menuService;
	}
	
	public List<MenuType> MenuList(User user) throws Throwable{
		List<MenuType> menuList = new ArrayList<MenuType>();
		menuList.add(getMenuType("yyxs数据管理", YYXSAdminConstant.MENU_TYPE_WEBSITE_DATA_MANAGE));
		menuList.add(getMenuType("yyxs数据审核", YYXSAdminConstant.MENU_TYPE_WEBSITE_DATA_EXAMINE));
		menuList.add(getMenuType("yyxs数据设置", YYXSAdminConstant.MENU_TYPE_WEBSITE_DATA_SET));
		menuList.add(getMenuType("后台数据管理", YYXSAdminConstant.MENU_TYPE_BACKSTAGE_DATA_MANAGE));
		List<Map<Object,Object>> authList = AuthService.getInstance().getUserFunction(user.getId(), user.getType());
		for(Map<Object,Object> map : authList){
			for(MenuType menuType : menuList){
				if(menuType.getType()==Integer.parseInt(map.get("type").toString())){
					menuType.getMenuItemList().add(map);
					break;
				}
			}
		}
		return menuList;
	}
	
	/**
	 * 获取菜单类型
	 * @param menuName 菜单名称
	 * @return
	 */
	private MenuType getMenuType(String menuName, int type){
		MenuType menuType = new MenuType();
		menuType.setName(menuName);
		menuType.setType(type);
		menuType.setMenuItemList(new ArrayList<Map<Object,Object>>());
		return menuType;
	}
}
