package com.igeek.ebuy.util.pojo;

import java.util.List;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 下午3:42:15
 */
public class SearchResult implements java.io.Serializable{
	/**
	 * 查询的数据列表
	 */
	private List<SearchItem> itemList;
	/**
	 * 总页码
	 */
	private int totalPages;
	/**
	 * 总记录数
	 */
	private int recourdCount;
	/**
	 * @return the itemList
	 */
	public List<SearchItem> getItemList() {
		return itemList;
	}
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * @return the recourdCount
	 */
	public int getRecourdCount() {
		return recourdCount;
	}
	/**
	 * @param recourdCount the recourdCount to set
	 */
	public void setRecourdCount(int recourdCount) {
		this.recourdCount = recourdCount;
	}
	
	

}
