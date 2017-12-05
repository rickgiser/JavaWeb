package com.wx.ssm.tool;

import java.util.List;

public class MyPage<T> {

	private int pageNow = 1;
	private int pageSize = 3;
	private int totalNum;
	private List<T> content;
	public MyPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "MyPage [pageNow=" + pageNow + ", pageSize=" + pageSize + ", totalNum=" + totalNum + ", content="
				+ content + "]";
	}
	//
	public int getPageCount(){
		int pageCount = totalNum / pageSize;
		if(totalNum % pageSize != 0){
			return pageCount + 1;
		}else{
			return pageCount;
		}
	}
	
	public boolean isHasPrev(){
		if(pageNow>1){
			return true;
		}else{
			return false;
		}
	}
	
	public int getPrev(){
		if(isHasPrev()){
			return pageNow - 1;
		}else{
			return pageNow;
		}
	}
	
	public boolean isHasNext(){
		if(pageNow<getPageCount()){
			return true;
		}else{
			return false;
		}
	}
	public int getNext(){
		if(isHasNext()){
			return pageNow + 1;
		}else{
			return pageNow;
		}
	}
}
