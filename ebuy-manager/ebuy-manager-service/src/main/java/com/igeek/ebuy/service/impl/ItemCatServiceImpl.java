package com.igeek.ebuy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igeek.ebuy.manager.mapper.TbItemCatMapper;
import com.igeek.ebuy.pojo.TbItemCat;
import com.igeek.ebuy.pojo.TbItemCatExample;
import com.igeek.ebuy.pojo.TbItemCatExample.Criteria;
import com.igeek.ebuy.service.ItemCatService;
import com.igeek.ebuy.util.pojo.EasyUITreeNode;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月22日 上午10:39:36
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	public List<EasyUITreeNode> queryCat(int parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo((long)parentId);// parentId = ?
		List<TbItemCat> list = itemCatMapper.selectByExample(example );
		//转换列表
		List<EasyUITreeNode> nodes = new ArrayList<EasyUITreeNode>();
		for (TbItemCat itemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			//设置该节点是否是叶子节点
			node.setState(itemCat.getIsParent()?"closed":"open");
			nodes.add(node);
		}
		return nodes;
	}

}
