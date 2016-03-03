package com.adanac.framework.exception.concurrent;

import com.adanac.framework.exception.handler.ExceptionInfoExtractor;
import com.adanac.framework.exception.handler.ExceptionInfoFormater;
import com.adanac.framework.exception.logger.ExceptionLogger;
import com.adanac.framework.exception.logger.LogMessageBuilder;
import com.adanac.framework.exception.model.ExceptionInfo;
import com.adanac.framework.exception.model.RawExceptionInfo;

/**
 * 异常写日志文件处理工作线程，调用ExceptionHandler来处理异常信息
 * @author adanac
 * @version 1.0
 */
public class LogExceptionProcessWorker implements Runnable {
	private RawExceptionInfo rawEx;
	private ExceptionInfoExtractor exceptionInfoExtractor;
	private ExceptionInfoFormater exceptionInfoFormater;

	/**
	 * 构造方法
	 * @param rawEx
	 * @param exceptionInfoExtractor
	 * @param exceptionInfoFormater
	 */
	public LogExceptionProcessWorker(RawExceptionInfo rawEx, ExceptionInfoExtractor exceptionInfoExtractor,
			ExceptionInfoFormater exceptionInfoFormater) {
		this.rawEx = rawEx;
		this.exceptionInfoExtractor = exceptionInfoExtractor;
		this.exceptionInfoFormater = exceptionInfoFormater;
	}

	public void run() {
		try {
			ExceptionInfo exceptionInfo = exceptionInfoExtractor.extract(rawEx);
			String formatMes = exceptionInfoFormater.getFormatString(exceptionInfo);
			ExceptionLogger.log(formatMes);
		} catch (Exception e) {
			String msg = LogMessageBuilder.build("Fail to handle the intercepted exception!", e);
			ExceptionLogger.log(msg);
		}
	}
}
