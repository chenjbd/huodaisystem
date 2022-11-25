package com.cc.app.base.common;

import com.github.pagehelper.PageInfo;

/**
 * 分页实体类
 * @author wang xing gang 2017-06-14
 *
 */
public class PageModel0 {

	public PageModel0(){

	}

	public PageModel0(int pageIndex, int pageSize, int recordCounts, Object dataRows) {
		this.page = pageIndex;
		this.records = recordCounts;
		this.rows = dataRows;
		this.total = recordCounts % pageSize == 0 ? recordCounts / pageSize : recordCounts / pageSize + 1;
	}

	/**
	 * 将分页信息转换成指定分页对象
	 * @param page
     */
	public PageModel0(PageInfo page) {
		this.page = page.getPageNum();
		this.records = (int) page.getTotal();
		this.rows = page.getList();
		this.total = page.getPages();//recordCounts % pageSize == 0 ? recordCounts / pageSize : recordCounts / pageSize + 1;
	}


	//总页数
	private int total;
	//当前页
	private int page;
	//总条数
	private int records;
	//数据集
	private Object rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public Object getRows() {
		return rows;
	}
	
	public void setRows(Object rows) {
		this.rows = rows;
	}

}
