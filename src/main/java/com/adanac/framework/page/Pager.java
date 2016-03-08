package com.adanac.framework.page;

import java.io.Serializable;
import java.util.List;

/**
 * 查询结果基类
 * 要返回的对象类型
 * @author adanac
 * @version 1.0
 */
public class Pager<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7736308743092497014L;

	/** list 列表 */
	private List<T> datas;

	/**是否是最后一页 */
	private Boolean isLastPage;

	/** 记录总条数 */
	private Integer totalDataCount = 0;

	/** 当前的页数 */
	private int pageNumber = 1;

	/** 每页记录条数，默认10条 */
	private int pageSize = 10;

	/** 记录总条数 */
	private Integer pageCount;

	/** 记录起始行 */
	private int indexNumber;

	/**
	 * @param pageSize 每页显示条数
	 * @param pageNumber 当前的页数
	 */
	public Pager(int pageSize, int pageNumber) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		if (this.pageNumber < 1) {
			this.pageNumber = 1;
		}
		this.indexNumber = (this.pageNumber - 1) * this.pageSize;
	}

	/**
	 * @param totalDataCount 总数据件数
	 * @param pageSize 每页显示条数
	 * @param pageNumber 当前的页数
	 */
	public Pager(int totalDataCount, int pageSize, int pageNumber) {
		super();
		this.totalDataCount = totalDataCount;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		if (this.pageNumber < 1) {
			this.pageNumber = 1;
		}
		if (this.totalDataCount <= 0) {
			return;
		}
		// 如果查询页数大于总页数，则取最后一页
		if (this.totalDataCount <= (this.pageNumber - 1) * this.pageSize) {
			this.pageNumber = (this.totalDataCount + this.pageSize - 1) / this.pageSize;
		}
		this.indexNumber = (this.pageNumber - 1) * this.pageSize;
		// 总页数
		this.pageCount = (this.totalDataCount + this.pageSize - 1) / this.pageSize;
		// 是否为最后一页
		this.isLastPage = (this.pageNumber == this.pageCount ? true : false);
	}

	public Pager() {
		super();
	}

	/**
	 * 返回的数据集
	 * @return the datas
	 */
	public List<T> getDatas() {
		return datas;
	}

	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	/**
	 * 满足查询条件的总记录数， null 意味着未知。注：只在查询第一页时返回正确的总记录数，其它页码时，返回-1
	 * @return the totalDataCount
	 */
	public Integer getTotalDataCount() {
		return totalDataCount;
	}

	/**
	 * @param totalDataCount the totalDataCount to set
	 */
	public void setTotalDataCount(Integer totalDataCount) {
		if (totalDataCount <= 0) {
			return;
		}
		this.totalDataCount = totalDataCount;
		// 如果查询页数大于总页数，则取最后一页
		if (this.totalDataCount <= (this.pageNumber - 1) * this.pageSize) {
			this.pageNumber = (this.totalDataCount + this.pageSize - 1) / this.pageSize;
		}
		this.indexNumber = (this.pageNumber - 1) * this.pageSize;
		// 总页数
		this.pageCount = (this.totalDataCount + this.pageSize - 1) / this.pageSize;
		// 是否为最后一页
		this.isLastPage = (this.pageNumber == this.pageCount ? true : false);

	}

	/**
	 * 页码,从1开始
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 满足查询条件的总页数， null 意味着未知。注：只在查询第一页时返回正确的总记录数，其它页码时，返回-1
	 * 
	 * @return the pageCount
	 */
	public Integer getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 每页大小，缺省为10条记录/页
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 标志是否最后一页，True: 是最后一页，False: 不是，null：未知
	 */
	public Boolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(Boolean lastPage) {
		this.isLastPage = lastPage;
	}

	/**
	 * 计算开始数
	 */
	public int getIndexNumber() {
		return indexNumber;
	}
}
