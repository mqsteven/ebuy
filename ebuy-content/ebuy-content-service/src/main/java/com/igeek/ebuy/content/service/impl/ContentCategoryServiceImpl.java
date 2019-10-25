package com.igeek.ebuy.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igeek.ebuy.content.service.ContentCategoryService;
import com.igeek.ebuy.manager.mapper.ContentCategoryExMapper;
import com.igeek.ebuy.manager.mapper.TbContentCategoryMapper;
import com.igeek.ebuy.pojo.TbContentCategory;
import com.igeek.ebuy.pojo.TbContentCategoryExample;
import com.igeek.ebuy.pojo.TbContentCategoryExample.Criteria;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUITreeNode;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 上午10:41:41
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCatMapper;
	
	@Autowired
	private ContentCategoryExMapper contentCatExMapper;
	
	@Override
	public List<EasyUITreeNode> queryCat(long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		criteria.andStatusEqualTo(1);
		List<TbContentCategory> list = contentCatMapper.selectByExample(example );
		List<EasyUITreeNode> nodes = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory cat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			nodes.add(node);
		}
		return nodes;
	}

	@Override
	public BuyResult saveCat(int parentId, String name) {
		TbContentCategory contentCat = new TbContentCategory();
		contentCat.setCreated(new Date());
		contentCat.setUpdated(new Date());
		contentCat.setIsParent(false);
		contentCat.setName(name);
		contentCat.setParentId((long)parentId);
		contentCat.setSortOrder(1);
		contentCat.setStatus(1);
		contentCatExMapper.insert(contentCat);
		
		//修改父节点
		TbContentCategory parent = contentCatMapper.selectByPrimaryKey((long)parentId);
		parent.setIsParent(true);
		contentCatMapper.updateByPrimaryKey(parent);
		
		return new BuyResult(200, contentCat);
	}

	@Override
	public BuyResult updateCat(long id, String name) {
		TbContentCategory cat = contentCatMapper.selectByPrimaryKey(id);
		cat.setName(name);
		contentCatMapper.updateByPrimaryKey(cat);
		return BuyResult.ok();
	}

	@Override
	public BuyResult deleteCat(long id) {
		TbContentCategory cat = contentCatMapper.selectByPrimaryKey(id);
		cat.setStatus(2);
		contentCatMapper.updateByPrimaryKey(cat);
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(cat.getParentId());
		criteria.andStatusEqualTo(1);
		List<TbContentCategory> list = contentCatMapper.selectByExample(example );
		if(list==null || list.size()==0) {
			//修改父节点
			TbContentCategory parent = contentCatMapper.selectByPrimaryKey((long) cat.getParentId());
			parent.setIsParent(false);
			contentCatMapper.updateByPrimaryKey(parent);
		}
		return BuyResult.ok();
	}

}
