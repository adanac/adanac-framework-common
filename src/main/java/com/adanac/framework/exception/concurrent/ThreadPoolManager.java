package com.adanac.framework.exception.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器，管理LogExceptionProcessWorker线程，对拦截到的每一个异常，分配一个LogExceptionProcessWorker来处理。
 * 利用JDK1.5 的ThreadPoolExecutor来管理线程池
 * @author adanac
 * @version 1.0
 */
public class ThreadPoolManager {
	private int coreSize = Runtime.getRuntime().availableProcessors();
	private ThreadPoolExecutor taskPool = new ThreadPoolExecutor(coreSize, coreSize * 4, 30, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(8), new ThreadPoolExecutor.CallerRunsPolicy());

	private static ThreadPoolManager instance = new ThreadPoolManager();

	private ThreadPoolManager() {
	}

	public static ThreadPoolManager getInstance() {
		return instance;
	}

	/**
	 * 功能描述：将command添加到线程池
	 * @param 参数说明 Runnable类型的command
	 */
	public void run(Runnable command) {
		this.taskPool.execute(command);
	}
}
