package com.yyxs.admin.service.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.Mvcs;

import com.ht.db.DBOperate;
import com.meinvli.util.BCryptUtil;
import com.meinvli.util.CommonUtil;
import com.meinvli.util.CookieMapUtil;
import com.meinvli.util.DateUtil;
import com.yyxs.admin.bean.User;
import com.yyxs.admin.constant.YYXSAdminConstant;
import com.yyxs.admin.service.auth.AuthService;
import com.yyxs.admin.service.loginit.LogInitService;

/**
 * 登录
 * @author power
 *
 */
public class LoginService {
	
	private static final DBOperate dbo = new DBOperate();
	
	private static final LoginService loginService = new LoginService();
	
	private LoginService(){}
	
	public static LoginService getInstance(){
		return loginService;
	}
	
	/**
	 * 用户登录
	 * @param userName 登录账户名
	 * @param password 登录密码
	 * @param request 
	 * @return String
	 * @throws Throwable
	 */
	public String login(String userName, String password, HttpServletRequest request) throws Throwable{
		String sql = "SELECT id,nickname,username,password,type,status FROM admin_user WHERE username = ?";
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(userName);
		List<Map<Object,Object>> list = dbo.retrieveSQL(sql, valueList);
		if(list.size()==0){
			return "accountError";	//用户名错误
		}
		Map<Object,Object> map = list.get(0);
		String dbPassword = map.get("password").toString();
		if(!BCryptUtil.checkpw(password, dbPassword)){
			return "passwordError";	//密码错误
		}
		int userStatus = Integer.parseInt(map.get("status").toString());
		if(userStatus == YYXSAdminConstant.USER_STATUS_FREEZEN){
			return "accountFreeze";  //账号被冻结
		}
		int userId = Integer.parseInt(map.get("id").toString());
		String nickName = map.get("nickname").toString();
		int userType = Integer.parseInt(map.get("type").toString());
		loginUserSet(userId, userName, nickName, userType, request);
		addLog(userId, nickName, "[" + nickName + "]的管理员已经登陆配配后台管理系统。");
		return "loginSuccess";	//登录成功
	}
	
	/**
	 * 添加登录日志
	 * @param userId 用户Id
	 * @param nickName 用户昵称
	 * @param content 日志内容
	 * @throws Throwable
	 */
	private void addLog(int userId, String nickName, String content) throws Throwable {
		String functionName = (String) Mvcs.getReq().getAttribute("functionName");
		Map<String, String> functionMap = Mvcs.getIoc().get(LogInitService.class).getAllFunctionList();
		String datetime = DateUtil.getSimpleDateFormat(new Date());
		List<Object> adminLogParam = new ArrayList<Object>();
		adminLogParam.add(userId);
		adminLogParam.add(nickName);
		adminLogParam.add(functionName==null?null:functionMap.get(functionName));
		adminLogParam.add(content);
		adminLogParam.add(datetime);
		adminLogParam.add(datetime);
		dbo.insertData("INSERT INTO admin_log(userid,nickname,fname,content,time,systime) VALUES(?,?,?,?,?,?)",adminLogParam);
	}
	
	/**
	 * 登录成功后设置当前登录用户信息
	 * @param userId 用户Id
	 * @param userName 登录账户
	 * @param nickName 用户昵称
	 * @param userType 用户类型(0:普通用户 1:超级管理员)
	 * @param request
	 * @throws Throwable
	 */
	private void loginUserSet(int userId, String userName, String nickName, int userType, HttpServletRequest request) throws Throwable{
		User user = new User();
		user.setId(userId);
		user.setUserName(userName);
		user.setNickName(nickName);
		user.setType(userType);
		user.setAuthList(AuthService.getInstance().getUserFunctionName(userId, userType));
		String sessionId = CommonUtil.getUUID();
		Mvcs.getServletContext().setAttribute(sessionId, user);
		CookieMapUtil cookie = new CookieMapUtil(request, Mvcs.getResp());
		cookie.put(YYXSAdminConstant.USER_LOGIN_SESSION_TAG, sessionId);
	}
}
