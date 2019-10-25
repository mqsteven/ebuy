package com.igeek.ebuy.service;

import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.pojo.TbItemDesc;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月20日 下午2:18:56
 */
public interface ItemService {
	/**
	 * 通过ID返回商品信息
	 * @param itemId
	 * @return
	 */
	public TbItem queryById(long itemId);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDatagridResult queryByPage(int page,int rows);
	/**
	 * 保存商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	public BuyResult saveItem(TbItem item,String desc);
	
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItemDesc queryDescById(long itemId);
}
