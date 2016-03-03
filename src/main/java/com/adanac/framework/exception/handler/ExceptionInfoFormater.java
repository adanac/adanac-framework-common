package com.adanac.framework.exception.handler;

import com.adanac.framework.exception.model.ExceptionInfo;

/**
 * 异常信息格式化器接口
 * @author adanac
 * @version 1.0
 * @see com.adanac.framework.exception.handler.JsonExceptionInfoFormater
 * @see com.adanac.framework.exception.handler.DefaultLogExceptionInfoFormater.TextExceptionInfoFormater
 */
public interface ExceptionInfoFormater {
	String getFormatString(ExceptionInfo expInfo);
}
