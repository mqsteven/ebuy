package com.igeek.ebuy.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.search.service.SearchService;
import com.igeek.ebuy.service.ItemService;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

/**
 * 
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月20日 下午2:17:37
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public BuyResult importIndex() {
		return searchService.importIndex();
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public BuyResult saveItem(TbItem item,String desc) {
		return itemService.saveItem(item, desc);
	}
	
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDatagridResult itemList(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="1")int rows) {
		System.out.println("123");
		return itemService.queryByPage(page, rows);
	}

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem queryById(@PathVariable long itemId) {
		//springMVC会自动将对象转换为json，由于配置了@ResponseBody  不走视图解析器
		return itemService.queryById(itemId);
	}
}
