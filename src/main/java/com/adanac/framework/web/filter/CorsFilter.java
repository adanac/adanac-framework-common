package com.adanac.framework.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/*
 * 通过CORS技术实现AJAX跨域访问
 * Access-Control-Allow-Origin：允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制。
Access-Control-Allow-Methods：允许访问的方法名，多个方法名用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS。
Access-Control-Allow-Credentials：是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true。
Access-Control-Allow-Headers：允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type。
Access-Control-Expose-Headers：允许客户端访问的服务端响应头，多个响应头用逗号分割。
需要注意的是，CORS规范中定义Access-Control-Allow-Origin只允许两种取值，要么为*，要么为具体的域名，也就是说，不支持同时配置多个域名。
为了解决跨多个域的问题，需要在代码中做一些处理，这里将Filter初始化参数作为一个域名的集合（用逗号分隔），只需从当前请求中获取Origin请求头，
就知道是从哪个域中发出的请求，若该请求在以上允许的域名集合中，则将其放入Access-Control-Allow-Origin响应头，这样跨多个域的问题就轻松解决了
 *  <init-param>  
        <param-name>allowOrigin</param-name>  
        <param-value>http://web.xxx.com</param-value>  
    </init-param>  
    <init-param>  
        <param-name>allowMethods</param-name>  
        <param-value>GET,POST,PUT,DELETE,OPTIONS</param-value>  
    </init-param>  
    <init-param>  
        <param-name>allowCredentials</param-name>  
        <param-value>true</param-value>  
    </init-param>  
    <init-param>  
        <param-name>allowHeaders</param-name>  
        <param-value>Content-Type</param-value>  
    </init-param>  
</filter>  
 */
public class CorsFilter implements Filter {

	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (StringUtils.isNotEmpty(allowOrigin)) {
			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
			if (CollectionUtils.isNotEmpty(allowOriginList)) {
				String currentOrigin = request.getHeader("Origin");
				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}
			}
		}
		if (StringUtils.isNotEmpty(allowMethods)) {
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (StringUtils.isNotEmpty(allowCredentials)) {
			response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		}
		if (StringUtils.isNotEmpty(allowHeaders)) {
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (StringUtils.isNotEmpty(exposeHeaders)) {
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}

}
