package com.adanac.framework.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用日志记录
 * @author adanac
 * @version 1.0
 */
public class OpLogMonitor implements IMonitor {

	public Object doMonitor(ProceedingJoinPoint point) throws Throwable {
		Logger logger = LoggerFactory.getLogger(getClass());
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(point.getTarget().getClass().getName()).append("#").append(point.getSignature().getName())
				.append("]");
		long begin = System.currentTimeMillis();
		sb.append("开始时间：[").append(begin).append("]");
		// 实际方法执行
		Object result = point.proceed();
		long end = System.currentTimeMillis();
		sb.append("结束时间：[").append(end).append("]");
		sb.append("共耗费：[").append((end - begin)).append("ms]");
		logger.info(sb.toString());
		return result;
	}

	public void doThrowing(JoinPoint jp, Throwable ex) throws Throwable {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.error(ex.getMessage(), ex);
	}

}
