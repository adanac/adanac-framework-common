package com.adanac.framework.utils;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.http.impl.cookie.BasicClientCookie;

/**
 * Cookie工具类
 * @author adanac
 * @version 1.0
 */
public class CookieUtils {
	private static final int NUM_1000 = 1000;

	public static Cookie getServletCookie(org.apache.http.cookie.Cookie apacheCookie) {
		if (apacheCookie == null) {
			return null;
		}
		String name = apacheCookie.getName();
		String value = apacheCookie.getValue();
		Cookie cookie = new Cookie(name, value);
		value = apacheCookie.getDomain();
		if (value != null) {
			cookie.setDomain(value);
		}
		value = apacheCookie.getPath();
		if (value != null) {
			cookie.setPath(value);
		}
		value = apacheCookie.getComment();
		if (value != null) {
			cookie.setComment(value);
		}
		cookie.setVersion(apacheCookie.getVersion());
		Date expiryDate = apacheCookie.getExpiryDate();
		if (expiryDate != null) {
			long maxAge = (expiryDate.getTime() - System.currentTimeMillis()) / NUM_1000;
			cookie.setMaxAge((int) maxAge);
		}
		return cookie;
	}

	public static org.apache.http.cookie.Cookie getApacheCookie(Cookie cookie) {
		if (cookie == null) {
			return null;
		}
		BasicClientCookie apacheCookie = null;

		// get all the relevant parameters
		String domain = cookie.getDomain();
		String name = cookie.getName();
		String value = cookie.getValue();
		String path = cookie.getPath();
		int maxAge = cookie.getMaxAge();
		boolean secure = cookie.getSecure();
		// create the apache cookie
		apacheCookie = new org.apache.http.impl.cookie.BasicClientCookie(name, value);
		// set additional parameters
		apacheCookie.setDomain(domain);
		apacheCookie.setPath(path);
		Calendar calendar = Calendar.getInstance();
		if (maxAge > 0) {
			calendar.add(Calendar.MILLISECOND, maxAge);
			apacheCookie.setExpiryDate(calendar.getTime());
		}
		apacheCookie.setSecure(secure);
		// return the apache cookie
		return apacheCookie;
	}
}
