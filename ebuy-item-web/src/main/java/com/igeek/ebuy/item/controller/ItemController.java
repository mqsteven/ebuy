package com.igeek.ebuy.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.pojo.TbItemDesc;
import com.igeek.ebuy.service.ItemService;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月1日 上午11:15:46
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	

	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable long itemId,Model model) {
		//根据商品的ID读取商品的信息
		TbItem item = itemService.queryById(itemId);
		model.addAttribute("item", item);
		TbItemDesc itemDesc = itemService.queryDescById(itemId);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
