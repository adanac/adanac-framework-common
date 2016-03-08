package com.adanac.framework.exception.handler.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.adanac.framework.exception.BaseException;
import com.adanac.framework.exception.handler.ExceptionInfoExtractor;
import com.adanac.framework.exception.model.ExceptionInfo;
import com.adanac.framework.exception.model.RawExceptionInfo;

/**
 * 异常信息事件的监听器
 * @author adanac
 * @version 1.0
 */
public class DefaultMonitorMessageExtractor implements ExceptionInfoExtractor {
	public ExceptionInfo extract(final RawExceptionInfo rawEx) {
		ExceptionInfo exceptionInfo = new ExceptionInfo();

		Throwable ex = rawEx.getThrowable();
		if (ex instanceof BaseException) {
			BaseException be = (BaseException) ex;
			exceptionInfo.setCode(be.getCode());
		}

		// set异常被拦截的时间
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String interceptTime = dateformat.format(new Date());
		exceptionInfo.setInterceptTime(interceptTime);

		exceptionInfo.setExceptionClass(ex.getClass().getName());
		exceptionInfo.setExceptionMessage(ex.getLocalizedMessage());
		return exceptionInfo;
	}
}
