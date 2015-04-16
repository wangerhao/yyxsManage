package com.yyxs.admin.service.addlog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.mvc.Mvcs;

import com.ht.db.DBOperate;
import com.meinvli.util.DateUtil;
import com.yyxs.admin.bean.User;
import com.yyxs.admin.service.loginit.LogInitService;

/**
 * 记录操作日志service
 * @author power
 *
 */
public class AddLogService {
	
	private static final DBOperate dbo = new DBOperate();
	
	private static final AddLogService addLog = new AddLogService();
	
	private AddLogService(){}
	
	public static AddLogService getInstance(){
		return addLog;
	}
	
	/**
	 * 记录操作日志
	 * @param content		-->日志内容
	 * @throws Throwable 
	 */
	public void addLog(String content) throws Throwable {
		User user = ((User)Mvcs.getReq().getAttribute("user"));
		String functionName = (String) Mvcs.getReq().getAttribute("functionName");
		Map<String, String> functionMap = Mvcs.getIoc().get(LogInitService.class).getAllFunctionList();
		String datetime = DateUtil.getSimpleDateFormat(new Date());
		List<Object> adminLogParam = new ArrayList<Object>();
		adminLogParam.add(user.getId());
		adminLogParam.add(user.getNickName());
		adminLogParam.add(functionName==null?null:functionMap.get(functionName));
		adminLogParam.add(content);
		adminLogParam.add(datetime);
		adminLogParam.add(datetime);
		dbo.insertData("INSERT INTO admin_log(userid,nickname,fname,content,time,systime) VALUES(?,?,?,?,?,?)",adminLogParam);
	}
}
