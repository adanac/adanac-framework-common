package com.adanac.framework.lang.dto;

/**
 * 追踪事件
 * 追踪事件用于记录应用程序认为应该在后期统计，分析的较ExceptionEventMessage,PerformanceEventMessage零散的信息。<br>
 * 
 * @author adanac
 * @version 1.0
 */
public class TraceEventMessage extends BaseEventMessage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7580180760391659794L;

	/**
	 * 追踪事件的级别类型
	 */
	public enum LOG_LEVEL {
		ERROR, WARN, INFO, DEBUG, TRACE;
	}

	public TraceEventMessage(String funName) {
		super(EventType.TRACE, funName);
	}

	public TraceEventMessage(String funName, String traceMsg, LOG_LEVEL logLevel) {
		super(EventType.TRACE, funName);
		this.traceMsg = traceMsg;
		this.logLevel = logLevel;
	}

	/**
	 * 追踪事件的具体信息
	 */
	private String traceMsg;

	/**
	 * 追踪事件的级别
	 */
	private LOG_LEVEL logLevel;

	/**
	 * @return the traceMsg
	 */
	public String getTraceMsg() {
		return traceMsg;
	}

	/**
	 * @param traceMsg the traceMsg to set
	 */
	public void setTraceMsg(String traceMsg) {
		this.traceMsg = traceMsg;
	}

	/**
	 * @return the logLevel
	 */
	public LOG_LEVEL getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(LOG_LEVEL logLevel) {
		this.logLevel = logLevel;
	}
}