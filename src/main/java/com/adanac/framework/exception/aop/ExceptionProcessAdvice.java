package com.adanac.framework.exception.aop;

import org.aspectj.lang.JoinPoint;

import com.adanac.framework.exception.logger.ExceptionLogger;
import com.adanac.framework.exception.logger.LogMessageBuilder;
import com.adanac.framework.exception.model.RawExceptionInfo;
import com.adanac.framework.interceptor.ExceptionProcessDispatch;

/**
 * 统一异常处理报告
 * @author adanac
 * @version 1.0
 */
public class ExceptionProcessAdvice {
	// 异常处理拦截器
	private ExceptionProcessDispatch exceptionProcessDispatch;

	/**
	 * 
	 * 功能描述：异常抛出后输出日志
	 * @param 参数说明 JoinPoint ，异常类型
	 * @return 返回值
	 * @throw 异常描述
	 */
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Exception {
		try {
			String interceptedClass = joinPoint.getTarget().getClass().getName();
			String interceptedInterface = joinPoint.getSignature().toString();
			Object[] parameterValue = joinPoint.getArgs();
			RawExceptionInfo rawEx = new RawExceptionInfo(interceptedClass, interceptedInterface, parameterValue, ex);

			exceptionProcessDispatch.process(rawEx);
		} catch (Exception e) {
			String msg = LogMessageBuilder.build("Fail to handle the intercepted exception!", e);
			ExceptionLogger.log(msg);
		}
	}

	public ExceptionProcessDispatch getExceptionProcessDispatch() {
		return exceptionProcessDispatch;
	}

	public void setExceptionProcessDispatch(ExceptionProcessDispatch exceptionProcessDispatch) {
		this.exceptionProcessDispatch = exceptionProcessDispatch;
	}
}
