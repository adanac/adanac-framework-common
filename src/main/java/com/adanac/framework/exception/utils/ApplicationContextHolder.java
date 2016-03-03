package com.adanac.framework.exception.utils;

import org.springframework.context.ApplicationContext;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class ApplicationContextHolder {
	private static ApplicationContext _context;

	public static ApplicationContext getApplicationContext() {
		return _context;
	}

	public static void setApplicationContext(ApplicationContext context) {
		_context = context;
	}
}
