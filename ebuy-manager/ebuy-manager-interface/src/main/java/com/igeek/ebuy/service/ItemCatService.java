package com.igeek.ebuy.service;

import java.util.List;

import com.igeek.ebuy.util.pojo.EasyUITreeNode;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月22日 上午10:38:35
 */
public interface ItemCatService {
	/**
	 * 根据类名的父ID查询子类目
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> queryCat(int parentId);
}
