package com.adanac.framework.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.adanac.framework.utils.ObjectUtil;
import com.adanac.framework.web.constant.CommonConstant;

/**
 * 主题和国际化信息处理
 * @author adanac
 * @version 1.0
 */
public class LocaleAndThemResolve {
	private LocaleAndThemResolve() {
	}

	/**
	 * 
	 * 功能描述：Spring使用CookieLocaleResolver保存国际化信息 从Cookie里面解析出 Locale
	 * 
	 * @param request
	 * @return
	 */
	public static Locale resolveLocale(HttpServletRequest request) {
		Locale locale = request.getLocale();
		if (request.getAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE) != null) {
			AcceptHeaderLocaleResolver cookieLocal = (AcceptHeaderLocaleResolver) request
					.getAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE);
			locale = cookieLocal.resolveLocale(request);
		}
		return locale;

	}

	/**
	 * 
	 * 功能描述：从Locale里面取出当前使用的语言代码
	 * 
	 * @param locale
	 * @return
	 */
	public static String getLocaleLang(Locale locale) {
		String lang = locale.getLanguage();
		if (ObjectUtil.isEmpty(lang)) {
			lang = CommonConstant.LANGUAGECODE_DEFAULT;
		}
		return lang;
	}
}
