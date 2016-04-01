package com.adanac.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.adanac.framework.contexts.RequestContext;
import com.adanac.framework.lang.utils.EventConstants;

public class RequestHelper4MonitorEventFilter implements Filter {

	private static final String USER_ID = "logonUserIdLastTime";

	private String cookieNames;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		cookieNames = filterConfig.getInitParameter("cookieNames");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		RequestContext.reset();
		fromRequest2Context(request);
		chain.doFilter(request, response);
	}

	private void fromRequest2Context(ServletRequest request) {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			RequestContext.put(EventConstants.REQUEST_USER_IP_4_EVENT, getIpAddr(httpServletRequest));
			RequestContext.put(EventConstants.REQUEST_SESSION_ID_4_EVENT, httpServletRequest.getSession().getId());
			RequestContext.put(EventConstants.REQUEST_SEVER_PORT, (Integer) httpServletRequest.getServerPort());
			putCookieValues(httpServletRequest);
		}

	}

	private void putCookieValues(HttpServletRequest httpServletRequest) {
		if (cookieNames == null) {
			return;
		}
		String[] cookieArr = cookieNames.split(",");
		for (String cookieName : cookieArr) {
			String cookieValue = getCookieValue(httpServletRequest, cookieName);
			if (cookieValue != null) {
				if (cookieName.equals(USER_ID)) {
					RequestContext.put(EventConstants.REQUEST_USER_NUM_4_EVENT, cookieValue);
				} else {
					RequestContext.put(cookieName, cookieValue);
				}
			}
		}

	}

	private String getCookieValue(HttpServletRequest httpServletRequest, String cookieName) {
		Cookie cookies[] = httpServletRequest.getCookies();
		if (cookies == null || cookieName == null || cookieName.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookieName.equals(cookies[i].getName())) {
				return cookies[i].getValue();
			}
		}
		return null;
	}

	private Object getIpAddr(HttpServletRequest httpServletRequest) {
		String ip = httpServletRequest.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public void destroy() {

	}
}
