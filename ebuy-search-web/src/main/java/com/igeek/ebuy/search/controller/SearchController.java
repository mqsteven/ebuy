package com.igeek.ebuy.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.igeek.ebuy.search.service.SearchService;
import com.igeek.ebuy.util.pojo.SearchResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 下午3:27:22
 */
@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/search")
	public String search(String keyword,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="60")int rows,Model model	) {
		//解码
		try {
			keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		SearchResult result = searchService.searchItem(page, rows, keyword);
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages", result.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("recourdCount", result.getRecourdCount());
		model.addAttribute("itemList", result.getItemList());
		return "search";
	}
}
