package com.igeek.ebuy.content.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igeek.ebuy.content.service.ContentService;
import com.igeek.ebuy.manager.mapper.ContentExMapper;
import com.igeek.ebuy.manager.mapper.TbContentMapper;
import com.igeek.ebuy.pojo.TbContent;
import com.igeek.ebuy.pojo.TbContentExample;
import com.igeek.ebuy.pojo.TbContentExample.Criteria;
import com.igeek.ebuy.util.jedis.JedisClient;
import com.igeek.ebuy.util.json.JsonUtils;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 下午3:10:15
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private ContentExMapper contentExMapper;
	
	@Autowired
	private JedisClient client;
	
	@Override
	public BuyResult saveContent(TbContent content) {
		//补全信息
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		//清理缓存
		String key = "CONTENT:"+content.getCategoryId();
		client.del(key);
		return BuyResult.ok();
	}

	@Override
	public EasyUIDatagridResult queryContent(long categoryId, int page, int rows) {
		EasyUIDatagridResult result = new EasyUIDatagridResult();
		Map map = new HashMap();
		map.put("categoryId", categoryId);
		map.put("start", (page-1)*rows);
		map.put("size", rows);
		List<TbContent> list = contentExMapper.queryByPage(map );
		int total = contentExMapper.queryCount(map);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public List<TbContent> queryByCategoryId(long categoryId) {
		//设计key
		String key = "CONTENT:"+categoryId;
		//先查缓存
		String json = client.get(key);
		if(StringUtils.isNoneBlank(json)) {
			//有数据，直接放回
			//将json转换为集合返回。
			return JsonUtils.jsonToList(json, TbContent.class);
		}
		//没有数据就查询数据库
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example );
		//向缓存中添加查询的数据
		//将集合转换为json存入缓存。
		client.set(key,JsonUtils.objectToJson(list));
		return list;
	}

}
