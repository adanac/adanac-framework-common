package com.adanac.framework.contexts;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

/**
 * 当前线程中的request和response变量
 * @author adanac
 * @version 1.0
 */
public class ActionContext {
	private static final ThreadLocal<HttpServletRequest> localrequest = new ThreadLocal<HttpServletRequest>();
	private static final ThreadLocal<HttpServletResponse> localresponse = new ThreadLocal<HttpServletResponse>();

	public static void setHttpServletRequest(HttpServletRequest request) {
		localrequest.set(request);
	}

	public static void setHttpServletResponse(HttpServletResponse response) {
		localresponse.set(response);
	}

	/**
	 * 获取HttpServletRequest对象
	 * @return HttpServletRequest对象
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return localrequest.get();
	}

	/**
	 * 获取HttpServletResponse对象
	 * @return HttpServletResponse对象
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return localresponse.get();
	}

	/**
	 * 获取HttpSession对象
	 * @return HttpSession对象
	 */
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 获取Servlet上下文对象
	 * @return ServletContext对象
	 */
	public static ServletContext getServletContext() {
		return getHttpServletRequest().getSession().getServletContext();
	}

	/**
	 * 获取绝对路径
	 * @param path 相对路径
	 * @return 绝对路径
	 */
	public static String getRealPath(String path) throws FileNotFoundException {
		return WebUtils.getRealPath(getServletContext(), path);
	}
}
