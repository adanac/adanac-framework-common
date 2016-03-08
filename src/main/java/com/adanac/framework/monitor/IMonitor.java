package com.adanac.framework.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 监听接口类型,如果可以实现日志监听、运用堆栈监听等
 * @author adanac
 * @version 1.0
 */
public interface IMonitor {
	/**
	 * 监听方法实现,可以记录想要得到的东西,
	 * @return
	 * @throws Throwable
	 */
	public Object doMonitor(ProceedingJoinPoint point) throws Throwable;

	/**
	 * 切入点抛出异常
	 * @param point 切入点
	 * @param ex 抛出的异常
	 */
	public void doThrowing(JoinPoint point, Throwable ex) throws Throwable;
}
