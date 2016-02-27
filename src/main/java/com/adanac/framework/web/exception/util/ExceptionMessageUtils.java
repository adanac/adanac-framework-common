package com.adanac.framework.web.exception.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.adanac.framework.lang.exception.BaseException;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class ExceptionMessageUtils {
	public static final String DEFAULT_EXCEPTION_MESSAGE_KEY = "exception.defaultMessage";
	public static final String DEFAULT_EXCEPTION_MESSAGE = "";

	public static String getFriendlyExceptionMessage(Throwable ex) {

		HttpServletRequest request = getCurrentRequest();
		Locale locale = LocaleContextHolder.getLocale();

		// 取出web应用上下文
		WebApplicationContext webAppContext = WebAppContextUtils.getWebApplicationContext(request);

		// 取全局缺省异常消息
		String defaultGlobalMessage = webAppContext.getMessage(DEFAULT_EXCEPTION_MESSAGE_KEY, null,
				DEFAULT_EXCEPTION_MESSAGE, locale);
		String message = defaultGlobalMessage;

		BaseException bex;
		if (!(ex instanceof BaseException)) {
			bex = new BaseException(ex);
		} else {
			bex = (BaseException) ex;
		}

		String code = bex.getCode();
		if (StringUtils.hasText(code)) { // 有异常code
			Object[] args = bex.getMessageArgs();
			String userDefaultMessage = bex.getDefaultFriendlyMessage(); // defaultFriendlyMessage一个异常时传递的defaultMessage参数

			if (StringUtils.hasText(userDefaultMessage)) {
				message = webAppContext.getMessage(code, args, userDefaultMessage, locale);
			} else {
				message = webAppContext.getMessage(code, args, defaultGlobalMessage, locale);
			}
		} else {
			/**
			 * 无异常code,根据异常类名查找匹配的message
			 */
			Throwable tr = bex.getMostSpecificCause();
			String exClassName = tr.getClass().getName();
			message = webAppContext.getMessage(exClassName, null, defaultGlobalMessage, locale);
		}
		return message;
	}

	public static HttpServletRequest getCurrentRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
		Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
		HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
		Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
		return servletRequest;
	}
}
