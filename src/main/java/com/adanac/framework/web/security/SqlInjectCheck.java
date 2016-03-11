package com.adanac.framework.web.security;

/**
 * 将请求中的参数进行如下处理
 * 其实现如下： 1、将’转化成&acute; 2、返回字符串。
 * @author adanac
 * @version 1.0
 */
public class SqlInjectCheck {
	public String check(String oldValue) {
		if (oldValue != null) {
			String ret = oldValue;
			ret = ret.replaceAll("'", "&acute");
			return ret;
		} else
			return oldValue;
	}
}
