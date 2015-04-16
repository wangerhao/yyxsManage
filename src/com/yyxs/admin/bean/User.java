package com.yyxs.admin.bean;

import java.util.List;

/**
 * 管理员用户
 * @author power
 *
 */
public class User {
	
	//管理员Id
	private int id;
	
	//管理员昵称
	private String nickName;
	
	//管理员账号
	private String userName;
	
	//管理员类型(0:普通管理员  1:超级管理员)
	private int type;
	
	//管理员权限列表
	private List<Object> authList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Object> getAuthList() {
		return authList;
	}

	public void setAuthList(List<Object> authList) {
		this.authList = authList;
	}
}
