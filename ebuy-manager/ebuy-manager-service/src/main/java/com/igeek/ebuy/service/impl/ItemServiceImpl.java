package com.igeek.ebuy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.ebuy.manager.mapper.TbItemDescMapper;
import com.igeek.ebuy.manager.mapper.TbItemMapper;
import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.pojo.TbItemDesc;
import com.igeek.ebuy.pojo.TbItemDescExample;
import com.igeek.ebuy.pojo.TbItemExample;
import com.igeek.ebuy.pojo.TbItemExample.Criteria;
import com.igeek.ebuy.service.ItemService;
import com.igeek.ebuy.util.jedis.JedisClient;
import com.igeek.ebuy.util.json.JsonUtils;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 *              2019年3月20日 下午2:19:54
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	JedisClient jedisClient;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("itemAdd-topicDestination")
	private Destination itemAddTopic;

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem queryById(long itemId) {
		// 先查缓存
		String baseKey = "ITEM_INFO:" + itemId + ":BASE";
		String baseInfo = jedisClient.get(baseKey);
		if (StringUtils.isNoneBlank(baseInfo)) {
			// 更新存活时间
			jedisClient.expire(baseKey, 10);
			return JsonUtils.jsonToPojo(baseInfo, TbItem.class);
		}
		TbItemExample example = new TbItemExample();
		// 设置条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> items = itemMapper.selectByExample(example);
		if (items != null && items.size() == 1) {
			// 将数据添加到缓存中
			jedisClient.set(baseKey, JsonUtils.objectToJson(items.get(0)));
			// 设置存活时间
			jedisClient.expire(baseKey, 10);
			return items.get(0);
		}
		return null;
	}

	public EasyUIDatagridResult queryByPage(int page, int rows) {
		EasyUIDatagridResult result = new EasyUIDatagridResult();
		// 设置分页信息
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = null;
		list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}

	@Override
	public BuyResult saveItem(TbItem item, String desc) {
		// 生成商品的ID
		final String idStr = System.currentTimeMillis() + "" + new Random().nextInt(100);
		long itemId = Long.parseLong(idStr);
		// 补全商品信息
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 保存商品信息
		itemMapper.insert(item);
		// 补全商品描述信息
		TbItemDesc itemDesc = new TbItemDesc();
		// 保存商品描述信息
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);
		// 发送消息
		/**
		 * 商品添加完成之后发送消息，消息中包裹商品的ID即可。
		 */
		jmsTemplate.send(itemAddTopic, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(idStr);
			}
		});
		///
		return BuyResult.ok();
	}

	@Override
	public TbItemDesc queryDescById(long itemId) {
		// 先查缓存
		String descKey = "ITEM_INFO:" + itemId + ":DESC";
		String descInfo = jedisClient.get(descKey);
		if (StringUtils.isNoneBlank(descInfo)) {
			// 更新存活时间
			jedisClient.expire(descKey, 10);
			return JsonUtils.jsonToPojo(descInfo, TbItemDesc.class);
		}
		TbItemDescExample example = new TbItemDescExample();
		com.igeek.ebuy.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		// 将数据存入缓存//判断是否从数据库查询到数据
		if (list != null && list.size() > 0) {
			jedisClient.set(descKey, JsonUtils.objectToJson(list.get(0)));
			// 设置存活时间
			jedisClient.expire(descKey, 10);
			return list.get(0);
		}
		return null;
		
	}

}
