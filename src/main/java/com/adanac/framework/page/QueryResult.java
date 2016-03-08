package com.adanac.framework.page;

import java.io.Serializable;
import java.util.List;

/**
 * 查询结果基类   已作废
 * @author adanac
 * @param <T> 要返回的对象类型
 * @version 1.0
 */
@Deprecated
public class QueryResult<T> implements Serializable {
	private List<T> datas;
	private Boolean isLastPage;
	private Integer totalDataCount;
	private int pageNumber = 1;
	private int pageSize = 10;
	private Integer pageCount;
	private int indexNumber;

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Boolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(Boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public Integer getTotalDataCount() {
		return totalDataCount;
	}

	public void setTotalDataCount(Integer totalDataCount) {
		this.totalDataCount = totalDataCount;
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

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

}
