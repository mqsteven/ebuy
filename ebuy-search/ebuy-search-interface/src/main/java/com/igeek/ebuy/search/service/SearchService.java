package com.igeek.ebuy.search.service;

import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.SearchResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 上午11:16:14
 */
public interface SearchService {
	/**
	 * 一键导入商品到索引库
	 */
	public BuyResult importIndex();
	/**
	 * 
	 * @param page
	 * @param rows
	 * @param keyword
	 * @return
	 */
	public SearchResult searchItem(int page,int rows,String keyword);
}
