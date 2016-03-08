package com.adanac.framework.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * 堆栈的运行信息类
 * @author adanac
 * @version 1.0
 */
public class StackContext implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1496096245003891936L;
	/**
	 * 标志
	 */
	private String stackId;
	/**
	 * 上级标志
	 */
	private String parentStackId;
	/**
	 * 层级
	 */
	private int level;
	/**
	 * 在调用 的类信息
	 */
	private String clazz;
	/**
	 * 方法名称
	 */
	private String method;
	/**
	 * 所在行数
	 */
	private int line;
	/**
	 * 传入参数
	 */
	private Object[] args;
	/**
	 * 返回结果
	 */
	private Object result;
	/**
	 * 执行情况0成功,-1异常,1运行中
	 */
	private int state;
	/**
	 * 开始时间
	 */
	private Date start;
	/**
	 * 结束时间
	 */
	private Date end;
	/**
	 * 耗时(ms)
	 */
	private long elapsedTime;
	/**
	 * 异常信息
	 */
	private Throwable throwable;

	/**
	 * 执行sql
	 */
	private String runSql;

	public String getStackId() {
		return stackId;
	}

	public void setStackId(String stackId) {
		this.stackId = stackId;
	}

	public String getParentStackId() {
		return parentStackId;
	}

	public void setParentStackId(String parentStackId) {
		this.parentStackId = parentStackId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getRunSql() {
		return runSql;
	}

	public void setRunSql(String runSql) {
		this.runSql = runSql;
	}

}
