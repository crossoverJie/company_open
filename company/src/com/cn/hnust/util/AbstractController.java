package com.cn.hnust.util;

public class AbstractController {
	private Integer pageNum;
	private Integer pageSize;
	private Integer rowCount;
	private Integer startIndex;
	private Integer endIndex;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	
	public void getIndex() {
		if(pageNum==null)pageNum=1;
//		if(pageSize==null) pageSize = WebUtil.pageSize;
		if(pageSize==null) pageSize = 6;
		int sqlPage=(int)Math.ceil((double)rowCount/(double)pageSize);
		if(pageNum >  sqlPage)
		{
			pageNum = sqlPage;
		}
		if(pageNum <1){
			pageNum = 1;
		}
		startIndex = pageSize *(pageNum-1);
		endIndex = pageSize * pageNum;
	}
}
