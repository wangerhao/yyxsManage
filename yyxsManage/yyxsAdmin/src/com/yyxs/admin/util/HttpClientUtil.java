package com.yyxs.admin.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;


/**
 * httpclient模拟客户端请求工具类
 * @author power
 *
 */
public class HttpClientUtil {
	
	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);
	
	/**
	 * 连接池管理器
	 */
	private static PoolingClientConnectionManager cm = null;
	    
    /** 
     * 最大连接数 
     */  
    public final static int MAX_TOTAL_CONNECTIONS = 1000;
    /** 
     * 每个路由最大连接数 
     */  
    public final static int MAX_ROUTE_CONNECTIONS = 500;
    /** 
     * 连接超时时间 （请求超时）
     */  
    public final static int CONNECT_TIMEOUT = 2000; 
    
    /**
     * socket超时时间（响应超时）
     */
    public final static int SO_TIMEOUT = 4000; 
    
    /**
     * 是否开启代理，默认不开启
     */
    public final static boolean ONPROXY = true;
    
    /**
     * 默认字符集
     */
    public final static String CHARSET = "UTF-8";
    
    static {  
        SchemeRegistry schemeRegistry = new SchemeRegistry();  
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));  
        schemeRegistry.register(new Scheme("https",443, SSLSocketFactory.getSocketFactory()));
        cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
    }  
    
    /**
     * 返回httpClient对象
     * @return
     */
    public static HttpClient getHttpClient() {  
        HttpParams params = new BasicHttpParams();  
        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);  
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT); 
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
        params.setParameter(CoreConnectionPNames.TCP_NODELAY, false);
        if(ONPROXY){
        	params.setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("192.168.199.151", 8888));
        }
        return new DefaultHttpClient(cm, params);  
    } 
    
    /**
     * get请求获取数据
     * @param url			-->请求的url
     * @param urlParamMap	-->url参数
     */
    public static String get(String url , Map<String,String> urlParamMap){
    	return get(url, urlParamMap, new HashMap<String, String>());
    }
    
    /**
     * get请求有header参数
     * @param url				-->请求的url			
     * @param headerMap			-->header参数
     */
    public static String getReqThereHeader(String url , Map<String,String> headerMap){
    	return get(url, null, headerMap);
    }
    
    /**
     * get请求
     * @param url
     */
    public static String get(String url){
    	return get(url, null);
    }
    
	/**
     * get请求获取数据
     */
	public static String get(String url , Map<String,String> urlParamMap, Map<String,String> headerMap){  
        try {  
        	HttpClient httpClient = getHttpClient();
            HttpGet get = new HttpGet(url + getQueryString(urlParamMap, true));
         	for(String key : headerMap.keySet()){
        		get.setHeader(key, headerMap.get(key));
        	}
        	HttpResponse res = httpClient.execute(get);  
        	HttpEntity entity = res.getEntity();
            Header encodingHeader = res.getFirstHeader("Content-Encoding");
            if(encodingHeader!=null&&encodingHeader.getValue().equals("gzip")){
            	entity = new GzipDecompressingEntity(entity);
            }
            String result = null;
            if(entity!=null){
            	result = EntityUtils.toString(entity);
            }
			EntityUtils.consume(entity); 
			return result;
        } catch (Exception e) {  
        	log.error(e.getMessage(), e);
        	return e.getMessage();
        }
    }
	
	/**
     * post添加数据
     */
	public static String postThereJsonBody(String url, Map<String,String> headerMap, Map<String,String> bodyParamMap){  
        String bodyStr = StringUtils.EMPTY;
        if(bodyParamMap != null){
        	bodyStr = JsonUtil.toJsonStr(bodyParamMap);
        }
        return post(url, null, headerMap, bodyStr);
    } 
	
	/**
     * post添加数据
     */
	public static String postThereJsonBody(String url, Map<String,String> urlParamMap, Map<String,String> headerMap, JsonObject bodyParamJson){  
        String bodyStr = StringUtils.EMPTY;
        if(bodyParamJson != null){
        	bodyStr = bodyParamJson.toString();
        }
        return post(url, urlParamMap, headerMap, bodyStr);
    } 
	
	/**
     * post添加数据
     */
	public static String postUrlMap(String url, Map<String,String> urlParamMap, Map<String,String> headerMap){  
        return post(url, urlParamMap, headerMap, null);
    } 
	
	/**
     * post添加数据
     */
	public static String postBodyMap(String url, Map<String,String> headerMap, Map<String,String> bodyParamMap){  
        String bodyStr = getQueryString(bodyParamMap, false);
        return post(url, null, headerMap, bodyStr);
    } 
		
	/**
     * post添加数据
     */
	public static String postUrlAndBody(String url, Map<String,String> urlParamMap, Map<String,String> headerMap, Map<String,String> bodyParamMap){  
        String bodyStr = getQueryString(bodyParamMap, false);
        return post(url, urlParamMap, headerMap, bodyStr);
    }  
	
	/**
     * post添加数据
     */
	public static String postUrlAndHeadAndBody(String url, Map<String,String> headerMap, String bodyStr){  
        return post(url, null, headerMap, bodyStr);
    }  
	
	
	/**
     * post添加数据
     */
	public static String post(String url, Map<String,String> urlParamMap , Map<String,String> headerMap, String bodyStr){  
        try {  
        	HttpClient httpClient = getHttpClient();
            HttpPost post = new HttpPost(url + getQueryString(urlParamMap,true));
            if(StringUtils.isNotBlank(bodyStr)){
            	post.setEntity(new ByteArrayEntity(bodyStr.getBytes()));
            }
        	for(String key : headerMap.keySet()){
        		post.setHeader(key, headerMap.get(key));
        	}
        	HttpResponse res = httpClient.execute(post);
            HttpEntity entity = res.getEntity();
            Header encodingHeader = res.getFirstHeader("Content-Encoding");
            if(encodingHeader != null && encodingHeader.getValue().equals("gzip")){
            	entity = new GzipDecompressingEntity(entity);
            }
			String result = EntityUtils.toString(entity);
			EntityUtils.consume(entity); 
			return result;
        } catch (Exception e) {  
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }  
	
	/**
	 * cookie获取值
	 * @param res
	 * @return 
	 */
	private static String setCookieGetVal(HttpResponse res, String key) {
		Header[] headers = res.getHeaders("Set-Cookie");
		if(headers != null && headers.length > 0){
			String setCookie = headers[0].toString();
			if(StringUtils.isNotBlank(setCookie) && setCookie.contains(key)){
				return setCookie.substring(setCookie.indexOf("=") + 1);
			}
		}
		return StringUtils.EMPTY;
	}
	
	/**
     * post 文件请求
     */
	public static String postFile(String url, Map<String,String> headerMap, Map<String,String> urlParamMap, ByteArrayEntity bodyStr){  
        try {  
        	HttpClient httpClient = getHttpClient();
            HttpPost post = new HttpPost(url + getQueryString(urlParamMap,true));
        	for(String key : headerMap.keySet()){
        		post.setHeader(key, headerMap.get(key));
        	}
            post.setEntity(bodyStr);
        	HttpResponse res = httpClient.execute(post);
            HttpEntity entity = res.getEntity();
            Header encodingHeader = res.getFirstHeader("Content-Encoding");
            if(encodingHeader!=null&&encodingHeader.getValue().equals("gzip")){
            	entity = new GzipDecompressingEntity(entity);
            }
			String result = EntityUtils.toString(entity);
			EntityUtils.consume(entity); 
			return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }	
	
	/**
     * put请求
     */
	public static String putJsonReq(String url, Map<String,String> headerMap, Map<String,String> bodyParamMap){  
        String bodyStr = StringUtils.EMPTY;
        if(bodyParamMap != null){
        	bodyStr = JsonUtil.toJsonStr(bodyParamMap);
        }
        return put(url, null, headerMap, bodyStr);
    } 
	
	/**
     * put请求
     */
	public static String put(String url, Map<String,String> urlParamMap , Map<String,String> headerMap, String bodyStr){  
        try {  
        	HttpClient httpClient = getHttpClient();
            HttpPut put = new HttpPut(url + getQueryString(urlParamMap,true));
            if(StringUtils.isNotBlank(bodyStr)){
            	put.setEntity(new ByteArrayEntity(bodyStr.getBytes()));
            }
        	for(String key : headerMap.keySet()){
        		put.setHeader(key, headerMap.get(key));
        	}
        	HttpResponse res = httpClient.execute(put);
            HttpEntity entity = res.getEntity();
            Header encodingHeader = res.getFirstHeader("Content-Encoding");
            if(encodingHeader != null && encodingHeader.getValue().equals("gzip")){
            	entity = new GzipDecompressingEntity(entity);
            }
			String result = EntityUtils.toString(entity);
			EntityUtils.consume(entity); 
			return result;
        } catch (Exception e) {  
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }  
	
	/**
     * put 文件请求
     */
	public static String putFile(String url, Map<String,String> urlParamMap, Map<String,String> headerMap, ByteArrayEntity bodyStr){  
        try {  
        	HttpClient httpClient = getHttpClient();
            HttpPut put = new HttpPut(url + getQueryString(urlParamMap,true));
        	for(String key : headerMap.keySet()){
        		put.setHeader(key, headerMap.get(key));
        	}
            put.setEntity(bodyStr);
        	HttpResponse res = httpClient.execute(put);
            HttpEntity entity = res.getEntity();
            Header encodingHeader = res.getFirstHeader("Content-Encoding");
            if(encodingHeader!=null&&encodingHeader.getValue().equals("gzip")){
            	entity = new GzipDecompressingEntity(entity);
            }
			String result = EntityUtils.toString(entity);
			EntityUtils.consume(entity); 
			return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }	
			
	/**
	 * 拼接参数字符串
	 * @param paramList 参数列表
	 * @return
	 */
	public static String getQueryString(Map<String,String> map, boolean urlParam) {
		if(map == null){
			return StringUtils.EMPTY;
		}
		StringBuilder builder = new StringBuilder();
		for(String key : map.keySet()){
			builder.append("&");
			builder.append(key);
			builder.append("=");
			builder.append(paramEncode(map.get(key)));
		}
		if(builder.length()==0){
			return StringUtils.EMPTY;
		}
		if(urlParam){
			return builder.toString().replaceFirst("&", "?");
		}
		return builder.deleteCharAt(0).toString();
	}
	
	/**
	 * 参数encodeing
	 * @param paramString
	 * @return
	 */
	public static String paramEncode(String paramString) {
		if (StringUtils.isBlank(paramString)) {
			return StringUtils.EMPTY;
		}
		try {
			String str = URLEncoder.encode(paramString, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~").replace("#", "%23");
			return str;
		} catch (Throwable e) {
		}
		return StringUtils.EMPTY;
	}
}
