package com.adanac.framework.lang.dto;

import java.util.Date;

/**
 * 性能事件<br>
 * 该事件用于记录应用程序某些方法（可配）执行的时间等性能相关字段
 * @author adanac
 * @version 1.0
 */
public class PerformanceEventMessage extends BaseEventMessage {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1996156173289883655L;

	public static final String DEFAULT_STATUS = "OK";

	public static final String NAN_ERROR_CODE = "NAN ERROR CODE";

	public static final String NAN_CALLER = "NAN CALLER";

	public PerformanceEventMessage(String funName) {
		super(EventType.PERFORMANCE, funName);
	}

	/**
	 * 产生性能事件的方法的调用者
	 */
	private String funCallerName;

	/**
	 * 产生性能事件的方法的被调用次序（以一次请求的开始为基准）
	 */
	private int sequence;

	/**
	 * 产生性能事件的方法的执行结束时间
	 */
	private Date endTime;

	/**
	 * 产生性能事件的方法执行时间，单位ms
	 */
	private long usedTime;

	/**
	 * 在一次请求的过程中，本性能事件相对于第一个性能事件发生时间的时间差，单位ms
	 */
	private long relativeTime;

	/**
	 * 本性能事件的执行情况，默认值为OK，否则为发生异常的errorCode，若无errorCode，则值为NAN
	 */
	private String status = DEFAULT_STATUS;

	public void begin() {
		super.eventTime = new Date();
	}

	public void end() {
		this.endTime = new Date();
		this.usedTime = this.endTime.getTime() - super.eventTime.getTime();
	}

	/**
	 * @return the funCallerName
	 */
	public String getFunCallerName() {
		return funCallerName;
	}

	/**
	 * @param funCallerName the funCallerName to set
	 */
	public void setFunCallerName(String funCallerName) {
		this.funCallerName = funCallerName;
	}

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the usedTime
	 */
	public long getUsedTime() {
		return usedTime;
	}

	/**
	 * @param usedTime the usedTime to set
	 */
	public void setUsedTime(long usedTime) {
		this.usedTime = usedTime;
	}

	/**
	 * @return the relativeTime
	 */
	public long getRelativeTime() {
		return relativeTime;
	}

	/**
	 * @param relativeTime the relativeTime to set
	 */
	public void setRelativeTime(long relativeTime) {
		this.relativeTime = relativeTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
