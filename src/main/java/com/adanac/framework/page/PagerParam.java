package com.adanac.framework.page;

import java.io.Serializable;

/**
 * 查询参数基类
 * 要查询的对象类型
 * @author adanac
 * @version 1.0
 */
public class PagerParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5758607622056828683L;
	private int pageNumber = 1;
	private int pageSize = 10;
	private String orderBy;
	private boolean orderAsc;
	private boolean needCount = true;

	// 要查询的页码，从1开始
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	// 页面大小，缺省为每页10行
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isOrderAsc() {
		return orderAsc;
	}

	public void setOrderAsc(boolean orderAsc) {
		this.orderAsc = orderAsc;
	}

	// 是否需要返回总记录数
	public boolean isNeedCount() {
		return needCount;
	}

	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}

}
