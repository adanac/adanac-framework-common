package com.adanac.framework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	/**
	 * 请求头名称：CDN
	 */
	public static final String HEADER_NAME_CDN = "Cdn-Src-Ip";

	/**
	 * 请求头名称：X-Real
	 */
	public static final String HEADER_NAME_X_REAL = "X-Real-IP";

	/**
	 * 请求头名称：X-Forwarded-For
	 */
	public static final String HEADER_NAME_X_FORWARDED_FOR = "X-Forwarded-For";

	private static final String STRING_BLANK = "";

	/**
	 * 功能描述: 取得客户端的IP地址
	 * @param request：http请求对象
	 * @return IP地址
	 */
	public static String getClientIP(HttpServletRequest request) {
		// 客户端IP
		String clientIP = "";

		// 检查Cdn-Src-Ip：如果取得了CDN的IP，直接返回
		clientIP = request.getHeader(HEADER_NAME_CDN);
		if (!isEmpty(clientIP)) {
			return clientIP;
		}

		// 检查X-Real-IP：如果取得了X-Real的IP，直接返回
		clientIP = request.getHeader(HEADER_NAME_X_REAL);
		if (!isEmpty(clientIP)) {
			return clientIP;
		}

		/*
		 * 1.经过代理或者代理服务器以后，由于在客户端和服务之间增加了中间层，
		 * 因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回 给客户端。
		 * 2.但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息，
		 * 用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
		 * 3.如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP，
		 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
		 */
		String forwordIPs = request.getHeader(HEADER_NAME_X_FORWARDED_FOR);
		if (!isEmpty(forwordIPs)) {
			// 存在多个IP时取第一个
			clientIP = forwordIPs.split(",")[0].trim();
			if (!isEmpty(clientIP)) {
				return clientIP;
			}
		}

		// 如果都没取到，直接取远程地址
		if (isEmpty(clientIP)) {
			clientIP = request.getRemoteAddr();
		}
		return clientIP;
	}

	/**
	 * 功能描述: 根据KEY值从请求对象中读取cookie值
	 * @param request：请求对象
	 * @param key：cookie键
	 * @return cookie值
	 */
	public static String getCookieValue(HttpServletRequest request, String key) {
		String value = null;
		if (!isEmpty(key)) {
			Cookie[] cookie = request.getCookies();
			if (cookie != null && cookie.length > 0) {
				for (int i = 0; i < cookie.length; i++) {
					if (((Cookie) cookie[i]).getName().equalsIgnoreCase(key)) {
						value = ((Cookie) cookie[i]).getValue();
						break;
					}
				}
			}
		}
		return value;
	}

	/**
	 * 功能描述: 判断字符串是否为null值或空串
	 * @param str：字符串
	 * @return 是否为空
	 */
	private static boolean isEmpty(String str) {
		boolean isEmpty = false;
		if (str == null || STRING_BLANK.equals(str.trim())) {
			isEmpty = true;
		}
		return isEmpty;
	}
}
