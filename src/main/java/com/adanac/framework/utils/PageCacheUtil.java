package com.adanac.framework.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * 页面缓存设置工具类
 * @author adanac
 * @version 1.0
 */
public class PageCacheUtil {
	private static final int MILLISECOND = 1000;

	/**
	 * 
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static void setCacheHeader(HttpServletResponse response, Integer cacheTime) {
		long now = System.currentTimeMillis();
		long cacheTimeLong = cacheTime * MILLISECOND;
		response.setDateHeader("Expires", now + cacheTimeLong);
		response.setHeader("Pragma", "Pragma");
		if (cacheTime != null && cacheTime > 0) {
			response.setHeader("Cache-Control", "max-age" + "=" + cacheTime);
			// response.setHeader("Cache-Control", "s-maxage" + "=" +
			// cacheTime);
			response.setDateHeader("Last-Modified", now - (now % cacheTimeLong));
		} else {
			response.setHeader("Cache-Control", "no-cache,no-store");
		}
	}
}
