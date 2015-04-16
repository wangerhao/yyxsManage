package com.yyxs.admin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.nutz.mvc.Mvcs;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 工具类
 * @author power
 *
 */
public class YYXSAdminUtil {
	
	private static Logger log = Logger.getLogger(YYXSAdminUtil.class);
	
	/**
	 * 将List<Map<Object,Object>> 其中的一组键值转换为List<Object> 
	 * @param mapList 待转换的mapList
	 * @param mapKey 要转换的键值
	 * @return
	 */
	public static List<Object> mapListToList(List<Map<Object,Object>> mapList, String mapKey){
		List<Object> list = new ArrayList<Object>(); 
		for(Map<Object,Object> map : mapList){
			list.add(map.get(mapKey));
		}
		return list;
	}
	
	/**
	 * 将List<Map<Object,Object>>转换为Map<String, String>
	 * @param mapList	待转换的mapList
	 * @param key		要转换的键
	 * @param valueKey	要转换的值
	 * @return
	 */
	public static Map<String, String> mapListToMap(List<Map<Object,Object>> mapList, String key, String valueKey){
		Map<String, String> map = new HashMap<String, String>();
		for(Map<Object,Object> mapItem : mapList){
			map.put(mapItem.get(key).toString(), mapItem.get(valueKey).toString());
		}
		return map;
	}
	
	/**
	 * 对传入的字符串里的特殊字符进行转义
	 */
	public static String encodeSpecialChar(String content){
		content = content.replaceAll("&", "&amp;");
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll("\"", "&quot;");
		content = content.replaceAll("'", "&#039;");
		return content;
	}
	
	/**
	 * 保存分页信息
	 * @param currentPage		-->当前页
	 * @param dataSaveType		-->数据保存类型
	 * @param totalNum			-->总数据个数
	 */
	public static void savePageInfo(int currentPage, String dataSaveType, int totalNum){
		Mvcs.getReq().setAttribute("currentPage", currentPage);
		Mvcs.getReq().setAttribute("dataSaveType", dataSaveType);
		Mvcs.getReq().setAttribute("totalNum", totalNum);
	}
	
	/**
	 * base64图片上传
	 * @param base64Img		 -->base64图片
	 * @param imgName		 -->数据库图片名
	 * @param imgSaveAddress -->图片保存地址
	 * @throws Throwable 
	 */
	public static void base64ImgUpload(String base64Img,String imgName, String imgSaveAddress) throws Throwable{
		BASE64Decoder decoder = new BASE64Decoder();
		File file = new File(imgSaveAddress + imgName);
		file.getParentFile().mkdirs();
		FileOutputStream write = new FileOutputStream(file);
		byte[] decoderBytes = decoder.decodeBuffer(base64Img);
	    write.write(decoderBytes);
	}
	
	/**
	 * 数字格式
	 * @param numberFormatVal
	 */
	public static String numberFormat(int numberFormatVal){
		return new DecimalFormat("##,###").format(numberFormatVal);
	}
	
	/**
	 * 字符串乱码转换
	 * @param str	-->字符串
	 */
	public static String strConversion(String str) throws Exception{
		return new String(str.getBytes("ISO-8859-1"), "utf-8");
	}
	
	/**
	 * list数据日期转换
	 * @param list			-->list数据
	 * @param keyArr		-->转换日期的键名
	 */
	public static void listDateConvertTo_yyyyMMddhhMMss(List<Map<Object, Object>> list, String[] keyArr){
		for (Map<Object, Object> map : list) {
			if(keyArr != null){
				for (int i = 0; i < keyArr.length; i++) {
					map.put(keyArr[i], dateConvertTo_yyyyMMddhhMMss(map.get(keyArr[i])));
				}
			}
		}
	}
	
	/**
	 * 日期转换
	 * @param date		-->转换的日期
	 * @return
	 */
	public static String dateConvertTo_yyyyMMddhhMMss(Object date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * list double数据转int
	 */
	public static void listDoubleDataToInt(List<Map<Object, Object>> list, String[] keyArr){
		for (Map<Object, Object> map : list) {
			if(keyArr != null){
				for (int i = 0; i < keyArr.length; i++) {
					double val = Double.valueOf(map.get(keyArr[i]).toString());
					map.put(keyArr[i], ((int)val));
				}
			}
		}
	}
	
    /**
     * json字符转map
     * @param jsonStr
     * @return
     */
    public static Map<Object, Object> jsonToMap(String jsonStr) {  
    	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.fromJson(jsonStr, new TypeToken<Map<Object, Object>>(){}.getType());
    }

    /**
     * map object类型的数字转int
     * @param copyMap		-->文案数据map
     * @param keyArr		-->key键数组
     */
	public static void mapDoubleDataToInt(Map<Object, Object> copyMap, String[] keyArr) {
		if(keyArr != null){
			for (int i = 0; i < keyArr.length; i++) {
				double val = Double.valueOf(copyMap.get(keyArr[i]).toString());
				copyMap.put(keyArr[i], ((int)val));
			}
		}
	}
	
	/**
	 * 获取properties配置文件数据
	 * @param name    -->键名称
	 * @throws Throwable 
	 */
	public static String getConfigData(String name){
		try {
			Properties properties = new Properties();
			properties.load(YYXSAdminUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			return properties.getProperty(name);
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * log日志
	 * @param message
	 * @param t
	 */
	public static void log(Object message, Throwable t){
		log.error(message, t);
	}
}
