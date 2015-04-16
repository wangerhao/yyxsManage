package com.yyxs.admin.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
/**
 * 工具类
 * 
 */
public class StringUtil {

	/**
	 * Formats a Date as a fifteen character long String made up of the Date's
	 * padded millisecond value.
	 * 
	 * @return a Date encoded as a String.
	 */
	public static String dateToMillis(Date date) {
		return zeroPadString(Long.toString(date.getTime()), 15);
	}

	private static final char[] zeroArray = "0000000000000000000000000000000000000000000000000000000000000000"
			.toCharArray();

	/**
	 * Pads the supplied String with 0's to the specified length and returns the
	 * result as a new String. For example, if the initial String is "9999" and
	 * the desired length is 8, the result would be "00009999". This type of
	 * padding is useful for creating numerical values that need to be stored
	 * and sorted as character data. Note: the current implementation of this
	 * method allows for a maximum <tt>length</tt> of 64.
	 * 
	 * @param string
	 *            the original String to pad.
	 * @param length
	 *            the desired length of the new padded String.
	 * @return a new String padded with the required number of 0's.
	 */
	public static String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		}
		StringBuilder buf = new StringBuilder(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}

	/**
	 * 校验是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		return ((str == null) || str.equals("") || str.equalsIgnoreCase("null"));
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/**
	 * 校验是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		byte[] chars = str.getBytes();
		for (byte b : chars) {
			if (b < '0' || b > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转码
	 * 
	 * @param text
	 * @param oldCharset
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String text, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (!isNullOrBlank(text)) {
			byte[] bytes = null;
			if (!isNullOrBlank(oldCharset)) {
				bytes = text.getBytes(oldCharset);
			} else {
				bytes = text.getBytes();
			}
			return new String(bytes, newCharset);
		}
		return text;
	}

	/**
	 * 将数字以逗号格式化，如把100000每隔3位用逗号隔开为100,000
	 */
	public static String formatNumber(long number, int byteLength) {
		String numberToFormat = number + "";
		int size = numberToFormat.length();

		if ((byteLength == 0) || byteLength > size) {
			return number + "";
		}

		char[] numbers = new char[numberToFormat.length()];
		numberToFormat.getChars(0, size, numbers, 0);

		String result = "";
		int n = 0;

		for (int i = size - 1; i >= 0; i--) {
			result = numbers[i] + result;
			n++;
			result = ((n % byteLength == 0) ? "," : "") + result;
		}

		return (result.startsWith(",") ? result.replaceFirst(",", "") : result);
	}

	/**
	 * 反转给定的字符串text
	 * 
	 * @param text
	 * @return
	 */
	public static String reverse(String text) {
		// return new StringBuffer(text).reverse();

		String result = "";
		char[] chars = new char[text.length()];
		text.getChars(0, text.length(), chars, 0);

		for (char c : chars) {
			result = c + result;
		}

		return result;
	}

	/**
	 * 日期格式化
	 * 
	 * @param formatPattern
	 * @param date
	 * @return
	 */
	public static String formatDate(String formatPattern, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
		return formatter.format(date);
	}

	/**
	 * 格式化当前日期，默认模式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(date);
	}

	public static String getNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	/**
	 * 日期相减
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long dateSub(String date1, String date2) {
		if (date1 == null) {
			date1 = "0 0";
		}
		if (date2 == null) {
			date2 = "0 0";
		}
		date1 = date1.replaceAll("-", "").replaceAll(":", "").replaceAll(" ",
				"");
		date2 = date2.replaceAll("-", "").replaceAll(":", "").replaceAll(" ",
				"");
		long d1 = Long.parseLong(date1);
		long d2 = Long.parseLong(date2);
		return d2 - d1;
	}

	/**
	 * 按照正则表达式匹配格式
	 * 
	 * @param formatPattern
	 * @param text
	 * @return
	 */
	public static boolean canPattern(String formatPattern, String text) {
		Pattern p = Pattern.compile(formatPattern);
		Matcher m = p.matcher(text);
		return m.find();
	}

	/**
	 * 将List中的数据用seprator分隔
	 * 
	 * @param srcData
	 * @param separator
	 * @return
	 */
	public static String join(List srcData, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < srcData.size(); i++) {
			sb.append(srcData.get(i).toString()).append(separator);
		}
		String result = sb.toString();
		if (result.endsWith(separator)) {
			result = result.substring(0, (result.length() - 1));
		}
		return result;
	}

	/**
	 * 将数组中的数据用seprator分隔
	 * 
	 * @param srcData
	 * @param separator
	 * @return
	 */
	public static String join(Object[] srcData, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < srcData.length; i++) {
			sb.append(srcData[i].toString()).append(separator);
		}
		String result = sb.toString();
		if (result.endsWith(separator)) {
			result = result
					.substring(0, (result.length() - separator.length()));
		}
		return result;
	}

	/**
	 * 将Iterator中的数据用seprator分隔
	 * 
	 * @param srcData
	 * @param separator
	 * @return
	 */
	public static String join(Iterator srcData, String separator) {
		StringBuilder sb = new StringBuilder();
		while (srcData.hasNext()) {
			sb.append(srcData.next().toString()).append(separator);
		}
		String result = sb.toString();
		if (result.endsWith(separator)) {
			result = result.substring(0, (result.length() - 1));
		}
		return result;
	}

	/**
	 * 将template重复n次
	 * 
	 * @param template
	 * @param n
	 * @return
	 */
	public static String repeat(String template, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(template);
		}
		return sb.toString();
	}

	/**
	 * 输出
	 */
	public static void print(Object obj) {
		System.out.println("\n*****************************************");
		System.out.println(obj.toString());
		System.out.println("*****************************************\n");
	}

	/**
	 * 输出HashMap
	 */
	public static void print(Map map) {
		StringBuilder sb = new StringBuilder("\n");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			sb.append(it.next()).append("\n");
		}
		System.out.println(sb.toString());
	}

	/**
	 * 获取URL的domain
	 * 
	 * @param args
	 */
	public static String getDomain(String url) {
		Pattern p_domain = Pattern
				.compile(
						"http\\://[a-zA-Z0-9]{0,100}[.]{0,1}[^.\\s]*?\\.(com|cn|net|org|biz|info|cc|tv|mil|mobi|int|rec)",
						Pattern.CASE_INSENSITIVE);
		Matcher m_domain = p_domain.matcher(url);
		if (m_domain.find()) {
			return m_domain.group(0);
		}
		return url;
	}

	/**
	 * 转义
	 * 
	 * @param args
	 */
	public static String trans2Text(String text) {
		if (!StringUtil.isNullOrBlank(text)) {
			text = text.trim().replaceAll("&", "&amp;").replace("'", "&apos;")
					.replaceAll("\"", "&quot;").replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;");
		}
		return text;
	}

	/**
	 * 去掉字符串两边指定的符号，如去掉"|3495|"两边的"|"
	 * 
	 * @param args
	 */
	public static String clearSide(String text, String seprator) {
		while (text.startsWith(seprator)) {
			text = text.substring(seprator.length());
		}
		while (text.endsWith(seprator)) {
			text = text.substring(0, text.length() - seprator.length());
		}
		return text;
	}

	/**
	 * 获取最大值
	 * 
	 * @param ids
	 * @return
	 */
	public static String getMax(List<Integer> ids) {
		return Collections.max(ids) + "";
	}

	/**
	 * 获取随机数列
	 * 
	 * @param args
	 */
	public static List<Integer> getRandomNumList(int size, int maxVal,
			int minVal, boolean unique) {
		List<Integer> result = new ArrayList<Integer>();
		HashSet<Integer> set = new HashSet<Integer>();

		if (minVal > maxVal) {
			minVal = maxVal;
		}

		while (result.size() <= size) {
			int random = (int) (Math.random() * maxVal + minVal);
			if (random > maxVal) {
				random = maxVal;
			}
			if (random < minVal) {
				random = minVal;
			}
			if (unique) {
				if (set.add(random)) {
					result.add(random);
				}
			} else {
				result.add(random);
			}
		}
		return result;
	}

	/**
	 * 反转义
	 * 
	 * @param text
	 * @return
	 */
	public static String trans2Html(String text) {
		if (!StringUtil.isNullOrBlank(text)) {
			text = text.replaceAll("&amp;", "&").replace("&apos;", "'")
					.replaceAll("&quot;", "\"").replace("&lt;", "<").replace(
							"&gt;", ">");
		}
		return text;
	}

	/**
	 * 剔除HTML标签
	 * 
	 * @param htmlcontent
	 * @return
	 */
	public static String delHTMLTag(String htmlcontent) {
		String txtcontent = htmlcontent.replaceAll("</?[^>]+>", ""); // 剔出<html>的标签
		txtcontent = txtcontent.replaceAll("\\s*|\t|\r|\n", "");// 去除字符串中的空格,回车,换行符,制表符
		return txtcontent;
	}

	/**
	 * 删除特殊汉字
	 * 
	 * @param input
	 * @return
	 */
	public static List<String> delSpecialChinese(String input) {
		List<String> result = new ArrayList<String>();
		String[] noneWord = new String[] { "你", "我", "他", "她", "它", "的", "是",
				"啊", "吧", "呢" };
		for (String string : noneWord) {
			if (input.contains(string)) {
				input = input.replaceAll(string, "*");
			}
		}
		Pattern chinesePattern = Pattern
				.compile("[^\u4e00-\u9fa5^0-9^a-z^A-Z]");
		Matcher m = chinesePattern.matcher(input);
		int preStart = 0;
		int start = 0;
		int end = 0;
		while (m.find()) {
			start = m.start();
			end = m.end();
			result.add(input.substring(preStart, start));
			preStart = end;
		}
		result.add(input.substring(end));
		return result;
	}

	/***************************************************************************
	 * 获取随机数
	 **************************************************************************/
	public static int random(int seed, int min) {
		return (int) (Math.random() * seed) + min;
	}

	/**
	 * 返回异常信息
	 * 
	 * @param e
	 * @return
	 */
	public static String exception2String(Exception e) {
		StringBuilder builder = new StringBuilder();
		builder.append("\nReason: ").append(e.getClass().toString()).append(
				" , ").append(e.getLocalizedMessage()).append("\n");
		StackTraceElement[] elements = e.getStackTrace();
		for (StackTraceElement stackTraceElement : elements) {
			builder.append(stackTraceElement.toString()).append("\n");
		}
		return builder.toString();
	}

	/**
	 * MD5加密方法
	 * 
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));
			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			return null;
		}
		return sb.toString().toUpperCase();
	}

	public static Integer size(List obj) {
		return obj.size();
	}

	public static void main(String[] args) {
		boolean str=canPattern("^[a-zA-Z]+$","e@");
		System.out.println(str);
	}

}
