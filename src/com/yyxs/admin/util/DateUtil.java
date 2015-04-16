package com.yyxs.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间util
 * @author power
 */
public class DateUtil {
	
	/**
	 * 自定义日期格式
	 * @param format	//自定义日期格式
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format){
		return new SimpleDateFormat(format); 
	}
	
	/**
	 * 获取指定日期几天后的Calendar对象
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 */
	private static Calendar getAewDaysAfterDateCalendar(Date date,int amount) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return calendar;
	}
	
	/**
	 * 获取指定日期几天后的日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 */
	public static String getAewDaysAfterDateTime(String format, Date date,int amount) throws Exception{
		return getSimpleDateFormat(format).format(getAewDaysAfterDateCalendar(date, amount).getTime());
	}
	
	/**
	 * 获取指定日期几天前的Calendar对象
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 */
	private static Calendar getAewDaysBeforeDateCalendar(Date date,int amount) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -amount);
		return calendar;
	}
	
	/**
	 * 获取定日期几天前的日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 */
	public static String getAewDaysBeforeDateTime(String format, Date date,int amount) throws Exception{
		return getSimpleDateFormat(format).format(getAewDaysBeforeDateCalendar(date, amount).getTime());
	}
	
	/**
	 * 获取指定月份的第一天Calendar对象
	 * @param date			//初始值日期
	 */
	private static Calendar getCurrentMonthFirstDateCalendar(Date date) throws Exception{
		Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(date); 
	    calendar.set(Calendar.DATE, 1);
	    return calendar;
	}
	
	/**
	 * 获取指定月份的第一天日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	public static String getCurrentMonthFirstDateTime(String format, Date date) throws Exception{
	    return getSimpleDateFormat(format).format(getCurrentMonthFirstDateCalendar(date).getTime());
	}
	
	/**
	 * 获取指定月份的最后一天Calendar对象
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	private static Calendar getCurrentMonthLastDateCalendar(Date date) throws Exception{
		Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(getNextMonthFirstDateCalendar(date).getTime());
	    calendar.add(Calendar.DATE, -1);
	    return calendar;
	}
	
	/**
	 * 获取指定月份的最后一天日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	public static String getCurrentMonthLastDateTime(String format, Date date) throws Exception{
	    return getSimpleDateFormat(format).format(getCurrentMonthLastDateCalendar(date).getTime());
	}
	
	/**
	 * 获取指定月份下个月份的第一天Calendar对象
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	private static Calendar getNextMonthFirstDateCalendar(Date date) throws Exception{
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		return calendar;
	}
	
	/**
	 * 获取指定月份下个月份的第一天日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	public static String getNextMonthFirstDateTime(String format, Date date) throws Exception{
		return getSimpleDateFormat(format).format(getNextMonthFirstDateCalendar(date).getTime());
	}
	
	/**
	 * 获取指定月份下个月份的最后一天Calendar对象
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	private static Calendar getNextMonthLastDateCalendar(Date date) throws Exception{
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(getCurrentMonthLastDateCalendar(getFewMonthAfterDateCalendar(date, 1).getTime()).getTime());
		return calendar;
	}
	
	/**
	 * 获取指定月份下个月份的最后一天日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @throws Exception 
	 */
	public static String getNextMonthLastDateTime(String format, Date date) throws Exception{
		return getSimpleDateFormat(format).format(getNextMonthLastDateCalendar(date).getTime());
	}
	
	/**
	 * 获取指定日期几月后的Calendar对象
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 * @throws Exception 
	 */
	private static Calendar getFewMonthAfterDateCalendar(Date date,int amount) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar;
	}
	
	/**
	 * 获取指定日期几月后的日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 * @throws Exception 
	 */
	public static String getFewMonthAfterDateTime(String format, Date date,int amount) throws Exception{
		return getSimpleDateFormat(format).format(getFewMonthAfterDateCalendar(date, amount).getTime());
	}

	/**
	 * 获取指定日期几月前的Calendar对象
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 */
	private static Calendar getFewMonthBeforeDatecalendar(String format, Date date,int amount) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -amount);
		return calendar;
	}
	
	/**
	 * 获取指定日期几月前的日期
	 * @param format		//自定义日期格式
	 * @param date			//初始值日期
	 * @param amount		//几（天、月、年）
	 */
	public static String getFewMonthBeforeDateTime(String format, Date date,int amount) throws Exception{
		return getSimpleDateFormat(format).format(getFewMonthBeforeDatecalendar(format, date, amount).getTime());
	}
}
