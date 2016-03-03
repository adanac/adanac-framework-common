package com.adanac.framework.exception.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

import com.adanac.framework.exception.concurrent.LogExceptionProcessWorker;
import com.adanac.framework.exception.concurrent.ThreadPoolManager;
import com.adanac.framework.exception.model.RawExceptionInfo;

/**
 * 提取拦截异常的信息，格式化信息
 * @author adanac
 * @version 1.0
 */
public class LogExceptionHandler implements InitializingBean, Ordered, ExceptionHandler {
	private ThreadPoolManager threadPool = ThreadPoolManager.getInstance();
	private int order = Integer.MIN_VALUE; // default: same as non-Ordered
	// 异常信息提取器
	private ExceptionInfoExtractor extractor;
	// 异常信息格式化器
	private ExceptionInfoFormater formater;

	public void handle(RawExceptionInfo exceptionInfo) {
		Assert.notNull(exceptionInfo, "RawExceptionInfo can't be null!");
		threadPool.run(new LogExceptionProcessWorker(exceptionInfo, extractor, formater));
	}

	public void afterPropertiesSet() throws Exception {
		if (null == extractor) {
			extractor = new DefaultLogExceptionInfoExtractor();
		}
		if (null == formater) {
			formater = new DefaultLogExceptionInfoFormater();
		}
	}

	public final void setOrder(int order) {
		this.order = order;
	}

	public final int getOrder() {
		return this.order;
	}

	public void setExtractor(ExceptionInfoExtractor extractor) {
		this.extractor = extractor;
	}

	public void setFormater(ExceptionInfoFormater formater) {
		this.formater = formater;
	}
}
