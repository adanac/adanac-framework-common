package com.adanac.framework.lang.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.adanac.framework.contexts.RequestContext;
import com.adanac.framework.lang.utils.EventConstants;
import com.adanac.framework.lang.utils.EventUtils;

/**
 * 基本事件类型
 * 事件主要针对Web应用程序发生的行为作后续追踪
 * Web应用程序触发事件产生，事件提取相应的信息做记录用于后续分析总结，告警、应用程序非正常等行为
 * 
 * 该类为基本类型，定义了各类事件的公共部分
 * @author adanac
 * @version 1.0
 */
public abstract class BaseEventMessage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8456403430523015307L;

	public static String DEFAULT_TEST_FLAG = "N";

	public static String EVENT_MESSAGE_VERSION = "1.0";

	/**
	 * 标识事件的ID
	 */
	protected String eventId;

	/**
	 * 事件类型
	 */
	protected EventType eventType;

	/**
	 * 标识每次请求的ID
	 */
	protected String requestId;

	/**
	 * 事件发生的时间
	 */
	protected Date eventTime;

	/**
	 * 产生事件的方法的名称
	 */
	protected String funName;

	/**
	 * 发出此次请求的用户编码
	 */
	protected String userNum;

	/**
	 * 请求关联的sessionId
	 */
	protected String sessionId;

	/**
	 * 发出此次请求的用户所使用的计算机IP
	 */
	protected String userIp;

	/**
	 * 接收并处理此次请求的计算机名称
	 */
	protected String hostName;

	/**
	 * 接收并处理此次请求的计算机IP
	 */
	protected String hostIp;

	/**
	 * 接收并处理此次请求的应用所部署的web服务器的名称
	 */
	protected String serverName;

	/**
	 * 接收并处理此次请求的应用的端口
	 */
	protected Integer serverPort;

	/**
	 * 事件所处的线程的ID
	 */
	protected long threadId;

	/**
	 * 标识本事件是否为测试事件
	 */
	protected String testFlag = DEFAULT_TEST_FLAG;

	/**
	 * 事件的版本号
	 */
	protected String version = EVENT_MESSAGE_VERSION;

	/**
	 * 事件的产生顺序
	 */
	protected int eventSequence;

	public BaseEventMessage() {
		init();
	}

	public BaseEventMessage(EventType eventType, String funName) {
		init();
		this.eventType = eventType;
		this.funName = funName;
	}

	private void init() {
		this.eventId = this.generateEventId();
		this.eventTime = new Date();
		this.eventSequence = this.generateEventSeq();
		this.requestId = RequestContext.getRequestId();
		if (this.requestId == null) {
			this.requestId = "";
		}
		this.userNum = (String) RequestContext.get(EventConstants.REQUEST_USER_NUM_4_EVENT);
		if (this.userNum == null) {
			this.userNum = "";
		}
		this.sessionId = (String) RequestContext.get(EventConstants.REQUEST_SESSION_ID_4_EVENT);
		if (this.sessionId == null) {
			this.sessionId = "";
		}
		this.userIp = (String) RequestContext.get(EventConstants.REQUEST_USER_IP_4_EVENT);
		if (this.userIp == null) {
			this.userIp = "";
		}
		this.serverPort = (Integer) RequestContext.get(EventConstants.REQUEST_SEVER_PORT);
		if (this.serverPort == null) {
			this.serverPort = 0;
		}
		this.hostIp = EventUtils.getHostIp();
		this.hostName = EventUtils.getHostName();
		this.serverName = EventUtils.getServerName();
		this.threadId = Thread.currentThread().getId();
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the eventTime
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the funName
	 */
	public String getFunName() {
		return funName;
	}

	/**
	 * @param funName the funName to set
	 */
	public void setFunName(String funName) {
		this.funName = funName;
	}

	/**
	 * @return the userNum
	 */
	public String getUserNum() {
		return userNum;
	}

	/**
	 * @param userNum the userNum to set
	 */
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the userIp
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * @param userIp the userIp to set
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the hostIp
	 */
	public String getHostIp() {
		return hostIp;
	}

	/**
	 * @param hostIp the hostIp to set
	 */
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the serverPort
	 */
	public Integer getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the threadId
	 */
	public long getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId the threadId to set
	 */
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	/**
	 * @return the testFlag
	 */
	public String getTestFlag() {
		return testFlag;
	}

	/**
	 * @param testFlag the testFlag to set
	 */
	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the eventSequence
	 */
	public int getEventSequence() {
		return eventSequence;
	}

	/**
	 * @param eventSequence the eventSequence to set
	 */
	public void setEventSequence(int eventSequence) {
		this.eventSequence = eventSequence;
	}

	private String generateEventId() {
		return "event-" + UUID.randomUUID().toString();
	}

	private int generateEventSeq() {
		AtomicInteger seq = (AtomicInteger) RequestContext.get(EventConstants.SEQUENCE_4_EVENT);
		if (seq == null) {
			seq = new AtomicInteger(1);
		} else {
			seq.getAndIncrement();
		}
		RequestContext.put(EventConstants.SEQUENCE_4_EVENT, seq);
		return seq.get();
	}

}