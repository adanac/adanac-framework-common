package com.adanac.framework.exception.event;

/**
 * 异常信息的监听事件
 * @author adanac
 * @version 1.0
 */
public class MonitorExceptionMessageEvent extends TextExceptionMessageEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8325554401963742611L;

	public MonitorExceptionMessageEvent(Object source, String message) {
		super(source, message);
	}

}
