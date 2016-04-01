package com.adanac.framework.web.security;

import javax.servlet.http.HttpServletRequest;

/**
 * 将请求中的参数进行如下处理
 * 1.通过参数名称，从请求中取得参数值。 
 * 2、将&,<,>,’,”转义: & -> &amp;< -> &it;  > ->  &gt;  “ ->  &quot; ‘ -> &acute; 
 * 3、返回安全的字符串。
 * @author adanac
 * @version 1.0
 */
public class XSSCheck {

	public String getParameter(HttpServletRequest request, String paramName) {
		if (request == null || paramName == null) {
			return null;
		}
		String ret = request.getParameter(paramName);
		if (ret == null) {
			return null;
		}
		ret = ret.replaceAll("'", "&acute");
		ret = ret.replaceAll("&", "&amp");
		ret = ret.replaceAll("<", "&it");
		ret = ret.replaceAll(">", "&gt");
		ret = ret.replaceAll("\"", "&quot");
		return ret;
	}

	public String check(String oldValue) {
		if (oldValue == null) {
			return null;
		}
		String ret = oldValue;
		ret = ret.replaceAll("'", "&acute");
		ret = ret.replaceAll("&", "&amp");
		ret = ret.replaceAll("<", "&it");
		ret = ret.replaceAll(">", "&gt");
		ret = ret.replaceAll("\"", "&quot");
		return ret;
	}
}
