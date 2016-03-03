package com.adanac.framework.exception.handler;

import com.adanac.framework.exception.model.RawExceptionInfo;

/**
 * 提取并格式化异常信息的接口
 * @author adanac
 * @version 1.0
 */
public interface ExceptionHandler {
	void handle(RawExceptionInfo exceptionInfo);
}
