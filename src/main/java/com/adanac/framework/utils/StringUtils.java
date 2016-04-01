package com.adanac.framework.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str 如果字符串为null,"null"或者为空，都将返回true，否则返回false
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 清洗字符串
	 * 
	 * @param str 待清晰的字符串
	 * @param defaultValue 默认替换值
	 * @return
	 */
	public static String clear(String str, String defaultValue) {
		if (isEmpty(str)) {
			return defaultValue;
		} else {
			return str.trim();
		}
	}

	/**
	 * 
	 * 清洗对象 <br>
	 * 〈功能详细描述〉
	 * 
	 * @param obj 待清晰的对象
	 * @param defaultValue 默认替换值
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String clear(Object obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		} else {
			if (isEmpty(obj.toString())) {
				return defaultValue;
			} else {
				return obj.toString().trim();
			}
		}
	}

	/**
	 * 判断字符串是否可转换为Long类型
	 * 
	 * @param longStr
	 * @return
	 */
	public static boolean isLong(String longStr) {
		if (isEmpty(longStr)) {
			return false;
		}
		try {
			Long.parseLong(longStr);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否可转换为Integer类型
	 * 
	 * @param intStr
	 * @return
	 */
	public static boolean isInteger(String intStr) {
		if (isEmpty(intStr)) {
			return false;
		}
		try {
			Integer.parseInt(intStr);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否可转换为BigDecimal类型
	 * 
	 * @param decimalStr
	 * @return
	 */
	public static boolean isDecimal(String decimalStr) {
		try {
			new BigDecimal(decimalStr);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 将数值型字符串转换为数字
	 * 
	 * @param intStr
	 * @return
	 */
	public static int intValue(String intStr) {
		return Integer.parseInt(intStr);
	}

	/**
	 * 将长整形字符串转换为长整形数值
	 * 
	 * @param longStr
	 * @return
	 */
	public static long longValue(String longStr) {
		return Long.parseLong(longStr);
	}

	/**
	 * 将数值型字符串转换为数值型
	 * 
	 * @param decimalStr
	 * @return
	 */
	public static BigDecimal decimalValue(String decimalStr) {
		return new BigDecimal(decimalStr);
	}

	/**
	 * 用于解决URL参数中文奇数乱码问题
	 * 
	 * @param s
	 * @return
	 */
	public static String encodeURL(String s) {
		String temp = "";
		if (!isEmpty(s)) {
			try {
				temp = java.net.URLEncoder.encode(s, "UTF-8");
			} catch (Throwable e) {
				logger.error(e.getMessage(), e);
			}
		}
		return temp;
	}

	/**
	 * 判断字符是否是中文
	 * 
	 * @param c 字符
	 * @return
	 */
	public static boolean isChinese(char c) {
		// 假定英文范围为0-127
		int enEnd = 127;
		int code = c;
		return code > enEnd;
	}

	/**
	 * 截取字符串，一个中文默认占2个字节
	 * 
	 * @param str 待截取的字符串
	 * @param len 截取长度
	 * @return
	 */
	public static String cutString(String str, int len) {
		return cutString(str, len, 2);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str 待截取的字符串
	 * @param len 截取长度
	 * @param defaultChineseLen 一个中文字符占用的长度
	 * @return
	 */
	public static String cutString(String str, int len, int defaultChineseLen) {

		if (StringUtils.isEmpty(str)) {
			return "";
		}

		if (len <= 0 || len * defaultChineseLen >= length(str)) {
			return str;// 字符长度未达到截断限制要求
		}

		int totalLength = 0;
		StringBuilder result = new StringBuilder();

		char[] chars = str.toCharArray();
		for (char c : chars) {
			if (totalLength < len * defaultChineseLen) {
				if (isChinese(c)) {
					if (totalLength + defaultChineseLen <= len * defaultChineseLen) {
						result.append(c);
						totalLength += defaultChineseLen;
					} else {
						break;
					}
				} else {
					if (totalLength + 1 <= len * defaultChineseLen) {
						result.append(c);
						totalLength++;
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
		result.append("...");
		return result.toString();
	}

	/**
	 * 
	 * 计算字符串长度(一个中文字符长度为2)
	 * 
	 * @param str 待计算的字符串
	 * @return
	 */
	public static int length(String str) {
		return length(str, 2);
	}

	/**
	 * 计算字符串长度
	 * 
	 * @param str 待计算的字符串
	 * @param defaultChineseLen 一个中文字符占用的长度
	 * @return
	 */
	public static int length(String str, int defaultChineseLen) {
		int totalLength = 0;
		char[] chars = str.toCharArray();
		for (char c : chars) {
			if (isChinese(c)) {
				totalLength += defaultChineseLen;
			} else {
				totalLength++;
			}
		}
		return totalLength;
	}

	/**
	 * 去除字符串中的HTML元素
	 * 
	 * @param str 待处理的字符串
	 * @return
	 */
	public static String removeHtmlTag(String str) {
		String htmlStr = str; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_script = "<[/s]*?script[^>]*?>[/s/S]*?<[/s]*?//[/s]*?script[/s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[/s/S]*?<//script>

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;
		} catch (Throwable e) {
			// System.err.println("Html2Text: " + e.getMessage());
			logger.error(e.getMessage());
		} finally {
			p_script = null;
			m_script = null;
			p_html = null;
			m_html = null;
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * 
	 * 获取年度范围 <br>
	 * 〈返回当前年度前后的若个连续年份，如当前年度为 2013，则getYearRange(5,5)则返回 2008-2018共11个年份数据〉
	 * 
	 * @param before 向前多少年
	 * @param after 向后多少年
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String[] getYearRange(int before, int after) {
		if (before < 0) {
			before = 0;
		}
		if (after < 0) {
			after = 0;
		}
		String[] years = new String[1 + before + after];

		int year = Calendar.getInstance().get(Calendar.YEAR);

		List<String> list = new ArrayList<String>(years.length);

		for (int i = before; i > 0; i--) {
			list.add(String.valueOf(year - i));
		}

		list.add(String.valueOf(year));

		for (int i = 1; i <= after; i++) {
			list.add(String.valueOf(year + i));
		}

		list.toArray(years);

		return years;
	}

	/**
	 * 
	 * 解析异常堆栈信息 <br>
	 * 〈功能详细描述〉
	 * 
	 * @param e 异常
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String paraseException(Throwable e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		String errors = writer.getBuffer().toString();
		return errors;
	}
}
