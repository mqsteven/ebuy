package com.igeek.ebuy.portal.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igeek.ebuy.content.service.ContentService;
import com.igeek.ebuy.pojo.TbContent;
import com.igeek.ebuy.util.cookie.CookieUtils;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 上午9:16:33
 */
@Controller
public class PageController {
	
	@Value("${LUNBO}")
	private int LUNBO;

	@Autowired
	private ContentService contentService;
	@RequestMapping("/")
	public String showIndex(Model model,HttpServletRequest request,HttpServletResponse response) {
		List<TbContent> list = contentService.queryByCategoryId(LUNBO);
		model.addAttribute("ad1List", list);
		String token = CookieUtils.getCookieValue(request, "EBUY-TOKEN");
		System.out.println("得到的cookie-tonek:"+token);
		return "index";
	}
	
	@RequestMapping("/error")
	public String createError() {
		String str = null;
		str.toCharArray();
		return "";
	}
}
