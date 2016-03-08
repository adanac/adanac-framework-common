package com.adanac.framework.page;

import java.io.Serializable;

/**
 * 查询参数基类     已作废 
 * @author adanac
 * @param <T> 要查询的对象类型
 * @version 1.0
 */
@Deprecated
public class QueryParam<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5656034453714244257L;
	private int pageNumber = 1;
	private int pageSize = 10;
	private String orderBy;
	private boolean orderAsc;
	private boolean needCount = true;
	private T queryParam;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

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

	public boolean isNeedCount() {
		return needCount;
	}

	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}

	public T getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(T queryParam) {
		this.queryParam = queryParam;
	}

}
