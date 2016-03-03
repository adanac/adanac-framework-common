package com.adanac.framework.exception.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.adanac.framework.exception.BaseException;
import com.adanac.framework.exception.model.ExceptionInfo;
import com.adanac.framework.exception.model.RawExceptionInfo;
import com.adanac.framework.exception.utils.CommonExceptionUtils;

/**
 * 异常信息提取器接口
 * @author adanac
 * @version 1.0
 */
public class DefaultLogExceptionInfoExtractor implements ExceptionInfoExtractor {
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

		exceptionInfo.setInterceptedClass(rawEx.getInterceptedClass()); // set
																		// 被拦截的类
		exceptionInfo.setInterceptedMethod(rawEx.getInterceptedInterface());
		exceptionInfo.setParameterValue(Arrays.toString(rawEx.getParameterValue())); // set
																						// 被拦截方法的参数值

		exceptionInfo.setExceptionClass(ex.getClass().getName());
		exceptionInfo.setExceptionMessage(ex.getLocalizedMessage());

		// get rootCause of exception
		Throwable rootCause = CommonExceptionUtils.getMostSpecificCause(ex); // 得到根异常
		StackTraceElement[] elements = rootCause.getStackTrace(); // 得到Stack
																	// trace的elements

		exceptionInfo.setRootException(rootCause.getClass().getName());
		exceptionInfo.setRootExceptionMsg(rootCause.getLocalizedMessage());

		exceptionInfo.setRootCauseSpotClass(elements[0].getClassName()); // 抛出根异常的类
		exceptionInfo.setRootCauseSpotMethod(elements[0].getMethodName()); // 抛出根异常的方法
		exceptionInfo.setRootCauseSpotLine("" + elements[0].getLineNumber()); // 抛出根异常的行号

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);

		exceptionInfo.setFullStackTrace(sw.toString());

		return exceptionInfo;
	}
}
