package com.adanac.framework.exception.handler;

import com.adanac.framework.exception.model.ExceptionInfo;
import com.adanac.framework.exception.model.RawExceptionInfo;

/**
 * 异常信息提取器接口
 * 从拦截的JoinPoint和异常提取相关信息封装为ExceptionInfo对象
 * @author adanac
 * @version 1.0
 */
public interface ExceptionInfoExtractor {
	ExceptionInfo extract(final RawExceptionInfo rawEx);
}
