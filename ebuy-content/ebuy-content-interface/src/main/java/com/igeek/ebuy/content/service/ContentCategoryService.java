package com.igeek.ebuy.content.service;

import java.util.List;

import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUITreeNode;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 上午10:40:57
 */
public interface ContentCategoryService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<EasyUITreeNode> queryCat(long id);
	/**
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	public BuyResult saveCat(int parentId,String name);
	/**
	 * @param id
	 * @param name
	 * @return
	 */
	public BuyResult updateCat(long id, String name);
	/**
	 * @param id
	 * @return
	 */
	public BuyResult deleteCat(long id);
}
