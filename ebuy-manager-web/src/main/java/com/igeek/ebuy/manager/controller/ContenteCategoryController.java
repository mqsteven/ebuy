package com.igeek.ebuy.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.ebuy.content.service.ContentCategoryService;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUITreeNode;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 上午10:39:11
 */
@Controller
public class ContenteCategoryController {
	
	@Autowired
	private ContentCategoryService contentCatService;
	
	@RequestMapping("/content/category/delete/")
	@ResponseBody
	public BuyResult deleteCat(long id) {
		return contentCatService.deleteCat(id);
	}
	
	@RequestMapping("/content/category/update")
	@ResponseBody
	public BuyResult updateCat(long id,String name) {
		return contentCatService.updateCat(id,name);
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public BuyResult createCat(int parentId,String name) {
		return contentCatService.saveCat(parentId, name);
	}
	

	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> queryCat(@RequestParam(defaultValue="0")long id){
		return contentCatService.queryCat(id);
	}
}
