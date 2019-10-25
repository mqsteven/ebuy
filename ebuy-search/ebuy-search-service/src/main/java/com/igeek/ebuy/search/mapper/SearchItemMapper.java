package com.igeek.ebuy.search.mapper;

import java.util.List;

import com.igeek.ebuy.util.pojo.SearchItem;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 上午11:26:24
 */
public interface SearchItemMapper {
	/**
	 * 查询所有的商品信息
	 * @return
	 */
	public List<SearchItem> getItemList();
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	public SearchItem getByItemId(long itemId);
}
