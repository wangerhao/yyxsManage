package com.yyxs.admin.bean;

import java.util.List;
import java.util.Map;

/**
 * 菜单类型
 * @author power
 *
 */
public class MenuType {
	
	//菜单名称
	private String name;
	
	//菜单类型
	private int type;
	
	//菜单包含的功能列表
	private List<Map<Object,Object>> menuItemList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Map<Object, Object>> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<Map<Object, Object>> menuItemList) {
		this.menuItemList = menuItemList;
	}
}
