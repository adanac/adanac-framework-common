package com.adanac.framework.monitor;

import java.util.Stack;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class StackContextHolder {
	private static final ThreadLocal<Stack<StackContext>> contextHolder = new ThreadLocal<Stack<StackContext>>();

	private static final ThreadLocal<String> stackFlagContextHolder = new ThreadLocal<String>();

	public static int size() {
		if (contextHolder.get() == null)
			return 0;
		else
			return contextHolder.get().size();
	}

	/**
	 * 获取当前的Stack,不移除
	 */
	public static StackContext getCurrentStack() {
		if (contextHolder.get() == null || contextHolder.get().isEmpty())
			return null;
		return contextHolder.get().peek();
	}

	/**
	 * 冒泡泡,将移除最后一个Stack
	 */
	public static StackContext popStack() {
		if (contextHolder.get() == null || contextHolder.get().isEmpty())
			return null;
		return contextHolder.get().pop();
	}

	public static void addStack(StackContext stackContext) {
		if (contextHolder.get() == null) {
			Stack<StackContext> stacks = new Stack<StackContext>();
			contextHolder.set(stacks);
			// setCurrentStackFlag(Thread.currentThread().getId());
		}
		contextHolder.get().push(stackContext);
	}

	public static void setCurrentStackFlag(String flag) {
		if (stackFlagContextHolder.get() == null) {
			stackFlagContextHolder.set(flag);
		}
	}

	public static String getCurrentStackFlag() {
		return stackFlagContextHolder.get();
	}
}
