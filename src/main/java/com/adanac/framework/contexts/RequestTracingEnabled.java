package com.adanac.framework.contexts;

/**
 * 实现此接口的类包含了Request的标识RequestId
 * @author adanac
 * @version 1.0
 */
public interface RequestTracingEnabled {
	String getRequestId();
}
