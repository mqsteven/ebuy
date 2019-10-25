package com.igeek.ebuy.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.ebuy.content.service.ContentService;
import com.igeek.ebuy.pojo.TbContent;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 下午3:08:36
 */
@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDatagridResult queryContent(long categoryId,int page,int rows) {
		return contentService.queryContent(categoryId, page, rows);
	}

	@RequestMapping("/content/save")
	@ResponseBody
	public BuyResult saveContent(TbContent content) {
		return contentService.saveContent(content);
	}
	
}
