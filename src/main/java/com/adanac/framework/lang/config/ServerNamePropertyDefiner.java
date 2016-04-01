package com.adanac.framework.lang.config;

import com.adanac.framework.lang.utils.EventUtils;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class ServerNamePropertyDefiner extends PropertyDefinerBase {
	public String getPropertyValue() {
		return EventUtils.getServerName();
	}
}
