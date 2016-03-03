package com.adanac.framework.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;

import com.adanac.framework.exception.handler.ExceptionHandler;
import com.adanac.framework.exception.model.RawExceptionInfo;
import com.adanac.framework.exception.utils.ApplicationContextHolder;

/**
 * 异常信息处理拦截器
 * @author adanac
 * @version 1.0
 */
public class ExceptionProcessDispatch implements InitializingBean, ApplicationContextAware {

	// 提取的异常信息
	private List<ExceptionHandler> handlers;

	// 应用上下文
	private ApplicationContext applicationContext;

	public void process(RawExceptionInfo exceptionInfo) {
		if (CollectionUtils.isEmpty(handlers)) {
			return;
		}

		for (ExceptionHandler handler : handlers) {
			handler.handle(exceptionInfo);
		}
	}

	/*
	 * initial handlers, include all ExceptionHandler bean defined in spring
	 * context
	 * 
	 */
	public void afterPropertiesSet() throws Exception {
		Map<String, ExceptionHandler> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				ExceptionHandler.class, true, false);

		if (!matchingBeans.isEmpty()) {
			this.handlers = new ArrayList<ExceptionHandler>(matchingBeans.values());
			// We keep handlers in sorted order.
			OrderComparator.sort(this.handlers);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		ApplicationContextHolder.setApplicationContext(applicationContext);
	}

}
