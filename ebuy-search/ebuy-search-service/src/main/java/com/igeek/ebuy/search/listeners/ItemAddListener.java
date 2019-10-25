package com.igeek.ebuy.search.listeners;

import java.awt.event.ItemEvent;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.directory.SearchControls;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.igeek.ebuy.manager.mapper.TbItemMapper;
import com.igeek.ebuy.search.mapper.SearchItemMapper;
import com.igeek.ebuy.search.service.dao.SearchDao;
import com.igeek.ebuy.util.pojo.SearchItem;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月1日 上午9:44:23
 */
public class ItemAddListener implements MessageListener {
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	
	@Autowired
	private SearchDao searchDao;
	
	public void onMessage(Message message) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		TextMessage msg = (TextMessage)message;
		try {
			String itemId = msg.getText();
			System.out.println("队列中商品的ID是："+itemId);
			if(StringUtils.isNoneBlank(itemId)) {
				//根据ID查询商品信息
				long id = Long.parseLong(itemId.trim());
				SearchItem searchItem = searchItemMapper.getByItemId(id);
				//将商品信息存入索引库
				searchDao.saveItem(searchItem);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
