package cn.gov.jyw.bean;

import java.util.List;

public class PageBean {

	private int pageCount; // 每页记录条数

	private boolean hasPrevPage; // 是否有上一页

	private long recordCount; // 总记录数

	private boolean hasNextPage; // 是否有下一页

	private int totalPage; // 总页数

	private int currentPage;// 当前页数

	private List list; // 数据集合

	private int start; // 起始位置
	private int limit;// 偏移量

	public void init(long recordCount, int currentPage, int pageCount) {
		this.recordCount = recordCount;
		this.currentPage = currentPage;
		this.pageCount = pageCount;

		this.start = (currentPage - 1) * pageCount;
		//this.limit = start + pageCount;
		this.limit=pageCount;
		this.totalPage = (int) (recordCount % pageCount == 0 ? recordCount
				/ pageCount : recordCount / pageCount + 1);
		if (currentPage == 1 && totalPage == currentPage) {
			hasPrevPage = false;
			hasNextPage = false;
		} else if (currentPage == 1 && totalPage > currentPage) {
			hasNextPage = true;
			hasPrevPage = false;
		} else if (currentPage > 1 && currentPage < totalPage) {
			hasNextPage = true;
			hasPrevPage = true;
		} else if (currentPage == totalPage && totalPage > 1) {
			hasPrevPage = true;
			hasNextPage = false;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public boolean isHasPrevPage() {
		return hasPrevPage;
	}

	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
