package com.yyxs.admin.service.mgnpasswordupdate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.mvc.Mvcs;

import com.ht.db.DBOperate;
import com.meinvli.util.BCryptUtil;
import com.meinvli.util.DBQueryUtil;
import com.meinvli.util.DateUtil;
import com.yyxs.admin.bean.User;
import com.yyxs.admin.service.addlog.AddLogService;

/**
 * 管理员密码更改service
 * @author power
 */
public class ManagerPasswordUpdateService {
	
	private static final DBOperate dbo = new DBOperate();
	
	private static final ManagerPasswordUpdateService managerPasswordUpdateService = new ManagerPasswordUpdateService();
	
	private ManagerPasswordUpdateService(){}
	
	public static final ManagerPasswordUpdateService getInstance(){
		return managerPasswordUpdateService;
	}
	
	/**
	 * 管理员密码更改
	 * @param mgnuserid		-->管理员用户编号
	 * @param oldMgnPwd		-->旧管理员密码
	 * @param newMgnPwd		-->新管理员密码
	 * @throws Throwable 
	 */
	public String managerPasswordUpdate(int mgnuserid , String oldMgnPwd, String newMgnPwd) throws Throwable {
		String sql = "SELECT password FROM admin_user WHERE id = ?";
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(mgnuserid);
		String mgnDBPwd = DBQueryUtil.getStringValue(sql, valueList, "password");
		if(!BCryptUtil.checkpw(oldMgnPwd, mgnDBPwd)){
			return "mgnPwdNonexistence";  //管理员密码不存在
		}
		sql = "UPDATE admin_user SET password = '" + BCryptUtil.hashpw(newMgnPwd) + "',systime = '" + DateUtil.getSimpleDateFormat(new Date()) + "' WHERE id = ?";
		dbo.updateData(sql, valueList);
		AddLogService.getInstance().addLog("[" + ((User)Mvcs.getReq().getAttribute("user")).getNickName() + "]的管理员修改了自己的密码。");
		return "mgnPwdUpdateSuccess";	//管理员密码更改成功
	}
	

}
