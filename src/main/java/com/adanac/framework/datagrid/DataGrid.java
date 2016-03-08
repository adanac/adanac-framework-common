package com.adanac.framework.datagrid;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class DataGrid<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1913691974056284554L;
	private List<T> data = Collections.emptyList();// 当前页数据
	private int pageNumber;// 当前页码
	private int pageSize;// 当前页码记录数

	public long getTotal() {
		return data.size();
	}

	public void setTotal(long total) {
		data.size();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

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

}
