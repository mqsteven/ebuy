package com.igeek.ebuy.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.ebuy.service.ItemCatService;
import com.igeek.ebuy.util.pojo.EasyUITreeNode;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月22日 上午10:36:19
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> queryCat(@RequestParam(defaultValue="0",name="id")int parentId){
		return itemCatService.queryCat(parentId);
	}
}
