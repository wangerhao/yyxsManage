package com.yyxs.admin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class JsonUtil {
	
	private static final Logger log = Logger.getLogger(JsonUtil.class);
		
	/**
	 * 转换为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJsonStr(Map<String,String> map){
		JSONStringer stringer = new JSONStringer();
		try {
			JSONWriter writer = stringer.object();
			for(String key : map.keySet()){
				writer.key(key).value(map.get(key));
			}
			writer.endObject();
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
		return stringer.toString();
	}
	
    /**
     * json格式转换为map
     * @param jsonStr
     * @return
     * @throws JSONException 
     */
    public static Map<String,Object> jsonToMap(String jsonStr){
    	Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject obj = new JSONObject(jsonStr);
			Iterator<?> i = obj.keys();
	        String key = null;
	        while(i.hasNext()){
	        	key = i.next().toString();
	        	resultMap.put(key, obj.get(key));
	        }
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
        return resultMap;
    }
    
    /**
     * json格式转换List<Map<Object,Object>>
     * @param jsonStr
     * @return
     */
    public static List<Map<String,Object>> jsonToList(String jsonStr) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	try {
			JSONArray jsonArr = new JSONArray(jsonStr);
			JSONObject obj = null;
			for(int i= 0;i<jsonArr.length();i++){
				obj = new JSONObject(jsonArr.get(i).toString());
				list.add(jsonToMap(obj.toString()));
			}
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
    	return list;
    }
           
    /**
     * 判断是否是合法的json字符串成
     * @param jsonStr 字符串
     * @return
     */
    public static boolean isJson(String jsonStr) {
    	try {
    		new JSONObject(jsonStr);
			return true;
		} catch (Throwable e) {// 抛错 说明JSON字符不是数组或根本就不是JSON
			try {
				JSONArray array = new JSONArray(jsonStr);
				for (int i = 0; i < array.length(); i++) {
					array.getJSONObject(i);
				}
				return true;
			} catch (Throwable e2) {// 抛错 说明JSON字符根本就不是JSON
				return false;
			}
		}
    }
}
