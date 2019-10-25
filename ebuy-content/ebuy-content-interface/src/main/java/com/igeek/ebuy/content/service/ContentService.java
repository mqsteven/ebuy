package com.igeek.ebuy.content.service;

import java.util.List;

import com.igeek.ebuy.pojo.TbContent;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 下午3:10:01
 */
public interface ContentService {

	/**
	 * 
	 * @param content
	 * @return
	 */
	public BuyResult saveContent(TbContent content);
	/**
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDatagridResult queryContent(long categoryId,int page,int rows);
	/**
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<TbContent> queryByCategoryId(long categoryId);
}
