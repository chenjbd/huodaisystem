package com.cc.app.base.common;

import com.github.pagehelper.PageInfo;

/**
 * 分页对象，针对layuimini
 *
 */
public class PageModel {
	
	public PageModel(){
		
	}
	
	public PageModel(int pageIndex, int pageSize, int recordCounts, Object dataRows) {
		this.page = pageIndex;
		this.count = recordCounts;
		this.data = dataRows;
		this.total = recordCounts % pageSize == 0 ? recordCounts / pageSize : recordCounts / pageSize + 1;
	}

	/**
	 * 将分页信息转换成指定分页对象
	 * @param page
     */
	public PageModel(PageInfo page) {
		this.page = page.getPageNum();
		this.count = (int) page.getTotal();
		this.data = page.getList();
		this.total = page.getPages();//recordCounts % pageSize == 0 ? recordCounts / pageSize : recordCounts / pageSize + 1;
	}

	private String code = "0";
	private String msg = "";

	//总页数
	private int total;
	//当前页
	private int page;
	//总条数
	private int count;
	//数据集
	private Object data;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
