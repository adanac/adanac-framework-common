package com.adanac.framework.exception.event;

import org.springframework.context.ApplicationEvent;

/**
 * 异常信息的监听事件
 * @author adanac
 * @version 1.0
 */
public class TextExceptionMessageEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 356712407466866299L;
	private String message;

	/**
	 * 构造方法
	 * @param source
	 * @param message
	 */
	public TextExceptionMessageEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
